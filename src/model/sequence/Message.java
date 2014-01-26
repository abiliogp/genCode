package model.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Model;
import utilities.Tool;

public class Message extends DataSequence{

	/*Attributes*/
	private ArrayList<Argument> arguments;
	
	private Fragment sendEvent;
	private Fragment receiveEvent;
	
	private String sort;
	private String number;
	
	//variavel que pega o retorno de um método
	private String variable;
	
	/*Constructor*/
	public Message(String name){
		super(name);
		this.sort = "syn";
		this.arguments = new ArrayList<Argument>();
	}

	public String getSort(){
		return this.sort;
	}
	
	public String getVariable(){
		return this.variable;
	}
	
	public Fragment getReceiveEvent(){
		return this.receiveEvent;
	}
	
	public ArrayList<Argument> getArguments(){
		return this.arguments;
	}
	
	public void parser(BufferedReader bf, String line) throws IOException{
		String value, key;

		number = name.substring(0, 1);
		name = Tool.manipulate(name, number, ":", "_Message");
		if(name.contains("=")){
			variable = name.substring(0, name.indexOf("=")-2);
			name = name.substring(name.indexOf("=") + 2);
		}
		if(line.contains("messageSort=")){
			sort = Tool.manipulate(line, "messageSort=");
		}
		key = Tool.manipulate(line, "receiveEvent=");
		receiveEvent = Model.getTrieFragment(key);
		key = Tool.manipulate(line, "sendEvent=");
		sendEvent = Model.getTrieFragment(key);
		if (line.contains("/>")){
			line = "</message>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</message>")) ; line = bf.readLine() ){
				if(line.contains("<argument")){
					value = Tool.manipulate(line, "name=");
					Argument argument = new Argument(value);
					argument.parser(bf, line);
					arguments.add(argument);
				}
			}
		}
	}

	public void printProp() {
		System.out.println("Message: " + name);
		System.out.println("\tsort: " + sort);
		System.out.println("\tvariable: " + (variable == null ? "NOT!" : variable));
		System.out.println("\tnumber: " + number);
		for(int i = 0 ; i <  this.arguments.size(); i++){
			System.out.print("\targument" + (i + 1) + ": ");
			arguments.get(i).printProp();
		}
	}

	
}