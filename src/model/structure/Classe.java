package model.structure;

import generator.GeneratorStrategy;
import generator.Android.ClasseAndroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.sequence.Interaction;
import utilities.Android;
import utilities.Parser;
import utilities.Tool;

public class Classe extends DataStructure {

	private GeneratorStrategy generator;

	public Pacote pacote;
	public String general;

	public boolean ativa;

	public ArrayList<Classe> listInnerClass;
	private ArrayList<Attribute> attributes;
	private ArrayList<Method> methods;
	public ArrayList<Associacao> listAssociacao;
	public ArrayList<Operation> listOperacao;
	public ArrayList<Interaction> listInteraction;
	public ArrayList<RealizationInterface> listRealInter;
	public ArrayList<Attribute> listStereotype;

	public boolean needImport;
	public boolean needGetSet;

	public boolean generalActivity;

	public Classe(String name) {
		super(name);
		ativa = false;
		general = null;
		listInnerClass = new ArrayList<Classe>();
		attributes = new ArrayList<Attribute>();
		listStereotype = new ArrayList<Attribute>();
		methods = new ArrayList<Method>();
		listAssociacao = new ArrayList<Associacao>();
		listOperacao = new ArrayList<Operation>();
		listInteraction = new ArrayList<Interaction>();
		listRealInter = new ArrayList<RealizationInterface>();
	}

	// Operation
	public void addOperation(Operation operation) {

		this.listOperacao.add(operation);
	}

	// Atributo
	public void addAtributo(String name, String key) {
		Attribute atributte = new Attribute(name);
		this.attributes.add(atributte);
		Tool.putTrieAtributte(key, atributte);
	}

	// Metodo
	public void addMetodo(String name, String key) {
		Method metodo = new Method(name);
		this.methods.add(metodo);
		Tool.putTrieMetodo(key, metodo);
	}

	/*
	 * Interaction
	 */
	public void addInteraction(Interaction interaction) {
		listInteraction.add(interaction);
	}
	
	// Associação
	public void addAssociacao(String nameAssoc) {
		this.listAssociacao.add(new Associacao(nameAssoc));
	}

	public void addAssociacao(Associacao assoc) {
		this.listAssociacao.add(assoc);
	}
	
	
	public void setActive(boolean ativa) {
		this.ativa = ativa;
	}

	public void setNeedImport(boolean needImport) {
		this.needImport = needImport;
	}

	public void setNeedGetSet(boolean needGetSet) {
		this.needGetSet = needGetSet;
	}
	
	// General
	public void setGeneral(String general) {
		this.general = general;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}


	public boolean isActive() {
		return this.ativa;
	}

	public Operation getLastOperation() {
		return this.listOperacao.get(this.listOperacao.size() - 1);
	}

	public Operation getIndexOfListOperacao(int index) {

		return this.listOperacao.get(index);
	}

	public Attribute getLastAtributo() {

		return this.attributes.get(this.attributes.size() - 1);
	}

	public Attribute getIndexOfAtributo(int index) {
		return this.attributes.get(index);
	}

	public ArrayList<Attribute> getAttributes(){
		return this.attributes;
	}
	
	public Method getLastMetodo() {
		return this.methods.get(this.methods.size() - 1);
	}

	public Method getIndexOfMetodo(int index) {
		return this.methods.get(index);
	}
	
	public ArrayList<Method> getMethods(){
		return this.methods;
	}

	public Associacao getLastAssociacao() {
		return this.listAssociacao.get(this.listAssociacao.size() - 1);
	}

	public Associacao getIndexOfAssociacao(int index) {
		return this.listAssociacao.get(index);
	}

	public String getGeneral() {
		return this.general;
	}

	public Pacote getPacote() {
		return this.pacote;
	}

	

	public void printProp() {

		if (this.pacote != null) {
			System.out.println("Pacote: " + this.pacote.getName());
			this.pacote.printProp();
		}
		System.out.println("Classe: " + this.name);
		System.out.println("\tVisibilidade: " + this.visibility);
		if (this.isAbstract) {
			System.out.println("\tClasse Abstrata");
		}
		if (this.ativa) {
			System.out.println("\tClasse Ativa");
		}
		if (this.general != null) {
			System.out.printf("\tSuper Classe %s \n", this.general);
		}

		for (int i = 0; i < this.listInnerClass.size(); i++) {
			System.out.println("Inner Class of " + name);
			this.listInnerClass.get(i).printProp();
		}

		for (int i = 0; i < this.attributes.size(); i++) {
			this.attributes.get(i).printProp();
		}

		for (int i = 0; i < this.methods.size(); i++) {
			this.methods.get(i).printProp();
		}

		for (int i = 0; i < this.listAssociacao.size(); i++) {
			this.listAssociacao.get(i).printProp();
		}

		for (int i = 0; i < this.listInteraction.size(); i++) {
			this.listInteraction.get(i).printProp();
		}

	}

