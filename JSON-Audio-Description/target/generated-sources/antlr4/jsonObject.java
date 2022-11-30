import java.util.ArrayList;

public class jsonObject extends jsonComplexElement{
	
	private ArrayList<jsonObject> childObjs;
	private ArrayList<jsonArray> childArrs;

	public jsonObject(int fieldNo, jsonParser.ValueContext ctx) {
		super(ctx, fieldNo);
		setTypeName("object");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param ctx
	 * @param fieldNo
	 * @param childObjs
	 * @param childArrs
	 */
	public jsonObject(String name, jsonParser.ValueContext ctx, int fieldNo) {
		super(name, ctx, fieldNo);
		if (name.equals("[10]")) {
			this.name = "This json file";
		} else {
			this.name = name;
		}
		setTypeName("object");
	}
	
	
	

}
