package model;

import generator.GeneratorStrategy;
import generator.Android.ClasseAndroid;
import generator.Android.ModelAndroid;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import model.sequence.Fragment;
import model.sequence.Interaction;
import model.sequence.Lifeline;
import model.sequence.Message;
import model.sequence.OperationEvent;
import model.structure.Attribute;
import model.structure.Classe;
import model.structure.Interface;
import model.structure.Method;
import model.structure.Operation;

public class Model {

	private GeneratorStrategy generator;
	
	protected String name;
	
	
	protected ArrayList<Classe> listClasse;
	private ArrayList<Interface> listInterface;
	
	private ArrayList<Interaction> listInteraction;
	
	
	/*
	 * Trie Structural 
	 */
	private static TreeMap<String, String> trieID = new TreeMap<String, String>();
	
	private static TreeMap<String, Method> trieMethod = new TreeMap<String, Method>(); 
		
	private static TreeMap< String, ArrayList<Method> > trieAbstractMethod = new TreeMap< String, ArrayList<Method> >();
	
	private static TreeMap<String, Attribute> trieAtributte = new TreeMap<String, Attribute>();
	
	private static TreeMap<String, Operation> trieOperation = new TreeMap<String, Operation>();
	
	private static TreeMap<String, Interface> trieInterface = new TreeMap<String, Interface>();
	
	/*
	 * Trie Sequence 				 
	 */
	private static TreeMap<String, Interaction> trieInteraction = new TreeMap<String, Interaction>(); 
	
	private static TreeMap<String, Lifeline> trieLifeline = new TreeMap<String, Lifeline>();
	
	private static TreeMap<String, Fragment> trieFragment = new TreeMap<String, Fragment>();

	private static TreeMap<String, Message> trieMessage = new TreeMap<String, Message>();
	
	private static TreeMap<String, OperationEvent> trieOperationEvent = new TreeMap<String, OperationEvent>();

	private static TreeMap<String, String> trieXMIfragment = new TreeMap<String, String>();
	

	
	
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
	
	

	/*
	 * Trie ID
	 */
	public static void putTrieID(String key, String value){
		trieID.put(key, value);
	}
	
	public static String getTrieID(String key){
		return trieID.get(key);
	}
	
	/*
	 * Trie Structural
	 */
	/*
	 * PUT
	 */
	public static void putTrieMetodo(String key, Method metodo){
		trieMethod.put(key, metodo);
	}
	
	public static void putTrieMetodoName(String name, Method metodo){
		trieMethod.put(name, metodo);
	}
	
	public static void putTrieAtributte(String key, Attribute atributte){
		trieAtributte.put(key, atributte);
	}
	
	public static void putTrieOperation(String key, Operation operation){
		trieOperation.put(key,operation);
	}

	public static void putTrieInterface(String key, Interface inter) {
		trieInterface.put(key,inter);
	}
	
	public static void putTrieAbstractMethod(String name, ArrayList<Method> listAbstractMethod){
		trieAbstractMethod.put(name, listAbstractMethod);
	}

	public static void addTrieAbstractMethod(String name, Method abstractMethod){
		trieAbstractMethod.get(name).add(abstractMethod);
	}
	
	/*
	 * GET
	 */
	public static Method getTrieMetodo(String key){
		return key == null ? null : trieMethod.get(key);
	}
	
	public static Method getTrieMetodoName(String name){
		return name == null ? null : trieMethod.get(name);
	}
	
	public static Attribute getTrieAtributte(String key){
		return key == null ? null : trieAtributte.get(key);
	}
	
	public static Operation getTrieOperation(String key){
		return key == null ? null : trieOperation.get(key);
	}
	
	public static Interface getTrieInterface(String key) {
		return key == null ? null : trieInterface.get(key);
	}
	
	public static ArrayList<Method> getTrieAbstractMethod(String name){
		return trieAbstractMethod.get(name);
	}
	
	public static boolean containsKeyTrieAbstractMethod(String name){
		return trieAbstractMethod.containsKey(name);
	}
	
	/*
	 * Trie Sequence 
	 */
	/*
	 * PUT
	 */
	public static void putTrieOperationEvent(String key, OperationEvent operationEvent){
		trieOperationEvent.put(key, operationEvent);
	}

	public static void putTrieMessage(String key, Message message){
		trieMessage.put(key, message);
	}

	public static void putTrieFragment(String key, Fragment fragment){
		trieFragment.put(key, fragment);
	}
	
	public static void putTrieLifeline(String key, Lifeline lifeline){
		trieLifeline.put(key, lifeline);
	}
	
	public static void putTrieInteraction(String key, Interaction interaction){
		trieInteraction.put(key, interaction);
	}
	
	public static void putTrieXMIfragment(String key) {
		trieXMIfragment.put(key,key);
	}
	
	/*
	 * GET 
	 */
	public static OperationEvent getTrieOperationEvent(String key){
		return key == null ? null : trieOperationEvent.get(key);
	}

	public static Message getTrieMessage(String key){
		return key == null ? null : trieMessage.get(key);
	}

	public static Fragment getTrieFragment(String key){
		return key == null ? null : trieFragment.get(key);
	}
	
	public static Lifeline getTrieLifeline(String key){
		return key == null ? null : trieLifeline.get(key);
	}
	
	public static Interaction getTrieInteraction(String key){
		return key == null ? null : trieInteraction.get(key);
	}

	public static boolean containsTrieXMIfragment(String key) {
		return key == null ? false : trieXMIfragment.containsKey(key);
	}

	
	
	public GeneratorStrategy getStrategy(){
		return generator;
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
	
	
	
	
	
	
	
}