	public void genCode() throws IOException {

		generator = new ClasseAndroid(this);
		generator.codeGenerator();
	}

	//
	public void genCode2() throws IOException {
		try {
			if (Android.Classes.valueOf(name) != null) {
				return;
			}
		} catch (java.lang.IllegalArgumentException ex) {
			File cls = new File(Parser.getModel().getName(),
					name.concat(".java"));
			BufferedWriter out = new BufferedWriter(new FileWriter(cls));
			// Pacote
			if (pacote != null) {
				out.write("package " + pacote.getName() + ";\n");
				if (this.pacote.getAssocPacote() != null) {
					out.write("import " + pacote.getAssocPacote().getSupplier()
							+ ".*;\n");
				}
			}
			// Imports
			if (this.generalActivity) {
				out.write("import android.app.Activity;\n");
			}
			if (needImport) {
				out.write("import java.util.ArrayList;\n");
			}
			if (!listStereotype.isEmpty()) {
				for (int i = 0; i < listStereotype.size(); i++) {
					listStereotype.get(i).genCodeImports(out);
				}
			}

			genInnerClass(out, 0);
			out.close();
		}
	}

	/**
	 * @param out
	 * @param tab
	 * @throws IOException
	 */
	public void genInnerClass(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		// Name Class and General
		out.write("\n" + tabInd + visibility
				+ (this.isAbstract == true ? " abstract " : " ") + "class "
				+ name + (general != null ? " extends " + general : ""));

		// Implements
		if (listRealInter.size() > 0) {
			out.write(" implements ");
			for (int i = 0; i < listRealInter.size(); i++) {
				out.write(listRealInter.get(i).getNameSupplier());
				if (i < listRealInter.size() - 1) {
					out.write(", ");
				}
			}
		}

		out.write("{");

		// Atributos
		if (this.attributes.size() > 0) {
			out.write("\n" + tabInd + "\t/**Attributes */");
			for (int i = 0; i < attributes.size(); i++) {
				this.attributes.get(i).genCode(out, tab + 1);
			}
		}

		// Atributos Return dos Métodos
		// for (int i = 0; i < listMethod.size(); i++) {
		// if (!((listMethod.get(i).getName().substring(0, 3).equals("get")) ||
		// (listMethod
		// .get(i).getName().substring(0, 3).equals("set")))) {
		// if (listMethod.get(i).getListReturn().size() > 0) {
		// out.write("\n" + tabInd + "/**Attribute of Return Method "
		// + listMethod.get(i).getName() + " */");
		// for (int j = 0; j < listMethod.get(i).getListReturn()
		// .size(); j++) {
		// listMethod.get(i).getListReturn().get(j)
		// .genCodeReturn(out);
		// }
		// }
		// }
		// }

		// Atributtes from Interface
		for (int i = 0; i < listRealInter.size(); i++) {
			listRealInter.get(i).genCodeAtributte(out);
		}

		// Construtor
		if (!(this.isAbstract)) {
			out.write("\n\n" + tabInd + "\t/** Constructor */");
			out.write("\n" + tabInd + "\tpublic " + name + "(");
			for (int i = 0; i < attributes.size(); i++) {
				attributes.get(i).genCodeConstructorSignature(out);
				if (i < attributes.size() - 1) {
					out.write(",");
				}
			}
			out.write("){");
			if (general != null) {
				out.write("\n" + tabInd + "\t\tsuper();");
			}
			for (int i = 0; i < attributes.size(); i++) {
				this.attributes.get(i).genCodeConstructor(out);
			}
			out.write("\n" + tabInd + "\t}\n");
		}

		// Get
		if (needGetSet) {
			out.write("\n" + tabInd + "\t/** Get */");
			for (int i = 0; i < attributes.size(); i++) {
				attributes.get(i).genCodeGet(out, tab + 1);
			}
		}

		// Set
		if (needGetSet) {
			out.write("\n" + tabInd + "\t/** Set */");
			for (int i = 0; i < attributes.size(); i++) {
				attributes.get(i).genCodeSet(out, tab + 1);
			}
		}

		// Metodo
		if (methods.size() > 0) {
			out.write("\n" + tabInd + "\t/** Methods */");
			for (int i = 0; i < this.methods.size(); i++) {
				try {
					if (Android.Methods.valueOf(methods.get(i).getName()) != null) {
						methods.get(i).genCodeAndroid(name, out, tab + 1);
					}
				} catch (java.lang.IllegalArgumentException ex) {
					methods.get(i).genCode(out, tab + 1);
				}
			}
		}

		// Métodos da Super
		if (general != null) {
			if ((Tool.containsKeyTrieAbstractMethod(general))) {
				for (int i = 0; i < Tool.getTrieAbstractMethod(general).size(); i++) {
					Tool.getTrieAbstractMethod(general).get(i)
							.genCodeMtSuper(out, tab + 1);
				}
			}
		}

		// Methods form Interface
		for (int i = 0; i < listRealInter.size(); i++) {
			listRealInter.get(i).genCodeMethods(out);
		}

		// Inner Class
		for (int i = 0; i < listInnerClass.size(); i++) {
			listInnerClass.get(i).genInnerClass(out, tab + 1);
		}

		out.write("\n" + tabInd + "}");

	}

