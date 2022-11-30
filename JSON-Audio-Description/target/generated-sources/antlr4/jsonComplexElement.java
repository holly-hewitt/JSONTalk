import java.util.ArrayList;

public class jsonComplexElement extends jsonElement {
	
	private int fieldNo;
	private ArrayList<jsonObject> childObjs;
	private ArrayList<jsonArray> childArrs;	

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
	
	public void addChildArr (jsonArray childArr) {
		childArrs.add(childArr);
	}
	
	//description methods
	
	public String elemDescription() {
		if (name.equals("")) {
			return "";
		}
		String description = String.format(("\"%s is an %s which contains %d field\", name, typeName, fieldNo"));
		if(childObjs.size()>0) {
			description += listChildObjects();
		}
		if(childArrs.size()>0) {
			description += listChildArrs();
		}
		return description;
	}

	private String listChildArrs() {
		
		if(childArrs.size() == 1) {
			String description = "1 field is an array";
			if (childArrs.get(0).getName() != "") {
				description += "named, "
			}
		}
		String description = String.format("%d field%s", childArrs.size(), (childArrs.size()==1 ? " is an array, ": "s are arrays, "));
		
		
		// TODO Auto-generated method stub
		return null;
	}

	private String listChildObjects() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
