package gencode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import sequence.Interaction;
import utilities.Parser;
import utilities.Tool;

public class Method extends DataModel{
	
	
	
	private ArrayList<Parametro> listParametro;
	private ArrayList<Operation> listOperacao;
	private ArrayList<Parametro> listRetorno;
	
	private Interaction interaction;
	private boolean isAbstract;
	
	public Method(String name){
		super(name);
		listParametro = new ArrayList<Parametro>();
		listOperacao = new ArrayList<Operation>();
		listRetorno = new ArrayList<Parametro>();
		isAbstract = false;
	}

	//add	
	public void addParametro(String name){
		this.listParametro.add(new Parametro(name));
	}
	
	public void addOperation(Operation operation){
		this.listOperacao.add(operation);
	}
	
	public void addReturn(String name ){
		this.listRetorno.add(new Parametro(name));
	}
	
	//get	
	public Operation getLastOperation(){
		return this.listOperacao.get(this.listOperacao.size() -1);
	}
	
	public Operation getIndexOfListOperacao(int index){
		
		return this.listOperacao.get(index);
	}
	

	public Parametro getLastParametro() {

		return this.listParametro.get(this.listParametro.size() - 1);
	}

	public Parametro getIndexOflistParametro(int index) {

		return this.listParametro.get(index);
	}	
	
	public boolean isAbstract(){
		return this.isAbstract;
	}
	
	public void isAbstract(boolean isAbstract){
		this.isAbstract = isAbstract;
	}
	
	public Parametro getLastReturn(){
		return this.listRetorno.get(this.listRetorno.size() - 1);
	}
	

	public ArrayList<Parametro> getListReturn(){
		return this.listRetorno;
	}
	
	
	
	
	
	
	
	public void printProp(){
		
		System.out.println( "\tMétodo: " + this.name );
		System.out.println("\t\tVisibilidade: " + this.visibility);
		
		for(int i = 0 ; i < this.listOperacao.size(); i++){
			this.listOperacao.get(i).printProp();
		}
		if(interaction != null){
			interaction.printProp();
		}
		
		for(int i = 0 ; i < this.listParametro.size(); i++){
			this.listParametro.get(i).printProp();
		}
		
		for(int i = 0 ; i < this.listRetorno.size(); i++){
			this.listRetorno.get(i).printProp();
		}
	}
	
	
	public void genCodeMtImplements(BufferedWriter out) throws IOException{
		out.write("\n\t" + this.type + " " + this.name );
		genCodePmtIn(out);
		out.write(";");
	}
	
	
	public void genCode(BufferedWriter out) throws IOException{
		out.write("\n\t" + this.visibility);
		if( (this.isStatic) ){
			out.write(" static");
		}
		if( (this.isAbstract) ){
			out.write(" abstract");
		}
		out.write(" " + this.type + " " + this.name );
		if(this.name.equals("main")){
			out.write("( String args[] )");
		} else{
			genCodePmtIn(out);
		}
		if( (this.isAbstract) ){
			out.write(";");
		} else{
			genCodeCpMet(out);
		}
	}
	
	
	//Gera os Parametros de Entrada
	private void genCodePmtIn(BufferedWriter out) throws IOException{
		out.write("(");
		for( int i = 0 ; i < this.listParametro.size() ; i++ ){
			if ( (this.listParametro.get(i).getDirection().equals("in")) || 
			     (this.listParametro.get(i).getDirection().equals("inout")) ){
				this.listParametro.get(i).genCode(out);
			}
			if( this.listParametro.size() > (i+1) ){
				if ( !this.listParametro.get(i+1).getDirection().equals("out")){
					out.write(", ");
				}
			}
		}
		out.write(")");
	}
	
	//gera o corpo do método
	private void genCodeCpMet(BufferedWriter out) throws IOException{
		//gera declaração dos out dentro do método
		out.write("{");
		this.genCodeDeclPmtOut(out);
		if(interaction != null){
			interaction.genCode(out,2);
		}
		this.genCodeReturnPmtOut(out);
		this.genCodeReturn(out);
		out.write("\n\t}\n");
	}
	
