import java.util.ArrayList;

public class Class{

	/*
	 *Attributes
	 */
	private String name;
	private String general;
	private String visibility;
	private boolean abstrat;
	private boolean active;
	
	private ArrayList<Attribute> listAttribute;
	
	public ArrayList<Methods> parameter;
	
	private ArrayList<Operation> listOperation;
	
	private ArrayList<Methods> listMethods;

	/*
	 *Constructor
	 */
	public Class(){
		this.name = new String();
		this.general = new String();
		this.visibility = new String();
		this.abstrat; //WARNING: This attribute must be initialized
		this.active; //WARNING: This attribute must be initialized
		this.listAttribute = new ArrayList<Attribute>();
		this.parameter = new ArrayList<Methods>();
		this.listOperation = new ArrayList<Operation>();
		this.listMethods = new ArrayList<Methods>();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}

	public String getGeneral(){
		return this.general;
	}

	public String getVisibility(){
		return this.visibility;
	}

	public boolean getAbstrat(){
		return this.abstrat;
	}

	public boolean getActive(){
		return this.active;
	}

	public Attribute getListAttribute(){
		return this.listAttribute;
	}

	public Operation getListOperation(){
		return this.listOperation;
	}

	public Methods getListMethods(){
		return this.listMethods;
	}


	/*
	 *Set
	 */
	public void setName( String name ){
		 this.name = name;
	}

	public void setGeneral( String general ){
		 this.general = general;
	}

	public void setVisibility( String visibility ){
		 this.visibility = visibility;
	}

	public void setAbstrat( boolean abstrat ){
		 this.abstrat = abstrat;
	}

	public void setActive( boolean active ){
		 this.active = active;
	}

	public void setListAttribute( Attribute listAttribute ){
		 this.listAttribute = listAttribute;
	}

	public void setListOperation( Operation listOperation ){
		 this.listOperation = listOperation;
	}

	public void setListMethods( Methods listMethods ){
		 this.listMethods = listMethods;
	}


	/*
	 *Method
	 */
	public void printProp(){
	}

	public void genCode(void param_0){
	}

}