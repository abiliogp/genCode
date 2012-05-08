package gencode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Parser;
import utilities.Tool;

public class Parametro extends DataModel{

	private String direction;

	public Parametro(String name) {
		super(name);
		this.direction = "in";
	}

	public Parametro(String name, String type) {
		super(name);
		this.type = type;
		this.direction = "in";
	}
	
	// set

	public void setDirection(String direction) {

		this.direction = direction;
	}

	// get
	

	public String getDirection() {

		return this.direction;
	}
	
	public void printProp(){
		
		System.out.printf("\t\tParâmetro: %s Tipo: %s Direção: %s Visibilidade: %s \n" , 
				this.name, this.type, this.direction , this.visibility);
	}
	
	public void genCode(BufferedWriter out) throws IOException{
		out.write(this.type + " " + this.name);
	}
	
	//Return
	public void genCodeReturn(BufferedWriter out) throws IOException{
		if(this.getLowerValue() == '*' || 
			this.getUpperValue() == '*'){
				if(this.getLowerValue() == '1'){
					out.write("\n\t/*\n\t *You must have at least ONE" +
							"\n\t *Occurrence of Attribute: " + this.name + " in this class\n\t */");
				}
				out.write(this.visibility != null ? "\n\t" + this.visibility + " " : "" );
				out.write("ArrayList<" + this.type + "> " + this.name + ";" );
			
		} else{
			out.write(this.visibility != null ? "\n\t" + this.visibility + " " : "" );
			out.write(this.type + " " + this.name + ";" );
		}
	}

	
	
	
	
	
	public void parser(BufferedReader bf, String line) throws IOException {
		String value = null;
		if(line.contains("visibility=")){
			visibility = Tool.manipulate(line, "visibility=");
		}
		if (line.contains("type=")){
			value = Tool.manipulate(line, "type=");
			type = Tool.getTrieID(value);
		}
		
		if (line.contains("direction=")) {
			direction = Tool.manipulate(line, "direction=");
		} 

		if (line.contains("/>")) {
			line = "</ownedParameter>";
		} else {
			for ( ; !(line.contains("</ownedParameter>")); line = bf.readLine()) {
				if (line.contains("uml:Stereotype")) {
					value = Tool.manipulate(line, "pathmap:", "#", "\"");
					type = Tool.getTrieID(value);					
				}
				if (line.contains("uml:PrimitiveType")) {
					type = parserType(value, line);
				}
				if (line.contains("upperValue")) {
					value = Tool.manipulate(line, "value");
					upperValue = value.charAt(0);
					if( value.substring(0, 1).equals("*") ){
						Parser.getModel().getLastClasse().setNeedImport(true);
					}
				}	
				if (line.contains("lowerValue")) {
					value = Tool.manipulate(line, "value");
					lowerValue = value.charAt(0);
				}
			}// end for
		}
	}
	
	
	
	
}
