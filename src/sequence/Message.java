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
	
	/*
	 *Constructor
	 */
	public Message(String name){
		this.name = name;
		this.listArgument = new ArrayList<Argument>();
	}

	public String getMessageSort(){
		return messageSort;
	}
	
	/*
	 *Abstract Method of Super
	 */
	public void parser(BufferedReader bf, String line) throws IOException{
		String value, key;
		messageSort = Tool.manipulate(line, "messageSort=");
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
		System.out.println("Message: " + this.name);
		System.out.println("	messageSort: " + this.messageSort);
		/*if(this.messageSort != null){
			System.out.println("\tMessageSort: " + this.messageSort);
		}
		if(this.receiveEvent != null){
			this.receiveEvent.printProp();
		}
		if(this.sendEvent != null){
			this.sendEvent.printProp();
		}
		for(int i=0; i < listArgument.size(); i++){
			this.listArgument.get(i).printProp();
		}*/
	}

	public void genCode(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write(tabInd);
		if(messageSort != null){
			if(messageSort.equals("createMessage")){
				genCodeCreate(out);
		}
		} else{
			genCodeVariable(out);
			genCodeAttribute(out);
		}
	}

	private void genCodeVariable(BufferedWriter out) throws IOException {
		if(name.contains("=")){
			out.write(name.substring(0, name.indexOf("=")));
			out.write(" = ");
		}
	} 
	
	private void genCodeAttribute(BufferedWriter out) throws IOException {
		if(sendEvent != receiveEvent){
			receiveEvent.genCodeAttribute(out);
		}
	}
	
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