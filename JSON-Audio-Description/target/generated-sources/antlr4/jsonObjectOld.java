import java.util.ArrayList;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class jsonObjectOld {
	private String name;
	public int fieldNo;
	public ArrayList<jsonObject> objects;
	public ArrayList<jsonObject> arrays;
	jsonParser.ObjContext ctx;
	jsonParser.ArrContext ctx2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFieldNo() {
		return fieldNo;
	}

	public void setFieldNo(int fieldNo) {
		this.fieldNo = fieldNo;
	}

	public ArrayList<jsonObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<jsonObject> objects) {
		this.objects = objects;
	}

	public jsonParser.ObjContext getCtx() {
		return ctx;
	}

	public void setCtx(jsonParser.ObjContext ctx) {
		this.ctx = ctx;
	}

	public jsonObjectOld(int fieldNo, String name, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		this.name = name;
		this.ctx = ctx;
		this.objects = new ArrayList<jsonObject>();
		this.arrays = new ArrayList<jsonObject>();
	}

	public jsonObjectOld(int fieldNo, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		this.name = "";
		this.ctx = ctx;
		this.objects = new ArrayList<jsonObject>();
		this.arrays = new ArrayList<jsonObject>();
	}

	public jsonObjectOld(int fieldNo, String name, jsonParser.ArrContext ctx2) {
		super();
		this.fieldNo = fieldNo;
		this.name = name + " is an array which";
		this.ctx2 = ctx2;
		this.objects = new ArrayList<jsonObject>();
		this.arrays = new ArrayList<jsonObject>();
	}

	public void addChildObj(jsonObject object) {
		objects.add(object);
	}

	public void addChildArr(jsonObject object) {
		arrays.add(object);
	}

	public String objDescription() {
		if (ctx2 == null) {

			if (name.equals("[10]")) {
				name = "This json file";
			}
			if (name.equals("")) {
				return "";
			}
			String description = String.format("%s contains %d fields", name, fieldNo);
			if (objects.size() > 0) {
				String descriptionPart2;
				String descriptionPart3 = "";
				if (objects.size() == 1) {
					descriptionPart2 = (String.format(", %d field is an object. The object name is:  ",
							objects.size()));
					
				} else {
					descriptionPart2 = (String.format(", %d fields are objects. The objects names are: ",
							objects.size()));
				}
				for (jsonObject child : objects) {
					descriptionPart3 += child.getName() + ", ";
				}
				
				return description + descriptionPart2 + descriptionPart3.substring(0, descriptionPart3.length() - 2);
			}
			return description;

		} else {
			String description = String.format("%s contains %d fields", name, fieldNo);
			if (objects.size() > 0) {
				String descriptionPart2;
				String descriptionPart3 = "";
				if (objects.size() == 1) {
					descriptionPart2 = (String.format(", %d field is an object.", objects.size()));
				} else {
					descriptionPart2 = (String.format(", %d fields are objects.", objects.size()));
				}

				for (jsonObject child : objects) {
					descriptionPart3 += (child.getFieldNo() + " fields ");
				}
				return description + descriptionPart2 + descriptionPart3.substring(0, descriptionPart3.length() - 2);
			}
		}
		return "";

	}
}
