package model.structure;

import generator.Android.Android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import model.Model;
import utilities.Parser;
import utilities.Tool;

public class Attribute extends DataStructure {

	/*Attributes*/
	private String aggregation;
	private String defautlValue;
	private String defautlValueType;
	
	private boolean objectType;
	private boolean primitiveType;
	private boolean hasGetMethod;
	private boolean hasSetMethod;
	
	/*Constructor*/
	public Attribute(String name) {
		super(name);
		this.aggregation = "simple";
	}
	
	/*Get*/		
	public boolean isPrimitiveType(){
		return this.primitiveType; 
	}
	
	public boolean hasGetMethod(){
		return this.hasGetMethod;
	}
	
	public boolean hasSetMethod(){
		return this.hasSetMethod;
	}
	
	public String getDefaultValue(){
		return this.defautlValue;
	}
	
	public String getDefaultValueType(){
		return this.getDefaultValue();
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
		
		if(Model.getTrieMetodoName("get" + name.substring(0, 1).toUpperCase() + name.substring(1)) != null){
			hasGetMethod = true;
		}
		
		if(Model.getTrieMetodoName("set" + name.substring(0, 1).toUpperCase() + name.substring(1)) != null){
			hasSetMethod = true;
		}
		
		if (line.contains("visibility=")) {
			visibility = Tool.manipulate(line, "visibility=");
			if( (visibility != null) && ((visibility.equals("private")) || (visibility.equals("protected"))) ){
				Parser.getModel().getLastClasse().setNeedGetSet(true);
				isPrivate = true;
			}
		} 
		
		if (line.contains("aggregation=")) {
			aggregation = Tool.manipulate(line, "aggregation=");
		}
		if (line.contains(" type=")) {
			
			value = Tool.manipulate(line, " type=");
			if (value.charAt(0) == '_') {
				objectType = true;
				value = Model.getTrieID(value);
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
					value = Model.getTrieID(value);
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
		Attribute atributte = new Attribute(value);
		Model.putTrieAtributte(key,  atributte);
	}

}
