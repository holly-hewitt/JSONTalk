package JSONTalk;

/**
 * Used to hold information for both anonymous and named arrays
 *
 */
public class jsonArray extends jsonComplexElement {

	/**
	 * Constructor method for anonymous arrays
	 * @param fieldNo
	 * @param depth
	 */
	public jsonArray(int fieldNo, int depth) {
		super(fieldNo, depth);
		setTypeName("array");
	}

	/**
	 * Constructor method for named arrays
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
