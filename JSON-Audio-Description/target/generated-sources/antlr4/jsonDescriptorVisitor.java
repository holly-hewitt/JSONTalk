
// Generated from java-escape by ANTLR 4.11.1
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * This class provides an empty implementation of {@link jsonVisitor}, which can
 * be extended to create a visitor which only needs to handle a subset of the
 * available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
@SuppressWarnings("CheckReturnValue")
public class jsonDescriptorVisitor<T> extends AbstractParseTreeVisitor<T> implements jsonVisitor<T> {

	public static LinkedHashMap<String, jsonObjectOrArray> objects;
	public static ArrayList<jsonObjectOrArray> objects1;

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitJson(jsonParser.JsonContext ctx) {
		objects = new LinkedHashMap<String, jsonObjectOrArray>();
		objects1 = new ArrayList<jsonObjectOrArray>();
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitObj(jsonParser.ObjContext ctx) {
		

		int numChildren = ctx.getChildCount();
		// account for {}
		numChildren -= 2;
		// account for ,
		numChildren -= (numChildren / 2);
		
//		for (ParseTree i : ctx.children) {
//			System.out.print(i.getChildCount());
//			if(i.getChildCount()>0) {
//				System.out.println(i.getChild(0));
//			}
//		}

		String objectName = ctx.parent.parent.getChild(0).toString();

		// discard objects within array
		if (!objectName.equals("[")) {
			jsonObjectOrArray currentObj = new jsonObjectOrArray(numChildren, objectName, ctx);

			if (ctx.parent.parent.parent != null) {
				if (objects.get(ctx.parent.parent.parent.toString()) != null) {
					objects.get(ctx.parent.parent.parent.toString()).addChildObj(currentObj);
				}
			}

			// add object to hashmap
			objects.put(ctx.toString(), currentObj);

		} else {

			jsonObjectOrArray currentObj = new jsonObjectOrArray(numChildren, ctx);
			if (ctx.parent.parent.parent.parent != null) {
				if (objects.get(ctx.parent.parent.toString()) != null) {
					objects.get(ctx.parent.parent.toString()).addChildObj(currentObj);
				}
			}
			objects.put(ctx.toString(), currentObj);

		}

		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitPair(jsonParser.PairContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitArr(jsonParser.ArrContext ctx) {
		String arrayName = ctx.parent.parent.getChild(0).toString();
		int numChildren = ctx.getChildCount();
		// account for {}
		numChildren -= 2;
		// account for ,
		numChildren -= (numChildren / 2);
		
		
		jsonObjectOrArray currentObj = new jsonObjectOrArray(numChildren, arrayName, ctx);
		if (ctx.parent.parent.parent != null) {
			if (objects.get(ctx.parent.parent.parent.toString()) != null) {
				objects.get(ctx.parent.parent.parent.toString()).addChildArr(currentObj);
			}
		}

		// add object to hashmap
		objects.put(ctx.toString(), currentObj);
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitValue(jsonParser.ValueContext ctx) {
		return visitChildren(ctx);
	}
}