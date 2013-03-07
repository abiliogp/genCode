package sequence;

import gencode.Method;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;


public class OperationEvent extends DataSequence{

	private Method operation;

	public OperationEvent(String name){
		super(name);
	}
	
	public Method getOperation(){
		return operation;
	}

	public static void load(BufferedReader bf, String line) throws IOException {
		String key, value;
		key = Tool.manipulate(line, "xmi:id");
		value = Tool.manipulate(line, "name=");
		OperationEvent event = new OperationEvent(value);
		value = Tool.manipulate(line, "type=");
		event.setType(value);
		Tool.putTrieOperationEvent(key,  event);
	}
	
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

	public void genCodeGet(BufferedWriter out) throws IOException {
		if(type.equals("uml:SendOperationEvent")){
			operation.genCodeCallGet(out);
		}
	}
}