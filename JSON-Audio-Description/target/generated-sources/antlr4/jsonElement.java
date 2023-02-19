
/**
 * Holds information about primitive jsonElements. Primitive jsonElements are
 * string, integer, boolean, and null values. Information stored about the
 * elements includes (1) name if the element has one, (2) type of element, (3)
 * depth of element, (4) element value, (5) element parent.
 *
 */
public class jsonElement {

	protected String name;
	protected String typeName;
	protected int depth;
	protected String value;
	protected jsonComplexElement parent;

	
	/**
	 * Constructor for named elements with an unspecified type name
	 * @param name
	 * @param depth
	 */
	public jsonElement(String name, int depth) {
		super();
		this.name = name;
		this.depth = depth - 1;
	}

	/**
	 * Constructor for named elements
	 * @param name
	 * @param typename
	 * @param depth
	 */
	public jsonElement(String name, String typename, int depth) {
		super();
		this.name = name;
		this.typeName = typename;
		this.depth = depth - 1;
	}

	
	/**
	 * Constructor for element with unspecified name and type name
	 * @param depth
	 */
	public jsonElement(int depth) {
		super();
		this.depth = depth - 1;
		this.name = "";
	}

	/**
	 * Name getter method
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter method
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Parent setter method
	 * @param parent
	 */
	public void setParent(jsonComplexElement parent) {
		this.parent = parent;
	}

	/**
	 * Value getter method
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Value setter method
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * typeName getter method
	 * @return typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * typeName setter method
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return Simple description of element in form: "<name> is a <typeName> value"
	 */
	protected String elemDescription() {
		String description = String.format("%s is a %s value", name, typeName);
		return description;
	}

}
