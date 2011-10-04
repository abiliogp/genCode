package sequence;

import gencode.Method;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;


public class OperationEvent extends DataSequence{

	/*
	 *Attributes
	 */
	private Method operation;
	private String type;
	
	/*
	 *Constructor
	 */
	public OperationEvent(String name){
		this.name = name;
	}

	
	/*
	 * Get
	 */
	public String getType(){
		return this.type;
	}

	/*
	 * Set
	 */
	public void setType(String type){
		this.type = type;
	}

	
	/*
	 * SendOperationEvent
	 * ReceiveOperationEvent
	 */
	public static void load(BufferedReader bf, String line) throws IOException {
		String key, value;
		key = Tool.manipulate(line, "xmi:id");
		value = Tool.manipulate(line, "name=");
		OperationEvent event = new OperationEvent(value);
		value = Tool.manipulate(line, "type=");
		event.setType(value);
		Tool.putTrieOperationEvent(key,  event);
	}
	
	/*
	 *Abstract Method of Super
	 */
	public void parser(BufferedReader bf, String line) throws IOException{
		String key;
		key = Tool.manipulate(line, "operation=");
		operation = Tool.getTrieMetodo(key);
	}


	public void printProp() {
		System.err.println("OperationEvent " + name);
		System.out.println("\tType " + type);
		System.out.println("\tMt Chamado " + operation.getName());
	}


	public void genCode(BufferedWriter out) throws IOException {
		if(type.equals("uml:SendOperationEvent")){
			operation.genCodeCall(out);
		}
	}
	
	

}