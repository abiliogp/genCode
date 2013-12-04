package model.structure;

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
	private boolean objectType;
	private boolean primitiveType;
	private boolean hasGetMethod;
	private boolean hasSetMethod;
	
	public Atributte(String name) {
		super(name);
		this.aggregation = "simple";
	}
			
	public boolean isPrimitiveType(){
		return this.primitiveType; 
	}
	
	public void printProp() {

		System.out.println("\tAtributo: " + this.name);
		System.out.println("\t\tTipo: " + this.type);
		System.out.println("\t\tPrimitivo: " + this.primitiveType);
		System.out.println("\t\tObjeto: " + this.primitiveType);
		System.out.println("\t\tVisibilidade: " + this.visibility);
		System.out.printf("\t\tUpper Value: %s\n", this.upperValue == -1 ? "*" : this.upperValue);
		System.out.printf("\t\tLower Value: %s\n", this.lowerValue == -1 ? "*" : this.lowerValue);
		System.out.printf("\t\tDefault Value: %s\n", this.defautlValue);	
		System.out.println("\t\thasGet "  + this.hasGetMethod);
		System.out.println("\t\thasGet "  + this.hasSetMethod);
	}
	
	public void genCode(BufferedWriter out, int tab) throws IOException{
		this.tab = tab;
		tabInd = Tool.indentation(tab);
		if(lowerValue == '*' || upperValue == '*'){
				if(this.getLowerValue() == '1'){
					out.write("\n" + tabInd + "/** You must have at least ONE " +
							"Occurrence of Attribute: " + this.name + " in this class*/");
				}
		} 
		out.write("\n" + tabInd);
	
		//otimização p android get e set visibilidade public
		if(this.hasGetMethod || this.hasSetMethod){
			out.write("public ");
		} else{
			out.write( !(this.visibility.equals("package")) ? "\n\t" + this.visibility + " " : "" );
		}
		
		//otimizações para android static final
		if(this.defautlValue != null || this.isStatic){
			out.write("static ");
			if(this.primitiveType){
				out.write("final ");
			}
		}
		
		if(lowerValue == '*' || upperValue == '*'){
			out.write("ArrayList<" + this.type + "> " + this.name);
		} else{
			out.write(this.type + " " + this.name);			
		}
		
		if(this.defautlValue != null){
			if(this.defautlValueType.equals("uml:LiteralString")){
				out.write(" = \"" + this.defautlValue + "\"");
			} else{
				out.write(" = " + this.defautlValue);	
			}
		}
		out.write(";");
	
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
		//otimização p android 
		if(this.hasGetMethod){
			return;
		}
		if( this.visbPrivate ){
			out.write("\n" + tabInd + "public " + this.type + " get" + this.name.substring(0, 1).toUpperCase().concat(this.name.substring(1)) + "(){");
			out.write("\n" + tabInd + "\treturn this." + this.name + ";\n\t}\n");
		}
	}
	
	public void genCodeSet(BufferedWriter out, int tab) throws IOException{
		tabInd = Tool.indentation(tab); 
		//otimização p android 
		if(this.hasSetMethod){
			return;
		}
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
		if (Android.os.contains(this.name)) {
			out.write("import android.os." + name + ";\n");
		}
		else if (Android.Widget.contains(name)) {
			out.write("import android.widget." + name + ";\n");
		}
		else if  (Android.View.contains(name)) {
			out.write("import android.view." + name + ";\n");
		}
		else if (Android.view.contains(name)) {
			out.write("import android.view.View." + name + ";\n");
		}
		else if (Android.Content.contains(name)) {
			out.write("import android.content." + name + ";\n");
		}
		else if (Android.res.contains(name)) {
			out.write("import android.content.res." + name + ";\n");
		}
		else if (Android.adntroidUtil.contains(name)) {
			out.write("import android.util." + name + ";\n");
		}
		else if (Android.javaUtil.contains(name)) {
			out.write("import java.util." + name + ";\n");
		}
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
		
		if(Tool.getTrieMetodoName("get" + name.substring(0, 1).toUpperCase() + name.substring(1)) != null){
			hasGetMethod = true;
		}

		
		if(Tool.getTrieMetodoName("set" + name.substring(0, 1).toUpperCase() + name.substring(1)) != null){
			hasSetMethod = true;
		}
		
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
		if (line.contains(" type=")) {
			
			value = Tool.manipulate(line, " type=");
			if (value.charAt(0) == '_') {
				objectType = true;
				value = Tool.getTrieID(value);
			} else{
				primitiveType = true;
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
					objectType = true;
				}
				if (line.contains("uml:PrimitiveType")) {
					value = parserType(value, line);
					type = value;
					primitiveType = true;
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
	
	public static void load(BufferedReader bf, String line) {
		String key, value;
		key = Tool.manipulate(line, "xmi:id");
		value = Tool.manipulate(line, "name=");
		Atributte atributte = new Atributte(value);
		Tool.putTrieAtributte(key,  atributte);
	}

}
