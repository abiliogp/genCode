package model.sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import model.structure.Attribute;
import model.structure.Parametro;
import utilities.Tool;

public class Lifeline extends DataSequence{

	/*
	 *Attributes
	 */
	private Attribute represents;
	private String parameter;
	
	private ArrayList<Fragment> coveredBy;
	private ArrayList<Fragment> order;

	/*
	 *Constructor
	 */
	public Lifeline(String name){
		super(name);
		this.coveredBy = new ArrayList<Fragment>();
		this.order = new ArrayList<Fragment>();
	}


	/*
	 *Get
	 */
	public Attribute getRepresents(){
		return this.represents;
	}

	public Fragment getLastFragment(){
		return this.coveredBy.get(this.coveredBy.size() - 1);
	}
	
	public Fragment getIndexOfFragment(int index){
		return this.coveredBy.get(index);
	}


	/*
	 *Set
	 */
	public void setRepresents( Attribute represents ){
		 this.represents = represents;
	}

	/*
	 * Add
	 */
	public void addFragment( Fragment coveredBy ){
		 this.coveredBy.add(coveredBy);
	}

	/*
	 *Abstract Method of Super
	 */
	public void parser(BufferedReader bf, String line) throws IOException{
		String value, key;
		if(line.contains("represents=")){//se tiver atributo associado a lifeline
			key = Tool.manipulate(line, "represents=");
			represents = Tool.getTrieAtributte(key);
			if( represents == null) {
				parameter = Tool.getTrieID(key);
			}
		}
		value = Tool.manipulate(line, "coveredBy=");
		for(int i = 0; i < value.length() ; i+=24){
			key = value.substring(i, i + 23);
			if(Tool.containsTrieXMIfragment(key)){
				order.add(Tool.getTrieFragment(key));
			}
		}
	}

	
	
	public void printProp() {
		System.out.println("Lifeline: " + this.name);
		if(represents != null){
			System.out.println("represents " + represents.getName());
		}
		for(int i = 0; i < order.size(); i++){
			order.get(i).printProp();
		}
	}


	public void genCode(BufferedWriter out, int tab) throws IOException{
		for(int i = 0; i < order.size(); i++){
			order.get(i).genCode(out, tab);
		}
	}


	public void genCodeAttribute(BufferedWriter out) throws IOException {
		if(parameter != null){
			out.write(parameter + ".");
		} else {
			if(represents != null) {
				out.write(represents.getName() + ".");
			}
		}
	}

	public void genCodeAttributeGetSet(BufferedWriter out) throws IOException {
		if(parameter != null){
			out.write(parameter);
		} else {
			if(represents != null) {
				out.write(represents.getName());
			}
		}
	}

	public void genCodeCreate(BufferedWriter out) throws IOException {
		if(represents != null){
			out.write(represents.getName() + " = new " + represents.getType() + "();");
		}
	}


	

}