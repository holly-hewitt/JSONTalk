import java.util.ArrayList;
import java.util.Set;

public class jsonObject extends jsonComplexElement {

	// private ArrayList<jsonObject> childObjs;
	// private ArrayList<jsonArray> childArrs;

	public jsonObject(int fieldNo) {
		super(fieldNo);
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
	public jsonObject(String name, int fieldNo) {
		super(name, fieldNo);
		if (name.equals("[10]")) {
			this.name = "This json file";
		} else {
			this.name = name;
		}
		setTypeName("object");
	}

	public String listAllChildren() {

		String description = "";

		Set<String> types = children.keySet();
		for (String type : types) {
			int numOfType = children.get(type).size();
			if (numOfType > 0) {
				if (numOfType == 1) {
					description += String.format("1 field is a %s value", type);
				} else{
					description += String.format("%d fields are %s values", numOfType, type);
				}
				
				String names = "";
				for (jsonElement child : children.get(type)) {
					if (!child.getName().equals("")) {
						names += child.getName() + ", ";
					}
				}
				if (!names.equals("")) {
					description += ", named: " + names;
					description = description.substring(0, description.length() - 2);
				}
				description += ". ";
			}
			
		}

		return description;
	}

}
