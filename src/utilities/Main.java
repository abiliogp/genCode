package utilities;



import java.io.IOException;

import sequence.Interaction;



public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String inputFileName = "model.uml";
		inputFileName = "in/" + inputFileName;

		//Parser.loadXMI("UML4Java");

		Parser.loadXMI(inputFileName);

		Parser.runParser(inputFileName);

		Parser.getModel().printProp();
		
		Parser.getModel().genCode();
		
		//Interaction.printProp();
		

	}
	
}// end class