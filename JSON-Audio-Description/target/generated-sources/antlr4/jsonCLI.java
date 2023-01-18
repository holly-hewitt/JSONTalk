


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        try {
        	if (filename==null)
				throw new jsonException();
			CommonTokenStream tokens = jsonRun.lex(filename);
			ParseTree tree = jsonRun.parse(tokens);
			String description = jsonRun.describe(tokens, tree, topLevel, objects, full, depth);
			
			if (outputFile != null) {
				writeDescriptionToFile(description, outputFile);
			}
        } catch (jsonException x) {
			System.out.println("Check input");
		} catch (Exception x) {
			x.printStackTrace();
		}
        
    }
    
    static void writeDescriptionToFile(String finalDescription, String outputFile) throws IOException {
        File file = new File(outputFile);
        FileWriter writer = new FileWriter(file);
        writer.write(finalDescription);
        writer.close();
        System.out.println("file path: " + file.getAbsolutePath());
        System.out.println("Description written to " + outputFile);
    }

    public static void main(String[] args) {
    	System.exit(new CommandLine(new jsonCLI()).execute(args));
        //CommandLine.execute(new jsonCLI(), args);
    	
    }
}
