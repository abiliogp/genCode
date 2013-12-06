package generator.Android;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.structure.Attribute;
import model.structure.Classe;
import model.structure.Method;
import model.structure.RealizationInterface;
import utilities.Android;
import utilities.Parser;
import utilities.Tool;
import generator.GeneratorStrategy;



public class ClasseAndroid implements GeneratorStrategy{

	private Classe classe;

	private AttributeAndroid generatorAttribute;
	
	public ClasseAndroid(Classe classe) {
		this.classe = classe;
	}

	


	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		System.out.println("strategy classe");
		
		try {
			if (Android.Classes.valueOf(classe.getName()) != null) {
				return;
			}
		} catch (java.lang.IllegalArgumentException ex) {
			File cls = new File("out/" + Parser.getModel().getName(),
					classe.getName().concat(".java"));
			out = new BufferedWriter(new FileWriter(cls));
			// Pacote
			if (classe.pacote != null) {
				out.write("package " + classe.pacote.getName() + ";\n");
				if (classe.pacote.getAssocPacote() != null) {
					out.write("import " + classe.pacote.getAssocPacote().getSupplier()
							+ ".*;\n");
				}
			}
			// Imports
			if(classe.generalActivity) {
				out.write("import android.app.Activity;\n");
			}
			if (classe.needImport) {
				out.write("import java.util.ArrayList;\n");
			}
			
			for(Attribute stereoType : classe.getStereotypes()){
				stereoType.genCodeImports(out);
			}
						
			genInnerClass(classe, out, 0);
			out.close();
		}
	}
	
	

	/**
	 * @param out
	 * @param tab
	 * @throws IOException
	 */
	public void genInnerClass(Classe classe, BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		// Name Class and General
		out.write("\n" + tabInd + classe.getVisibility()
				+ (classe.isAbstract() == true ? " abstract " : " ") + "class " + classe.getName()
				+ (classe.general != null ? " extends " + classe.general : ""));

		// Implements
		if (!classe.getRealInterfaces().isEmpty()) {
			out.write(" implements ");
			int i = 0;
			for (RealizationInterface realization : classe.getRealInterfaces()) {
				out.write(realization.getNameSupplier());
				if (i++ < classe.getRealInterfaces().size() - 1) {
					out.write(", ");
				}
			}
		}

		out.write("{");

		// Atributos
		if (classe.getAttributes().size() > 0) {
			out.write("\n" + tabInd + "\t/**Attributes */");
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeAndroid(atr);
				generatorAttribute.codeGenerator(out, tab + 1);
			}
		}

		// Atributos Return dos Métodos
//		for (int i = 0; i < listMethod.size(); i++) {
//			if (!((listMethod.get(i).getName().substring(0, 3).equals("get")) || (listMethod
//					.get(i).getName().substring(0, 3).equals("set")))) {
//				if (listMethod.get(i).getListReturn().size() > 0) {
//					out.write("\n" + tabInd + "/**Attribute of Return Method "
//							+ listMethod.get(i).getName() + " */");
//					for (int j = 0; j < listMethod.get(i).getListReturn()
//							.size(); j++) {
//						listMethod.get(i).getListReturn().get(j)
//								.genCodeReturn(out);
//					}
//				}
//			}
//		}
		
		
		// Atributtes from Interface
		for (RealizationInterface realization : classe.getRealInterfaces()) {
			realization.genCodeAtributte(out);
		}

		// Construtor
		if (!(classe.isAbstract())) {
			out.write("\n\n" + tabInd + "\t/** Constructor */");
			out.write("\n" + tabInd + "\tpublic " + classe.getName() + "(");
			int i=0;
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeAndroid(atr);
				generatorAttribute.generatorConstructorSignature(out);
				if (i++ < classe.getAttributes().size() - 1) {
					out.write(",");
				}
			}
			out.write("){");
			if (classe.general != null) {
				out.write("\n" + tabInd + "\t\tsuper();");
			}
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeAndroid(atr);
				generatorAttribute.generatorConstructor(out);
			}
			out.write("\n" + tabInd + "\t}\n");
		}


		// Get
		if (classe.needGetSet) {
			out.write("\n" + tabInd + "\t/** Get */");
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeAndroid(atr);
				generatorAttribute.generatorGet(out, tab+1);
			}
		}

		// Set
		if (classe.needGetSet) {
			out.write("\n" + tabInd + "\t/** Set */");
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeAndroid(atr);
				generatorAttribute.genCodeSet(out,tab+1);
			}
		}

		// Metodo 
		if (classe.getMethods().size() > 0) {
			out.write("\n" + tabInd + "\t/** Methods */");
			for (Method met : classe.getMethods()) {
				try {
					if (Android.Methods.valueOf(met.getName()) != null) {
						met.genCodeAndroid(classe.getName(), out, tab + 1);
					}
				} catch (java.lang.IllegalArgumentException ex) {
					met.genCode(out, tab + 1);
				}
			}
		}

		// Métodos da Super
		if (classe.general != null) {
			if ((Tool.containsKeyTrieAbstractMethod(classe.general))) {
				for (int i = 0; i < Tool.getTrieAbstractMethod(classe.general).size(); i++) {
					Tool.getTrieAbstractMethod(classe.general).get(i)
							.genCodeMtSuper(out, tab + 1);
				}
			}
		}

		// Methods form Interface
		for (RealizationInterface realization : classe.getRealInterfaces()) {
			realization.genCodeMethods(out);
		}
		

		//Inner Class
		for (Classe inner : classe.getInnerClasses()) {
			genInnerClass(inner,out, tab + 1);
		}
		
		out.write("\n" + tabInd + "}");

	}


	

}
