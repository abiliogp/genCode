
public abstract class DataSequence{

	/*
	 *Attributes
	 */
	private String name;

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
	public abstract void parser();
}