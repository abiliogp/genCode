package sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;

public class Specification extends DataSequence{
	
	private String language;

	private String body;
	private String variable;
	private String expression;
	
	private boolean isLogic;
	private boolean isNumeric;
	private boolean isExpressionLogic;
	
	
	public Specification(String name) {
		super(name);
		isLogic = false;
		isNumeric = false;
		isExpressionLogic = false;
	}
	
	public String getBody(){
		return body;
	}
	
	public String getExpression(){
		return expression;
	}
	
	
	public void parser(BufferedReader bf, String line) throws IOException {
		type = Tool.manipulate(line,"xmi:type=");
		body = Tool.manipulate(line, "value=");
		
		if (line.contains("/>")){
			line = "</specification>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</specification>")) ; line = bf.readLine() ){
			}
		}
		separate();
	}
	
	private void separate(){
		int delimiter, delimiterValue;
		delimiterValue = 1;
		if(body.contains("!")){
			delimiter = body.indexOf("!");
		} else{
			delimiter = body.indexOf("=");
		} if(delimiter == -1) {
			return;
		}
		variable = body.substring(0, delimiter);
		expression = body.substring(delimiter, delimiter + 1);
		if(body.substring(delimiter, delimiter + 2).equals("==") || 
		   body.substring(delimiter, delimiter + 2).equals("!="))
		{
			expression = body.substring(delimiter, delimiter + 2);
			delimiterValue++;
		}
		value = body.substring(delimiter + delimiterValue, body.length());
		isNumeric = value.matches("-?\\d+(.\\d+)?");
		if((value.equals("true")) || (value.equals("false"))){
			isLogic = true;
		}
		if((expression.equals("==")) || (expression.equals("!="))){
			isExpressionLogic = true;
		}
	}


	public void printProp() {
		System.out.println("Specification: " + this.name);
		System.out.println("\tBody: " + this.body);
		System.err.println("\tvariable: " +variable);
		System.err.println("\texpression: " +expression);
		System.err.println("\tvalue: " +value);
		System.out.println("\tLanguage: " + this.language);
		System.out.println("\tType: " + this.type);
	}

	public void genCode(BufferedWriter out) throws IOException {
		if(variable != null) {
			out.write(variable + " " +  expression + " " + value);
		} else {
			out.write(body);
		}
	}
	
	public void genCodeVariable(BufferedWriter out) throws IOException {
		out.write(variable);
	}
	
	public void genCodeValue(BufferedWriter out) throws IOException {
		out.write(value);
	}

	public boolean isLogic(){
		return isLogic;
	}
	
	public boolean isNumeric(){
		return isNumeric; 
	}
	
	public boolean isExpressionLogic(){
		return isExpressionLogic;
	}
}
