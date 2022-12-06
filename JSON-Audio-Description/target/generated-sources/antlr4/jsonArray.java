
public class jsonArray extends jsonComplexElement {

	public jsonArray(int fieldNo) {
		super(fieldNo);
		setTypeName("Array");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numChildren
	 * @param arrayName
	 * @param ctx
	 */
	public jsonArray(int fieldNo, String arrayName) {
		super(arrayName, fieldNo);

		if (arrayName.equals("[")) {
			this.name = "";

		} else {
			this.name = arrayName;
		}
		setTypeName("Array");
	}

}
