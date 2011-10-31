
public class Parameters extends DataModel{

	/*
	 *Attributes
	 */
	private String type;
	private String direction;

	/*
	 *Constructor
	 */
	public Parameters(){
		super();
		this.type = new String();
		this.direction = new String();
	}


	/*
	 *Get
	 */
	public String getType(){
		return this.type;
	}

	public String getDirection(){
		return this.direction;
	}


	/*
	 *Set
	 */
	public void setType( String type ){
		 this.type = type;
	}

	public void setDirection( String direction ){
		 this.direction = direction;
	}


	/*
	 *Method
	 */
	public void printProp(){
	}

	private void genCode(void out){
	}

	/*
	 *Abstract Method of Super
	 */
	public void printProp(){
	}

}