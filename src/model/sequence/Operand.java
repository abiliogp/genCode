package model.sequence;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Model;
import utilities.Tool;

public class Operand extends DataSequence{
	
	/*Attributes*/
	private ArrayList<Fragment> fragments;
	private Guard guard;
	
	/*Constructor*/
	public Operand(String name) {
		super(name);
		fragments = new ArrayList<Fragment>();
	}
	
	/*Get*/
	public Guard getGuard(){
		return guard;
	}
	
	public ArrayList<Fragment> getFragments(){
		return this.fragments;
	}
	
	
	public void parser(BufferedReader bf, String line) throws IOException {
		String value;
		if (line.contains("/>")){
			line = "</operand>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</operand>")) ; line = bf.readLine() ){
				if(line.contains("<guard")){
					value = Tool.manipulate(line, "name=");
					guard = new Guard(value);
					guard.parser(bf, line);
				}
				if(line.contains("<fragment")){
					value = Tool.manipulate(line, "xmi:id=");
					Fragment fragment = Model.getTrieFragment(value);
					fragment.parser(bf, line);
					fragments.add(fragment);
				}
			}
		}	
	}
	
	public void printProp(){
		System.err.println("Operand"+this.name);
		this.guard.printProp();
		for(int i=0 ; i < this.fragments.size() ; i++){
			this.fragments.get(i).printProp();
		}
	}

	

}
