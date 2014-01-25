
public class Contact extends {

	/**Attributes */	
	private string name;	
	private int telephone;

	/** Constructor */
	public Contact( string name , int telephone ){
		super();
		this.name = name;
		this.telephone = telephone;
	}

	/** Get */
	public string getName(){
		return this.name;
	}

	public int getTelephone(){
		return this.telephone;
	}

	/** Set */
	public void setName( string name ){
		 this.name = name;
	}

	public void setTelephone( int telephone ){
		 this.telephone = telephone;
	}

	/** Methods */
	public void add(){
	}

}