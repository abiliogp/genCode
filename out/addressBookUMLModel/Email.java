
public class Email extends AddressEntry{

	/*
	 *Attributes
	 */
	private String email;

	/*
	 *Constructor
	 */
	public Email( String email ){
		super();
		this.email = email;
	}


	/*
	 *Get
	 */
	public String getEmail(){
		return this.email;
	}


	/*
	 *Set
	 */
	public void setEmail( String email ){
		 this.email = email;
	}

	/*
	 *Abstract Method of Super
	 */
	public void effectContact(){
	}

}