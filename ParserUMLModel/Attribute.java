
public class Attribute extends DataModel{

	/*
	 *Attributes
	 */
	private String name;

	/*
	 *Constructor
	 */
	public Attribute(){
		super();
		this.name = new String();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}


	/*
	 *Set
	 */
	public void setName( String name ){
		 this.name = name;
	}


	/*
	 *Method
	 */
	public void printProp(){
	}

	public void genCode(void out){
	}

	public void genCodeGet(void out){
	}

	public void genCodeSet(void out){
	}

	public void genCodeConst(void out){
	}

	/*
	 *Abstract Method of Super
	 */
	public void printProp(){
	}

}