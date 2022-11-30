
public class jsonArray extends jsonComplexElement {

	public jsonArray(int fieldNo, jsonParser.ValueContext ctx) {
		super(ctx, fieldNo);
		setTypeName("Array");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param fieldNo
	 * @param ctx
	 */
	public jsonArray(String name, int fieldNo, jsonParser.ValueContext ctx) {
		super(name, ctx, fieldNo);
		// TODO Auto-generated constructor stub
		if (name.equals("[")) {
			setName("");
			//super.name = name;
		}else {
			setName(name);
		}
		setTypeName("Array");
	}
	
	

}
