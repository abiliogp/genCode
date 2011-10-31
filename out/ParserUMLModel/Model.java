import java.util.ArrayList;

public class Model{

	/*
	 *Attributes
	 */
	protected String name;
	private String dir;
	
	protected ArrayList<Package> listPackage;
	
	protected ArrayList<Class> listClass;

	/*
	 *Constructor
	 */
	public Model(){
		this.name = new String();
		this.dir = new String();
		this.listPackage = new ArrayList<Package>();
		this.listClass = new ArrayList<Class>();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}

	public String getDir(){
		return this.dir;
	}

	public Package getListPackage(){
		return this.listPackage;
	}

	public Class getListClass(){
		return this.listClass;
	}


	/*
	 *Set
	 */
	public void setName( String name ){
		 this.name = name;
	}

	public void setDir( String dir ){
		 this.dir = dir;
	}

	public void setListPackage( Package listPackage ){
		 this.listPackage = listPackage;
	}

	public void setListClass( Class listClass ){
		 this.listClass = listClass;
	}


	/*
	 *Method
	 */
	public void printProp(){
	}

	public void genCode(){
	}

}