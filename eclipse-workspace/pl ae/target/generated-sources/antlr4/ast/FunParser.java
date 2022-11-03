// Generated from Fun.g4 by ANTLR 4.4

    package ast;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FunParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BOOL=1, ELSE=2, FALSE=3, FUNC=4, IF=5, INT=6, PROC=7, RETURN=8, TRUE=9, 
		WHILE=10, FOR=11, TO=12, SWITCH=13, CASE=14, DEFAULT=15, EQ=16, LT=17, 
		GT=18, PLUS=19, MINUS=20, TIMES=21, DIV=22, NOT=23, RANGE=24, ASSN=25, 
		LPAR=26, RPAR=27, COLON=28, DOT=29, COMMA=30, NUM=31, ID=32, SPACE=33, 
		EOL=34, COMMENT=35;
	public static final String[] tokenNames = {
		"<INVALID>", "'bool'", "'else'", "'false'", "'func'", "'if'", "'int'", 
		"'proc'", "'return'", "'true'", "'while'", "'for'", "'to'", "'switch'", 
		"'case'", "'default'", "'=='", "'<'", "'>'", "'+'", "'-'", "'*'", "'/'", 
		"'not'", "'..'", "'='", "'('", "')'", "':'", "'.'", "','", "NUM", "ID", 
		"SPACE", "EOL", "COMMENT"
	};
	public static final int
		RULE_program = 0, RULE_proc_decl = 1, RULE_formal_decl_seq = 2, RULE_formal_decl = 3, 
		RULE_var_decl = 4, RULE_type = 5, RULE_com = 6, RULE_seq_com = 7, RULE_expr = 8, 
		RULE_sec_expr = 9, RULE_prim_expr = 10, RULE_actual_seq = 11, RULE_switch_expr = 12;
	public static final String[] ruleNames = {
		"program", "proc_decl", "formal_decl_seq", "formal_decl", "var_decl", 
		"type", "com", "seq_com", "expr", "sec_expr", "prim_expr", "actual_seq", 
		"switch_expr"
	};

	@Override
	public String getGrammarFileName() { return "Fun.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FunParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	 
		public ProgramContext() { }
		public void copyFrom(ProgramContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProgContext extends ProgramContext {
		public TerminalNode EOF() { return getToken(FunParser.EOF, 0); }
		public List<Proc_declContext> proc_decl() {
			return getRuleContexts(Proc_declContext.class);
		}
		public Var_declContext var_decl(int i) {
			return getRuleContext(Var_declContext.class,i);
		}
		public List<Var_declContext> var_decl() {
			return getRuleContexts(Var_declContext.class);
		}
		public Proc_declContext proc_decl(int i) {
			return getRuleContext(Proc_declContext.class,i);
		}
		public ProgContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitProg(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			_localctx = new ProgContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BOOL || _la==INT) {
				{
				{
				setState(26); var_decl();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32); proc_decl();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==FUNC || _la==PROC );
			setState(37); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Proc_declContext extends ParserRuleContext {
		public Proc_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proc_decl; }
	 
		public Proc_declContext() { }
		public void copyFrom(Proc_declContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProcContext extends Proc_declContext {
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public TerminalNode DOT() { return getToken(FunParser.DOT, 0); }
		public TerminalNode PROC() { return getToken(FunParser.PROC, 0); }
		public TerminalNode COLON() { return getToken(FunParser.COLON, 0); }
		public Formal_decl_seqContext formal_decl_seq() {
			return getRuleContext(Formal_decl_seqContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FunParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FunParser.RPAR, 0); }
		public Seq_comContext seq_com() {
			return getRuleContext(Seq_comContext.class,0);
		}
		public Var_declContext var_decl(int i) {
			return getRuleContext(Var_declContext.class,i);
		}
		public List<Var_declContext> var_decl() {
			return getRuleContexts(Var_declContext.class);
		}
		public ProcContext(Proc_declContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterProc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitProc(this);
		}
	}
	public static class FuncContext extends Proc_declContext {
		public TerminalNode COLON() { return getToken(FunParser.COLON, 0); }
		public TerminalNode LPAR() { return getToken(FunParser.LPAR, 0); }
		public Var_declContext var_decl(int i) {
			return getRuleContext(Var_declContext.class,i);
		}
		public List<Var_declContext> var_decl() {
			return getRuleContexts(Var_declContext.class);
		}
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public TerminalNode DOT() { return getToken(FunParser.DOT, 0); }
		public TerminalNode RETURN() { return getToken(FunParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Formal_decl_seqContext formal_decl_seq() {
			return getRuleContext(Formal_decl_seqContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(FunParser.RPAR, 0); }
		public Seq_comContext seq_com() {
			return getRuleContext(Seq_comContext.class,0);
		}
		public TerminalNode FUNC() { return getToken(FunParser.FUNC, 0); }
		public FuncContext(Proc_declContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitFunc(this);
		}
	}

	public final Proc_declContext proc_decl() throws RecognitionException {
		Proc_declContext _localctx = new Proc_declContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_proc_decl);
		int _la;
		try {
			setState(76);
			switch (_input.LA(1)) {
			case PROC:
				_localctx = new ProcContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(39); match(PROC);
				setState(40); match(ID);
				setState(41); match(LPAR);
				setState(43);
				_la = _input.LA(1);
				if (_la==BOOL || _la==INT) {
					{
					setState(42); formal_decl_seq();
					}
				}

				setState(45); match(RPAR);
				setState(46); match(COLON);
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==BOOL || _la==INT) {
					{
					{
					setState(47); var_decl();
					}
					}
					setState(52);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(53); seq_com();
				setState(54); match(DOT);
				}
				break;
			case FUNC:
				_localctx = new FuncContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(56); match(FUNC);
				setState(57); type();
				setState(58); match(ID);
				setState(59); match(LPAR);
				setState(61);
				_la = _input.LA(1);
				if (_la==BOOL || _la==INT) {
					{
					setState(60); formal_decl_seq();
					}
				}

				setState(63); match(RPAR);
				setState(64); match(COLON);
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==BOOL || _la==INT) {
					{
					{
					setState(65); var_decl();
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(71); seq_com();
				setState(72); match(RETURN);
				setState(73); expr();
				setState(74); match(DOT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Formal_decl_seqContext extends ParserRuleContext {
		public Formal_decl_seqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_decl_seq; }
	 
		public Formal_decl_seqContext() { }
		public void copyFrom(Formal_decl_seqContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FormalseqContext extends Formal_decl_seqContext {
		public List<TerminalNode> COMMA() { return getTokens(FunParser.COMMA); }
		public List<Formal_declContext> formal_decl() {
			return getRuleContexts(Formal_declContext.class);
		}
		public Formal_declContext formal_decl(int i) {
			return getRuleContext(Formal_declContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(FunParser.COMMA, i);
		}
		public FormalseqContext(Formal_decl_seqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterFormalseq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitFormalseq(this);
		}
	}

	public final Formal_decl_seqContext formal_decl_seq() throws RecognitionException {
		Formal_decl_seqContext _localctx = new Formal_decl_seqContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_formal_decl_seq);
		int _la;
		try {
			_localctx = new FormalseqContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(78); formal_decl();
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(79); match(COMMA);
				setState(80); formal_decl();
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Formal_declContext extends ParserRuleContext {
		public Formal_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_decl; }
	 
		public Formal_declContext() { }
		public void copyFrom(Formal_declContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FormalContext extends Formal_declContext {
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FormalContext(Formal_declContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterFormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitFormal(this);
		}
	}

	public final Formal_declContext formal_decl() throws RecognitionException {
		Formal_declContext _localctx = new Formal_declContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_formal_decl);
		try {
			_localctx = new FormalContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(86); type();
			setState(87); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declContext extends ParserRuleContext {
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
	 
		public Var_declContext() { }
		public void copyFrom(Var_declContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarContext extends Var_declContext {
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ASSN() { return getToken(FunParser.ASSN, 0); }
		public VarContext(Var_declContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitVar(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_var_decl);
		try {
			_localctx = new VarContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(89); type();
			setState(90); match(ID);
			setState(91); match(ASSN);
			setState(92); expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BoolContext extends TypeContext {
		public TerminalNode BOOL() { return getToken(FunParser.BOOL, 0); }
		public BoolContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitBool(this);
		}
	}
	public static class IntContext extends TypeContext {
		public TerminalNode INT() { return getToken(FunParser.INT, 0); }
		public IntContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitInt(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(96);
			switch (_input.LA(1)) {
			case BOOL:
				_localctx = new BoolContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(94); match(BOOL);
				}
				break;
			case INT:
				_localctx = new IntContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(95); match(INT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComContext extends ParserRuleContext {
		public ComContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_com; }
	 
		public ComContext() { }
		public void copyFrom(ComContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssnContext extends ComContext {
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ASSN() { return getToken(FunParser.ASSN, 0); }
		public AssnContext(ComContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterAssn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitAssn(this);
		}
	}
	public static class ProccallContext extends ComContext {
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public Actual_seqContext actual_seq() {
			return getRuleContext(Actual_seqContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FunParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FunParser.RPAR, 0); }
		public ProccallContext(ComContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterProccall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitProccall(this);
		}
	}
	public static class ForContext extends ComContext {
		public ExprContext e1;
		public ExprContext e2;
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public TerminalNode DOT() { return getToken(FunParser.DOT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COLON() { return getToken(FunParser.COLON, 0); }
		public TerminalNode FOR() { return getToken(FunParser.FOR, 0); }
		public TerminalNode TO() { return getToken(FunParser.TO, 0); }
		public TerminalNode ASSN() { return getToken(FunParser.ASSN, 0); }
		public Seq_comContext seq_com() {
			return getRuleContext(Seq_comContext.class,0);
		}
		public ForContext(ComContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitFor(this);
		}
	}
	public static class WhileContext extends ComContext {
		public TerminalNode DOT() { return getToken(FunParser.DOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(FunParser.COLON, 0); }
		public Seq_comContext seq_com() {
			return getRuleContext(Seq_comContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(FunParser.WHILE, 0); }
		public WhileContext(ComContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitWhile(this);
		}
	}
	public static class IfContext extends ComContext {
		public Seq_comContext c1;
		public Seq_comContext c2;
		public TerminalNode DOT() { return getToken(FunParser.DOT, 0); }
		public TerminalNode ELSE() { return getToken(FunParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(FunParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON(int i) {
			return getToken(FunParser.COLON, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FunParser.COLON); }
		public List<Seq_comContext> seq_com() {
			return getRuleContexts(Seq_comContext.class);
		}
		public Seq_comContext seq_com(int i) {
			return getRuleContext(Seq_comContext.class,i);
		}
		public IfContext(ComContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitIf(this);
		}
	}
	public static class SwitchContext extends ComContext {
		public List<TerminalNode> CASE() { return getTokens(FunParser.CASE); }
		public TerminalNode DOT() { return getToken(FunParser.DOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SWITCH() { return getToken(FunParser.SWITCH, 0); }
		public TerminalNode COLON(int i) {
			return getToken(FunParser.COLON, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FunParser.COLON); }
		public List<Seq_comContext> seq_com() {
			return getRuleContexts(Seq_comContext.class);
		}
		public TerminalNode DEFAULT() { return getToken(FunParser.DEFAULT, 0); }
		public Switch_exprContext switch_expr(int i) {
			return getRuleContext(Switch_exprContext.class,i);
		}
		public Seq_comContext seq_com(int i) {
			return getRuleContext(Seq_comContext.class,i);
		}
		public TerminalNode CASE(int i) {
			return getToken(FunParser.CASE, i);
		}
		public List<Switch_exprContext> switch_expr() {
			return getRuleContexts(Switch_exprContext.class);
		}
		public SwitchContext(ComContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterSwitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitSwitch(this);
		}
	}

	public final ComContext com() throws RecognitionException {
		ComContext _localctx = new ComContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_com);
		int _la;
		try {
			setState(153);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new AssnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(98); match(ID);
				setState(99); match(ASSN);
				setState(100); expr();
				}
				break;
			case 2:
				_localctx = new ProccallContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(101); match(ID);
				setState(102); match(LPAR);
				setState(104);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << TRUE) | (1L << NOT) | (1L << LPAR) | (1L << NUM) | (1L << ID))) != 0)) {
					{
					setState(103); actual_seq();
					}
				}

				setState(106); match(RPAR);
				}
				break;
			case 3:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(107); match(IF);
				setState(108); expr();
				setState(109); match(COLON);
				setState(110); ((IfContext)_localctx).c1 = seq_com();
				setState(117);
				switch (_input.LA(1)) {
				case DOT:
					{
					setState(111); match(DOT);
					}
					break;
				case ELSE:
					{
					setState(112); match(ELSE);
					setState(113); match(COLON);
					setState(114); ((IfContext)_localctx).c2 = seq_com();
					setState(115); match(DOT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 4:
				_localctx = new WhileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(119); match(WHILE);
				setState(120); expr();
				setState(121); match(COLON);
				setState(122); seq_com();
				setState(123); match(DOT);
				}
				break;
			case 5:
				_localctx = new ForContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(125); match(FOR);
				setState(126); match(ID);
				setState(127); match(ASSN);
				{
				setState(128); ((ForContext)_localctx).e1 = expr();
				}
				setState(129); match(TO);
				{
				setState(130); ((ForContext)_localctx).e2 = expr();
				}
				setState(131); match(COLON);
				setState(132); seq_com();
				setState(133); match(DOT);
				}
				break;
			case 6:
				_localctx = new SwitchContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(135); match(SWITCH);
				setState(136); expr();
				setState(137); match(COLON);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE) {
					{
					{
					setState(138); match(CASE);
					setState(139); switch_expr();
					setState(140); match(COLON);
					setState(141); seq_com();
					}
					}
					setState(147);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(148); match(DEFAULT);
				setState(149); match(COLON);
				setState(150); seq_com();
				setState(151); match(DOT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Seq_comContext extends ParserRuleContext {
		public Seq_comContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seq_com; }
	 
		public Seq_comContext() { }
		public void copyFrom(Seq_comContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SeqContext extends Seq_comContext {
		public ComContext com(int i) {
			return getRuleContext(ComContext.class,i);
		}
		public List<ComContext> com() {
			return getRuleContexts(ComContext.class);
		}
		public SeqContext(Seq_comContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitSeq(this);
		}
	}

	public final Seq_comContext seq_com() throws RecognitionException {
		Seq_comContext _localctx = new Seq_comContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_seq_com);
		int _la;
		try {
			_localctx = new SeqContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << SWITCH) | (1L << ID))) != 0)) {
				{
				{
				setState(155); com();
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Sec_exprContext e1;
		public Token op;
		public Sec_exprContext e2;
		public TerminalNode LT() { return getToken(FunParser.LT, 0); }
		public Sec_exprContext sec_expr(int i) {
			return getRuleContext(Sec_exprContext.class,i);
		}
		public TerminalNode GT() { return getToken(FunParser.GT, 0); }
		public TerminalNode EQ() { return getToken(FunParser.EQ, 0); }
		public List<Sec_exprContext> sec_expr() {
			return getRuleContexts(Sec_exprContext.class);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); ((ExprContext)_localctx).e1 = sec_expr();
			setState(164);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << LT) | (1L << GT))) != 0)) {
				{
				setState(162);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << LT) | (1L << GT))) != 0)) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(163); ((ExprContext)_localctx).e2 = sec_expr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sec_exprContext extends ParserRuleContext {
		public Prim_exprContext e1;
		public Token op;
		public Sec_exprContext e2;
		public Prim_exprContext prim_expr() {
			return getRuleContext(Prim_exprContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(FunParser.TIMES, 0); }
		public TerminalNode PLUS() { return getToken(FunParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(FunParser.MINUS, 0); }
		public Sec_exprContext sec_expr() {
			return getRuleContext(Sec_exprContext.class,0);
		}
		public TerminalNode DIV() { return getToken(FunParser.DIV, 0); }
		public Sec_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sec_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterSec_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitSec_expr(this);
		}
	}

	public final Sec_exprContext sec_expr() throws RecognitionException {
		Sec_exprContext _localctx = new Sec_exprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_sec_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); ((Sec_exprContext)_localctx).e1 = prim_expr();
			setState(169);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << TIMES) | (1L << DIV))) != 0)) {
				{
				setState(167);
				((Sec_exprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << TIMES) | (1L << DIV))) != 0)) ) {
					((Sec_exprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(168); ((Sec_exprContext)_localctx).e2 = sec_expr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Prim_exprContext extends ParserRuleContext {
		public Prim_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prim_expr; }
	 
		public Prim_exprContext() { }
		public void copyFrom(Prim_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotContext extends Prim_exprContext {
		public TerminalNode NOT() { return getToken(FunParser.NOT, 0); }
		public Prim_exprContext prim_expr() {
			return getRuleContext(Prim_exprContext.class,0);
		}
		public NotContext(Prim_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitNot(this);
		}
	}
	public static class ParensContext extends Prim_exprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FunParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FunParser.RPAR, 0); }
		public ParensContext(Prim_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterParens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitParens(this);
		}
	}
	public static class FunccallContext extends Prim_exprContext {
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public Actual_seqContext actual_seq() {
			return getRuleContext(Actual_seqContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(FunParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FunParser.RPAR, 0); }
		public FunccallContext(Prim_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterFunccall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitFunccall(this);
		}
	}
	public static class NumContext extends Prim_exprContext {
		public TerminalNode NUM() { return getToken(FunParser.NUM, 0); }
		public NumContext(Prim_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitNum(this);
		}
	}
	public static class FalseContext extends Prim_exprContext {
		public TerminalNode FALSE() { return getToken(FunParser.FALSE, 0); }
		public FalseContext(Prim_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitFalse(this);
		}
	}
	public static class TrueContext extends Prim_exprContext {
		public TerminalNode TRUE() { return getToken(FunParser.TRUE, 0); }
		public TrueContext(Prim_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitTrue(this);
		}
	}
	public static class IdContext extends Prim_exprContext {
		public TerminalNode ID() { return getToken(FunParser.ID, 0); }
		public IdContext(Prim_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitId(this);
		}
	}

	public final Prim_exprContext prim_expr() throws RecognitionException {
		Prim_exprContext _localctx = new Prim_exprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_prim_expr);
		int _la;
		try {
			setState(187);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(171); match(FALSE);
				}
				break;
			case 2:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(172); match(TRUE);
				}
				break;
			case 3:
				_localctx = new NumContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(173); match(NUM);
				}
				break;
			case 4:
				_localctx = new IdContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(174); match(ID);
				}
				break;
			case 5:
				_localctx = new FunccallContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(175); match(ID);
				setState(176); match(LPAR);
				setState(178);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << TRUE) | (1L << NOT) | (1L << LPAR) | (1L << NUM) | (1L << ID))) != 0)) {
					{
					setState(177); actual_seq();
					}
				}

				setState(180); match(RPAR);
				}
				break;
			case 6:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(181); match(NOT);
				setState(182); prim_expr();
				}
				break;
			case 7:
				_localctx = new ParensContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(183); match(LPAR);
				setState(184); expr();
				setState(185); match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Actual_seqContext extends ParserRuleContext {
		public Actual_seqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actual_seq; }
	 
		public Actual_seqContext() { }
		public void copyFrom(Actual_seqContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ActualseqContext extends Actual_seqContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FunParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FunParser.COMMA, i);
		}
		public ActualseqContext(Actual_seqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterActualseq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitActualseq(this);
		}
	}

	public final Actual_seqContext actual_seq() throws RecognitionException {
		Actual_seqContext _localctx = new Actual_seqContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_actual_seq);
		int _la;
		try {
			_localctx = new ActualseqContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(189); expr();
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(190); match(COMMA);
				setState(191); expr();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Switch_exprContext extends ParserRuleContext {
		public Prim_exprContext e1;
		public Token op;
		public Prim_exprContext e2;
		public List<Prim_exprContext> prim_expr() {
			return getRuleContexts(Prim_exprContext.class);
		}
		public TerminalNode RANGE() { return getToken(FunParser.RANGE, 0); }
		public Prim_exprContext prim_expr(int i) {
			return getRuleContext(Prim_exprContext.class,i);
		}
		public Switch_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).enterSwitch_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FunListener ) ((FunListener)listener).exitSwitch_expr(this);
		}
	}

	public final Switch_exprContext switch_expr() throws RecognitionException {
		Switch_exprContext _localctx = new Switch_exprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_switch_expr);
		try {
			setState(202);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(197); ((Switch_exprContext)_localctx).e1 = prim_expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198); ((Switch_exprContext)_localctx).e1 = prim_expr();
				setState(199); ((Switch_exprContext)_localctx).op = match(RANGE);
				setState(200); ((Switch_exprContext)_localctx).e2 = prim_expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u00cf\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\2\6\2$\n"+
		"\2\r\2\16\2%\3\2\3\2\3\3\3\3\3\3\3\3\5\3.\n\3\3\3\3\3\3\3\7\3\63\n\3\f"+
		"\3\16\3\66\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3@\n\3\3\3\3\3\3\3\7"+
		"\3E\n\3\f\3\16\3H\13\3\3\3\3\3\3\3\3\3\3\3\5\3O\n\3\3\4\3\4\3\4\7\4T\n"+
		"\4\f\4\16\4W\13\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\5\7c\n\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\5\bk\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\5\bx\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0092\n\b\f\b\16\b\u0095"+
		"\13\b\3\b\3\b\3\b\3\b\3\b\5\b\u009c\n\b\3\t\7\t\u009f\n\t\f\t\16\t\u00a2"+
		"\13\t\3\n\3\n\3\n\5\n\u00a7\n\n\3\13\3\13\3\13\5\13\u00ac\n\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\f\u00b5\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00be"+
		"\n\f\3\r\3\r\3\r\7\r\u00c3\n\r\f\r\16\r\u00c6\13\r\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u00cd\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\4"+
		"\3\2\22\24\3\2\25\30\u00de\2\37\3\2\2\2\4N\3\2\2\2\6P\3\2\2\2\bX\3\2\2"+
		"\2\n[\3\2\2\2\fb\3\2\2\2\16\u009b\3\2\2\2\20\u00a0\3\2\2\2\22\u00a3\3"+
		"\2\2\2\24\u00a8\3\2\2\2\26\u00bd\3\2\2\2\30\u00bf\3\2\2\2\32\u00cc\3\2"+
		"\2\2\34\36\5\n\6\2\35\34\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2"+
		" #\3\2\2\2!\37\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2"+
		"\2\2&\'\3\2\2\2\'(\7\2\2\3(\3\3\2\2\2)*\7\t\2\2*+\7\"\2\2+-\7\34\2\2,"+
		".\5\6\4\2-,\3\2\2\2-.\3\2\2\2./\3\2\2\2/\60\7\35\2\2\60\64\7\36\2\2\61"+
		"\63\5\n\6\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65"+
		"\67\3\2\2\2\66\64\3\2\2\2\678\5\20\t\289\7\37\2\29O\3\2\2\2:;\7\6\2\2"+
		";<\5\f\7\2<=\7\"\2\2=?\7\34\2\2>@\5\6\4\2?>\3\2\2\2?@\3\2\2\2@A\3\2\2"+
		"\2AB\7\35\2\2BF\7\36\2\2CE\5\n\6\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2"+
		"\2\2GI\3\2\2\2HF\3\2\2\2IJ\5\20\t\2JK\7\n\2\2KL\5\22\n\2LM\7\37\2\2MO"+
		"\3\2\2\2N)\3\2\2\2N:\3\2\2\2O\5\3\2\2\2PU\5\b\5\2QR\7 \2\2RT\5\b\5\2S"+
		"Q\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V\7\3\2\2\2WU\3\2\2\2XY\5\f\7\2"+
		"YZ\7\"\2\2Z\t\3\2\2\2[\\\5\f\7\2\\]\7\"\2\2]^\7\33\2\2^_\5\22\n\2_\13"+
		"\3\2\2\2`c\7\3\2\2ac\7\b\2\2b`\3\2\2\2ba\3\2\2\2c\r\3\2\2\2de\7\"\2\2"+
		"ef\7\33\2\2f\u009c\5\22\n\2gh\7\"\2\2hj\7\34\2\2ik\5\30\r\2ji\3\2\2\2"+
		"jk\3\2\2\2kl\3\2\2\2l\u009c\7\35\2\2mn\7\7\2\2no\5\22\n\2op\7\36\2\2p"+
		"w\5\20\t\2qx\7\37\2\2rs\7\4\2\2st\7\36\2\2tu\5\20\t\2uv\7\37\2\2vx\3\2"+
		"\2\2wq\3\2\2\2wr\3\2\2\2x\u009c\3\2\2\2yz\7\f\2\2z{\5\22\n\2{|\7\36\2"+
		"\2|}\5\20\t\2}~\7\37\2\2~\u009c\3\2\2\2\177\u0080\7\r\2\2\u0080\u0081"+
		"\7\"\2\2\u0081\u0082\7\33\2\2\u0082\u0083\5\22\n\2\u0083\u0084\7\16\2"+
		"\2\u0084\u0085\5\22\n\2\u0085\u0086\7\36\2\2\u0086\u0087\5\20\t\2\u0087"+
		"\u0088\7\37\2\2\u0088\u009c\3\2\2\2\u0089\u008a\7\17\2\2\u008a\u008b\5"+
		"\22\n\2\u008b\u0093\7\36\2\2\u008c\u008d\7\20\2\2\u008d\u008e\5\32\16"+
		"\2\u008e\u008f\7\36\2\2\u008f\u0090\5\20\t\2\u0090\u0092\3\2\2\2\u0091"+
		"\u008c\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2"+
		"\2\2\u0094\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7\21\2\2\u0097"+
		"\u0098\7\36\2\2\u0098\u0099\5\20\t\2\u0099\u009a\7\37\2\2\u009a\u009c"+
		"\3\2\2\2\u009bd\3\2\2\2\u009bg\3\2\2\2\u009bm\3\2\2\2\u009by\3\2\2\2\u009b"+
		"\177\3\2\2\2\u009b\u0089\3\2\2\2\u009c\17\3\2\2\2\u009d\u009f\5\16\b\2"+
		"\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1"+
		"\3\2\2\2\u00a1\21\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a6\5\24\13\2\u00a4"+
		"\u00a5\t\2\2\2\u00a5\u00a7\5\24\13\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3"+
		"\2\2\2\u00a7\23\3\2\2\2\u00a8\u00ab\5\26\f\2\u00a9\u00aa\t\3\2\2\u00aa"+
		"\u00ac\5\24\13\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\25\3\2"+
		"\2\2\u00ad\u00be\7\5\2\2\u00ae\u00be\7\13\2\2\u00af\u00be\7!\2\2\u00b0"+
		"\u00be\7\"\2\2\u00b1\u00b2\7\"\2\2\u00b2\u00b4\7\34\2\2\u00b3\u00b5\5"+
		"\30\r\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00be\7\35\2\2\u00b7\u00b8\7\31\2\2\u00b8\u00be\5\26\f\2\u00b9\u00ba"+
		"\7\34\2\2\u00ba\u00bb\5\22\n\2\u00bb\u00bc\7\35\2\2\u00bc\u00be\3\2\2"+
		"\2\u00bd\u00ad\3\2\2\2\u00bd\u00ae\3\2\2\2\u00bd\u00af\3\2\2\2\u00bd\u00b0"+
		"\3\2\2\2\u00bd\u00b1\3\2\2\2\u00bd\u00b7\3\2\2\2\u00bd\u00b9\3\2\2\2\u00be"+
		"\27\3\2\2\2\u00bf\u00c4\5\22\n\2\u00c0\u00c1\7 \2\2\u00c1\u00c3\5\22\n"+
		"\2\u00c2\u00c0\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5"+
		"\3\2\2\2\u00c5\31\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00cd\5\26\f\2\u00c8"+
		"\u00c9\5\26\f\2\u00c9\u00ca\7\32\2\2\u00ca\u00cb\5\26\f\2\u00cb\u00cd"+
		"\3\2\2\2\u00cc\u00c7\3\2\2\2\u00cc\u00c8\3\2\2\2\u00cd\33\3\2\2\2\26\37"+
		"%-\64?FNUbjw\u0093\u009b\u00a0\u00a6\u00ab\u00b4\u00bd\u00c4\u00cc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}