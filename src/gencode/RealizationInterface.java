package gencode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;

public class RealizationInterface {
	private String name;
	private Interface supplier;
	private Interface contract;
	
	public RealizationInterface(String name) {
		this.name = name;
	}
	
	public String getNameSupplier(){
		return supplier.getName();
	}

	public void genCodeAtributte(BufferedWriter out) throws IOException{
		if(supplier.tamListAtributte() > 0){
			out.write("\n\t/** Atributte From " + name + " */");
			supplier.genCodeAtributte(out);
		}
	}
	
	public void genCodeMethods(BufferedWriter out) throws IOException{
		if(supplier.tamListMethod() > 0){
			out.write("\n\t/** Methods From " + name + " */");
			supplier.genCodeMethods(out, 0);
		}
	}
	
	public void parser(BufferedReader bf, String line) throws IOException{
		String key;
		name = Tool.manipulate(line, "name=");
		key = Tool.manipulate(line, "supplier=");
		supplier = Tool.getTrieInterface(key);
		key = Tool.manipulate(line, "contract=");
		contract = Tool.getTrieInterface(key);
	}
}
