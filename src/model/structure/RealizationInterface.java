package model.structure;

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
	
	
	public String getName(){
		return this.name;
	}
	
	public String getNameSupplier(){
		return supplier.getName();
	}

	public Interface getSupplier(){
		return this.supplier;
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
