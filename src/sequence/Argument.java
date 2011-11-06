package sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;

public class Argument extends DataSequence {
	
	private String symbol;
	
	public Argument(String name){
		super(name);
	}
		
	public void parser(BufferedReader bf, String line) throws IOException{
		type = Tool.manipulate(line, "xmi:type=");
		value = Tool.manipulate(line, "value=");
		symbol = Tool.manipulate(line, "symbol=");
	}

	public void genCode(BufferedWriter out) throws IOException {
		out.write(symbol);
	}
	
	public void printProp() {
		System.out.println(this.symbol);
	}
}
