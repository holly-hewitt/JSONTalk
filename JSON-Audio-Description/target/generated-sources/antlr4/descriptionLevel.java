
/**
 * This enum assists the handling of command line arguments. It is more favourable over lots of boolean flags set to true or false.
 *
 */
public enum descriptionLevel {
	
		/**
		 * descriptionLevel is set to TOPLEVEL if the topLevel option has been specified on the command line interface
		 */
		TOPLEVEL,
		/**
		 * descriptionLevel is set to COMPLEXELEMENTS if the objectsAndArrays option has been specified on the command line interface
		 */
		COMPLEXELEMENTS,
		/**
		 * descriptionLevel is set to FULL if the full option has been specified on the command line interface
		 */
		FULL,
		/**
		 * descriptionLevel is set to NESTING if the nesting option has been specified on the command line interface
		 */
		NESTING,
		/**
		 * descriptionLevel is set to NO_NESTING if the nesting option has not been specified on the command line interface
		 */
		NO_NESTING
	
}
