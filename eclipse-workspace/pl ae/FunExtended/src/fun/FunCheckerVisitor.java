//////////////////////////////////////////////////////////////
//
// A visitor for contextual analysis of Fun.
//
// Based on a previous version developed by
// David Watt and Simon Gay (University of Glasgow).
//
// Extended by Holly Hewitt (2463548h)
// 14/03/2022
//
//////////////////////////////////////////////////////////////

package fun;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ast.*;
import ast.FunParser.ExprContext;
import ast.FunParser.Seq_comContext;
import ast.FunParser.Switch_exprContext;

public class FunCheckerVisitor extends AbstractParseTreeVisitor<Type> implements FunVisitor<Type> {

	// Contextual errors

	private int errorCount = 0;

	private CommonTokenStream tokens;

	// Constructor

	public FunCheckerVisitor(CommonTokenStream toks) {
		tokens = toks;
	}

	private void reportError(String message, ParserRuleContext ctx) {
		// Print an error message relating to the given
		// part of the AST.
		Interval interval = ctx.getSourceInterval();
		Token start = tokens.get(interval.a);
		Token finish = tokens.get(interval.b);
		int startLine = start.getLine();
		int startCol = start.getCharPositionInLine();
		int finishLine = finish.getLine();
		int finishCol = finish.getCharPositionInLine();
		System.err.println(startLine + ":" + startCol + "-" + finishLine + ":" + finishCol + " " + message);
		errorCount++;
	}

	public int getNumberOfContextualErrors() {
		// Return the total number of errors so far detected.
		return errorCount;
	}

	// Scope checking

	private SymbolTable<Type> typeTable = new SymbolTable<Type>();

	private void predefine() {
		// Add predefined procedures to the type table.
		typeTable.put("read",
				// new Type.Mapping(Type.VOID, Type.INT));
				new Type.Mapping(Type.EMPTY, Type.INT));
		ArrayList<Type> writeParams = new ArrayList<Type>();
		writeParams.add(Type.INT);
		typeTable.put("write",
				// new Type.Mapping(Type.INT, Type.VOID));
				new Type.Mapping(new Type.Sequence(writeParams), Type.VOID));
		// new Type.Sequence(new ArrayList<Type>()));
	}

	private void define(String id, Type type, ParserRuleContext decl) {
		// Add id with its type to the type table, checking
		// that id is not already declared in the same scope.
		boolean ok = typeTable.put(id, type);
		if (!ok)
			reportError(id + " is redeclared", decl);
	}

	private Type retrieve(String id, ParserRuleContext occ) {
		// Retrieve id's type from the type table.
		Type type = typeTable.get(id);
		if (type == null) {
			reportError(id + " is undeclared", occ);
			return Type.ERROR;
		} else
			return type;
	}

	// Type checking

	private static final Type.Mapping NOTTYPE = new Type.Mapping(Type.BOOL, Type.BOOL),
			COMPTYPE = new Type.Mapping(new Type.Pair(Type.INT, Type.INT), Type.BOOL),
			ARITHTYPE = new Type.Mapping(new Type.Pair(Type.INT, Type.INT), Type.INT),
			MAINTYPE = new Type.Mapping(Type.EMPTY, Type.VOID);

	private void checkType(Type typeExpected, Type typeActual, ParserRuleContext construct) {
		// Check that a construct's actual type matches
		// the expected type.
		if (!typeActual.equiv(typeExpected))
			reportError("type is " + typeActual + ", should be " + typeExpected, construct);
	}

	private Type checkCall(String id, Type typeArg, ParserRuleContext call) {
		// Check that a procedure call identifies a procedure
		// and that its argument type matches the proecure's
		// type. Return the type of the procedure call.
		Type typeProc = retrieve(id, call);
		if (!(typeProc instanceof Type.Mapping)) {
			reportError(id + " is not a procedure", call);
			return Type.ERROR;
		} else {
			Type.Mapping mapping = (Type.Mapping) typeProc;
			checkType(mapping.domain, typeArg, call);
			return mapping.range;
		}
	}

	private Type checkUnary(Type.Mapping typeOp, Type typeArg, ParserRuleContext op) {
		// Check that a unary operator's operand type matches
		// the operator's type. Return the type of the operator
		// application.
		if (!(typeOp.domain instanceof Type.Primitive))
			reportError("unary operator should have 1 operand", op);
		else
			checkType(typeOp.domain, typeArg, op);
		return typeOp.range;
	}

