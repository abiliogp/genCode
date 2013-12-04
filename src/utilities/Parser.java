package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Model;
import model.sequence.Interaction;
import model.sequence.OperationEvent;
import model.structure.AssocPacote;
import model.structure.Associacao;
import model.structure.Atributte;
import model.structure.Classe;
import model.structure.Interface;
import model.structure.Method;
import model.structure.Operation;
import model.structure.Pacote;




public abstract class Parser {
	
	
	/**
	 * Trie : Colhetam informações da primeira passagem,
	 *        necessárias para resolução de referências avante
	 *        trieID : xmi:id;
	 *        trieOpe : Behavior;
	 *        trieAssoc : uml:Association.
	 *        trieMetAbs : métodos abstratos 
	 *        trieAssocPacotes: associações do pacote (dependecy)       
	 */
	
	
	//static private TreeMap<String, Associacao> trieAssoc = new TreeMap<String, Associacao>();
	
	
	static private TreeMap<String, AssocPacote> trieAssocPacote = new TreeMap<String, AssocPacote>();
	
	/*
	 * Model: structure when is store the information from model. 
	 */
	static private Model model ;
	
	
	public static Model getModel(){
		return model;
	}
	
	
	//Modificar os metodos de acesso a trieMetodo abstrato
	
	
	
	

	/**
	 * loadXMI: First step, created trie need to forward references.
	 * @param inputFileName
	 * @throws IOException
	 */
	public static void loadXMI(String inputFileName) throws IOException {
		String line, key, value;
		BufferedReader bf = new BufferedReader(new FileReader(inputFileName));
		while (bf.ready()) {
			line = bf.readLine();
			key = Tool.manipulate(line, "xmi:id");
			
			
			if(line.contains("<ownedAttribute")){
				Atributte.load(bf, line);
			}
			
			/*
			 * Associação
			 */
			if (line.contains("uml:Association")) {
				Associacao.load(bf, line);
			}
			

			/*
			 * Método
			 */
			if (line.contains("<ownedOperation")) {
				Method.load(bf, line);
			}
			
			
			/*
			 * Operação
			 */
			if (line.contains("<ownedBehavior")) {
				loadOperation(bf, line, key);				
			} 
			
			
			/*
			 * Associação Pacote
			 */
			if (line.contains("uml:Dependency")) {
				loadAssocPacote(bf, line, key);				
			} 
			
			/*
			 * Sequence
			 */
			if(line.contains("uml:Interaction")){				
				Interaction.load(bf,line);
			}
			
			if (line.contains("uml:SendOperationEvent")) { 		
				OperationEvent.load(bf, line);
			}
			
			if (line.contains("uml:ReceiveOperationEvent")) {
				OperationEvent.load(bf, line);
			}

			/*
			 * Interface
			 */
			if (line.contains("uml:Interface")) {
				Interface.load(bf, line);
			}
			
			/*
			 * XMI ID
			 */
			else {
				if (key != null) {
					value = Tool.manipulate(line, "name=");
					Tool.putTrieID(key, value);
				}
			}
		}
	}

	
	/**
	 * runParser: Second step,created structure of model.
	 * @param inputFileName
	 * @throws IOException
	 */
	public static void runParser(String inputFileName) throws IOException {
		String line, value;
		BufferedReader bf = new BufferedReader(new FileReader(inputFileName));
		while (bf.ready()) {
			line = bf.readLine();
			
			/*
			 * Arquivo Modelo
			 */
			if(line.contains("<uml:Model")){
				model = new Model(Tool.manipulate(line, "name="));
			}
			
			/*
			 * Classe
			 */
			if (line.contains("uml:Class")) {// nome da classe
				value = Tool.manipulate(line, "name");
				Classe classe = new Classe(value);
				model.addClasse(classe);
				classe.parser(bf, line);
			}
			
			/*
			 * Pacote
			 */
			if (line.contains("uml:Package")) {// nome da classe
				parserPackage(bf, line);
			}
			
			/*
			 * Sequência
			 */
			if (line.contains("uml:Interaction")) {// nome da classe
				value = Tool.manipulate(line, "xmi:id=");
				Interaction interaction = Tool.getTrieInteraction(value);
				model.addInteraction(interaction);
				interaction.parser(bf, line);
						
			}
			
			if ( (line.contains("uml:SendOperationEvent")) || (line.contains("uml:ReceiveOperationEvent")) ) {
				value = Tool.manipulate(line, "xmi:id");
				OperationEvent event = Tool.getTrieOperationEvent(value);
				event.parser(bf, line);
			}
			
			/*
			 * Interface
			 */
			if (line.contains("uml:Interface")) {
				value = Tool.manipulate(line, "xmi:id");
				Interface inter = Tool.getTrieInterface(value);
				model.addInterface(inter);
				inter.parser(bf, line);
			}
		}// end while
	}// end parser
	
	
	
		
	/**
	 * loadOperation: Criação trie Behavior
	 * @param bf
	 * @param line
	 * @param key
	 * @throws IOException
	 */
	private static void loadOperation(BufferedReader bf, String line, String key) throws IOException{
		String value;
		value = Tool.manipulate(line, "xmi:type=");
		Operation operacao = new Operation(value);
		value = Tool.manipulate(line, "name=");
		operacao.setName(value);
		if (line.contains("visibility=")) {
			value = Tool.manipulate(line, "visibility=");
			operacao.setVisibility(value);
		} 
		Tool.putTrieOperation(key, operacao);
	}//end loadOperation
	
