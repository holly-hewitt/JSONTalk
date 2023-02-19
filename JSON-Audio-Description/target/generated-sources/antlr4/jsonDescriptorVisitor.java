
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link jsonVisitor}, which can
 * be extended to create a visitor which only needs to handle a subset of the
 * available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public class jsonDescriptorVisitor<T> extends AbstractParseTreeVisitor<T> implements jsonVisitor<T> {

	public static LinkedHashMap<ParserRuleContext, jsonComplexElement> ctxElems;
	public static LinkedHashMap<String, ArrayList<jsonComplexElement>> objects;
	public static ArrayList<jsonComplexElement> visited;
	//public static LinkedHashMap<ParserRuleContext, jsonComplexElement> ctxElems;

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
		ctxElems = new LinkedHashMap<ParserRuleContext, jsonComplexElement>();
		objects = new LinkedHashMap<String, ArrayList<jsonComplexElement>>();
		visited = new ArrayList<jsonComplexElement>();
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
		
		

		String objectName = ctx.parent.parent.getChild(0).toString();
		//System.out.println(ctx.depth()/3);
		

		// if object is not anonymous, create named jsonObject
		if (!objectName.equals("[")) {

			jsonObject currentObj = new jsonObject(objectName, numChildren, ctx, ctx.depth()/3);

			if (ctx.parent.parent.parent != null) {
				if (ctxElems.get(ctx.parent.parent.parent) != null) {
					ctxElems.get(ctx.parent.parent.parent).addChildObj(currentObj);
					currentObj.setParent(ctxElems.get(ctx.parent.parent.parent));
					
					
					
				}
			}

			// add object to hashmap
			ctxElems.put(ctx, currentObj);

		} // if object is anonymous create, unnamed jsonObject
		else {
			

			jsonObject currentObj = new jsonObject(numChildren, ctx, ctx.depth()/3);
			if (ctx.parent.parent.parent.parent != null) {
				if (ctxElems.get(ctx.parent.parent) != null) {
					ctxElems.get(ctx.parent.parent).addChildObj(currentObj);
					currentObj.setParent(ctxElems.get(ctx.parent.parent));
				}
			}
			ctxElems.put(ctx, currentObj);
			


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
		//System.out.println("key: " + key + ctx.value().depth()/3);
		//System.out.println(ctx.value().STRING().getText());
		ctx.value().depth();
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
		//System.out.println("Array Depth: " + ctx.depth());
		String arrayName = ctx.parent.parent.getChild(0).toString();
		int numChildren = ctx.getChildCount();
		// account for {}
		numChildren -= 2;
		// account for ,
		numChildren -= (numChildren / 2);

		jsonArray currentArr = new jsonArray(numChildren, arrayName, ctx.depth()/3);
		if (ctx.parent.parent.parent != null) {
			if (ctxElems.get(ctx.parent.parent.parent) != null) {
				ctxElems.get(ctx.parent.parent.parent).addChildArr(currentArr);
			}
		}

		// add object to hashmap
		ctxElems.put(ctx, currentArr);
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

		if (ctx.STRING() != null) {
			typename = "string";
		}

		if (ctx.NUMBER() != null) {
			typename = "integer";
		}
		
		if (ctx.getChild(0).toString().equals("true") || ctx.getChild(0).toString().equals("false")) {
			typename = "boolean";
		}
		
		if (ctx.getChild(0).toString().equals("null")) {
			typename = "null";
		}
		if (typename != null) {
			jsonElement elem = new jsonElement(ctx.getParent().getChild(0).toString(), typename, ctx.depth()/3);
			if (ctx.parent.parent.parent.parent != null) {
				if (ctxElems.get(ctx.parent.parent) != null) {
					ctxElems.get(ctx.parent.parent).addChildElement(elem);
				}
			}
			elem.setValue(ctx.getText().toString());

		}
		return visitChildren(ctx);
	}
}