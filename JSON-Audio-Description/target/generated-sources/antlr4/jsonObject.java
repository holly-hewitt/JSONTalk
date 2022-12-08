import java.util.ArrayList;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;

public class jsonObject extends jsonComplexElement {
	
	jsonParser.ObjContext ctx;

	// private ArrayList<jsonObject> childObjs;
	// private ArrayList<jsonArray> childArrs;

	public jsonObject(int fieldNo, jsonParser.ObjContext ctx) {
		super(fieldNo);
		this.ctx = ctx;
		setTypeName("object");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param ctx
	 * @param fieldNo
	 * @param childObjs
	 * @param childArrs
	 */
	public jsonObject(String name, int fieldNo, jsonParser.ObjContext ctx) {
		super(name, fieldNo);
		if (name.equals("[10]")) {
			this.name = "This json file";
		} else {
			this.name = name;
		}
		this.ctx = ctx;
		setTypeName("object");
	}

	public String listAllChildren() {

		String description = "";

		Set<String> types = children.keySet();
		for (String type : types) {
			int numOfType = children.get(type).size();
			if (numOfType > 0) {
				if (numOfType == 1) {
					description += String.format("1 field is a %s value", type);
				} else{
					description += String.format("%d fields are %s values", numOfType, type);
				}
				
				String names = "";
				for (jsonElement child : children.get(type)) {
					if (!child.getName().equals("")) {
						names += child.getName() + ", ";
					}
				}
				if (!names.equals("")) {
					description += ", named: " + names;
					description = description.substring(0, description.length() - 2);
				}
				description += ". ";
			}
			
		}

		return description;
	}
	
	/**
	 * Method to check whether two objects are of the same structure Checks for 2
	 * things 1. Do objects have same field number 2. Do objects have same field
	 * names
	 * 
	 * @param obj1: the object we are comparing this objectOrArr to
	 * @return true if objects are same structure else false
	 */
	boolean sameFields(jsonObject obj1) {
		if (fieldNo != obj1.getFieldNo()) {
			return false;
		}
		int n = 0;
		for (ParseTree i : ctx.children) {
			ParseTree j = obj1.getCtx().getChild(n);
			if (i.getChildCount() != j.getChildCount()) {
				return false;
			}
			if (i.getChildCount() > 0) {
				if (!i.getChild(0).toString().equals(j.getChild(0).toString())) {
					return false;
				}
			}
			n++;
		}
		return true;
	}

	/**
	 * @return the ctx
	 */
	public jsonParser.ObjContext getCtx() {
		return ctx;
	}

}
