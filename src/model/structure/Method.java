package model.structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.sequence.Interaction;
import model.sequence.OperationEvent;
import utilities.Parser;
import utilities.Tool;

public class Method extends DataStructure {

	/*Attributes*/
	private ArrayList<Parameter> parameters;
	private ArrayList<Operation> listOperacao;
	private ArrayList<Parameter> returns;

	private Interaction interaction;
	private boolean isSet;
	private boolean isGet;
	
	/*Constructor*/
	public Method(String name) {
		super(name);
		parameters = new ArrayList<Parameter>();
		listOperacao = new ArrayList<Operation>();
		returns = new ArrayList<Parameter>();
		isAbstract = false;
	}

	/*Add*/
	public void addParameter(String name) {
		this.parameters.add(new Parameter(name));
	}

	public void addParameter(String name, String type) {
		this.parameters.add(new Parameter(name));
	}
	
	public void addOperation(Operation operation) {
		this.listOperacao.add(operation);
	}

	public void addReturn(String name) {
		this.returns.add(new Parameter(name));
	}

	/*Get*/
	public Operation getLastOperation() {
		return this.listOperacao.get(this.listOperacao.size() - 1);
	}

	public Operation getIndexOfListOperacao(int index) {

		return this.listOperacao.get(index);
	}

	public ArrayList<Parameter> getParameters(){
		return this.parameters;
	}
	
	public Parameter getLastParameter() {
		return this.parameters.get(this.parameters.size() - 1);
	}

	public Parameter getParameterAtIndex(int index) {
		return this.parameters.get(index);
	}
	
	public Parameter getLastReturn() {
		return this.returns.get(this.returns.size() - 1);
	}

	public ArrayList<Parameter> getReturns() {
		return this.returns;
	}
	
	public Interaction getInteraction(){
		return this.interaction;
	}

	public boolean isSet(){
		return this.isSet;
	}
	
	public boolean isGet(){
		return this.isGet;
	}

	
	public void printProp() {

		System.out.println("\tMétodo: " + this.name);
		System.out.println("\t\tVisibilidade: " + this.visibility);
		System.out.println("\t\tisGetSet: " + (this.isSet || isGet));

		for (int i = 0; i < this.listOperacao.size(); i++) {
			this.listOperacao.get(i).printProp();
		}
		if (interaction != null) {
			interaction.printProp();
		}

		for (int i = 0; i < this.parameters.size(); i++) {
			this.parameters.get(i).printProp();
		}

		for (int i = 0; i < this.returns.size(); i++) {
			this.returns.get(i).printProp();
		}
	}


	
	public void parser(BufferedReader bf, String line) throws IOException {
		String value, str, key;
		if(name.length() > 3){
			if(name.substring(0, 3).equals("set")){
				this.isSet = true;
			} else if(name.substring(0, 3).equals("get")){
				this.isGet = true;
			}
		}
		
		if (line.contains("visibility=")) {
			visibility = Tool.manipulate(line, "visibility=");
		}

		if (line.contains("isAbstract=")) {
			isAbstract = true;
		}

		if (line.contains("isStatic=")) {
			isStatic = true;
		}

		if (line.contains("method=")) {
			value = Tool.manipulate(line, "method=");
			interaction = Tool.getTrieInteraction(value);
			/*
			 * for (int i = 0; i < value.length(); i = +23) { str =
			 * value.substring(i, i + 23);
			 * listOperacao.add(Tool.getTrieOperation(str)); }
			 */
		}

		if (line.contains("/>")) {
			if (isAbstract) {
				parserMetodoAbstrato();
			}
			return;
		} else {
			for (; !(line.contains("</ownedOperation>")); line = bf.readLine()) {
				if (line.contains("<ownedParameter")) {
					key = Tool.manipulate(line, "xmi:id=");
					value = Tool.manipulate(line, "name");
					Parameter parametro = new Parameter(value);
					parametro.parser(bf, line);
					if ((type.equals("void"))
							&& !(parametro.getDirection().equals("in"))) {
						type = parametro.getType();
					}
					if ((parametro.getDirection().equals("return"))) {
						returns.add(parametro);
					} else {
						parameters.add(parametro);
					}
				}
			}
		}
		if (isAbstract) {
			parserMetodoAbstrato();
		}
	}

	private static void parserMetodoAbstrato() {
		String str;
		str = Parser.getModel().getLastClasse().getName();
		if (Tool.containsKeyTrieAbstractMethod(str)) {
			Tool.addTrieAbstractMethod(str, Parser.getModel().getLastClasse()
					.getLastMetodo());
		} else {
			ArrayList<Method> listMetAbs = new ArrayList<Method>();
			listMetAbs.add(Parser.getModel().getLastClasse().getLastMetodo());
			Tool.putTrieAbstractMethod(str, listMetAbs);
		}
	}

	public static void load(BufferedReader bf, String line) {
		String key, value;
		key = Tool.manipulate(line, "xmi:id");
		value = Tool.manipulate(line, "name=");
		Method method = new Method(value);
		Tool.putTrieMetodo(key,  method);
		Tool.putTrieMetodoName(value, method);
	}

	

}// end class
