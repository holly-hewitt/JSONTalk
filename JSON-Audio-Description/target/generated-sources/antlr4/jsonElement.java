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
	
	
	

	/**
	 * @param name
	 * @param ctx
	 * @param typeName
	 */
	public jsonElement(String name) {
		super();
		this.name = name;
	}

	
	/**
	 * @param ctx
	 * @param typeName
	 */
	public jsonElement() {
		super();
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


	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String elemDescription() {
		String description = String.format("%s is a %s value", name, typeName);
		return description;
	}
	

}
