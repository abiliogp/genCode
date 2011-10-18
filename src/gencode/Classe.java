package gencode;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sequence.Interaction;
import utilities.Parser;
import utilities.Tool;

public class Classe {

	private String name;
	
	private Pacote pacote;
	private String general;	
	private String visibility;

	private boolean abstrata;
	private boolean ativa;
	
	private ArrayList<Classe> listInnerClass;
	private ArrayList<Atributte> listAtributte;
	private ArrayList<Method> listMethod;
	private ArrayList<Associacao> listAssociacao;
	private ArrayList<Operation> listOperacao;
	private ArrayList<Interaction> listInteraction;
	private ArrayList<RealizationInterface> listRealInter;
	
	private boolean needImport;
	private boolean needGetSet;

	public Classe(String name) {

		this.name = name;

		visibility = "public";
		abstrata = false;
		ativa = false;
		general = null;
		listInnerClass = new ArrayList<Classe>();
		listAtributte = new ArrayList<Atributte>();
		listMethod = new ArrayList<Method>();
		listAssociacao = new ArrayList<Associacao>();
		listOperacao = new ArrayList<Operation>(); 
		listInteraction = new ArrayList<Interaction>();
		listRealInter = new ArrayList<RealizationInterface>();
	}
	
	
	public boolean isAbstract(){
		return this.abstrata;
	}
	
	public boolean isActive(){
		return this.ativa;
	}
	
	public void setAbstract(boolean abstrata){
		this.abstrata = abstrata;
	}
	
	public void setActive(boolean ativa){
		this.ativa = ativa;
	}
	
	public void setNeedImport(boolean needImport){
		this.needImport = needImport;
	}
	
	public void setNeedGetSet(boolean needGetSet){
		this.needGetSet = needGetSet;
	}
	
	//Operation
	public void addOperation(Operation operation){
			
		this.listOperacao.add(operation);
	}
	
	public Operation getLastOperation(){
		return this.listOperacao.get(this.listOperacao.size() -1);
	}
	
	public Operation getIndexOfListOperacao(int index){
		
		return this.listOperacao.get(index);
	}
	
	
	//Visibilidade
	public void setVisibility(String visibility){
		
		this.visibility = visibility;
	}	
	
	
	public String getVisibility(){
		
		return this.visibility;
	}
	

	// Atributo
	public void addAtributo(String name, String key) {
		Atributte atributte = new Atributte(name);
		this.listAtributte.add(atributte);
		Tool.putTrieAtributte(key, atributte);
	}

	public Atributte getLastAtributo() {

		return this.listAtributte.get(this.listAtributte.size() - 1);
	}

	public Atributte getIndexOfAtributo(int index) {

		return this.listAtributte.get(index);
	}

	// Metodo
	public void addMetodo(String name, String key) {
		Method metodo = new Method(name);
		this.listMethod.add(metodo);
		Tool.putTrieMetodo(key, metodo);
	}

	public Method getLastMetodo() {

		return this.listMethod.get(this.listMethod.size() - 1);
	}

	public Method getIndexOfMetodo(int index) {

		return this.listMethod.get(index);
	}

	// Associação
	public void addAssociacao(String nameAssoc) {

		this.listAssociacao.add(new Associacao(nameAssoc));
	}
	
	public void addAssociacao(Associacao assoc) {

		this.listAssociacao.add(assoc);
	}

	public Associacao getLastAssociacao() {

		return this.listAssociacao.get(this.listAssociacao.size() - 1);
	}

	public Associacao getIndexOfAssociacao(int index) {
		return this.listAssociacao.get(index);
	}

	public String getName() {
		return this.name;
	}

	
	//General
	
	public void setGeneral(String general){
		this.general = general;
	}
	
	public String getGeneral(){
		return this.general;
	}
	

	public void setPacote(Pacote pacote){
		this.pacote = pacote;
	}
	
	public Pacote getPacote(){
		return this.pacote;
	}
	
	/*
	 * Interaction
	 */
	public void addInteraction(Interaction interaction) {
		listInteraction.add(interaction);
	}
	
