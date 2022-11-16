import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class jsonObject {
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

	public jsonObject(int fieldNo, String name, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		if (name.equals("[10]")) {
			this.name = "This json file";
		} else {
			this.name = name;
		}
		this.ctx = ctx;
		this.objects = new ArrayList<jsonObject>();
		this.arrays = new ArrayList<jsonObject>();
	}

	public jsonObject(int fieldNo, jsonParser.ObjContext ctx) {
		super();
		this.fieldNo = fieldNo;
		this.name = "";
		this.ctx = ctx;
		this.objects = new ArrayList<jsonObject>();
		this.arrays = new ArrayList<jsonObject>();
	}

	public jsonObject(int fieldNo, String name, jsonParser.ArrContext ctx2) {
		super();
		this.fieldNo = fieldNo;
		this.name = name;
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

	private boolean sameFields(jsonObject obj1) {
		if (fieldNo != obj1.getFieldNo()) {
			return false;
		}
		int n=0;
		for (ParseTree i : ctx.children) {
			ParseTree j = obj1.getCtx().getChild(n);
			if(i.getChildCount()!=j.getChildCount()) {
				return false;
			}
			if(i.getChildCount()>0) {
				if(!i.getChild(0).toString().equals(j.getChild(0).toString())) {
					return false;
				}
			}
			n++;
		}
		return true;
	}

	private String listObjectsOrArrays(ArrayList<jsonObject> objectOrArrList, Boolean object) {
		String description = "";
		String objectOrArr = "object";
		// are we describing an object or an array?
		if (!object) {
			objectOrArr = "array";
		}
		
		

		if (objectOrArrList.size() > 0) {
			
			

			int listSize = objectOrArrList.size();
			description += String.format("%d field%s %s%s. ", listSize, (listSize == 1 ? " is an" : "s are"),
					objectOrArr, (listSize == 1 ? "" : "s"));
			if (ctx2 == null) {
				description += String.format("The %s name%s: ", objectOrArr, (listSize == 1 ? " is" : "s are"));
			}
			
			ArrayList<jsonObject> objList = new ArrayList<jsonObject>(objectOrArrList);
			ArrayList<ArrayList<jsonObject>> groupedObjs = groupSimilarObjects(objList);
			for (ArrayList<jsonObject> list : groupedObjs) {
				int numOfSimObj = list.size();
				int sharedFieldNo = list.get(0).getFieldNo();
				if(list.size()==1) {
					description += String.format("1 object contains %d field%s. ", sharedFieldNo, (sharedFieldNo==1 ? "" : "s"));
				}else {
					description += String.format("%d objects with %d fields are of the same structure. ", numOfSimObj, sharedFieldNo);
				}
				
			}

			// List names of objects or number of fields of each object if it is an array of
			// objects
			if (ctx2 == null) {
				for (jsonObject child : objectOrArrList) {
					description += child.getName() + ", ";
				}
				description = description.substring(0, description.length() - 2);
				description += ".";
			}else {
				if (objectOrArrList.size() > 0) {
					HashMap<Integer, Integer> fieldNoObjNo = new HashMap<Integer, Integer>();
					for (jsonObject child : objectOrArrList) {
						int count = fieldNoObjNo.containsKey(child.getFieldNo()) ? fieldNoObjNo.get(child.getFieldNo()) : 0;
						fieldNoObjNo.put(child.getFieldNo(), count + 1);
					}
					for(Integer fieldNum : fieldNoObjNo.keySet()) {
						Integer objNum = fieldNoObjNo.get(fieldNum);
						description += String.format("%d object%s %d field%s. ", objNum, (objNum==1?" has":"s have"), fieldNum, (fieldNum==1?"":"s"));
					}
				}
			}			
		}
		return description;
	}
	
	
	/**
	 * @param objList: Takes a list of objects
	 * @return Returns a list of object lists, where objects are grouped into the same lists if they have the same structure
	 */
	private ArrayList<ArrayList<jsonObject>> groupSimilarObjects(ArrayList<jsonObject> objList) {
		ArrayList<ArrayList<jsonObject>> groupedObjects = new ArrayList<ArrayList<jsonObject>>();
		while(!objList.isEmpty()) {
			jsonObject child1 = objList.get(0);
			objList.remove(0);
			ArrayList<jsonObject> simList = new ArrayList<jsonObject>();
			simList.add(child1);
			
			java.util.Iterator<jsonObject> iter = objList.iterator();
			
			while(iter.hasNext()) {
				jsonObject child2 = iter.next();
				if(child2.sameFields(child1)) {
					simList.add(child2);
					iter.remove();
				}
			}
			
			groupedObjects.add(simList);
		
		}
		return groupedObjects;
	}

	public String objDescription() {
		if (name.equals("")) {
			return "";
		}
		String objOrArr = "object";
		if (ctx2 != null) {
			objOrArr = "array";
		}
		String description = String.format("%s is an %s which contains %d field", name, objOrArr, fieldNo)
				+ (fieldNo == 1 ? ". " : "s. ");
		description += listObjectsOrArrays(objects, true);
		description += listObjectsOrArrays(arrays, false);
		return description;

	}
}
