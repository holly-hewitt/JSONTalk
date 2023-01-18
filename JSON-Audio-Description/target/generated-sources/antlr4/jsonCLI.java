


import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "jsonCLI", mixinStandardHelpOptions = true)
public class jsonCLI implements Runnable {

    @Option(names = {"-tl", "--Toplevel"}, description = "Top level description of JSON file")
    private boolean topLevel;

    @Option(names = {"-oa", "--objectsAndArrays"}, description = "Description of object and array fields within JSON file")
    private boolean objects;
    
    @Option(names = {"-f", "--full"}, description = "Full description of JSON file")
    private boolean full;
    
    @Option(names = {"-d", "--depth"}, description = "Depth of nesting described")
    private int depth;
    
    @Option(names = {"-o", "--outputFile"}, description = "File to write description to")
	private String outputFile;

    @Parameters(paramLabel = "filename", description = "File name")
    private String filename;

    public void run() {
        // code to execute when the command is called goes here
        System.out.println("Option 1: " + topLevel);
        System.out.println("Option 2: " + objects);
        System.out.println("Option 3: " + array);
        System.out.println("File name: " + filename);
        try {
        	if (filename==null)
				throw new jsonException();

			CommonTokenStream tokens = jsonRun.lex(filename);
			ParseTree tree = jsonRun.parse(tokens);
			jsonRun.describe(tokens, tree, topLevel, objects, array, full, depth);
			//JSONDescriptor visitor = new JSONDescriptor();
	        //String description = visitor.visit(tree);

	        // Print the description to the console
	        //System.out.println("new description: ");
	        //System.out.println(description);
        } catch (jsonException x) {
			System.out.println("Check input");
		} catch (Exception x) {
			x.printStackTrace();
		}
        
    }

    public static void main(String[] args) {
    	System.exit(new CommandLine(new jsonCLI()).execute(args));
        //CommandLine.execute(new jsonCLI(), args);
    	
    }
}
