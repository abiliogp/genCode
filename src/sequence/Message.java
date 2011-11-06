package sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import utilities.Tool;

public class Message extends DataSequence{

	/*
	 *Attributes
	 */
	private ArrayList<Argument> listArgument;
	
	private Fragment sendEvent;
	private Fragment receiveEvent;
	
	private String messageSort;
	
	//variavel que pega o retorno de um método
	private String variable;
	
	/*
	 *Constructor
	 */
	public Message(String name){
		super(name);
		this.messageSort = "syn";
		this.listArgument = new ArrayList<Argument>();
	}

	public String getMessageSort(){
		return messageSort;
	}
	
	
	public void parser(BufferedReader bf, String line) throws IOException{
		String value, key;
		if(name.contains("=")){
			variable = name.substring(0, name.indexOf("="));
			name = name.substring(name.indexOf("=") + 1);
		}
		if(line.contains("messageSort=")){
			messageSort = Tool.manipulate(line, "messageSort=");
		}
		key = Tool.manipulate(line, "receiveEvent=");
		receiveEvent = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "sendEvent=");
		sendEvent = Tool.getTrieFragment(key);
		if (line.contains("/>")){
			line = "</message>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</message>")) ; line = bf.readLine() ){
				if(line.contains("<argument")){
					value = Tool.manipulate(line, "name=");
					Argument argument = new Argument(value);
					argument.parser(bf, line);
					listArgument.add(argument);
				}
			}
		}
	}

	public void printProp() {
		System.out.println("Message: " + name);
		System.out.println("\tsort: " + messageSort);
		System.out.println("\tvariable: " + (variable == null ? "NOT!" : variable));
		for(int i = 0 ; i <  this.listArgument.size(); i++){
			System.out.print("\targument" + (i + 1) + ": ");
			listArgument.get(i).printProp();
		}
	}

	public void genCode(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		if(messageSort.equals("createMessage")){
			genCodeCreate(out);
		} else{
			genCodeVariable(out);
			genCodeAtributte(out);
		}
	}

	private void genCodeVariable(BufferedWriter out) throws IOException {
		if(variable != null){
			out.write(variable);
			out.write(" = ");
		}
	} 

	/*
	 * Faz chamadas de método da própria classe e de outros objetos
	 */
	private void genCodeAtributte(BufferedWriter out) throws IOException {
		if(sendEvent.getCovered() != receiveEvent.getCovered()){
			receiveEvent.genCodeAttribute(out);
		}
	}
	
	/*
	 * Gera os n argumentos para um chamada de método
	 */
	public void genCodeArguments(BufferedWriter out) throws IOException {
		for(int i=0; i < listArgument.size(); i++){
			listArgument.get(i).genCode(out);
			if(i < listArgument.size() - 1){
				out.write(",");
			}
		}
	}

	private void genCodeCreate(BufferedWriter out) throws IOException {		
		receiveEvent.genCodeCreate(out);
	}
	
}