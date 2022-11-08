import java.util.ArrayList;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class jsonObject {
	private String name;
	public int fieldNo;
	public ArrayList<jsonObject> objects;
	jsonParser.ObjContext ctx;
	
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
	public jsonObject(int fieldNo, String name, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		this.name = name;
		this.ctx=ctx;
		this.objects = new ArrayList<jsonObject>();
	}
	
	public void addChildObj(jsonObject object) {
		objects.add(object);	
	}
	public String objDescription() {
		String description = String.format("%s contains %d fields", name, fieldNo);
		if (objects.size()>0) {
			String descriptionPart2;
			if (objects.size() ==1) {
				descriptionPart2 = (String.format(", %d of which is an object", objects.size()));
			}else {
				descriptionPart2 = (String.format(", %d of which are objects", objects.size()));
			}			
			return description + descriptionPart2;
		}
		return description;
	}
}
