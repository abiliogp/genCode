package model.sequence;

import java.io.BufferedReader;
import java.io.IOException;

import utilities.Tool;

public class Argument extends DataSequence {
		
	public Argument(String name){
		super(name);
	}
		
	public void parser(BufferedReader bf, String line) throws IOException{
		type = Tool.manipulate(line, "xmi:type=");
		value = line.contains("symbol=") ? Tool.manipulate(line, "symbol=") : Tool.manipulate(line, "value=");
		if(value.equals("")) {
			value = name;
		}
	}
	
	public void printProp() {
		System.out.println("\t" + type);
		System.out.println("\t" + value);
	}
}