	public void printProp(){
		
		if(this.pacote != null){
			System.out.println("Pacote: " + this.pacote.getName());
			this.pacote.printProp();
		}
		System.out.println("Classe: " + this.name);
		System.out.println("\tVisibilidade: " + this.visibility);
		if(this.abstrata){
			System.out.println("\tClasse Abstrata");
		}
		if(this.ativa){
			System.out.println("\tClasse Ativa");
		}
		if(this.general != null){
			System.out.printf("\tSuper Classe %s \n", this.general);
		}

		for(int i = 0 ; i < this.listInnerClass.size(); i++){
			System.out.println("Inner Class of " + name);
			this.listInnerClass.get(i).printProp();
		}
		
		for(int i = 0 ; i < this.listAtributte.size() ; i++){
			this.listAtributte.get(i).printProp();
		}
		
		for(int i = 0 ; i < this.listMethod.size() ; i++){
			this.listMethod.get(i).printProp();
		}
		
		for(int i = 0 ; i < this.listAssociacao.size() ; i++){
			this.listAssociacao.get(i).printProp();
		}
		
		for(int i = 0; i < this.listInteraction.size() ; i++){
			this.listInteraction.get(i).printProp();
		}
		
	}
	
	
	public void genCode() throws IOException{
		File cls = new File(Parser.getModel().getFile() ,name.concat(".java"));
		BufferedWriter out = new BufferedWriter( new FileWriter(cls));
		
		//Pacote
		if(pacote != null){
			out.write("package " + pacote.getName()  + ";\n");
			if(this.pacote.getAssocPacote() != null){
				out.write("import " + pacote.getAssocPacote().getSupplier() + ".*;\n");
			}
		}
		
		//Imports
		if(needImport){
			out.write("import java.util.ArrayList;\n");
		}
		
		genInnerClass(out);
		out.close();
	}

