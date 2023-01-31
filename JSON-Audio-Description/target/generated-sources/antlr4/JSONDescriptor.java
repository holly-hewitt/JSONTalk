import java.util.LinkedHashMap;
import java.util.List;


public class JSONDescriptor extends jsonBaseVisitor<String> {
	
	private String description;
	
	@Override
	public String visitJson(jsonParser.JsonContext ctx) {
		description = "";
		System.out.println("visitingJson...");
		visitChildren(ctx);
		return description;
	}
	
    @Override
    public String visitPair(jsonParser.PairContext ctx) {
    	System.out.println("visitingPair...");
        String key = ctx.STRING().getText();
        String value = visit(ctx.value());
        description += key + " is " + value;
        return visitChildren(ctx);
        //return key + " is " + value;
    }
    
    @Override
    public String visitValue(jsonParser.ValueContext ctx) {
    	System.out.println("visitingValue...");
    	System.out.println(ctx.obj());
    	visitChildren(ctx);
        if (ctx.obj() != null) {
            return "an object";
        } else if (ctx.arr() != null) {
            return "an array";
        } else if (ctx.STRING() != null) {
            return ctx.STRING().getText();
        } else if (ctx.NUMBER() != null) {
            return ctx.NUMBER().getText();
        } else if (ctx.getText().equals("true")) {
            return "true";
        } else if (ctx.getText().equals("false")) {
            return "false";
        } else {
            return "null";
        }
    }
    
    @Override
    public String visitObj(jsonParser.ObjContext ctx) {
    	System.out.println("visitingObj...");
    	visitChildren(ctx);
        List<jsonParser.PairContext> pairs = ctx.pair();
        description += "an object with " + pairs.size() + " key-value pairs: ";
        for (jsonParser.PairContext pair : pairs) {
            description += visit(pair) + ", ";
        }
        // remove the last ", "
        return description.substring(0, description.length() - 2);
    }
    
    @Override
    public String visitArr(jsonParser.ArrContext ctx) {
    	System.out.println("visitingArr...");
    	visitChildren(ctx);
        List<jsonParser.ValueContext> values = ctx.value();
        description += "an array with " + values.size() + " elements: ";
        for (jsonParser.ValueContext value : values) {
            description += visit(value) + ", ";
        }
        // remove the last ", "
        return description.substring(0, description.length() - 2);
    }
}


