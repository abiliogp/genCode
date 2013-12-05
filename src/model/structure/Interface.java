package model.structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.sequence.Interaction;
import model.sequence.Lifeline;
import utilities.Parser;
import utilities.Tool;

public class Interface {
	
	private String name;
	private String visibility;

	private boolean needImport;
	private boolean abstrata;
	private boolean ativa;
	
	private ArrayList<Attribute> listAtributte;
	private ArrayList<Method> listMetodo;
	private ArrayList<Operation> listOperacao;
	
	
	public Interface(String name) {
		this.name = name;
		visibility = "public";
		abstrata = false;
		ativa = false;
		needImport = false;
		listAtributte = new ArrayList<Attribute>();
		listMetodo = new ArrayList<Method>();
		listOperacao = new ArrayList<Operation>(); 
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public int tamListAtributte(){
		return listAtributte.size();
	}
	
	public int tamListMethod(){
		return listMetodo.size();
	}
	
	public void genCodeAtributte(BufferedWriter out) throws IOException{
		for(int i = 0 ; i < this.listAtributte.size() ; i++){
			this.listAtributte.get(i).genCode(out, 0);
		}
	}
	
	public void genCodeMethods(BufferedWriter out,int tab) throws IOException{
		for(int i = 0 ; i < this.listMetodo.size() ; i++){
			this.listMetodo.get(i).genCode(out,tab);	
		}
	}
	
	public void genCode() throws IOException{
		File inter = new File("out/" + Parser.getModel().getName() ,name.concat(".java"));
		BufferedWriter out = new BufferedWriter( new FileWriter(inter));
		
		out.write("\n" + this.visibility + " interface " + name  + "{");
		
		//Imports
		if(this.needImport){
			out.write("import java.util.ArrayList;\n");
		}
		
		//Atributos
		if(this.listAtributte.size() > 0){
			out.write("\n\n\t/*\n\t *Attributes\n\t */");
			for(int i = 0 ; i < this.listAtributte.size() ; i++){
				this.listAtributte.get(i).genCodeAtributteImplements(out);
			}
		}
		
		
		//Metodo
		if(this.listMetodo.size() > 0){
			out.write("\n\n\t/*\n\t *Method\n\t */");
			for(int i = 0 ; i < this.listMetodo.size() ; i++){
				this.listMetodo.get(i).genCodeMtImplements(out);	
			}
		}
		
		out.write("\n}");
		out.close();
	}
	
	
	

	public static void load(BufferedReader bf, String line) {
		String key, value;
		key = Tool.manipulate(line, "xmi:id");
		value = Tool.manipulate(line, "name=");
		Interface inter = new Interface(value);
		Tool.putTrieInterface(key, inter);
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
			for (line = bf.readLine(); !(line.contains("</packagedElement")) ; line = bf.readLine() ) {
				/*
				 * Atributo
				 */
				if (line.contains("<ownedAttribute")) {
					key = Tool.manipulate(line, "xmi:id=");
					value = Tool.manipulate(line, "name");
					Attribute atributte = new Attribute(value);
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
					listMetodo.add(metodo);
					metodo.parser(bf, line);
					Tool.putTrieMetodo(key, metodo);
				}
			}
		}// end for PackagedElement	
	}//end class parser
	
}
