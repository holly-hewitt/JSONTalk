


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * This class is responsible for parsing the command line options and arguments and producing the required description(s)
 *
 */
@Command(name = "jsonCLI", mixinStandardHelpOptions = true)
public class jsonCLI implements Runnable {

    @Option(names = {"-tl", "--Toplevel"}, description = "Generate top level description of JSON file")
    private boolean topLevel;

    @Option(names = {"-oa", "--objectsAndArrays"}, description = "Generate description of object and array fields within JSON file")
    private boolean objects;
    
    @Option(names = {"-f", "--full"}, description = "Generate full description of JSON file")
    private boolean full;
    
    @Option(names = {"-d", "--depth"}, description = "Specify depth of nesting that description covers")
    private int depth = 1000;
    
    @Option(names = {"-o", "--outputFile"}, description = "Write description to output file")
	private String outputFile;
    
    @Option(names = {"-r", "--readAloud"}, description = "Read description aloud")
    private boolean readAloud;
    
    @Option(names = {"-n", "--nesting"}, description = "Specify nesting for each value described")
	private boolean nesting;

    @Parameters(paramLabel = "filepath", description = "Input JSON file. Absolute file path")
    private String filename;

    /**
     * Passes the command line arguments to the jsonRun.describe method
     * If an output file is specified, writes description to output file location
     * If the readAloud option is enabled, the TextToSpeech.speakString() method is invoked
     */
    public void run() {
        try {
        	if (filename==null || depth==0)
				throw new jsonException();
        	
        	CommonTokenStream tokens = jsonRun.lex(filename);
			ParseTree tree = jsonRun.parse(tokens);
			String description = jsonRun.describe(tokens, tree, topLevel, objects, full, depth, nesting);
			
			if (outputFile != null) {
				writeDescriptionToFile(description, outputFile);
			}
			if (readAloud) {
				TextToSpeech.SpeakString(description);
			}
        } catch (jsonException x) {
			System.out.println("Check input");
		} catch (Exception x) {
			x.printStackTrace();
		}
        
    }
    
    /**
     * Writes finalDescription to outputFile location
     * @param finalDescription
     * @param outputFile
     * @throws IOException
     */
    private static void writeDescriptionToFile(String finalDescription, String outputFile) throws IOException {
        File file = new File(outputFile);
        FileWriter writer = new FileWriter(file);
        writer.write(finalDescription);
        writer.close();
        System.out.println("\nDescription written to " + outputFile + " at location: \n" + file.getAbsolutePath());
    }

    /**
     * Parses command line arguments and creates new command line object
     * @param args
     */
    public static void main(String[] args) {
    	System.exit(new CommandLine(new jsonCLI()).execute(args));
    	
    }
}
