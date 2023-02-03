import java.util.ArrayList;

public class jsonElement {
	
	/**
	 * name: name of object/array fieldNo: no. of fields in object/array objects:
	 * no. of child objects of object/array arrays: no. of child arrays in
	 * object/array for every jsonObject one of (ctx, ctx2) will be null ctx: if
	 * this is not null the jsonObject is an object ctx2: if this is not null the
	 * jsonObject is an array
	 */
	protected String name;	
	protected String typeName;
	protected int depth;
	protected String value;
	protected jsonComplexElement parent;
	
	

	/**
	 * @param name
	 * @param ctx
	 * @param typeName
	 */
	public jsonElement(String name, int depth) {
		super();
		this.name = name;
		this.depth = depth;
	}
	
	public jsonElement(String name, String typename, int depth) {
		super();
		this.name = name;
		this.typeName=typename;
		this.depth = depth;
	}

	
	/**
	 * @param ctx
	 * @param typeName
	 */
	public jsonElement(int depth) {
		super();
		this.depth = depth;
		this.name = "";
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setParent(jsonComplexElement parent) {
		this.parent = parent;
	}
	
	public void setValue(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}


	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public String elemDescription() {
		String description = String.format("%s is a %s value", name, typeName);
		return description;
	}
	

}
