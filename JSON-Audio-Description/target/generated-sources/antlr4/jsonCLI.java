


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

    @Option(names = {"-o", "--objects"}, description = "Description of object fields within JSON file")
    private boolean objects;

    @Option(names = {"-a", "--array"}, description = "Description of array fields within JSON file")
    private boolean array;
    
    @Option(names = {"-f", "--full"}, description = "Full description of JSON file")
    private boolean full;

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
			jsonRun.describe(tokens, tree, topLevel, objects, array, full);
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
