import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class jsonComplexElement extends jsonElement {

	private int fieldNo;
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
		
		String description = String.format("%s is an %s which contains %d field", name, typeName, fieldNo);
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
			}
		}

		

		return description;
	}

	public String elementDescription(boolean describeTypes) {
		String description = elemDescription();
		if (name.equals("")) {
			return "";
		}
		if (describeTypes) {
			description += listAllChildren();
		}else {
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
