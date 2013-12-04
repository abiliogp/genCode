package model.sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import utilities.Tool;

public class Operand extends DataSequence{
	
	private ArrayList<Fragment> listFragment;
	private Guard guard;
	
	
	public Operand(String name) {
		super(name);
		listFragment = new ArrayList<Fragment>();
	}
	
	public Guard getGuard(){
		return guard;
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
					Fragment fragment = Tool.getTrieFragment(value);
					fragment.parser(bf, line);
					listFragment.add(fragment);
				}
			}
		}	
	}
	
	public void printProp(){
		System.err.println("Operand"+this.name);
		this.guard.printProp();
		for(int i=0 ; i < this.listFragment.size() ; i++){
			this.listFragment.get(i).printProp();
		}
	}

	public void genCodeOpt(BufferedWriter out, int tab) throws IOException {
		String tabInd, subInd;
		tabInd = Tool.indentation(tab);
		if( !(guard.getSpecification().getBody().equals("else")) ){
			out.write("\n" + tabInd + "if(");
			guard.genCode(out);	
			out.write(")");
		} 
		out.write("{");
		genCode(out, tab);
		out.write("\n" + tabInd + "}");
	}
	
	public void genCode(BufferedWriter out, int tab) throws IOException {
		for(int i=0 ; i < this.listFragment.size() ; i++){	
			listFragment.get(i).genCode(out, tab + 1);
		}
	}
	
	public void genCodeCaseSwitch(BufferedWriter out, int tab) throws IOException {
		String tabInd;
		out.write("case ");
		guard.genCodeValue(out);
		out.write(":\n");
		for(int i=0 ; i < this.listFragment.size() ; i++){	
			listFragment.get(i).genCode(out, tab + 1);
		}
		tabInd = Tool.indentation(tab);
		out.write("\t" + tabInd +"break;\n");
	}

}
