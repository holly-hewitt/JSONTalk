package JSONTalk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * jsonComplexElement is used to hold information for the two different complex
 * elements: array and object To hold necessary hierarchical information, each
 * jsonComplexElement has a list children of each type
 *
 */
public class jsonComplexElement extends jsonElement {

	protected int fieldNo;
	private ArrayList<jsonObject> childObjs;
	private ArrayList<jsonArray> childArrs;
	protected HashMap<String, ArrayList<jsonElement>> children;

	/**
	 * Constructor for named complex elements
	 * 
	 * @param name    The name of the element
	 * @param fieldNo The number of children fields belonging to the complex
	 *                element. If the complex element is an object, the number of
	 *                child fields, or if it is an array, the number of entries.
	 * @param depth   The depth of the complex element within the abstract syntax
	 *                tree.
	 */
	public jsonComplexElement(String name, int fieldNo, int depth) {
		super(name, depth);
		this.fieldNo = fieldNo;
		this.childObjs = new ArrayList<>();
		this.childArrs = new ArrayList<>();
		initialiseChildrenArray();
	}

	/**
	 * Constructor for anonymous complex elements
	 * 
	 * @param fieldNo The number of children fields belonging to the complex
	 *                element. If the complex element is an object, the number of
	 *                child fields, or if it is an array, the number of entries.
	 * @param depth   The depth of the complex element within the abstract syntax
	 *                tree.
	 */
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
	 * Getter for the childObjs arraylist.
	 * 
	 * @return The list of all children of the complex element that are objects.
	 */
	public ArrayList<jsonObject> getChildObjs() {
		return childObjs;
	}

	/**
	 * Getter for the depth of the complex object.
	 * 
	 * @return The depth of the complex element.
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Setter for the depth of the complex element
	 * 
	 * @param depth The depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Getter for the field number of the complex element.
	 * 
	 * @return The field number will be returned. If the complex element is a
	 *         jsonObject, the number of fields contained in the object will be
	 *         returned. If the complex element is a jsonArray, the number of items
	 *         in the array will be returned.
	 */
	public int getFieldNo() {
		return fieldNo;
	}


	 /** Getter for the childArrs arraylist.
	 * 
	 * @return The list of all children of the complex element that are arrays.
	 */
	public ArrayList<jsonArray> getChildArrs() {
		return childArrs;
	}

	/**
	 * Method to add a child object to the list of children objects of the complex element.
	 * @param childObj The child object to be added to the list of child objects.
	 */
	public void addChildObj(jsonObject childObj) {
		childObjs.add(childObj);
		children.get("object").add(childObj);
	}

	/**
	 * Method to add a child array to the list of children arrays of the complex element.
	 * @param childArr The child array to be added to the list of child arrays.
	 */
	public void addChildArr(jsonArray childArr) {
		childArrs.add(childArr);
		children.get("array").add(childArr);
	}

	/**
	 * Method to add a simple child element (i.e. int, string, null, boolean values) to the list of child elements belonging to the complex element.
	 * @param child The child element to be added to the list of children
	 */
	public void addChildElement(jsonElement child) {
		children.get(child.typeName).add(child);
	}

	/** Description Methods **/

