import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;

public class jsonComplexElement extends jsonElement {

	protected int fieldNo;
	private ArrayList<jsonObject> childObjs;
	private ArrayList<jsonArray> childArrs;
	protected HashMap<String, ArrayList<jsonElement>> children;

	/**
	 * @param name
	 * @param ctx
	 * @param fieldNo
	 */
	public jsonComplexElement(String name, int fieldNo) {
		super(name);
		this.fieldNo = fieldNo;
		this.childObjs = new ArrayList<jsonObject>();
		this.childArrs = new ArrayList<jsonArray>();
		this.children = new HashMap<String, ArrayList<jsonElement>>();
		this.children.put("object", new ArrayList<jsonElement>());
		this.children.put("array", new ArrayList<jsonElement>());
		this.children.put("string", new ArrayList<jsonElement>());
		this.children.put("integer", new ArrayList<jsonElement>());
		this.children.put("boolean", new ArrayList<jsonElement>());
		this.children.put("null", new ArrayList<jsonElement>());
	}

	public jsonComplexElement(int fieldNo) {
		super();
		this.fieldNo = fieldNo;
		this.childObjs = new ArrayList<jsonObject>();
		this.childArrs = new ArrayList<jsonArray>();
		this.children = new HashMap<String, ArrayList<jsonElement>>();
		this.children.put("object", new ArrayList<jsonElement>());
		this.children.put("array", new ArrayList<jsonElement>());
		this.children.put("string", new ArrayList<jsonElement>());
		this.children.put("integer", new ArrayList<jsonElement>());
		this.children.put("boolean", new ArrayList<jsonElement>());
		this.children.put("null", new ArrayList<jsonElement>());
	}

	/**
	 * @return the childObjs
	 */
	public ArrayList<jsonObject> getChildObjs() {
		return childObjs;
	}

	/**
	 * @return the fieldNo
	 */
	public int getFieldNo() {
		return fieldNo;
	}

	/**
	 * @return the childArrs
	 */
	public ArrayList<jsonArray> getChildArrs() {
		return childArrs;
	}

	public void addChildObj(jsonObject childObj) {
		childObjs.add(childObj);
		children.get("object").add(childObj);		
	}

	public void addChildArr(jsonArray childArr) {
		childArrs.add(childArr);
		children.get("array").add(childArr);	
	}
	
	public void addChildElement(jsonElement child) {
		children.get(child.typeName).add(child);
	}
	

	// description methods

	public String elemDescription() {
		String description = "";
		if (name.equals("This json file")){
			description = String.format("%s contains %d field", name, fieldNo);
		}else {
			description = String.format("%s is an %s which contains %d field", name, typeName, fieldNo);
		}		
		description+= fieldNo == 1 ? ". " : "s. ";
		return description;
	}

	private String listChildArrs() {
		if (childArrs.size() > 0) {
			return String.format("%d field%s", childArrs.size(),
					(childArrs.size() == 1 ? " is an array, " : "s are arrays, "));
		} else {
			return "";
		}
	}

	private String listChildObjects() {
		// TODO Auto-generated method stub
		if (childObjs.size() > 0) {
			return String.format("%d field%s", childObjs.size(),
					(childObjs.size() == 1 ? " is an object, " : "s are objects, "));
		}
		return null;
	}
	
	// describe objects with identical structure
	


	/**
	 * @param objList: Takes a list of objects
	 * @return groupedObjects: a list of object lists, where objects are grouped
	 *         into the same lists if they have the same structure
	 */
	private ArrayList<ArrayList<jsonElement>> groupSimilarObjects(ArrayList<jsonElement> objList) {
		ArrayList<ArrayList<jsonElement>> groupedObjects = new ArrayList<ArrayList<jsonElement>>();
		while (!objList.isEmpty()) {
			jsonObject child1 = (jsonObject) objList.get(0);
			objList.remove(0);
			ArrayList<jsonElement> simList = new ArrayList<jsonElement>();
			simList.add(child1);

			Iterator<jsonElement> iter = objList.iterator();
			while (iter.hasNext()) {
				jsonObject child2 = (jsonObject) iter.next();
				if (child2.sameFields(child1)) {
					simList.add(child2);
					iter.remove();
				}
			}
			groupedObjects.add(simList);
		}
		return groupedObjects;
	}
	
	private String describeSimObjects(ArrayList<ArrayList<jsonElement>> groupedObjs) {
		String description = "";
		for (ArrayList<jsonElement> objectList : groupedObjs) {
			if (objectList.size()==1) {
				String name = objectList.get(0).getName();
				description += "1 object has a unique structure. ";
				//if(!name.equals("")) {
				//	description += " named " + name + ".";
				//}else {
				//	description += ".";
				//}
			}else {
				description += String.format("%d objects are of the same structure. ", objectList.size());
				
			}
		}
		return description;
	}
	
	public String listAllChildren() {

		String description = "";
		if (children != null) {
			Set<String> types = children.keySet();
			for (String type : types) {
				int numOfType = children.get(type).size();
				if (numOfType == 1) {
					description += String.format("1 field is a %s value. ", type);
				} else if (numOfType > 1){
					description += String.format("%d fields are %s values. ", numOfType, type);
				}
				
				if (type.equals("object")) {
					ArrayList<jsonElement> objList = new ArrayList<jsonElement>(children.get(type));
					
					ArrayList<ArrayList<jsonElement>> SimilarObjects = groupSimilarObjects(objList);
					description += describeSimObjects(SimilarObjects);
				}
			}
		}	

		return description;
	}

	public String elementDescription(boolean describeTypes, boolean describeObjectsAndArrays) {
		String description = elemDescription();
		if (name.equals("")) {
			return "";
		}
		if (describeTypes) {
			description += listAllChildren();
		}else if (describeObjectsAndArrays){
			if (childObjs.size() > 0) {
				description += listChildObjects();
			}
			if (childArrs.size() > 0) {
				description += listChildArrs();
			}
		}
		return description;
	}
}
