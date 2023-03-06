
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
 * This class is responsible for parsing the command line options and arguments
 * and producing the required description(s)
 *
 */
@Command(name = "jsonCLI", mixinStandardHelpOptions = true)
public class jsonCLI implements Runnable {

	/**
	 * Command line option to generate top level description of JSON file. Enabled
	 * using "-tl" or "--Toplevel".
	 */
	@Option(names = { "-tl", "--Toplevel" }, description = "Generate top level description of JSON file")
	protected boolean topLevel;

	/**
	 * Command line option to generate description only including details of the
	 * complex elements within the file (objects and arrays). Enabled using "-oa" or
	 * "--objectsAndArrays".
	 */
	@Option(names = { "-oa",
			"--objectsAndArrays" }, description = "Generate description of object and array fields within JSON file")
	protected boolean objects;

	/**
	 * Command line option to generate a full description of the JSON file,
	 * including values of key-value pairs. Enabled using "-f" or "--full"
	 */
	@Option(names = { "-f", "--full" }, description = "Generate full description of JSON file")
	protected boolean full;

	/**
	 * 
	 */
	@Option(names = { "-d", "--depth" }, description = "Specify depth of nesting that description covers")
	protected int depth = 1000;

	/**
	 * Command line option for the description to be written to a separate text file
	 * for easier use with a screen reader. The file can be specified using
	 * "-o=<output file path>" or "--outputFile=<output file path>"
	 */
	@Option(names = { "-o", "--outputFile" }, description = "Write description to output file")
	protected String outputFile;

	/**
	 * Command line option for the description to be read aloud. Can be enabled with
	 * the flag "-r" or "--readAloud".
	 */
	@Option(names = { "-r", "--readAloud" }, description = "Read description aloud")
	protected boolean readAloud;

	/**
	 * Command line option for the level of nesting to be explicitly specified
	 * before each complex element is described. Can be enabled with the flag "-n"
	 * or "--nesting".
	 */
	@Option(names = { "-n", "--nesting" }, description = "Specify nesting for each value described")
	protected boolean nesting;

	/**
	 * Command line parameter for specifying the input JSON file. The input file is specified with the flag "filepath" = <absolute file path of input file path>
	 */
	@Parameters(paramLabel = "filepath", description = "Input JSON file. Absolute file path")
	protected String filename;

	/**
	 * Passes the command line arguments to the jsonRun.describe method If an output
	 * file is specified, writes description to output file location If the
	 * readAloud option is enabled, the TextToSpeech.speakString() method is invoked
	 */
	public void run() {
		try {
			if (filename == null || depth == 0)
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
	 * 
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
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.exit(new CommandLine(new jsonCLI()).execute(args));

	}
}
