
/**
 * Holds information about primitive jsonElements. Primitive jsonElements are
 * string, integer, boolean, and null values. Information stored about the
 * elements includes (1) name if the element has one, (2) type of element, (3)
 * depth of element, (4) element value, (5) element parent.
 *
 */
public class jsonElement {

	/**
	 * Name of the jsonElement. It will be set to "" if the element is anonymous.
	 */
	protected String name;
	/**
	 * typeName of the element. Represented as a string, for example, "string" or "boolean".
	 */
	protected String typeName;
	/**
	 * Depth within the abstract syntax tree that the element is positioned at.
	 */
	protected int depth;
	/**
	 * Value of the jsonElement.
	 */
	protected String value;
	/**
	 * Direct parent element of the jsonElement. This will either be a jsonObject or jsonArray.
	 */
	protected jsonComplexElement parent;

	
	/**
	 * Constructor for named elements with an unspecified type name
	 * @param name Name of the element.
	 * @param depth Depth of the element within the abstract syntax tree.
	 */
	public jsonElement(String name, int depth) {
		super();
		this.name = name;
		this.depth = depth - 1;
	}

	/**
	 * Constructor for named elements
	 * @param name Name of the element.
	 * @param typename Type name of the element.
	 * @param depth Depth of the element within the abstract syntax tree.
	 */
	public jsonElement(String name, String typename, int depth) {
		super();
		this.name = name;
		this.typeName = typename;
		this.depth = depth - 1;
	}

	
	/**
	 * Constructor for element with unspecified name and type name
	 * @param depth Depth of the element within the abstract syntax tree.
	 */
	public jsonElement(int depth) {
		super();
		this.depth = depth - 1;
		this.name = "";
	}

	/**
	 * Name getter method
	 * @return The name of the element
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter method
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Parent setter method
	 * @param parent The jsonComplexElement that should be set as the parent of the element.
	 */
	public void setParent(jsonComplexElement parent) {
		this.parent = parent;
	}

	/**
	 * Value getter method
	 * @return The value of the element.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Value setter method
	 * @param value The string value that should be set as the value of the element.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * typeName getter method
	 * @return The type name of the element.
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * typeName setter method
	 * @param typeName The type name that should be set as the typeName of the element.
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * Generates a simple description of the element, including the name and type name of the element.
	 * @return Simple description of element in form: "<name> is a <typeName> value"
	 */
	protected String elemDescription() {
		String description = String.format("%s is a %s value", name, typeName);
		return description;
	}

}
