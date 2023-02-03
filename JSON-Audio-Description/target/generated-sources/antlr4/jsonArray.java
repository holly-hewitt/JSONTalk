
public class jsonArray extends jsonComplexElement {

	public jsonArray(int fieldNo, int depth) {
		super(fieldNo, depth);
		setTypeName("array");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numChildren
	 * @param arrayName
	 * @param ctx
	 */
	public jsonArray(int fieldNo, String arrayName, int depth) {
		super(arrayName, fieldNo, depth);

		if (arrayName.equals("[")) {
			this.name = "";

		} else {
			this.name = arrayName;
		}
		setTypeName("array");
	}

}
