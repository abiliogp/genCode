package model.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import model.Model;
import utilities.Tool;


public class Interaction extends DataSequence{

	/*Attributes*/
	private  ArrayList<Lifeline> lifelines;
	private  ArrayList<Fragment> fragments;
	
	/*Constructor*/
	public Interaction(String name){
		super(name);
		lifelines = new ArrayList<Lifeline>();
		fragments = new ArrayList<Fragment>();
		new TreeMap<String, String>();
	}
	
	/*Get*/
	public ArrayList<Lifeline> getLifelines(){
		return this.lifelines;
	}
	
	public ArrayList<Fragment> getFragments(){
		return this.fragments;
	}


	public  void parser(BufferedReader bf, String line) throws IOException{
		String key;
		if (line.contains("/>"))
			line = "</ownedBehavior>"; 		
		for ( ; !((line.contains("</ownedBehavior>")) || (line.contains("</packagedElement>"))) ; line = bf.readLine() ) {
			key = Tool.manipulate(line, "xmi:id");
			
			if (line.contains("<lifeline")) {
				key = Tool.manipulate(line, "xmi:id");
				Lifeline lifeline = Model.getTrieLifeline(key);
				lifeline.parser(bf, line);
				lifelines.add(lifeline);
			}

			if (line.contains("<fragment")) {
				key = Tool.manipulate(line, "xmi:id");
				Fragment fragment = Model.getTrieFragment(key);
				fragments.add(fragment);
				fragment.parser(bf, line);
			}
			
			if (line.contains("<message")) { 		
				key = Tool.manipulate(line, "xmi:id");
				Message message = Model.getTrieMessage(key);
				message.parser(bf, line);
			}
		}
	}
	
	/*
	 * Faz a primeira estruturação, da sequence.
	 * Constroe as trie. com xmi:id e objeto
	 * Na segunda passagem apenas busca na trie e vai referenciado
	 */
	public static  void load(BufferedReader bf, String line) throws IOException {
		String key, value;
		key = Tool.manipulate(line, "xmi:id=");
		value = Tool.manipulate(line, "name");
		Interaction interaction = new Interaction(value);
		Model.putTrieInteraction(key, interaction);
		if (line.contains("/>")){
			line = "</ownedBehavior>";
		} else{
			for ( ; !((line.contains("</ownedBehavior>")) || (line.contains("</packagedElement>"))); line = bf.readLine() ) {
				key = Tool.manipulate(line, "xmi:id");
				
				if (line.contains("<lifeline")) {
					key = Tool.manipulate(line, "xmi:id");
					value = Tool.manipulate(line, "name=");
					Lifeline lifeline = new Lifeline(value);
					Model.putTrieLifeline(key, lifeline);
				}
	
				if (line.contains("<fragment")) {
					key = Tool.manipulate(line, "xmi:id");
					Model.putTrieXMIfragment(key);
					value = Tool.manipulate(line, "name=");
					Fragment fragment = new Fragment(value);
					fragment.load(bf, line);
					Model.putTrieFragment(key, fragment);
				}
				
				if (line.contains("<message")) { 		
					key = Tool.manipulate(line, "xmi:id");
					value = Tool.manipulate(line, "name=");
					Message message = new Message(value);
					Model.putTrieMessage(key, message);
				}
			}
		}
	}
	
	
	
	
	/*
	 * Rever isso pois a OperationEvent não é estruturada, talvez deslocar isso para o parser
	 * tlz fazer um addTrieEvent ou coisa do tipo
	 */
	public void printProp(){
		System.out.println("Interaction : " + name);
		for(int i = 0 ; i < lifelines.size() ; i++){
			lifelines.get(i).printProp();
		}
	
		
	}


	

}