	//gera declaração dos parametros out dentro do método
	private void genCodeDeclPmtOut(BufferedWriter out) throws IOException{
		for(int i = 0; i < this.listParametro.size() ; i++){
			if(this.listParametro.get(i).getDirection().equals("out")){
				out.write("\n\t\t");
				this.listParametro.get(i).genCode(out);
				out.write(";");
			}
		}
	}

	//gera return dos out dentro do método
	private void genCodeReturnPmtOut(BufferedWriter out) throws IOException{
		for(int i = 0; i < this.listParametro.size() ; i++){
			if(this.listParametro.get(i).getDirection().equals("out")){
				if( !(this.listParametro.get(i).getType().equals(this.type)) ){
					out.write("\n\t\t/*\n\t\t *WARNING: The Type of Return <" +
							   this.listParametro.get(i).getType() + 
			   		   			"> \n\t\t *disagree of type the Method\n\t\t */");
				}
				out.write("\n\t\treturn " + this.listParametro.get(i).getName() + ";");
			}
		}
	}
	
	//gera return
	private void genCodeReturn(BufferedWriter out) throws IOException{
		for(int i = 0; i < this.listRetorno.size() ; i++){
			if( !(this.listRetorno.get(i).getType().equals(this.type)) ){
				out.write("\n\t\t/*\n\t*WARNING: The Type of Return <" +
						   this.listRetorno.get(i).getType() + 
				   		   "> \n\t *disagree of type the Method\n\t */");
			}
			out.write("\n\t\treturn " + this.listRetorno.get(i).getName() + ";");
		}
	}

	public void genCodeMtSuper(BufferedWriter out) throws IOException{
		out.write("\n\t/*\n\t *Abstract Method of Super\n\t */");
		out.write("\n\t" + this.visibility);
		out.write(" " + this.type + " " + this.name);
		this.genCodePmtIn(out);
		this.genCodeCpMet(out);
		
	}

	public void genCodeCall(BufferedWriter out) throws IOException {
		out.write(name + "(" );
	}
	
	public void parser(BufferedReader bf, String line) throws IOException {
		String value, str, key;

		if (line.contains("visibility=")) {
			visibility = Tool.manipulate(line, "visibility=");
		}
		
		if (line.contains("isAbstract=")) {
			isAbstract = true;
		}
		
		if (line.contains("isStatic=")) {
			isStatic = true;
		}
		
		if (line.contains("method=")) {
			value = Tool.manipulate(line, "method=");
			interaction = Tool.getTrieInteraction(value);
			/*
			for (int i = 0; i < value.length(); i = +23) {
				str = value.substring(i, i + 23);
				listOperacao.add(Tool.getTrieOperation(str));
			}
			*/
		}
		
		if (line.contains("/>")) {
			if( isAbstract ){
				parserMetodoAbstrato();
			}
			return;
		} else {
			for ( ; !(line.contains("</ownedOperation>")); line = bf.readLine() ) {
				if (line.contains("<ownedParameter")) {
					key = Tool.manipulate(line, "xmi:id=");
					value = Tool.manipulate(line, "name");
					Parametro parametro = new Parametro(value);
					parametro.parser(bf, line);
					if( (type.equals("void")) && !(parametro.getDirection().equals("in"))){
						type = parametro.getType();
					}
					if( (parametro.getDirection().equals("return")) ){
						listRetorno.add(parametro);
					} else{
						listParametro.add(parametro);	
					}
				}// end if owedParameter
			}// end for
		}
		if( isAbstract ){
			parserMetodoAbstrato();
		}
	}
	
	private static void parserMetodoAbstrato(){
		String str;
		str = Parser.getModel().getLastClasse().getName();
		if( Tool.containsKeyTrieAbstractMethod(str) ){
			Tool.addTrieAbstractMethod(str, Parser.getModel().getLastClasse().getLastMetodo());
		} else{
			ArrayList<Method> listMetAbs = new ArrayList<Method>();
			listMetAbs.add(Parser.getModel().getLastClasse().getLastMetodo());
			Tool.putTrieAbstractMethod(str, listMetAbs);
		}
	}

	
	
	
	
}//end class
