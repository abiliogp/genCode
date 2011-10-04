
public class Operation{

	/*
	 *Attributes
	 */
	private String name;
	private String type;
	private String visibility;

	/*
	 *Constructor
	 */
	public Operation(){
		this.name = new String();
		this.type = new String();
		this.visibility = new String();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}

	public String getType(){
		return this.type;
	}

	public String getVisibility(){
		return this.visibility;
	}


	/*
	 *Set
	 */
	public void setName( String name ){
		 this.name = name;
	}

	public void setType( String type ){
		 this.type = type;
	}

	public void setVisibility( String visibility ){
		 this.visibility = visibility;
	}


	/*
	 *Method
	 */
	public void printProp(){
	}

}