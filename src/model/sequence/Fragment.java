package model.sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import utilities.Tool;

public class Fragment extends DataSequence{

	/*
	 *Attributes
	 */
	private OperationEvent event;
	
	private Message message;
	
	private String operator;
	
	private String visibility;
	private String xmi;
	private boolean send;
	
	private ArrayList<Operand> listOperand;

	private Fragment start;
	private Fragment finish;
	private Fragment execution;
	
	private Interaction refersTo;

	private Lifeline covered;
	
	/*
	 *Constructor
	 */
	public Fragment(String name){
		super(name);
		if(name.contains("_MessageSend")){
			send = true;
			this.name = name.substring(0, name.indexOf("_MessageSend"));
		}
		listOperand = new ArrayList<Operand>();
		refersTo = null;
	}


	/*
	 *Get
	 */
	public OperationEvent getEvent(){
		return event;
	}
	
	public Message getLastMessage(){
		return message;
	}
	
	public String getXMI(){
		return xmi;
	}
	
	public Lifeline getCovered(){
		return covered;
	}
	
	/*
	 *Set
	 */
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	/*
	 * Enum
	 */
	private enum Operator{
		opt, alt, loop, seq, par, ref;
	}
	
	/*
	 * Add
	 */
	public void setEvent( OperationEvent event ){
		this.event = event;
	}
	
	public void setMessage( Message message ){
		this.message = message;
	}
	
