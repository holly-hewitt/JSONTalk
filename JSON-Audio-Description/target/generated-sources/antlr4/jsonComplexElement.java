import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
	public jsonComplexElement(String name, int fieldNo, int depth) {
		super(name, depth);
		this.fieldNo = fieldNo;
		this.childObjs = new ArrayList<>();
		this.childArrs = new ArrayList<>();
		initialiseChildrenArray();
	}

	public jsonComplexElement(int fieldNo, int depth) {
		super(depth);
		this.fieldNo = fieldNo;
		this.childObjs = new ArrayList<>();
		this.childArrs = new ArrayList<>();
		initialiseChildrenArray();
	}

	private void initialiseChildrenArray() {
		this.children = new HashMap<>();
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
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
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

	@Override
	public String elemDescription() {
		String description = "";
		if (name.equals("This json file")) {
			description = String.format("%s contains %d field", name, fieldNo);
		} else {
			if (this.parent == null) {
				description = String.format("%s is an %s, which contains %d field", name, typeName, fieldNo);
			} else {
				description = String.format("%s is an %s, belonging to %s, which contains %d field", name, typeName,
						parent.getName(), fieldNo);
			}
		}
		if (name.equals("")) {
			description = String.format("There is an anonymous %s, belonging to %s, which contains %d field", typeName,
					parent.getName(), fieldNo);
		}
		description += fieldNo == 1 ? "" : "s";
		return description;
	}

	public String fullElementDescription() {
		String description = elemDescription();
		if (name.equals("")) {
			return "";
		}
		description += listAllChildren();
		return description;
	}
	

	public String elementDescription1(descriptionLevel l, descriptionLevel n) {
		String description = "";
		if (n== descriptionLevel.NESTING) {
			description += "At depth " + depth + ": ";
		}
		description += elemDescription();
		if (l == descriptionLevel.TOPLEVEL) {
			description += listAllChildren();
		}

		if (l == descriptionLevel.COMPLEXELEMENTS) {
			description += ". ";
			if (childObjs.size() > 0) {
				description += listChildObjects();
			}
			if (childArrs.size() > 0) {
				description += listChildArrs();
			}
			if (description.charAt(description.length() - 2) == ',') {
				description = description.substring(0, description.length() - 2) + ".";
				
			}else if (description.charAt(description.length() - 1) == ',') {
				description = description.substring(0, description.length() - 1) + ".";
			}
		}
		if (l == descriptionLevel.FULL) {
			description += fullListAllChildren();
		}
		return description;
	}

	public String elementDescription(boolean describeTypes, boolean describeObjectsAndArrays, boolean full) {
		String description = elemDescription();
		if (name.equals("")) {
			return "";
		}
		if (describeTypes) {
			description += listAllChildren();
		} else if (describeObjectsAndArrays) {
			if (childObjs.size() > 0) {
				description += listChildObjects();
			}
			if (childArrs.size() > 0) {
				description += listChildArrs();
			}
			if (childArrs.size() > 0 || childObjs.size() > 0) {
				description = description.substring(0, -2);
				description += ". ";
			}
		} else if (full) {
			description += fullListAllChildren();
		}
		return description;
	}

	public String listAllChildren() {

		String description = "";
		if (children != null) {
			Set<String> types = children.keySet();
			for (String type : types) {
				int numOfType = children.get(type).size();
				if (numOfType == fieldNo && description.length() > 3) {
					description = description.substring(0, -2);
				}
				description += listFields(numOfType, type);
				description += ". ";

				if (type.equals("object")) {
					ArrayList<jsonElement> objList = new ArrayList<>(children.get(type));

					ArrayList<ArrayList<jsonElement>> SimilarObjects = groupSimilarObjects(objList);
					description += describeSimObjects(SimilarObjects);
				} else {

				}
			}
		}

		return description;
	}

	// to be overrode by jsonArray and object
	public String fullListAllChildren() {
		String description = "";

		Set<String> types = children.keySet();
		for (String type : types) {
			int numOfType = children.get(type).size();
			if (numOfType > 0) {
				if (numOfType == fieldNo && description.length() > 3) {
					description = description.substring(0, -2);
				}
				if (description.length() > 3) {
					if (description.charAt(description.length() - 2) == ',' || description.charAt(description.length()-1) == '.') {
						description = description.substring(0, description.length() - 2);
					}
				}

				description += listFields(numOfType, type);
				if (!typeName.equals("array")) {
					description += " named: ";
				}

				for (jsonElement child : children.get(type)) {
					if (!(type.equals("object") || type.equals("array"))) {
						if (!child.getName().equals("")) {
							description += child.getName();
							// if (!child.getValue().equals("")) {
							description += " with value " + child.getValue();
							// }
							description += ", ";
						}
					} else {
						if (!child.getName().equals("")) {
							description += child.getName();
							description += ", ";
						}
					}

				}
				if (description.charAt(description.length() - 2) == ',') {
					description = description.substring(0, description.length() - 2);

				}
				description += ". ";
				if (type.equals("object")) {
					ArrayList<jsonElement> objList = new ArrayList<>(children.get(type));

					ArrayList<ArrayList<jsonElement>> SimilarObjects = groupSimilarObjects(objList);
					description += describeSimObjects(SimilarObjects);
				}

			}

		}

		return description;
	}

	public String listFields(int numOfType, String type) {
		String description = "";
		if (numOfType != fieldNo) {
			if (numOfType == 1) {
				description += String.format(". 1 field is a %s value", type);
			} else if (numOfType > 1) {
				description += String.format(". %d fields are %s values", numOfType, type);
			}
		} else {

			if (numOfType == 1) {
				description += String.format(", which is a %s value", type);
			} else if (numOfType > 1) {
				description += String.format(", which are all %s values", type);
			}
		}
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
		ArrayList<ArrayList<jsonElement>> groupedObjects = new ArrayList<>();
		while (!objList.isEmpty()) {
			jsonObject child1 = (jsonObject) objList.get(0);
			objList.remove(0);
			ArrayList<jsonElement> simList = new ArrayList<>();
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
			if (objectList.size() == 1) {
				description += "1 object has a unique structure. ";
				// if(!name.equals("")) {
				// description += " named " + name + ".";
				// }else {
				// description += ".";
				// }
			} else {
				description += String.format("%d objects are of the same structure. ", objectList.size());

			}
		}
		return description;
	}
}
