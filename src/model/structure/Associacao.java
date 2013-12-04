package model.structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import utilities.Tool;

public class Associacao extends DataModel {

	
	private Atributte member1;
	private Atributte member2;
	

	public Associacao(String name) {
		super(name);
	}
	
		
	/**
	 * loadAssociation: Criação da trieAssoc, informações referentes ao Member(1)
	 * @param bf
	 * @param line
	 * @throws IOException
	 */
	public static void load(BufferedReader bf, String line) throws IOException{
		String key, value;
		
		value = Tool.manipulate(line, "name=");
		Associacao assoc = new Associacao(value);

		if (line.contains("/>")) {
			line = "</packagedElement>";
		} else {
			for (line = bf.readLine(); !(line.contains("</packagedElement")); 	line = bf.readLine() ) {
				if (line.contains("<ownedEnd")) {
					key = Tool.manipulate(line, "xmi:id=");
					value = Tool.manipulate(line, "name");
					Atributte atributte = new Atributte(value);
					//listAtributte.add(atributte);
					//boolean needImport = atributte.parser(bf, line);
					
					Tool.putTrieAtributte(key, atributte);
				}
			}// end else
		}
	}//end loadAssociation

	
	public void printProp() {

		System.out.println( "\tNome da associação: " + this.name );
		System.out.println("\t\tVisibilidade: " + this.visibility);

		
	
	}

}
