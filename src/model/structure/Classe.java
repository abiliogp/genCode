package model.structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Model;
import model.sequence.Interaction;
import utilities.Tool;

public class Classe extends DataStructure {

	/*Attributes*/
	public Pacote pacote;
	private String general;

	public boolean active;

	private ArrayList<Classe> innerClasses;
	
	private ArrayList<Attribute> attributes;
	private ArrayList<Attribute> stereotypes;
	private ArrayList<Method> methods;
	
	public ArrayList<Associacao> listAssociacao;
	public ArrayList<Operation> listOperacao;
	public ArrayList<Interaction> listInteraction;
	private ArrayList<RealizationInterface> realInterfaces;
	
	public boolean needImport;
	public boolean needGetSet;

	/*Constructor*/
	public Classe(String name) {
		super(name);
		general = "";
		innerClasses = new ArrayList<Classe>();
		attributes = new ArrayList<Attribute>();
		stereotypes = new ArrayList<Attribute>();
		methods = new ArrayList<Method>();
		listAssociacao = new ArrayList<Associacao>();
		listOperacao = new ArrayList<Operation>();
		listInteraction = new ArrayList<Interaction>();
		realInterfaces = new ArrayList<RealizationInterface>();
	}

	/*Add*/
	public void addOperation(Operation operation) {
		this.listOperacao.add(operation);
	}

	public void addAttribute(String name, String key) {
		Attribute atributte = new Attribute(name);
		this.attributes.add(atributte);
		Model.putTrieAtributte(key, atributte);
	}

	public void addMethod(String name, String key) {
		Method metodo = new Method(name);
		this.methods.add(metodo);
		Model.putTrieMetodo(key, metodo);
	}

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
	
	/*set*/
	public void setName(String name){
		this.name = name;
	}
	
	public void setActive(boolean ativa) {
		this.active = ativa;
	}

	public void setNeedImport(boolean needImport) {
		this.needImport = needImport;
	}

	public void setNeedGetSet(boolean needGetSet) {
		this.needGetSet = needGetSet;
	}
	
	public void setGeneral(String general) {
		this.general = general;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	/*Get*/
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
	
	public ArrayList<Attribute> getStereotypes(){
		return this.stereotypes;
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
	
	public ArrayList<Classe> getInnerClasses(){
		return this.innerClasses;
	}

	public ArrayList<RealizationInterface> getRealInterfaces(){
		return this.realInterfaces;
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

	public boolean isActive() {
		return this.active;
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
		if (this.active) {
			System.out.println("\tClasse Ativa");
		}
		if (this.general != null) {
			System.out.printf("\tSuper Classe %s \n", this.general);
		}

		for (int i = 0; i < this.innerClasses.size(); i++) {
			System.out.println("Inner Class of " + name);
			this.innerClasses.get(i).printProp();
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
				this.listOperacao.add(Model.getTrieOperation(key));
			}
		}

		if (line.contains("isActive=")) {
			active = true;
		}

		if (line.contains("/>")) {
			line = "</packagedElement";
		} else {
			for (line = bf.readLine(); !((line.contains("</packagedElement")) || (line
					.contains("</nestedClassifier"))); line = bf.readLine()) {
				if (line.contains("<generalization")) {
					key = Tool.manipulate(line, "general=");
					value = Model.getTrieID(key);
					general = value;
				}

				if (line.contains("<nestedClassifier")) {
					key = Tool.manipulate(line, "xmi:id=");
					value = Tool.manipulate(line, "name=");
					if (line.contains("uml:Class")) {
						Classe innerClass = new Classe(value);
						innerClasses.add(innerClass);
						innerClass.parser(bf, line);
					}
					if (line.contains("uml:Stereotype")) {
						Attribute stereotype = new Attribute(value);
						stereotypes.add(stereotype);
						Model.putTrieAtributte(key, stereotype);
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
					Attribute atributte = Model.getTrieAtributte(key);
					attributes.add(atributte);
					needImport = atributte.parser(bf, line);
				}
				/*
				 * Método
				 */
				if (line.contains("<ownedOperation")) {
					key = Tool.manipulate(line, "xmi:id=");
					Method metodo = Model.getTrieMetodo(key);
					methods.add(metodo);
					metodo.parser(bf, line);
				}

				if (line.contains("uml:Interaction")) {// nome da classe
					key = Tool.manipulate(line, "xmi:id=");
					Interaction interaction = Model.getTrieInteraction(key);
					listInteraction.add(interaction);
					interaction.parser(bf, line);
				}

				if (line.contains("<interfaceRealization")) {
					value = Tool.manipulate(line, "name");
					RealizationInterface realInter = new RealizationInterface(
							value);
					realInterfaces.add(realInter);
					realInter.parser(bf, line);
				}
			}
		}// end for PackagedElement
	}// end class parserClass

}
