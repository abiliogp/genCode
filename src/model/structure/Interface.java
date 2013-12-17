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

public class Interface extends DataStructure{
	
	/*Attributes*/
	private boolean needImport;
	private boolean active;
	
	private ArrayList<Attribute> attributes;
	private ArrayList<Method> methods;
	private ArrayList<Operation> listOperacao;
	
	/*Constructor*/
	public Interface(String name) {
		super(name);
		attributes = new ArrayList<Attribute>();
		methods = new ArrayList<Method>();
		listOperacao = new ArrayList<Operation>(); 
	}

	/*Get*/
	public ArrayList<Attribute> getAttributes(){
		return this.attributes;
	}
	
	public ArrayList<Method> getMethods(){
		return this.methods;
	}
	
	public boolean isNeedImport(){
		return this.needImport;
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
			isAbstract = true;
		} 

		if (line.contains("classifierBehavior=")) {
			value = Tool.manipulate(line, "classifierBehavior=");
			for (int i = 0; i < value.length(); i = +23) {
				key = value.substring(i, i + 23);
				this.listOperacao.add(Tool.getTrieOperation(key));
			}
		}

		if (line.contains("isActive=")) {
			active = true;
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
					attributes.add(atributte);
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
					methods.add(metodo);
					metodo.parser(bf, line);
					Tool.putTrieMetodo(key, metodo);
				}
			}
		}	
	}

	
	
}