	private Type checkBinary(Type.Mapping typeOp, Type typeArg1, Type typeArg2, ParserRuleContext op) {
		// Check that a binary operator's operand types match
		// the operator's type. Return the type of the operator
		// application.
		if (!(typeOp.domain instanceof Type.Pair))
			reportError("binary operator should have 2 operands", op);
		else {
			Type.Pair pair = (Type.Pair) typeOp.domain;
			checkType(pair.first, typeArg1, op);
			checkType(pair.second, typeArg2, op);
		}
		return typeOp.range;
	}

	/**
	 * Visit a parse tree produced by the {@code prog} labeled alternative in
	 * {@link FunParser#program}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitProg(FunParser.ProgContext ctx) {
		predefine();
		visitChildren(ctx);
		Type tmain = retrieve("main", ctx);
		checkType(MAINTYPE, tmain, ctx);
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code proc} labeled alternative in
	 * {@link FunParser#proc_decl}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitProc(FunParser.ProcContext ctx) {
		typeTable.enterLocalScope();
		Type t;
		FunParser.Formal_decl_seqContext fd = ctx.formal_decl_seq();
		if (fd != null)
			t = visit(fd);
		else
			t = Type.EMPTY;
		Type proctype = new Type.Mapping(t, Type.VOID);
		define(ctx.ID().getText(), proctype, ctx);
		List<FunParser.Var_declContext> var_decl = ctx.var_decl();
		for (FunParser.Var_declContext vd : var_decl)
			visit(vd);
		visit(ctx.seq_com());
		typeTable.exitLocalScope();
		define(ctx.ID().getText(), proctype, ctx);
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code func} labeled alternative in
	 * {@link FunParser#proc_decl}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitFunc(FunParser.FuncContext ctx) {
		typeTable.enterLocalScope();
		Type t1 = visit(ctx.type());
		Type t2;
		FunParser.Formal_decl_seqContext fd = ctx.formal_decl_seq();
		if (fd != null)
			t2 = visit(fd);
		else
			t2 = Type.EMPTY;
		Type functype = new Type.Mapping(t2, t1);
		define(ctx.ID().getText(), functype, ctx);
		List<FunParser.Var_declContext> var_decl = ctx.var_decl();
		for (FunParser.Var_declContext vd : var_decl)
			visit(vd);
		visit(ctx.seq_com());
		Type returntype = visit(ctx.expr());
		checkType(t1, returntype, ctx);
		typeTable.exitLocalScope();
		define(ctx.ID().getText(), functype, ctx);
		return null;
	}

	public Type visitFormalseq(FunParser.FormalseqContext ctx) {
		List<FunParser.Formal_declContext> contextList = ctx.formal_decl();
		ArrayList<Type> types = new ArrayList<Type>();
		for (FunParser.Formal_declContext c : contextList) {
			Type t = visit(c);
			types.add(t);
			// define(ctx.ID().getText(), t, ctx);
		}
		return new Type.Sequence(types);
	}

	/**
	 * Visit a parse tree produced by the {@code formal} labeled alternative in
	 * {@link FunParser#formal_decl}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitFormal(FunParser.FormalContext ctx) {
		FunParser.TypeContext tc = ctx.type();
		Type t = visit(tc);
		define(ctx.ID().getText(), t, ctx);
		return t;
	}

	/**
	 * Visit a parse tree produced by the {@code var} labeled alternative in
	 * {@link FunParser#var_decl}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitVar(FunParser.VarContext ctx) {
		Type t1 = visit(ctx.type());
		Type t2 = visit(ctx.expr());
		define(ctx.ID().getText(), t1, ctx);
		checkType(t1, t2, ctx);
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code bool} labeled alternative in
	 * {@link FunParser#type}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitBool(FunParser.BoolContext ctx) {
		return Type.BOOL;
	}

	/**
	 * Visit a parse tree produced by the {@code int} labeled alternative in
	 * {@link FunParser#type}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitInt(FunParser.IntContext ctx) {
		return Type.INT;
	}

	/**
	 * Visit a parse tree produced by the {@code assn} labeled alternative in
	 * {@link FunParser#com}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitAssn(FunParser.AssnContext ctx) {
		Type tvar = retrieve(ctx.ID().getText(), ctx);
		Type t = visit(ctx.expr());
		checkType(tvar, t, ctx);
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code proccall} labeled alternative in
	 * {@link FunParser#com}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitProccall(FunParser.ProccallContext ctx) {
//		FunParser.Actual_seqContext c = ctx.actual_seq();
//		Type t;
//		if (c != null) {
//			t = visit(c);
//			Type tres = checkCall(ctx.ID().getText(), t, ctx);
//			if (!tres.equiv(Type.VOID))
//				reportError("procedure should be void", ctx);
//		} else {
//			t = Type.EMPTY;
//		}
//		return t;
		FunParser.Actual_seqContext actuals = ctx.actual_seq();
		Type t;
		if (actuals != null)
			t = visit(actuals);
		else
			t = new Type.Sequence(new ArrayList<Type>());
		Type tres = checkCall(ctx.ID().getText(), t, ctx);
		if (!tres.equiv(Type.VOID))
			reportError("procedure should be void", ctx);
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code if} labeled alternative in
	 * {@link FunParser#com}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitIf(FunParser.IfContext ctx) {
		Type t = visit(ctx.expr());
		visit(ctx.c1);
		if (ctx.c2 != null)
			visit(ctx.c2);
		checkType(Type.BOOL, t, ctx);
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code while} labeled alternative in
	 * {@link FunParser#com}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitWhile(FunParser.WhileContext ctx) {
		Type t = visit(ctx.expr());
		visit(ctx.seq_com());
		checkType(Type.BOOL, t, ctx);
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code seq} labeled alternative in
	 * {@link FunParser#seq_com}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitSeq(FunParser.SeqContext ctx) {
		visitChildren(ctx);
		return null;
	}

	/**
	 * Visit a parse tree produced by {@link FunParser#expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitExpr(FunParser.ExprContext ctx) {
		Type t1 = visit(ctx.e1);
		if (ctx.e2 != null) {
			Type t2 = visit(ctx.e2);
			return checkBinary(COMPTYPE, t1, t2, ctx);
			// COMPTYPE is INT x INT -> BOOL
			// checkBinary checks that t1 and t2 are INT and returns BOOL
			// If necessary it produces an error message.
		} else {
			return t1;
		}
	}

	/**
	 * Visit a parse tree produced by {@link FunParser#sec_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitSec_expr(FunParser.Sec_exprContext ctx) {
		Type t1 = visit(ctx.e1);
		if (ctx.e2 != null) {
			Type t2 = visit(ctx.e2);
			return checkBinary(ARITHTYPE, t1, t2, ctx);
		} else {
			return t1;
		}
	}

	/**
	 * Visit a parse tree produced by the {@code false} labeled alternative in
	 * {@link FunParser#prim_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitFalse(FunParser.FalseContext ctx) {
		return Type.BOOL;
	}

	/**
	 * Visit a parse tree produced by the {@code true} labeled alternative in
	 * {@link FunParser#prim_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitTrue(FunParser.TrueContext ctx) {
		return Type.BOOL;
	}

	/**
	 * Visit a parse tree produced by the {@code num} labeled alternative in
	 * {@link FunParser#prim_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitNum(FunParser.NumContext ctx) {
		return Type.INT;
	}

	/**
	 * Visit a parse tree produced by the {@code id} labeled alternative in
	 * {@link FunParser#prim_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitId(FunParser.IdContext ctx) {
		return retrieve(ctx.ID().getText(), ctx);
	}

	/**
	 * Visit a parse tree produced by the {@code funccall} labeled alternative in
	 * {@link FunParser#prim_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitFunccall(FunParser.FunccallContext ctx) {
//		Type t = visit(ctx.actual());
//		Type tres = checkCall(ctx.ID().getText(), t, ctx);
//		if (tres.equiv(Type.VOID))
//			reportError("procedure should be non-void", ctx);
//		return tres;

//		FunParser.Actual_seqContext c = ctx.actual_seq();
//		Type t = visit(c);
//		Type tres = checkCall(ctx.ID().getText(), t, ctx);
//		if (c != null) {
//			t = visit(c);
//			tres = checkCall(ctx.ID().getText(), t, ctx);
//			if (!tres.equiv(Type.VOID))
//				reportError("procedure should be void", ctx);
//		} else {
//			t = Type.EMPTY;
//		}
//		return tres;
		FunParser.Actual_seqContext actuals = ctx.actual_seq();
		Type t;
		if (actuals != null)
			t = visit(actuals);
		else
			t = new Type.Sequence(new ArrayList<Type>());
		Type tres = checkCall(ctx.ID().getText(), t, ctx);
		if (tres.equiv(Type.VOID))
			reportError("function should be non-void", ctx);
		return tres;
	}

	/**
	 * Visit a parse tree produced by the {@code not} labeled alternative in
	 * {@link FunParser#prim_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitNot(FunParser.NotContext ctx) {
		Type t = visit(ctx.prim_expr());
		return checkUnary(NOTTYPE, t, ctx);
	}

	/**
	 * Visit a parse tree produced by the {@code parens} labeled alternative in
	 * {@link FunParser#prim_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitParens(FunParser.ParensContext ctx) {
		return visit(ctx.expr());
	}

	/**
	 * Visit a parse tree produced by {@link FunParser#actual}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public Type visitActualseq(FunParser.ActualseqContext ctx) {
		List<ExprContext> ec = ctx.expr();
		Type t;
		ArrayList<Type> types = new ArrayList<Type>();
		if (ec != null) {
			for (ExprContext c : ec) {
				t = visit(c);
				types.add(t);
			}
		} else {
			types.add(Type.EMPTY);
		}
		return new Type.Sequence(types);
	}

	// extension A

	/**
	 * Visit a parse tree produced by ForContext
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 *
	 */
	public Type visitFor(FunParser.ForContext ctx) {
		if (typeTable.get(ctx.ID().getText()) == null) {
			define(ctx.ID().getText(), Type.INT, ctx);
		}
		
		checkType(Type.INT, visit(ctx.e1), ctx);
		checkType(Type.INT, visit(ctx.e2), ctx);
		visit(ctx.seq_com());
		return null;
	}
	
