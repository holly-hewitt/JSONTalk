import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author User
 *
 */
public class jsonObjectOrArray {

	/**
	 * name: name of object/array fieldNo: no. of fields in object/array objects:
	 * no. of child objects of object/array arrays: no. of child arrays in
	 * object/array for every jsonObject one of (ctx, ctx2) will be null ctx: if
	 * this is not null the jsonObject is an object ctx2: if this is not null the
	 * jsonObject is an array
	 */
	private String name;
	public int fieldNo;
	public ArrayList<jsonObjectOrArray> objects;
	public ArrayList<jsonObjectOrArray> arrays;
	jsonParser.ObjContext ctx;
	jsonParser.ArrContext ctx2;

	/**
	 * 3 different constructor methods 1. a named object 2. an anonymous object 3. a
	 * named array
	 * 
	 * @param fieldNo
	 * @param name    <optional>
	 * @param ctx
	 */

	public jsonObjectOrArray(int fieldNo, String name, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		if (name.equals("[10]")) {
			this.name = "This json file";
		} else {
			this.name = name;
		}
		this.ctx = ctx;
		this.objects = new ArrayList<jsonObjectOrArray>();
		this.arrays = new ArrayList<jsonObjectOrArray>();
	}

	public jsonObjectOrArray(int fieldNo, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		this.name = "";
		this.ctx = ctx;
		this.objects = new ArrayList<jsonObjectOrArray>();
		this.arrays = new ArrayList<jsonObjectOrArray>();
	}

	public jsonObjectOrArray(int fieldNo, String name, jsonParser.ArrContext ctx2) {
		super();
		this.fieldNo = fieldNo;
		if (name.equals("[")) {
			this.name = "";
		}else {
			this.name=name;
		}
		this.ctx2 = ctx2;
		this.objects = new ArrayList<jsonObjectOrArray>();
		this.arrays = new ArrayList<jsonObjectOrArray>();
	}
	
	

	/**
	 * Getter & setter methods
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFieldNo() {
		return fieldNo;
	}

	public void setFieldNo(int fieldNo) {
		this.fieldNo = fieldNo;
	}

	public ArrayList<jsonObjectOrArray> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<jsonObjectOrArray> objects) {
		this.objects = objects;
	}

	public jsonParser.ObjContext getCtx() {
		return ctx;
	}

	public void setCtx(jsonParser.ObjContext ctx) {
		this.ctx = ctx;
	}

	/**
	 * Methods to add child objects/arrays to list of children objects/arrays
	 */

	public void addChildObj(jsonObjectOrArray object) {
		objects.add(object);
	}

	public void addChildArr(jsonObjectOrArray array) {
		arrays.add(array);
	}

	/**
	 * Method to check whether two objects are of the same structure Checks for 2
	 * things 1. Do objects have same field number 2. Do objects have same field
	 * names
	 * 
	 * @param obj1: the object we are comparing this objectOrArr to
	 * @return true if objects are same structure else false
	 */
	private boolean sameFields(jsonObjectOrArray obj1) {
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
	 * @param objList: Takes a list of objects
	 * @return groupedObjects: a list of object lists, where objects are grouped
	 *         into the same lists if they have the same structure
	 */
	private ArrayList<ArrayList<jsonObjectOrArray>> groupSimilarObjects(ArrayList<jsonObjectOrArray> objList) {
		ArrayList<ArrayList<jsonObjectOrArray>> groupedObjects = new ArrayList<ArrayList<jsonObjectOrArray>>();
		while (!objList.isEmpty()) {
			jsonObjectOrArray child1 = objList.get(0);
			objList.remove(0);
			ArrayList<jsonObjectOrArray> simList = new ArrayList<jsonObjectOrArray>();
			simList.add(child1);

			java.util.Iterator<jsonObjectOrArray> iter = objList.iterator();
			while (iter.hasNext()) {
				jsonObjectOrArray child2 = iter.next();
				if (child2.sameFields(child1)) {
					simList.add(child2);
					iter.remove();
				}
			}
			groupedObjects.add(simList);
		}
		return groupedObjects;
	}
	
	private String describeSimObjects(ArrayList<ArrayList<jsonObjectOrArray>> groupedObjs, String objectOrArr) {
		String description = "";
		for (ArrayList<jsonObjectOrArray> list : groupedObjs) {
			int numOfSimObj = list.size();
			int sharedFieldNo = list.get(0).getFieldNo();
			if (list.size() == 1) {
				// "1 object contains x fields"
				String name = list.get(0).getName();
				description += String.format("1 field is an %s containing %d field%s,", objectOrArr, sharedFieldNo, 
						(sharedFieldNo == 1 ? "" : "s"));
				if(!name.equals("")) {
					description += " named " + name + ".";
				}else {
					description += ".";
				}
			} else {
				// "x objects with y fields are of the same structure"
				description += String.format(
						"%d fields are %ss of the same structure, %s containing %d field%s. ",
						 numOfSimObj, objectOrArr, (numOfSimObj == 2 ? "both" : "all"), sharedFieldNo,
						(sharedFieldNo == 1 ? "" : "s"));
				
				description += nameDescription(list, "object");
				
			}

		}
		System.out.println("sim obj desc: " + description);
		return description;
	}

	/**
	 * @param objectOrArrList: List of the jsonObjectOrArray's child objects or
	 *                         arrays
	 * @param objectOrArr:     Set to "object" if children objects is being
	 *                         described and vice versa
	 * @return The names in the string "named: name1, name2, name3"
	 */
	private String nameDescription(ArrayList<jsonObjectOrArray> objectOrArrList, String objectOrArr) {
		String description = "";
		String names = "";
		for (jsonObjectOrArray child : objectOrArrList) {
			if (!child.getName().equals("")) {
				names += child.getName() + ", ";
			}
		}
		if (!names.equals("")) {
			description += "named: " + names;
			description = description.substring(0, description.length() - 2);
		}
		description += ". ";
		System.out.println("name desc: "+ description);
		return description;
	}

	/**
	 * @param objectOrArrList
	 * @param object
	 * @return
	 */
	private String listObjectsOrArrays(ArrayList<jsonObjectOrArray> objectOrArrList, Boolean object) {
		String description = "";
		String objectOrArr = "object";
		// are we describing an object or an array?
		if (!object) {
			objectOrArr = "array";
		}

		if (objectOrArrList.size() > 0) {

			int listSize = objectOrArrList.size();
			// "x fields are objects" / "x fields are arrays"
			//description += String.format("%d field%s %s%s ", listSize, (listSize == 1 ? " is an" : "s are"),
			//		objectOrArr, (listSize == 1 ? "" : "s"));
			//description += nameDescription(objectOrArrList, objectOrArr);
			ArrayList<jsonObjectOrArray> objList = new ArrayList<jsonObjectOrArray>(objectOrArrList);
			//System.out.println("child elemenr list: " + objList);
			ArrayList<ArrayList<jsonObjectOrArray>> groupedObjs = groupSimilarObjects(objList);
			//System.out.println("groupedObjs: " + groupedObjs);
			description += describeSimObjects(groupedObjs, objectOrArr);
			//if (ctx2 != null && !object) {
				
			//}
		}
		return description;
	}

	public String objDescription() {
		if (name.equals("")) {
			return "";
		}
		String objOrArr = "object";
		if (ctx2 != null) {
			objOrArr = "array";
		}
		String description = String.format("%s is an %s which contains %d field", name, objOrArr, fieldNo)
				+ (fieldNo == 1 ? ". " : "s. ");
		description += listObjectsOrArrays(objects, true);
		description += listObjectsOrArrays(arrays, false);
		return description;

	}
}
