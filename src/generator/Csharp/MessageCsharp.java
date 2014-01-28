package generator.Csharp;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.sequence.Argument;
import model.sequence.Message;
import generator.GeneratorStrategy;

public class MessageCsharp implements GeneratorStrategy{
	
	private Message message;
	private FragmentCsharp generatorFragment;
	
	public MessageCsharp(Message message){
		this.message = message;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		if(message.getSort().equals("createMessage")){
			genCodeCreate(out);
		} else{
			genCodeVariable(out);
			genCodeAtributte(out);
		}
	}
	

	public void genCodeVariable(BufferedWriter out) throws IOException {
		if(message.getVariable() != null){
			out.write(message.getVariable());
			out.write(" = ");
		}
	} 
	

	/*
	 * Faz chamadas de método da própria classe e de outros objetos
	 */
	public void genCodeAtributte(BufferedWriter out) throws IOException {
		//if(sendEvent.getCovered() != receiveEvent.getCovered()){
		generatorFragment = new FragmentCsharp(message.getReceiveEvent());
		generatorFragment.genCodeAttribute(out);
		//}
	}
	
	public void genCodeAtributteGetSet(BufferedWriter out) throws IOException {
		//if(sendEvent.getCovered() != receiveEvent.getCovered()){
		generatorFragment = new FragmentCsharp(message.getReceiveEvent());
		generatorFragment.genCodeAttributeGetSet(out);
		//}
	}
	
	/*
	 * Gera os n argumentos para um chamada de método
	 */
	public void genCodeArguments(BufferedWriter out) throws IOException {
		int i = 0;
		for(Argument argument : message.getArguments()){
			out.write(argument.getValue());
			if(i++ < message.getArguments().size() - 1){
				out.write(",");
			}
		}
	}

	private void genCodeCreate(BufferedWriter out) throws IOException {		
		generatorFragment = new FragmentCsharp(message.getReceiveEvent());
		generatorFragment.genCodeCreate(out);
	}
	
	
	
}
