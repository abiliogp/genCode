package sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import utilities.Tool;


/*
 * This "package code" was generate by GenCode
 */

public class Interaction extends DataSequence{

	
	
	private  ArrayList<Lifeline> listLifeline = new ArrayList<Lifeline>();
	private  ArrayList<Fragment> listFragment = new ArrayList<Fragment>();
	
	/*
	 * trieXMIfragment <XMI, Interaction.name > 
	 */
	private  TreeMap<String, String> trieXMIfragment = new TreeMap<String, String>();
	
	/*
	 *Constructor
	 */
	public Interaction(String name){
		this.name = name;
	}


	/*
	 *Abstract Method of Super
	 */
	public  void parser(BufferedReader bf, String line) throws IOException{
		String value, key, str;
		if (line.contains("/>"))
			line = "</ownedBehavior>"; 		
		for ( ; !((line.contains("</ownedBehavior>")) || (line.contains("</packagedElement>"))) ; line = bf.readLine() ) {
			key = Tool.manipulate(line, "xmi:id");
			
			/*
			 * LifelineInteraction.get
			 */
			if (line.contains("<lifeline")) {
				key = Tool.manipulate(line, "xmi:id");
				Lifeline lifeline = Tool.getTrieLifeline(key);
				lifeline.parser(bf, line);
				listLifeline.add(lifeline);
			}

			/*
			 * Fragment
			 */
			if (line.contains("<fragment")) {
				key = Tool.manipulate(line, "xmi:id");
				Fragment fragment = Tool.getTrieFragment(key);
				listFragment.add(fragment);
				fragment.parser(bf, line);
			}
			
			/*
			 * Message
			 */
			if (line.contains("<message")) { 		
				key = Tool.manipulate(line, "xmi:id");
				Message message = Tool.getTrieMessage(key);
				message.parser(bf, line);
			}
		}//end for
	}
	
	/*
	 * Faz a primeira estruturação, da sequence.
	 * Constroe as trie. com xmi:id e objeto
	 * Na segunda passagem apenas busca na trie e vai referenciado
	 */
	public static  void load(BufferedReader bf, String line) throws IOException {
		String key, value, str;
		key = Tool.manipulate(line, "xmi:id=");
		value = Tool.manipulate(line, "name");
		Interaction interaction = new Interaction(value);
		Tool.putTrieInteraction(key, interaction);
		if (line.contains("/>")){
			line = "</ownedBehavior>";
		} else{
			for ( ; !((line.contains("</ownedBehavior>")) || (line.contains("</packagedElement>"))); line = bf.readLine() ) {
				key = Tool.manipulate(line, "xmi:id");
				
				/*
				 * Lifeline
				 */
				if (line.contains("<lifeline")) {
					key = Tool.manipulate(line, "xmi:id");
					value = Tool.manipulate(line, "name=");
					Lifeline lifeline = new Lifeline(value);
					Tool.putTrieLifeline(key, lifeline);
				}
	
				/*
				 * Fragment
				 */
				if (line.contains("<fragment")) {
					key = Tool.manipulate(line, "xmi:id");
					Tool.putTrieXMIfragment(key);
					value = Tool.manipulate(line, "name=");
					Fragment fragment = new Fragment(value);
					fragment.load(bf, line);
					Tool.putTrieFragment(key, fragment);
				}
				
				/*
				 * Message
				 */
				if (line.contains("<message")) { 		
					key = Tool.manipulate(line, "xmi:id");
					value = Tool.manipulate(line, "name=");
					Message message = new Message(value);
					Tool.putTrieMessage(key, message);
				}
			}//end for
		}
	}//end loadSequence
	
	

	public void genCode(BufferedWriter out, int tab) throws IOException{
		String tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd + "/** Specified from Sequence Diagram " + name + " */\n");
		if(!(listLifeline.isEmpty()))
			this.listLifeline.get(0).genCode(out, tab);
		//for(int i = 0 ; i < listLifeline.size() ; i++){
		//	this.listLifeline.get(0).genCode(out);
		//}
	}
	
	
	/*
	 * Rever isso pois a OperationEvent não é estruturada, talvez deslocar isso para o parser
	 * tlz fazer um addTrieEvent ou coisa do tipo
	 */
	public void printProp(){
		System.out.println("Interaction : " + name);
		for(int i = 0 ; i < listLifeline.size() ; i++){
			listLifeline.get(i).printProp();
		}
	
		
	}


	

}