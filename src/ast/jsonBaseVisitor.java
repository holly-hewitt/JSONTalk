package ast;
// Generated from src/json.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import java.util.ArrayList;

/**
 * This class provides an empty implementation of {@link jsonVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class jsonBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements jsonVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 * 
	 */
	public int numObjects;
	public ArrayList<Integer> numFieldsArr;
	
	@Override public T visitJson(jsonParser.JsonContext ctx) { 
		numObjects = 0;
		return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitObj(jsonParser.ObjContext ctx) {
		public int numFields = 0;
		numObjects += 1;
		numFieldsArr.
		return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitPair(jsonParser.PairContext ctx) { 
		numFields += 1
		return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitArr(jsonParser.ArrContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitValue(jsonParser.ValueContext ctx) { return visitChildren(ctx); }
}