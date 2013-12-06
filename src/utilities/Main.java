package utilities;



import generator.GeneratorStrategy;
import generator.Android.ModelAndroid;

import java.io.IOException;

import model.Model;
import model.sequence.Interaction;



public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String inputFileName = "model.uml";
		inputFileName = "in/" + inputFileName;

		GeneratorStrategy generator;
		//Parser.loadXMI("UML4Java");
		

		Parser.loadXMI(inputFileName);

		Parser.runParser(inputFileName);

		Parser.getModel().printProp();
		
		generator = new ModelAndroid(Parser.getModel());
		generator.codeGenerator(null, 0);
		//Interaction.printProp();
		

	}
	
}// end class