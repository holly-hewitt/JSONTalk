
import org.antlr.v4.runtime.*;

import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

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
			describe(tokens, tree, false,  false, false, 4);
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

	static String describe(CommonTokenStream tokens, ParseTree tree, boolean topLevel, boolean objects, 
			boolean full,int depth) {
		jsonDescriptorVisitor3<?> descriptor = new jsonDescriptorVisitor3<Object>();
		descriptor.visit(tree);
		String finalDescription = "";
		Collection<jsonComplexElement> elements = jsonDescriptorVisitor3.objects.values();
		
		boolean[] topLevelOptions = {true, false};
		boolean[] objectsOptions = {true, false};
		boolean[] fullOptions = {true, true};
		
		
		if (topLevel) {
			System.out.println("\nTop level description: \n");
			finalDescription += "Top level description: ";
			finalDescription += generateDescription(topLevelOptions[0], topLevelOptions[1], elements, depth, true);
		}
		
		if(objects) {
			System.out.println("\nDescription including object and array details: \n");
			finalDescription += "Description including object and array details: ";
			finalDescription += generateDescription(objectsOptions[0], objectsOptions[1], elements, depth, false);
		}
		
		if(full) {
			System.out.println("\nFull description: \n");
			finalDescription += "Full description: ";
			finalDescription += generateDescription(fullOptions[0], fullOptions[1], elements, depth, false);
		}
		
		return finalDescription;
		
		// TextToSpeech.SpeakString(finalDescription);
	}
	
	private static String generateDescription(boolean a, boolean b, Collection<jsonComplexElement> x, int depth, boolean toplevel) {
		String description = "";
		for (jsonComplexElement object : x) {
			if (!object.elementDescription(a, b).equals("")) {				
				if (object.getDepth()<= depth) {
					System.out.println(object.elementDescription(a, b));
					description += object.elementDescription(a, b);
				}
			
			}
			if (toplevel) {
				break;
			}
		}
		return description;
	}

	

}
