package generator.Android;

import java.io.File;
import java.io.IOException;

import model.Model;
import model.structure.Classe;
import model.structure.Interface;
import generator.GeneratorStrategy;

public class ModelAndroid implements GeneratorStrategy{

	
	private Model model;
	private File dir;
	
	public ModelAndroid(Model model){
		this.model = model;
	}
	
	@Override
	public void codeGenerator() throws IOException {
		System.out.println("strategy model");

		dir = new File("out/" + model.getName());
		dir.mkdir();
		for(Classe classe :  model.getListClasse()){
			classe.genCode();
		}
		
		for(Interface inter : model.getListInterface()){
			inter.genCode();
		}	
	}

}
