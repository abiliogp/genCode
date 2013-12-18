package generator.Csharp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import model.Model;
import model.structure.Classe;
import model.structure.Interface;
import generator.GeneratorStrategy;

public class ModelCsharp implements GeneratorStrategy{

	private Model model;
	private File dir;
	
	private ClasseCsharp generatorClasse;
	
	public ModelCsharp(Model model){
		this.model = model;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		System.out.println("strategy model csharp");

		dir = new File("out/" + model.getName());
		dir.mkdir();
		for(Classe classe :  model.getListClasse()){
			generatorClasse = new ClasseCsharp(classe, model.getName());
			generatorClasse.codeGenerator(out, tab + 1);
		}
//		
//		for(Interface inter : model.getListInterface()){
//			generatorInterface = new InterfaceAndroid(inter);
//			generatorInterface.codeGenerator(out, tab);
//		}	
	}
	
}
