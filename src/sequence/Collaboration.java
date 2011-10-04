package sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import utilities.Tool;

public class Collaboration extends DataSequence{
	
	private static ArrayList<Interaction> listInteraction = new ArrayList<Interaction>();
	
	/*
	 *Constructor
	 */
	public Collaboration(String name){
		this.name = name;
	}
	
	public static void parser(BufferedReader bf, String line) throws IOException{
		String value, key, str;
		key = Tool.manipulate(line, "xmi:id=");
		Interaction interaction = Tool.getTrieInteraction(key);
		interaction.parser(bf, line);
	}

	public void printProp() {
		System.out.println("Collaboration " + this.name);
		for(int i=0; i < listInteraction.size(); i++){
			listInteraction.get(i).printProp();
		}
		
	}
}
