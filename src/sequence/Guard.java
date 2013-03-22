package sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;

public class Guard extends DataSequence{

	private Specification specification;
	
	private GuardInt maxint;
	private GuardInt minint;
	
	public Guard(String name) {
		super(name);
	}
	
	public Specification getSpecification(){
		return specification;
	}
	
	public String getMaxint() {
		return maxint.getValue();
	}

	public String getMinint() {
		return minint.getValue();
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
					value = Tool.manipulate(line, "name=");
					minint = new GuardInt(value);
					minint.setValue(Tool.manipulate(line, "value="));
					minint.setType(Tool.manipulate(line, "type="));
				}
				if(line.contains("<maxint")){
					value = Tool.manipulate(line, "name=");
					maxint = new GuardInt(value);
					maxint.setValue(Tool.manipulate(line, "value="));
					maxint.setType(Tool.manipulate(line, "type="));
				}
			}
		}
		
	}

	

	public void printProp() {
		System.out.println("Guard " + this.name);
		this.specification.printProp();
		this.maxint.printProp();
		this.minint.printProp();
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

	public void genCodeForNormal(BufferedWriter out) throws IOException {
		out.write("for(int i=" + minint.getValue() + "; i < " + this.specification.getBody() + "; i++");
	}

	
}
