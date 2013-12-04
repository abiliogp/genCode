package generator.Android;

import java.io.File;
import java.io.IOException;

import model.Model;
import generator.generatorStrategy;

public class ModelAndroid implements generatorStrategy{

	
	private Model model;
	private File dir;
	
	public ModelAndroid(Model model){
		this.model = model;
	}
	
	@Override
	public void codeGenerator() throws IOException {
		System.out.println("strategy model");
		
		dir = new File("./out/" + model.getName());
		dir.mkdir();
		for(int i = 0; i < model.getListClasse().size(); i++){
			model.getListClasse().get(i).genCode();
		}
		for(int i = 0; i < model.getListInterface().size(); i++){
			model.getListInterface().get(i).genCode();
		}	
	}

}