	/**
	 * This method creates a description for each complex element depending on which
	 * command line options have been specified by the user
	 * 
	 * @param l: The description level required. Will either be toplevel, complex
	 *           elements, or full. This parameter is dependent upon the input
	 *           options specified by the user on the command line
	 * @param n: If the nesting option is enabled by the user at the command line
	 *           interface, parameter n will be set to n so that the nesting depth
	 *           of each element will be explicitly specified before it is described
	 * @return A description of the complex element
	 */
	public String elementDescription(descriptionLevel l, descriptionLevel n) {
		String description = "";
		if (n == descriptionLevel.NESTING) {
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

			} else if (description.charAt(description.length() - 1) == ',') {
				description = description.substring(0, description.length() - 1) + ".";
			}
		}
		if (l == descriptionLevel.FULL) {
			description += listAllChildrenAndValues();
		}
		return description;
	}

	/**
	 * For each complex element a description is created with the following
	 * information (1) Whether the element is named or anonymous (2) The type of the
	 * complex element (array or object) (3) How many fields belong to the complex
	 * element
	 * 
	 * @return A description of the basic properties of the complex element
	 */
	@Override
	protected String elemDescription() {
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

	/**
	 * Generates a description string based on the child elements of the complex
	 * element. listAllChildren() iterates through the children data structure and
	 * lists all of the children the complex element has, categorised by type. Child
	 * objects are passed to the groupSimilarObjects so that information about
	 * objects with identical structure can be added to the description.
	 * 
	 * @return A description of all of the children belonging to the complex element
	 */
	protected String listAllChildren() {

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
				}
			}
		}

		return description;
	}

	// to be overrode by jsonArray and object
	/**
	 * Similar to the listAllChildren() method. fullListAllChildren() lists the
	 * child elements of the complex element and their values.
	 * 
	 * @return A list of all of the children of the complex element and their
	 *         corresponding values.
	 */
	protected String listAllChildrenAndValues() {
		String description = "";

		Set<String> types = children.keySet();
		for (String type : types) {
			int numOfType = children.get(type).size();
			if (numOfType > 0) {
				if (numOfType == fieldNo && description.length() > 3) {
					description = description.substring(0, -2);
				}
				if (description.length() > 3) {
					if (description.charAt(description.length() - 2) == ','
							|| description.charAt(description.length() - 1) == '.') {
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
							description += " with value " + child.getValue();
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

	/**
	 * Helper method for the listAllChildren and listAllChildrenAndValues methods
	 * 
	 * @param numOfType Number of elements of the specified type.
	 * @param type The type of the elements. E.g. "integer", "string" etc.
	 * @return A string description in the form: "<numOfType> fields are/is a <type>
	 *         value(s)"
	 */
	protected String listFields(int numOfType, String type) {
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

	/**
	 * Helper method for the elementDescription method. Will be called if the
	 * objectsAndArrays option is enabled by the user on the command line. This
	 * method lists how many child elements of the complex element are arrays.
	 * 
	 * @return Description of the form: "x fields are arrays"
	 */
	private String listChildArrs() {
		if (childArrs.size() > 0) {
			return String.format("%d field%s", childArrs.size(),
					(childArrs.size() == 1 ? " is an array, " : "s are arrays, "));
		} else {
			return "";
		}
	}

	/**
	 * Helper method for the elementDescription method. Will be called if the
	 * objectsAndArrays option is enabled by the user on the command line. This
	 * method lists how many child elements of the complex element are objects.
	 * 
	 * @return Description of the form: "x fields are objects"
	 */
	private String listChildObjects() {
		if (childObjs.size() > 0) {
			return String.format("%d field%s", childObjs.size(),
					(childObjs.size() == 1 ? " is an object, " : "s are objects, "));
		}
		return null;
	}

	/**
	 * Helper method for the listAllChildren and listAllChildrenAndValues methods.
	 * 
	 * @param objList Takes a list of objects
	 * @return A list of object lists, where objects are grouped
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

	/**
	 * Helper method for the listAllCHildren and listAllChildrenAndValues methods.
	 * Uses the groupedObjs data structure produced by the groupSimilarObjects
	 * method and produces a description which takes provides information about
	 * objects of the same structure.
	 * 
	 * @param groupedObjs A list of lists. Where elements are in the same list if they have similar structure and all children objects are present within exactly 1 list.
	 * @return Description in form: "x objects have unique structure, y objects are
	 *         of the same structure"
	 */
	private String describeSimObjects(ArrayList<ArrayList<jsonElement>> groupedObjs) {
		String description = "";
		for (ArrayList<jsonElement> objectList : groupedObjs) {
			if (objectList.size() == 1) {
				description += "1 object has a unique structure. ";
			} else {
				description += String.format("%d objects are of the same structure. ", objectList.size());

			}
		}
		return description;
	}
}
