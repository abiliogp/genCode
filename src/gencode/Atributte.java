package gencode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Android;
import utilities.Parser;
import utilities.Tool;

public class Atributte extends DataModel {

	private String aggregation;
	private String defautlValue;
	private String defautlValueType;
	
	public Atributte(String name) {
		super(name);
		this.aggregation = "simple";
	}

	
	public void printProp() {

		System.out.println("\tAtributo: " + this.name);
		System.out.println("\t\tTipo: " + this.type);
		System.out.println("\t\tVisibilidade: " + this.visibility);
		System.out.printf("\t\tUpper Value: %s\n", this.upperValue == -1 ? "*" : this.upperValue);
		System.out.printf("\t\tLower Value: %s\n", this.lowerValue == -1 ? "*" : this.lowerValue);
		System.out.printf("\t\tDefault Value: %s\n", this.defautlValue);		
	}
	
	public void genCode(BufferedWriter out, int tab) throws IOException{
		this.tab = tab;
		tabInd = Tool.indentation(tab);
		if(lowerValue == '*' || upperValue == '*'){
				if(this.getLowerValue() == '1'){
					out.write("\n" + tabInd + "/** You must have at least ONE " +
							"Occurrence of Attribute: " + this.name + " in this class*/");
				}
				out.write("\n\t");
				out.write( !(this.visibility.equals("package")) ? "\n\t" + this.visibility + " " : "" );
				out.write("ArrayList<" + this.type + "> " + this.name + ";" );
			
		} else{
			out.write("\n" + tabInd);
			out.write( !(this.visibility.equals("package")) ?  this.visibility + " " : "" );
			out.write(this.type + " " + this.name);
			if(this.defautlValue != null){
				if(this.defautlValueType.equals("uml:LiteralString")){
					out.write(" = \"" + this.defautlValue + "\"");
				} else{
					out.write(" = " + this.defautlValue);	
				}
			}
			out.write(";");
		}
	}
	
	public void genCodeAtributteImplements(BufferedWriter out) throws IOException{
		if(lowerValue == '*' || upperValue == '*'){
				if(this.getLowerValue() == '1'){
					out.write("\n" + tabInd + "/** You must have at least ONE " +
							"Occurrence of Attribute: " + this.name + " in this class*/");
				}
				out.write("\n\tArrayList<" + this.type + "> " + this.name + ";" );
			
		} else{
			out.write("\n" + tabInd);
			out.write(this.type + " " +  this.name + ";" );
		}
	}
	
	public void genCodeGet(BufferedWriter out , int tab) throws IOException{
		tabInd = Tool.indentation(tab); 
		if( this.visbPrivate ){
			out.write("\n" + tabInd + "public " + this.type + " get" + this.name.substring(0, 1).toUpperCase().concat(this.name.substring(1)) + "(){");
			out.write("\n" + tabInd + "\treturn this." + this.name + ";\n\t}\n");
		}
	}
	
	public void genCodeSet(BufferedWriter out, int tab) throws IOException{
		tabInd = Tool.indentation(tab); 
		if( this.visbPrivate ){
			out.write("\n" + tabInd + "public void set" + this.name.substring(0, 1).toUpperCase().concat(this.name.substring(1)) 
					+ "( " + this.type + " " + this.name + " ){");
			out.write("\n"+ tabInd + "\t this." + this.name + " = " + this.name + ";\n\t}\n");
		}
	}
	
	public void genCodeConstructor(BufferedWriter out) throws IOException{
		if(lowerValue == '*' || upperValue == '*'){
			out.write("\n\t\tthis." + name + " = new ArrayList<" + type + ">();" );
		} else{ 
			out.write("\n\t\tthis." + name + " = " + name + ";");
		}	
	}

	public void genCodeConstructorSignature(BufferedWriter out) throws IOException{
		if(lowerValue != '*' && upperValue != '*'){
			out.write(" " + this.type + " " + this.name + " ");
		}
	}
	
	public void genCodeImports(BufferedWriter out) throws IOException{
		
		try {
			if (Android.Widget.valueOf(name) != null) {
				out.write("import android.widget." + name + ";\n");
			}
			else if (Android.View.valueOf(name) != null) {
				out.write("import android.view." + name + ";\n");
			}
			else if (Android.Content.valueOf(name) != null) {
				out.write("import android.content." + name + ";\n");
			}
			else if (Android.res.valueOf(name) != null) {
				out.write("import android.content.res." + name + ";\n");
			}
			else if (Android.os.valueOf(name) != null) {
				out.write("import android.os." + name + ";\n");
			}
			else if (Android.adntroidUtil.valueOf(name) != null) {
				out.write("import android.util." + name + ";\n");
			}
			else if (Android.javaUtil.valueOf(name) != null) {
				out.write("import java.util." + name + ";\n");
			}
		} catch (java.lang.IllegalArgumentException ex) {}		
	}

	/**
	 * 
	 * @param bf
	 * @param line
	 * @return needImport (boolean)
	 * @throws IOException
	 */
	public boolean parser(BufferedReader bf, String line) throws IOException {
		String value = null, key;
		boolean needImport = false;
		if (line.contains("visibility=")) {
			visibility = Tool.manipulate(line, "visibility=");
			if( (visibility != null) && ((visibility.equals("private")) || (visibility.equals("protected"))) ){
				Parser.getModel().getLastClasse().setNeedGetSet(true);
				visbPrivate = true;
			}
		} 
		
		if (line.contains("aggregation=")) {
			aggregation = Tool.manipulate(line, "aggregation=");
		}
		if (line.contains("type=")) {
			value = Tool.manipulate(line, "type=");
			if (value.charAt(0) == '_') {
				value = Tool.getTrieID(value);
			}
			type = value;
		}
		if (line.contains("/>")) {
			line = "</ownedAttribute>";
		} else{
			for (line = bf.readLine(); !((line.contains("</ownedAttribute>")) ||  (line.contains(" </ownedEnd>"))) ; line = bf.readLine() ) {
				if (line.contains("uml:Stereotype")) {
					value = Tool.manipulate(line, "pathmap:", "#", "\"");
					value = Tool.getTrieID(value);
					type = value;
				}
				if (line.contains("uml:PrimitiveType")) {
					value = parserType(value, line);
					type = value;
				}
				if ( (line.contains("upperValue"))  && (line.contains("value=")) ) {
					value = Tool.manipulate(line, "value");
					upperValue = value.charAt(0);
					if( value.substring(0, 1).equals("*") ){
						needImport = true;
					}
				}
				if ( (line.contains("lowerValue")) && (line.contains("value=")) ) {
					value = Tool.manipulate(line, "value");
					lowerValue = value.charAt(0);
				}
				if ( (line.contains("defaultValue")) && (line.contains("value=")) ) {
					defautlValueType = Tool.manipulate(line, "type");
					defautlValue = Tool.manipulate(line, "value");
				}  
			}// end for
		}
		return needImport;
	}

}
