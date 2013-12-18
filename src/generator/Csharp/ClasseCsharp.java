package generator.Csharp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utilities.Parser;
import utilities.Tool;
import model.structure.Attribute;
import model.structure.Classe;
import model.structure.Method;
import model.structure.RealizationInterface;
import generator.GeneratorStrategy;
import generator.Android.Android;
import generator.Android.AttributeAndroid;
import generator.Android.MethodAndroid;
import generator.Android.RealizationAndroid;

public class ClasseCsharp implements GeneratorStrategy {

	private Classe classe;
	private String modelName;

	private AttributeCsharp generatorAttribute;
	private MethodCsharp generatorMethod;

	public ClasseCsharp(Classe classe, String modelName) {
		this.classe = classe;
		this.modelName = modelName;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		File cls = new File("out/" + Parser.getModel().getName(), classe
				.getName().concat(".cs"));
		out = new BufferedWriter(new FileWriter(cls));

		out.write("\nnamespace " + modelName + "\n{");

		// Name Class and General
		out.write("\n" + tabInd + classe.getVisibility()
				+ (classe.isAbstract() == true ? " abstract " : " ")
				+ "class " + classe.getName()
				+ (classe.getGeneral() != null ? " : " + classe.getGeneral() : ""));

		out.write("\n" + tabInd + "{");

		// Atributos
		if (classe.getAttributes().size() > 0) {
			out.write("\n" + tabInd + "\t//Attributes");
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeCsharp(atr);
				generatorAttribute.codeGenerator(out, tab + 1);
			}
		}

		// Get
		if (classe.needGetSet) {
			out.write("\n\n" + tabInd + "\t//Get and Set");
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeCsharp(atr);
				generatorAttribute.generatorGet(out, tab + 1);
			}
		}

		// Construtor
		if (!(classe.isAbstract())) {
			out.write("\n\n" + tabInd + "\t//Constructor");
			out.write("\n" + tabInd + "\tpublic " + classe.getName() + "(");
			int i = 0;
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeCsharp(atr);
				generatorAttribute.generatorConstructorSignature(out);
				if (i++ < classe.getAttributes().size() - 1) {
					out.write(",");
				}
			}
			out.write(")\n" +tabInd + "\t{");
			if (classe.getGeneral() != null) {
				out.write("\n" + tabInd + "\t\tsuper();");
			}
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeCsharp(atr);
				generatorAttribute.generatorConstructor(out);
			}
			out.write("\n" + tabInd + "\t}\n");
		}

		
		// Metodo 
		if (classe.getMethods().size() > 0) {
			out.write("\n" + tabInd + "\t//Methods");
			for (Method method : classe.getMethods()) {
				generatorMethod = new MethodCsharp(method);
				generatorMethod.codeGenerator(out, tab + 1);
			}
		}

		
		
		out.write("\n" + tabInd + "}");

		out.write("\n}");

		out.close();
	}

}
