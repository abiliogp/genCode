package utilities;

import gencode.Classe;
import gencode.Interface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import sequence.Interaction;

public class Model {

	protected String name;
	
	protected ArrayList<Classe> listClasse;
	
	private ArrayList<Interaction> listInteraction;
	
	private ArrayList<Interface> listInterface;
	
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
