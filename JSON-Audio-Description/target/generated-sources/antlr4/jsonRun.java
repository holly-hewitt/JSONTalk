
import org.antlr.v4.runtime.*;

import org.antlr.v4.runtime.tree.*;
import java.io.*;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class jsonRun {

	public static void jsonRunMain(String[] args) {
		System.out.print(args[0]);
		try {
			if (args.length == 0)
				throw new jsonException();

			CommonTokenStream tokens = lex(args[0]);
			ParseTree tree = parse(tokens);
			describe(tokens, tree, false, false, false, false);
		} catch (jsonException x) {
			System.out.println("Check input");
		} catch (Exception x) {
			x.printStackTrace();
		}

	}

	static CommonTokenStream lex(String filename) {
		try {
			jsonLexer lexer = new jsonLexer(CharStreams.fromFileName(filename));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			return tokens;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	static ParseTree parse(CommonTokenStream tokens) {
		jsonParser parser = new jsonParser(tokens);
		ParseTree tree = parser.json();
		return tree;
	}

	static void describe(CommonTokenStream tokens, ParseTree tree, boolean topLevel, boolean objects, boolean arrays, boolean full) {
		System.out.println("\nStructural description: ");
		jsonDescriptorVisitor3<?> descriptor = new jsonDescriptorVisitor3<Object>();
		descriptor.visit(tree);
		String finalDescription = "";
		for (jsonComplexElement object : jsonDescriptorVisitor3.objects.values()) {
			if (!object.elementDescription(full).equals("")) {
				System.out.println(object.elementDescription(full));
				finalDescription += object.elementDescription(full);
				
			}
		}
		//TextToSpeech.SpeakString(finalDescription);
	}

}



