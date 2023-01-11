


import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "mycli", mixinStandardHelpOptions = true, version = "mycli 1.0")
public class jsonCLI implements Runnable {

    @Option(names = {"--tl"}, description = "Top level description of JSON file")
    private boolean topLevel;

    @Option(names = {"--o"}, description = "Description of object fields within JSON file")
    private boolean objects;

    @Option(names = {"--option3"}, description = "Option 3")
    private boolean array;

    @Parameters(arity = "l", description = "File name")
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
			jsonRun.describe(tokens, tree);
        } catch (jsonException x) {
			System.out.println("Check input");
		} catch (Exception x) {
			x.printStackTrace();
		}
        
    }

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        CommandLine.run(new jsonCLI(), args);
    }
}
