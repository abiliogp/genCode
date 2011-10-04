
public abstract class AddressEntry{

	/*
	 *Attributes
	 */
	protected String name;

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
	public abstract void effectContact();
}