	// end of extension A
	
	// extension B

	/**
	 * Visit a parse tree produced by SwitchContext
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 *
	 */
	public Type visitSwitch(FunParser.SwitchContext ctx) {
		// check all cases are of the same type
		List<Switch_exprContext> caseExprList = ctx.switch_expr();
		ArrayList<String> allGuards = new ArrayList<String>();

		if (!(caseExprList.isEmpty())) {
			Type type = visitSwitch_expr(caseExprList.get(0));
			for (Switch_exprContext c : caseExprList) {
				Type t = visitSwitch_expr(c);
				checkType(type, t, ctx);

				ArrayList<String> guards = checkForGuardOverlap(c);
				allGuards.addAll(guards);
			}
		}
		HashSet<String> guardSet = new HashSet<String>();
		for (String guard : allGuards) {
			if (guardSet.add(guard) == false) {
				reportError("guard overlap or repetition", ctx);
			}
		}
		List<Seq_comContext> commandList = ctx.seq_com();
		for (Seq_comContext command : commandList) {
			visit(command);
		}
		return null;
	}

	/**
	 * Visit a parse tree produced by Switch_exprContext
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 *
	 */
	public Type visitSwitch_expr(Switch_exprContext ctx) {
		Type t1 = visit(ctx.e1);
		if (ctx.e2 != null) {
			Type t2 = visit(ctx.e2);
			checkType(t1, t2, ctx);
			return t2;
		} else {
			return t1;
		}
	}
	
	/**
	 * Helper method for VisitSwitch()
	 * 
	 * @param ctx the parse tree
	 * @return a list of guards
	 *
	 */
	private ArrayList<String> checkForGuardOverlap(Switch_exprContext ctx) {
		ArrayList<String> guards = new ArrayList<String>();
		guards.add(ctx.e1.getText());
		if (visit(ctx.e1).equiv(Type.INT)) {
			Integer a = Integer.valueOf(ctx.e1.getText());
			if (ctx.e2 != null) {
				if (visit(ctx.e2).equiv(Type.INT)) {
					Integer b = Integer.valueOf(ctx.e2.getText());
					for (Integer i = a + 1; i.intValue() <= b; i++) {
						guards.add(i.toString());
					}
				}
			}
		}
		return guards;
	}
	
	// end of extension b

}