	public void load(BufferedReader bf, String line) throws IOException{
		String value, key;
		if (line.contains("/>")){
			line = "</fragment>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</fragment>")) ; line = bf.readLine() ){
				if(line.contains("<fragment")){
					key = Tool.manipulate(line, "xmi:id");
					value = Tool.manipulate(line, "name=");
					Fragment fragment = new Fragment(value);
					Tool.putTrieFragment(key, fragment);
				}
			}
		}
	}
	
	/*
	 *Abstract Method of Super
	 */
	public void parser(BufferedReader bf, String line) throws IOException{
		String value, key;
		xmi = Tool.manipulate(line,"xmi:id=");
		type = Tool.manipulate(line, "xmi:type=");
		visibility = Tool.manipulate(line, "visibility=");
		//key = Tool.manipulate(line, "event=");
		//event = Tool.getTrieOperationEvent(key);
		key = Tool.manipulate(line, "message="); 
		message = Tool.getTrieMessage(key);
		key = Tool.manipulate(line, "covered=");
		covered = Tool.getTrieLifeline(key);
		operator = Tool.manipulate(line,"interactionOperator=");
		key = Tool.manipulate(line, "start=");
		start = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "start=");
		start = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "finish=");
		finish = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "execution=");
		execution = Tool.getTrieFragment(key);
		key = Tool.manipulate(line, "refersTo=");
		refersTo = Tool.getTrieInteraction(key);
		if (line.contains("/>")){
			line = "</fragment>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</fragment>")) ; line = bf.readLine() ){
				if(line.contains("<operand")){
					value = Tool.manipulate(line, "name=");
					Operand operand = new Operand(value);
					operand.parser(bf, line);
					listOperand.add(operand);
				}
			}
		}
	}


	public void printProp() {
		try{
			System.out.println("Fragment: " + this.name);
			System.out.println("\tType: " + this.type);
			//System.out.println("\tEvent: " + this.event.getName());
			System.out.println("\tOperator: " + this.operator);
			if(refersTo != null)
			System.err.println("\tRefer: " + refersTo.name);
			for(int i = 0; i < this.listOperand.size(); i++){
				this.listOperand.get(i).printProp();
			}
			this.message.printProp();
			//this.event.printProp();
			System.err.println("\trepresents " + this.covered.getRepresents().getUpperValue());
			
		} catch (NullPointerException e){}
	}


	public void genCodeCombined(BufferedWriter out, int tab) throws IOException {
		if(message != null)
		message.genCode(out, tab);
		if(operator != null){
			switch(Operator.valueOf(operator)){
				case opt:
					genCodeOpt(out, tab);
					break;
				case alt:
					genCodeAlt(out, tab);
					break;
				case loop:
					genCodeLoop(out, tab);
					break;
					
			}
		}		
	}


	


	public void genCode(BufferedWriter out, int tab) throws IOException {
		if(type.equals("uml:MessageOccurrenceSpecification")){
			genCodeMessage(out, tab);
		}
		if(type.equals("uml:CombinedFragment")){
			genCodeCombined(out, tab);
		}
		
		if(type.equals("uml:InteractionUse")){
			if(refersTo != null){
				refersTo.genCode(out, tab);
			}
		}
		
	}


	public void genCodeMessage(BufferedWriter out, int tab) throws IOException {
		if(this.send){
			genCodeMessageNormal(out, tab);
		}
//		if(event.getType().equals("uml:SendOperationEvent")){
//			if(event.getOperation().isSet()){
//				genCodeMessageSet(out, tab);
//			} else if(event.getOperation().isGet()){
//				genCodeMessageGet(out, tab);
//			} else{
//				genCodeMessageNormal(out, tab);
//			}
//		}
	}

	/**
	 * Geração de chamadas de método:
	 * 	para métodos get e set pertencentes ao modelo avaliado
	 * 	realiza geração de acordo com as otimizações Android
	 * @param out
	 * @param tab
	 * @throws IOException
	 */
	private void genCodeMessageSet(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		message.genCodeAtributteGetSet(out);
		out.write(" = ");
		message.genCodeArguments(out);
		out.write(";");
	}	
	
	private void genCodeMessageGet(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		message.genCodeVariable(out);
		out.write(" = ");
		message.genCodeAtributteGetSet(out);
		out.write(".");
		event.genCodeGet(out);
		out.write(";");
	}
	
	private void genCodeMessageNormal(BufferedWriter out, int tab) throws IOException {
		message.genCode(out, tab);
		out.write(name +"(");
		if(message.getMessageSort()!= null)
		if(message.getMessageSort().equals("createMessage"))
			return;
		//event.genCode(out);
		message.genCodeArguments(out);
		out.write(");");
	}
	
	private void genCodeOpt(BufferedWriter out, int tab) throws IOException {
		listOperand.get(0).genCodeOpt(out, tab);
	}

	private void genCodeAlt(BufferedWriter out, int tab) throws IOException {
		String tabInd, tabSubInd;
		tabInd = Tool.indentation(tab);
		tabSubInd = Tool.indentation(tab + 1);
		if(listOperand.size() > 2){
			if(listOperand.get(0).getGuard().isExpressionLogic()){
				out.write(tabInd + "switch(");
				listOperand.get(0).getGuard().genCodeVariable(out);
				out.write("){");
				int i = 0;
				for(; i < listOperand.size() ; i++){
					if( !(listOperand.get(i).getGuard().isExpressionLogic()) ){
						break;
					}
					out.write("\n" + tabSubInd);
					listOperand.get(i).genCodeCaseSwitch(out, tab + 1);
				}
				out.write("\n" + tabSubInd + "default:");
				out.write("\n\t" + tabSubInd + "break;");
				out.write("\n" + tabSubInd + "}//endSwitch\n");
				for(; i < listOperand.size() ; i++){
					listOperand.get(i).genCodeOpt(out, tab);
				}
			}
		} else{
			listOperand.get(0).genCodeOpt(out, tab);
			out.write(" else ");
			listOperand.get(1).genCodeOpt(out, tab);
		}
		
	}
	
	private void genCodeLoop(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		if(listOperand.get(0).getGuard().getMinint() != null){
			genCodeFor(out,tab);
		}
		else {
			 genCodeWhile(out,tab);
		}
		out.write("){");
		listOperand.get(0).genCode(out, tab);
		out.write("\n" + tabInd + "}");
	}

	
	private void genCodeWhile(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write(tabInd + "while(");
		if(listOperand.get(0).getGuard().getSpecification().isLogic()){
			if((listOperand.get(0).getGuard().getSpecification().getValue().equals("false")) ||
			   (listOperand.get(0).getGuard().getSpecification().getExpression().equals("!=")))
			{
				out.write("!");
			}
			listOperand.get(0).getGuard().genCodeVariable(out);
		} else{
			listOperand.get(0).getGuard().getSpecification().genCode(out);
		}
	}

	private void genCodeFor(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		if(this.covered.getRepresents().getUpperValue() == '*'){//for		
			if(this.covered.getRepresents().isPrimitiveType()){
				out.write("for(int i=" + listOperand.get(0).getGuard().getMinint() + "; " +
						  "i < " + this.covered.getRepresents().getName() + "length() ; i++");
			} else{
				out.write("for(" + this.covered.getRepresents().getType() + " c : " + this.covered.getRepresents().getName());
			}
		}
		else if(listOperand.get(0).getGuard().getMinint() != null) {
			listOperand.get(0).getGuard().genCodeForNormal(out);
		}
		
	}
	
	public void genCodeAttribute(BufferedWriter out) throws IOException {
		if(covered != null){
			covered.genCodeAttribute(out);
		}
	}


	public void genCodeAttributeGetSet(BufferedWriter out) throws IOException {
		if(covered != null){
			covered.genCodeAttributeGetSet(out);
		}
	}

	public void genCodeCreate(BufferedWriter out) throws IOException {
		if(covered != null){
			covered.genCodeCreate(out);
		}
	}
}