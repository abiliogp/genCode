package model;

import generator.generatorStrategy;
import generator.Android.ClasseAndroid;
import generator.Android.ModelAndroid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.sequence.Interaction;
import model.structure.Classe;
import model.structure.Interface;

public class Model {

	private generatorStrategy generator;
	
	protected String name;
	
	protected ArrayList<Classe> listClasse;
	private ArrayList<Interface> listInterface;
	
	private ArrayList<Interaction> listInteraction;
	
	private File dir;
	
	public Model(String name){
		this.name = name;
		
		listClasse = new ArrayList<Classe>();
		listInteraction = new ArrayList<Interaction>();
		listInterface = new ArrayList<Interface>();
	}
	
	public void addClasse(Classe classe){	
		listClasse.add(classe);
	}
	
	public void addInteraction(Interaction interaction){
		listInteraction.add(interaction);
	}

	public void addInterface(Interface inter){
		listInterface.add(inter);
	}

	
	//get
	public Classe getLastClasse(){
		return this.listClasse.get(this.listClasse.size()-1);
	}
	
	public Classe getIndexOfClasse(int index){		
		return this.listClasse.get(index);
	}
	
	public ArrayList<Classe> getListClasse() {
		return this.listClasse;
	}

	public ArrayList<Interface> getListInterface() {
		return this.listInterface;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public File getFile(){
		return this.dir;
	}
	
	public void printProp(){
		System.out.println("Nome Arquivo: " + name);
		for(int i = 0; i < this.listClasse.size(); i++){
			this.listClasse.get(i).printProp();
		}
		for(int i = 0; i < this.listInteraction.size(); i++){
			this.listInteraction.get(i).printProp();
		}
		
	}
	
	
	public void genCode() throws IOException{
		generator = new ModelAndroid(this);
		generator.codeGenerator();
	}
	
	
	public void genCode2() throws IOException{
		
		dir = new File("out/" + name);
		dir.mkdir();
		for(int i = 0; i < listClasse.size(); i++){
			listClasse.get(i).genCode();
		}
		for(int i = 0; i < listInterface.size(); i++){
			listInterface.get(i).genCode();
		}
	}

	
}
