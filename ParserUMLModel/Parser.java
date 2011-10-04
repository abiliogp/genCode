import java.util.ArrayList;

public class Parser{

	/*
	 *Attributes
	 */
	
	private ArrayList<Model> est;
	
	private ArrayList<String> trieID;
	
	private ArrayList<Operation> trieOpe;
	
	private ArrayList<DataModel> trieMetAbs;

	/*
	 *Attribute of Return Method manipulate
	 */
	private String str;

	/*
	 *Constructor
	 */
	public Parser(){
		this.est = new ArrayList<Model>();
		this.trieID = new ArrayList<String>();
		this.trieOpe = new ArrayList<Operation>();
		this.trieMetAbs = new ArrayList<DataModel>();
	}


	/*
	 *Get
	 */
	public Model getEst(){
		return this.est;
	}

	public String getTrieID(){
		return this.trieID;
	}

	public Operation getTrieOpe(){
		return this.trieOpe;
	}

	public DataModel getTrieMetAbs(){
		return this.trieMetAbs;
	}


	/*
	 *Set
	 */
	public void setEst( Model est ){
		 this.est = est;
	}

	public void setTrieID( String trieID ){
		 this.trieID = trieID;
	}

	public void setTrieOpe( Operation trieOpe ){
		 this.trieOpe = trieOpe;
	}

	public void setTrieMetAbs( DataModel trieMetAbs ){
		 this.trieMetAbs = trieMetAbs;
	}


	/*
	 *Method
	 */
	private String manipulate(String str, String Key){
		return str;
	}

	public void loadXMI(String inputFileName){
	}

	private void loadOperation(String bf, String key){
	}

	public void runParser(String input FileName){
	}

	private void ParserPackage(String bf, String Key){
	}

	private void parserClass(String bf, String Key){
	}

	private void parserAttribute(String bf, String Key){
	}

	private void parserOperation(String bf, String key){
	}

	private void parserParameter(String bf, String key){
	}

}