import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class jsonComplexElement extends jsonElement {

	private int fieldNo;
	private ArrayList<jsonObject> childObjs;
	private ArrayList<jsonArray> childArrs;
	private LinkedHashMap<String, ArrayList <jsonElement>> children;

	/**
	 * @param name
	 * @param ctx
	 * @param fieldNo
	 */
	public jsonComplexElement(String name, jsonParser.ValueContext ctx, int fieldNo) {
		super(name, ctx);
		this.fieldNo = fieldNo;
		this.childObjs = new ArrayList<jsonObject>();
		this.childArrs = new ArrayList<jsonArray>();
	}

	public jsonComplexElement(jsonParser.ValueContext ctx, int fieldNo) {
		super(ctx);
		this.fieldNo = fieldNo;
		this.childObjs = new ArrayList<jsonObject>();
		this.childArrs = new ArrayList<jsonArray>();
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
	}

	public void addChildArr(jsonArray childArr) {
		childArrs.add(childArr);
	}

	// description methods

	public String elemDescription() {
		if (name.equals("")) {
			return "";
		}
		String description = String.format(("\"%s is an %s which contains %d field\", name, typeName, fieldNo"));
		if (childObjs.size() > 0) {
			description += listChildObjects();
		}
		if (childArrs.size() > 0) {
			description += listChildArrs();
		}
		return description;
	}

	private String listChildArrs() {
		if(childArrs.size()>0) {
			return String.format("%d field%s", childArrs.size(),
					(childArrs.size() == 1 ? " is an array, " : "s are arrays, "));
		}else {
			return "";
		}		
	}

	private String listChildObjects() {
		// TODO Auto-generated method stub
		if(childObjs.size()>0) {
			return String.format("%d field%s", childArrs.size(),
					(childObjs.size() == 1 ? " is an object, " : "s are objects, "));
		}
		return null;
	}
	
	private String listAllChildren() {
		
		String description = "";
		
		Set<String> types = children.keySet();
		for (String type : types) {
			int numOfType = children.get(type).size();
			if (numOfType == 1) {
				description += String.format("1 field is a %s value. ", type);
			}else {
				description += String.format("%d fields are %s values. ", numOfType, type);
			}
		}
		
		return description;
	}
	
	public String elementDescription(boolean describeTypes) {
		String description = elemDescription();
		if (describeTypes) {
			description += listAllChildren();
		}
		return description;
	}
}