	public void parser(BufferedReader bf, String line) throws IOException {
		String key, value;
		if (line.contains("visibility=")) {
			value = Tool.manipulate(line, "visibility=");
			visibility = value;
		}

		if (line.contains("isAbstract=")) {
			this.isAbstract(true);
		}

		if (line.contains("classifierBehavior=")) {
			value = Tool.manipulate(line, "classifierBehavior=");
			for (int i = 0; i < value.length(); i = +23) {
				key = value.substring(i, i + 23);
				this.listOperacao.add(Tool.getTrieOperation(key));
			}
		}

		if (line.contains("isActive=")) {
			ativa = true;
		}

		if (line.contains("/>")) {
			line = "</packagedElement";
		} else {
			for (line = bf.readLine(); !((line.contains("</packagedElement")) || (line
					.contains("</nestedClassifier"))); line = bf.readLine()) {
				if (line.contains("<generalization")) {
					key = Tool.manipulate(line, "general=");
					value = Tool.getTrieID(key);
					general = value;
					generalActivity = general.equals("Activity");
				}

				if (line.contains("<nestedClassifier")) {
					key = Tool.manipulate(line, "xmi:id=");
					value = Tool.manipulate(line, "name=");
					if (line.contains("uml:Class")) {
						Classe innerClass = new Classe(value);
						listInnerClass.add(innerClass);
						innerClass.parser(bf, line);
					}
					if (line.contains("uml:Stereotype")) {
						Attribute stereotype = new Attribute(value);
						listStereotype.add(stereotype);
						Tool.putTrieAtributte(key, stereotype);
						if (!line.contains("/>")) {
							for (line = bf.readLine(); !line
									.contains("</nestedClassifier"); line = bf
									.readLine()) {
							}
						}
					}
				}

				/*
				 * Atributo
				 */
				if (line.contains("<ownedAttribute")) {
					key = Tool.manipulate(line, "xmi:id=");
					Attribute atributte = Tool.getTrieAtributte(key);
					attributes.add(atributte);
					needImport = atributte.parser(bf, line);
				}
				/*
				 * Método
				 */
				if (line.contains("<ownedOperation")) {
					key = Tool.manipulate(line, "xmi:id=");
					Method metodo = Tool.getTrieMetodo(key);
					methods.add(metodo);
					metodo.parser(bf, line);
				}

				if (line.contains("uml:Interaction")) {// nome da classe
					key = Tool.manipulate(line, "xmi:id=");
					Interaction interaction = Tool.getTrieInteraction(key);
					listInteraction.add(interaction);
					interaction.parser(bf, line);
				}

				if (line.contains("<interfaceRealization")) {
					value = Tool.manipulate(line, "name");
					RealizationInterface realInter = new RealizationInterface(
							value);
					listRealInter.add(realInter);
					realInter.parser(bf, line);
				}
			}
		}// end for PackagedElement
	}// end class parserClass

}