	/**
	 * loadAssocPacote: Carrega Associação do Pacote (Dependency).
	 * @param bf
	 * @param line
	 * @param key
	 * @throws IOException
	 */
	public static void loadAssocPacote(BufferedReader bf, String line, String key) throws IOException{
		
	 	String value;
	 	value = Tool.manipulate(line, "name=");
	 	AssocPacote assoc = new AssocPacote(value);		
		
		if (line.contains("visibility=")) {
			value = Tool.manipulate(line, "visibility=");
			assoc.setVisibility(value);
		} 
		
		value = Tool.manipulate(line, "supplier=");
		value = Tool.getTrieID(value);
		assoc.setSupplier(value);
			
		trieAssocPacote.put(key, assoc);
	 }
	
	
	/**
	 * parserAssociation: Associação de uma Classe de um Pacote
	 * 							 Adicionando informações referentes ao member2
	 * 							 setMember2; setVisibility2; setAggregation2;
	 * 							 setUpperValue2; setLowerValue2
	 * @param bf
	 * @param line
	 * @param str
	 * @throws IOException
	 */
	
	
	
	
	/**
	 * parserPackage: Pacote
	 * @param bf
	 * @param line
	 * @throws IOException
	 */
	private static void parserPackage(BufferedReader bf, String line) throws IOException{
		String str;
		Pacote pacote;
		str = Tool.manipulate(line, "name=");
		pacote = new Pacote(str);
		if (line.contains("clientDependency=")){
			str = Tool.manipulate(line, "clientDependency=");
			pacote.setAssocPacote(trieAssocPacote.get(str));
		}
		if (line.contains("visibility=")) {
			str = Tool.manipulate(line, "visibility=");
			pacote.setVisibility(str);
		} 
		
		if (line.contains("/>")) {
			return;
		}
		
		for ( line = bf.readLine() ; !(line.contains("</packagedElement")) ; 
			  line = bf.readLine() ) 
		{
			/*
			 * Pacote
			 */
			if(line.contains("uml:Package")){
				parserPackage(bf, line); 
			}
			
			/*
			 * Classe
			 */
			if(line.contains("uml:Class")){
				//parserClass(bf, line, pacote);	
			}
		}//end for
	}// end class parserPackage
	
	
	
	
}//end class Parser 
