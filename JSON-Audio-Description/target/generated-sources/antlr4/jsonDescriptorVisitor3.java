
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
public class jsonDescriptorVisitor3<T> extends AbstractParseTreeVisitor<T> implements jsonVisitor<T> {

	public static LinkedHashMap<String, jsonComplexElement> objects;

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
		objects = new LinkedHashMap<String, jsonComplexElement>();
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
			//jsonObjectOrArray currentObj = new jsonObjectOrArray(numChildren, objectName, ctx);
			jsonObject currentObj = new jsonObject(objectName, numChildren, ctx);

			if (ctx.parent.parent.parent != null) {
				if (objects.get(ctx.parent.parent.parent.toString()) != null) {
					objects.get(ctx.parent.parent.parent.toString()).addChildObj(currentObj);
				}
			}

			// add object to hashmap
			objects.put(ctx.toString(), currentObj);

		} else {

			jsonObject currentObj = new jsonObject(numChildren, ctx);
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
		
		
		jsonArray currentArr = new jsonArray(numChildren, arrayName);
		if (ctx.parent.parent.parent != null) {
			if (objects.get(ctx.parent.parent.parent.toString()) != null) {
				objects.get(ctx.parent.parent.parent.toString()).addChildArr(currentArr);
			}
		}

		// add object to hashmap
		objects.put(ctx.toString(), currentArr);
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
		
		
		
		String typename = null;

		
		if(ctx.STRING()!=null) {
			//jsonElement elem = new jsonElement(ctx.getParent().getChild(0).toString(), "String");
			//System.out.println(elem.name);
			typename = "string";
			
		}
		
		
		if(ctx.NUMBER()!=null) {
			//jsonElement elem = new jsonElement(ctx.getParent().getChild(0).toString(), "Integer");
			typename = "integer";
		}
		if(ctx.getChild(0).toString().equals("true") || ctx.getChild(0).toString().equals("false")) {
			//jsonElement elem = new jsonElement(ctx.getParent().getChild(0).toString(), "Boolean");
			typename = "boolean";
		}
		if (ctx.getChild(0).toString().equals("null")) {
			typename = "null";
		}
		if (typename != null) {
			jsonElement elem = new jsonElement(ctx.getParent().getChild(0).toString(), typename);
			if (ctx.parent.parent.parent.parent != null) {
				if (objects.get(ctx.parent.parent.toString()) != null) {
					objects.get(ctx.parent.parent.toString()).addChildElement(elem);
				}
			}
			
		}
		return visitChildren(ctx);
	}
}