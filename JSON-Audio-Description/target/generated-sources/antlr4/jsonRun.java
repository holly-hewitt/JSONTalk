
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
			describe(tokens, tree, false, false, false, false, 4);
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

	static void describe(CommonTokenStream tokens, ParseTree tree, boolean topLevel, boolean objects, 
			boolean full,int depth) {
		System.out.println("\nStructural description: ");
		jsonDescriptorVisitor3<?> descriptor = new jsonDescriptorVisitor3<Object>();
		descriptor.visit(tree);
		String finalDescription = "";
		if (topLevel) {
			System.out.println("Top level description: ");
			for (jsonComplexElement object : jsonDescriptorVisitor3.objects.values()) {
				if (!object.elementDescription(true, false).equals("")) {
					if(object.getDepth()<= depth) {
						System.out.println(object.elementDescription(true, false));
						finalDescription += object.elementDescription(true, false);
						break;
					}
					
				}
			}
		}if (objects) {
			System.out.println("Description including object and array details: ");
			for (jsonComplexElement object : jsonDescriptorVisitor3.objects.values()) {
				if (!object.elementDescription(true, false).equals("")) {
					if (object.getDepth()<= depth) {
						System.out.println(object.elementDescription(true, false));
						finalDescription += object.elementDescription(true, false);
					}
					
				}
			}
		}if(full) {
			System.out.println("Full description: ");
			for (jsonComplexElement object : jsonDescriptorVisitor3.objects.values()) {
				if (!object.elementDescription(true, false).equals("")) {
					System.out.println("Depth: " + depth + " Object depth: " + object.getDepth());
					System.out.println("name" + object.getName());
					
					if (object.getDepth()<= depth) {
						System.out.println(object.elementDescription(true, true));
						finalDescription += object.elementDescription(true, true);
					}
				
				}
			}
		}
		
		// TextToSpeech.SpeakString(finalDescription);
	}

	// elementDescription(true, true) or (true, false) !!!!!!!!!!!!FULL
	// = This json file is an object which contains 6 fields. 1 field is a boolean
	// value, named: "married". 1 field is a string value, named: "name". 1 field is
	// a null value, named: "dietary_requirements". 1 field is a array value, named:
	// "siblings". 1 field is a integer value, named: "age". 1 field is a object
	// value, named: "address".
	// "address" is an object which contains 2 fields. 2 fields are string values,
	// named: "town", "postcode".
	// "siblings" is an Array which contains 2 fields. 2 fields are object values. 2
	// objects are of the same structure.

	// elementDescription(false, true)!!!!!!!!!! describes object and array structures within
	// = This json file is an object which contains 6 fields. 1 field is an object,
	// 1 field is an array,
	// "address" is an object which contains 2 fields.
	// "siblings" is an Array which contains 2 fields. 2 fields are objects,

	static void TopLevelDescription(CommonTokenStream tokens, ParseTree tree) {
		jsonDescriptorVisitor3<?> descriptor = new jsonDescriptorVisitor3<Object>();
		descriptor.visit(tree);
		String finalDescription = "";
	}

}
