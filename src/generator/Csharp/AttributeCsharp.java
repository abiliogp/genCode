package generator.Csharp;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.structure.Attribute;
import generator.GeneratorStrategy;

public class AttributeCsharp implements GeneratorStrategy {

	private Attribute attribute;
	private String tabInd;

	public AttributeCsharp(Attribute attribute) {
		this.attribute = attribute;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd + attribute.getVisibility() + " "
				+ attribute.getType() + " " + attribute.getName() + ";");
	}

	public void generatorGet(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd + "public "
				+ attribute.getType() + " "
				+ attribute.getName().substring(0, 1).toUpperCase()
						.concat(attribute.getName().substring(1)) + 
						"{ get; set;}");
	}

}
