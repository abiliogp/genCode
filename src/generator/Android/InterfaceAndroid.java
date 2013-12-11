package generator.Android;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utilities.Parser;
import model.structure.Attribute;
import model.structure.Interface;
import model.structure.Method;
import generator.GeneratorStrategy;

public class InterfaceAndroid implements GeneratorStrategy{

	private Interface inter;
	
	private AttributeAndroid generatorAttribute;
	private MethodAndroid generatorMethod;
	
	public InterfaceAndroid(Interface myInterface){
		this.inter = myInterface;
	}
	
	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		File file = new File("out/" + Parser.getModel().getName(), inter.getName().concat(".java"));
		out = new BufferedWriter( new FileWriter(file));
		
		out.write("\n" + inter.getVisibility() + " interface " + inter.getName()  + "{");
		
		//Imports
		if(inter.isNeedImport()){
			out.write("import java.util.ArrayList;\n");
		}
		
		//Atributos
		if(inter.getAttributes().size() > 0){
			out.write("\n\n\t/*\n\t *Attributes\n\t */");
			for(Attribute attr : inter.getAttributes()){
				generatorAttribute = new AttributeAndroid(attr);
				generatorAttribute.genCodeAtributteImplements(out);
			}
		}
		
		
		//Metodo
		if(inter.getMethods().size() > 0){
			out.write("\n\n\t/*\n\t *Method\n\t */");
			for(Method method : inter.getMethods()){
				generatorMethod = new MethodAndroid(method);
				generatorMethod.genCodeMtImplements(out);	
			}
		}
		
		out.write("\n}");
		out.close();
	}

	
	
	public void genCodeAtributte(BufferedWriter out) throws IOException{
		for(Attribute attr : inter.getAttributes()){
			generatorAttribute = new AttributeAndroid(attr);
			generatorAttribute.codeGenerator(out, 0);
		}
	}
	
	public void genCodeMethods(BufferedWriter out,int tab) throws IOException{
		for(Method method : inter.getMethods()){
			generatorMethod = new MethodAndroid(method);
			generatorMethod.codeGenerator(out, tab);	
		}
	}
	
	
}
