
import org.antlr.v4.runtime.*;

import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class jsonRun {

	public static void main(String[] args) {
		System.out.print(args[0]);
		try {
			if (args.length == 0)
				throw new jsonException();

			CommonTokenStream tokens = lex(args[0]);
			ParseTree tree = parse(tokens);
			describe(tokens, tree);
		} catch (jsonException x) {
			System.out.println("Check input");
		} catch (Exception x) {
			x.printStackTrace();
		}

	}

	private static CommonTokenStream lex(String filename) {
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

	private static ParseTree parse(CommonTokenStream tokens) {
		jsonParser parser = new jsonParser(tokens);
		ParseTree tree = parser.json();
		return tree;
	}

	private static void describe(CommonTokenStream tokens, ParseTree tree) {
		System.out.println("\nStructural description: ");
		jsonDescriptorVisitor3<?> descriptor = new jsonDescriptorVisitor3<Object>();
		descriptor.visit(tree);
		String finalDescription = "";
		for (jsonComplexElement object : jsonDescriptorVisitor3.objects.values()) {
			if (!object.elementDescription(true).equals("")) {
				System.out.println(object.elementDescription(true));
				finalDescription += object.elementDescription(true);
				
			}
		}
		TextToSpeech.SpeakString(finalDescription);
	}

}
