package sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;

public class Argument extends DataSequence {
	private String type;
	private String value;
	private String symbol;
	
	public Argument(String name){
		this.name = name;
	}
	
	/*
	 * Get
	 */
	public String getType(){
		return this.type;
	}
	
	public String getValue(){
		return this.value;
	}
	
	/*
	 * Set
	 */
	public void setType(String type){
		this.type = type;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	
	public void parser(BufferedReader bf, String line) throws IOException{
		type = Tool.manipulate(line, "xmi:type=");
		value = Tool.manipulate(line, "value=");
		symbol = Tool.manipulate(line, "symbol=");
	}

	public void printProp() {
		System.out.println("Argument: " + this.name);
		System.out.println("\tType: " + this.type);
		System.out.println("\tValue: " + this.symbol);
	}

	public void genCode(BufferedWriter out) throws IOException {
		out.write(symbol);
	}
}
