
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
		
		boolean[] topLevelOptions = {true, false, false};
		boolean[] objectsOptions = {true, false, false};
		boolean[] fullOptions = {false, false, true};
		
		
		if (topLevel) {
			System.out.println("\nTop level description: \n");
			finalDescription += "Top level description: ";
			finalDescription += generateDescription1(descriptionLevel.TOPLEVEL, elements, depth, true);
		}
		
		if(objects) {
			System.out.println("\nDescription including object and array details: \n");
			finalDescription += "Description including object and array details: ";
			finalDescription += generateDescription1(descriptionLevel.COMPLEXELEMENTS, elements, depth, false);
		}
		
		if(full) {
			System.out.println("\nFull description: \n");
			finalDescription += "Full description: ";
			finalDescription += generateDescription1(descriptionLevel.FULL, elements, depth, false);
		}
		
		return finalDescription;
		
		// TextToSpeech.SpeakString(finalDescription);
	}
	
	private static String generateDescription(boolean a, boolean b, boolean c, Collection<jsonComplexElement> x, int depth, boolean toplevel) {
		String description = "";
		for (jsonComplexElement object : x) {
			if (!object.elementDescription(a, b, c).equals("")) {				
				if (object.getDepth()<= depth) {
					System.out.println(object.elementDescription(a, b, c));
					description += object.elementDescription(a, b, c);
				}
			
			}
			if (toplevel) {
				break;
			}
		}
		return description;
	}
	private static String generateDescription1(descriptionLevel l, Collection<jsonComplexElement> x, int depth, boolean toplevel) {
		String description = "";
		for (jsonComplexElement object : x) {
			if (!object.elementDescription1(l).equals("")) {				
				if (object.getDepth()<= depth) {
					System.out.println(object.elementDescription1(l));
					description += object.elementDescription1(l);
				}
			
			}
			if (toplevel) {
				break;
			}
		}
		return description;
	}
	

}
