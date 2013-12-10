package generator.Android;

import java.io.BufferedWriter;
import java.io.IOException;

import model.structure.RealizationInterface;
import generator.GeneratorStrategy;

public class RealizationAndroid implements GeneratorStrategy{

	private RealizationInterface realization;
	
	public RealizationAndroid(RealizationInterface realization){
		this.realization = realization;
	}
	
	
	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		// TODO Auto-generated method stub
		
	}


	
	
	public void generatorAttributes(BufferedWriter out) throws IOException{
		if(realization.getSupplier().tamListAtributte() > 0){
			out.write("\n\t/** Atributte From " + realization.getName() + " */");
			realization.getSupplier().genCodeAtributte(out);
		}
	}
	
	public void generatorMethods(BufferedWriter out) throws IOException{
		if(realization.getSupplier().tamListMethod() > 0){
			out.write("\n\t/** Methods From " + realization.getName() + " */");
			realization.getSupplier().genCodeMethods(out, 0);
		}
	}

}