	private void genInnerClass(BufferedWriter out) throws IOException{
		//Name Class and General
				out.write("\n" + visibility + (abstrata == true ? " abstract " : " ") + "class " + 
						  name + (general != null ? " extends " + general  : "") );
				
				//Implements
				if( listRealInter.size() > 0){
					out.write(" implements ");
					for(int i = 0 ; i < listRealInter.size() ; i++){
						out.write( listRealInter.get(i).getNameSupplier() );
						if(i < listRealInter.size() -1){
							out.write(", ");
						}
					}
				}
				
				out.write("{");
				
				//Atributos
				if(this.listAtributte.size() > 0){
					out.write("\n\n\t/**Attributes */");
					for(int i = 0 ; i < listAtributte.size() ; i++){
						this.listAtributte.get(i).genCode(out);
					}
				}
				
				//Atributos Return dos Métodos
				for(int i = 0 ; i < listMethod.size() ; i++){
					if( !((listMethod.get(i).getName().substring(0, 3).equals("get")) || 
						 (listMethod.get(i).getName().substring(0, 3).equals("set")) ) )
					{
						if( listMethod.get(i).getListReturn().size() > 0 ){
							out.write( "\n\n\t/**Attribute of Return Method " + listMethod.get(i).getName() + " */" );
							for(int j = 0 ; j < listMethod.get(i).getListReturn().size() ; j++){
								listMethod.get(i).getListReturn().get(j).genCodeReturn(out);
							}
						}
					}
				}
					
				//Atributtes from Interface
				for(int i = 0 ; i < listRealInter.size() ; i++){
					listRealInter.get(i).genCodeAtributte(out);
				}
				
				
				//Construtor
				if( !(this.abstrata) ){
					out.write("\n\n\t/** Constructor */");
					out.write("\n\tpublic " + name + "(");
					for(int i = 0 ; i < listAtributte.size() ; i++){
						listAtributte.get(i).genCodeConstructorSignature(out);
						if(i < listAtributte.size() - 1){
							out.write(",");
						}
					}
					out.write("){");
					if( general != null ){
						out.write("\n\t\tsuper();");
					}
					for(int i = 0 ; i < listAtributte.size() ; i++){
						this.listAtributte.get(i).genCodeConstructor(out);
					}
					out.write("\n\t}\n");
				}
				
				for(int i = 0; i < listInnerClass.size(); i++){
					listInnerClass.get(i).genInnerClass(out);
				}
				
				//Get
				if(needGetSet){
					out.write("\n\n\t/** Get */");
					for(int i = 0 ; i < listAtributte.size() ; i++){
						listAtributte.get(i).genCodeGet(out);
					}
				}
				
				//Set
				if(needGetSet){
					out.write("\n\n\t/** Set */");
					for(int i = 0 ; i < listAtributte.size() ; i++){
						listAtributte.get(i).genCodeSet(out);
					}
				}
				
				//Metodo
				if(listMethod.size() > 0){
					if( !((listMethod.get(0).getName().substring(0, 3).equals("get")) || 
							 (listMethod.get(0).getName().substring(0, 3).equals("set")) ) )
					{
						out.write("\n\n\t/** Methods */");
					}
					for(int i = 0 ; i < this.listMethod.size() ; i++){
						if( !((listMethod.get(i).getName().substring(0, 3).equals("get")) || 
								 (listMethod.get(i).getName().substring(0, 3).equals("set")) ) )
						{
							listMethod.get(i).genCode(out);
						}
					}
				}
				
				//Métodos da Super
				if(general != null){
					if( (Tool.containsKeyTrieAbstractMethod(general)) ){				
						for(int i = 0 ; i < Tool.getTrieAbstractMethod(general).size() ; i++){
							Tool.getTrieAbstractMethod(general).get(i).genCodeMtSuper(out);	
						}
					}
				}
				
				//Methods form Interface
				for(int i = 0 ; i < listRealInter.size() ; i++){
					listRealInter.get(i).genCodeMethods(out);
				}
				
				out.write("\n}");
				
	}

	
	public void parser(BufferedReader bf, String line) throws IOException{ 
		String key, value;
		if (line.contains("visibility=")) {
			value = Tool.manipulate(line, "visibility=");
			visibility = value;
		} 

		if (line.contains("isAbstract=")) {
			abstrata = true;
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
			for (line = bf.readLine(); 
				!((line.contains("</packagedElement")) || (line.contains("</nestedClassifier"))) ; 
				line = bf.readLine() ) 
			{
				if (line.contains("<generalization")) {
					key = Tool.manipulate(line, "general=");
					value = Tool.getTrieID(key);
					general = value;
				}
				
				if(line.contains("<nestedClassifier")){
					value = Tool.manipulate(line, "name=");
					Classe innerClass = new Classe(value);
					listInnerClass.add(innerClass);
					innerClass.parser(bf, line);
				}
				/*
				 * Atributo
				 */
				if (line.contains("<ownedAttribute")) {
					key = Tool.manipulate(line, "xmi:id=");
					value = Tool.manipulate(line, "name");
					Atributte atributte = new Atributte(value);
					listAtributte.add(atributte);
					needImport = atributte.parser(bf, line);
					Tool.putTrieAtributte(key, atributte);
				}
				/*
				 * Método
				 */
				if (line.contains("<ownedOperation")) {
					key = Tool.manipulate(line, "xmi:id=");
					value = Tool.manipulate(line, "name");
					Method metodo = new Method(value);
					listMethod.add(metodo);
					metodo.parser(bf, line);
					Tool.putTrieMetodo(key, metodo);
				}
				
				if (line.contains("uml:Interaction")) {// nome da classe
					key = Tool.manipulate(line, "xmi:id=");
					Interaction interaction = Tool.getTrieInteraction(key);
					listInteraction.add(interaction);
					interaction.parser(bf, line);
				}
				
				if (line.contains("<interfaceRealization")) {
					value = Tool.manipulate(line, "name");
					RealizationInterface realInter = new RealizationInterface(value);
					listRealInter.add(realInter);
					realInter.parser(bf, line);
				}
			}
		}// end for PackagedElement	
	}//end class parserClass
	



	
}
