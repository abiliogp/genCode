package generator.Android;

import java.io.BufferedWriter;
import java.io.IOException;

import model.structure.RealizationInterface;
import generator.GeneratorStrategy;

public class RealizationAndroid implements GeneratorStrategy{

	private RealizationInterface realization;
	private InterfaceAndroid generator; 
	
	public RealizationAndroid(RealizationInterface realization){
		this.realization = realization;
	}
	
	
	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		// TODO Auto-generated method stub
		
	}


	
	
	public void generatorAttributes(BufferedWriter out) throws IOException{
		if(!realization.getSupplier().getAttributes().isEmpty()){
			out.write("\n\t/** Atributte From " + realization.getName() + " */");
			generator = new InterfaceAndroid(realization.getSupplier());
			generator.genCodeAtributte(out);
		}
	}
	
	public void generatorMethods(BufferedWriter out) throws IOException{
		if(!realization.getSupplier().getMethods().isEmpty()){
			out.write("\n\t/** Methods From " + realization.getName() + " */");
			generator = new InterfaceAndroid(realization.getSupplier());
			generator.genCodeMethods(out, 0);
		}
	}

}
