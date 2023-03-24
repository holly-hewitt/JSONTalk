package JSONTalk;

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

	/**
	 * The ctxElems list keeps track of each individual jsonComplexElement object
	 * created when visiting each object or array node of the abstract syntax tree
	 * of the json file. This allows for keeping track of nodes that have already
	 * been visited, and for setting the parents and children of elements.
	 */
	public static LinkedHashMap<ParserRuleContext, jsonComplexElement> ctxElems;

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * Initialises the ctxElems data structure, then returns the result of calling
	 * AbstractParseTreeVisitor.visitChildren(org.antlr.v4.runtime.tree.RuleNode) on
	 * ctx.
	 * </p>
	 */
	@Override
	public T visitJson(jsonParser.JsonContext ctx) {
		ctxElems = new LinkedHashMap<ParserRuleContext, jsonComplexElement>();
		// objects = new LinkedHashMap<String, ArrayList<jsonComplexElement>>();
		// visited = new ArrayList<jsonComplexElement>();
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * Creates a jsonObject object for the visited object. Sets the parent of the
	 * object accordingly. The method then returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitObj(jsonParser.ObjContext ctx) {

		// set the number of children of the object
		// accounts for "{", "}" and "," in the abstract syntax tree
		int numChildren = ctx.getChildCount();
		numChildren -= 2;
		numChildren -= (numChildren / 2);

		String objectName = ctx.parent.parent.getChild(0).toString();

		// 1: create named jsonObject or unnamed jsonObject, depending on whether or not
		// the object is part of an array
		// 2: set the parent of the object
		// 3: add the object to the children of the parent
		if (!objectName.equals("[")) {

			jsonObject currentObj = new jsonObject(objectName, numChildren, ctx, ctx.depth() / 3);

			if (ctx.parent.parent.parent != null) {
				if (ctxElems.get(ctx.parent.parent.parent) != null) {
					ctxElems.get(ctx.parent.parent.parent).addChildObj(currentObj);
					currentObj.setParent(ctxElems.get(ctx.parent.parent.parent));
				}
			}
			ctxElems.put(ctx, currentObj);

		} else {
			jsonObject currentObj = new jsonObject(numChildren, ctx, ctx.depth() / 3);
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
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * Creates a jsonObject object for the visited object. Sets the parent of the
	 * object accordingly. The method then returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitArr(jsonParser.ArrContext ctx) {
		String arrayName = ctx.parent.parent.getChild(0).toString();

		// set the number of children of the object
		// accounts for "{", "}" and "," in the abstract syntax tree
		int numChildren = ctx.getChildCount();
		numChildren -= 2;
		numChildren -= (numChildren / 2);

		jsonArray currentArr = new jsonArray(numChildren, arrayName, ctx.depth() / 3);
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
	 * This method creates jsonElement objects for any string, integer, boolean or
	 * null value visited. The child jsonElement is then added to the children of
	 * the parent complexElement. The method then returns the result of
	 * calling {@link #visitChildren} on {@code ctx}.
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
			jsonElement elem = new jsonElement(ctx.getParent().getChild(0).toString(), typename, ctx.depth() / 3);
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