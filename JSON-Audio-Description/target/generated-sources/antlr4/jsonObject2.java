import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class jsonObject2 {
	private String name;
	public int fieldNo;
	public ArrayList<jsonObject2> objects;
	public ArrayList<jsonObject2> arrays;
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

	public ArrayList<jsonObject2> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<jsonObject2> objects) {
		this.objects = objects;
	}

	public jsonParser.ObjContext getCtx() {
		return ctx;
	}

	public void setCtx(jsonParser.ObjContext ctx) {
		this.ctx = ctx;
	}

	public jsonObject2(int fieldNo, String name, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		if(name.equals("[10]")) {
			this.name = "This json file";
		}else {
			this.name = name;
		}
		this.ctx = ctx;
		this.objects = new ArrayList<jsonObject2>();
		this.arrays = new ArrayList<jsonObject2>();
	}

	public jsonObject2(int fieldNo, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		this.name = "";
		this.ctx = ctx;
		this.objects = new ArrayList<jsonObject2>();
		this.arrays = new ArrayList<jsonObject2>();
	}

	public jsonObject2(int fieldNo, String name, jsonParser.ArrContext ctx2) {
		super();
		this.fieldNo = fieldNo;
		this.name = name + " is an array which";
		this.ctx2 = ctx2;
		this.objects = new ArrayList<jsonObject2>();
		this.arrays = new ArrayList<jsonObject2>();
	}

	public void addChildObj(jsonObject2 object) {
		objects.add(object);
	}

	public void addChildArr(jsonObject2 object) {
		arrays.add(object);
	}

	public String objDescription() {
		if (name.equals("")) {
			return "";
		}
		String description = String.format("%s contains %d fields", name, fieldNo);
		
		//list how many fields are objects
		if (objects.size() > 0 ) {
			if (objects.size() == 1) {
				description += (String.format(", %d field is an object.", objects.size()));
				if (ctx2 == null) { description += " The object name is: ";}

			} else {
				description += (String.format(", %d fields are objects.", objects.size()));
				if (ctx2 == null) { description += " The object names are: ";}
			} 
		}
		
		//if jsonObject is not an array, list the names of the objects and list any arrays within the file
		if (ctx2 == null) {
			if (objects.size()>0) {
				for (jsonObject2 child : objects) {
					description += child.getName() + ", ";
				}
				description = description.substring(0, description.length() - 2);
			}
			if (arrays.size() > 0 ) {
				if (arrays.size() == 1) {
					description += (String.format(", %d field is an array.", arrays.size()));
					description += " The array name is: ";

				} else {
					description += (String.format(", %d fields are arrays.", arrays.size()));
					if (ctx2 == null) { description += " The array names are: ";}
				}
				for(jsonObject2 child : arrays) {
					description += child.getName() + ", ";
				}
				description = description.substring(0, description.length()-2);
			}
		}else {
			if (objects.size() > 0) {
				HashMap<Integer, Integer> fieldNoObjNo = new HashMap<Integer, Integer>();
				for (jsonObject2 child : objects) {
					int count = fieldNoObjNo.containsKey(child.getFieldNo()) ? fieldNoObjNo.get(child.getFieldNo()) : 0;
					fieldNoObjNo.put(child.getFieldNo(), count + 1);
				}
				for(Integer fieldNum : fieldNoObjNo.keySet()) {
					Integer objNum = fieldNoObjNo.get(fieldNum);
					description += String.format("%d object%s %d field%s. ", objNum, (objNum==1?" has":"s have"), fieldNum, (fieldNum==1?"":"s"));
				}
				description = description.substring(0, description.length() - 2);
			}
		}
		
		return description;

	}
}
