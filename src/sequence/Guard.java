package sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;

public class Guard extends DataSequence{

	private Specification specification;
	
	private String minintName;
	private String minintValue;
	
	private String maxintName;
	private String maxintValue;
	
	
	public Guard(String name) {
		this.name = name;
	}
	
	public Specification getSpecification(){
		return specification;
	}

	
	public void parser(BufferedReader bf, String line) throws IOException {
		String value;
		if (line.contains("/>")){
			line = "</guard>";
		} else{
			for (line = bf.readLine() ; !(line.contains("</guard>")) ; line = bf.readLine() ){
				if(line.contains("<specification")){
					value = Tool.manipulate(line, "name=");
					specification = new Specification(value);
					specification.parser(bf, line);
				}
				if(line.contains("<minint")){
					minintName = Tool.manipulate(line, "name=");
					minintValue = Tool.manipulate(line, "value=");
				}
				if(line.contains("<maxint")){
					minintName = Tool.manipulate(line, "name=");
					minintValue = Tool.manipulate(line, "value=");
				}
			}
		}
		
	}

	

	public void printProp() {
		System.out.println("Guard " + this.name);
		this.specification.printProp();
	}

	public boolean isExpressionLogic(){
		return specification.isExpressionLogic();
	}
	
	public boolean isValueNumeric(){
		return specification.isNumeric();
	}
	
	public void genCode(BufferedWriter out) throws IOException {
		specification.genCode(out);
	}
	
	public void genCodeValue(BufferedWriter out) throws IOException {
		specification.genCodeValue(out);
	}
	
	public void genCodeVariable(BufferedWriter out) throws IOException {
		specification.genCodeVariable(out);
	}
}
