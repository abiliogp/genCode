
public class Phone extends AddressEntry{

	/*
	 *Attributes
	 */
	private String number;

	/*
	 *Constructor
	 */
	public Phone( String number ){
		super();
		this.number = number;
	}


	/*
	 *Get
	 */
	public String getNumber(){
		return this.number;
	}


	/*
	 *Set
	 */
	public void setNumber( String number ){
		 this.number = number;
	}


	/*
	 *Method
	 */
	public void sendSMS(){
	}

	public void receiveSMS(){
	}

	/*
	 *Abstract Method of Super
	 */
	public void effectContact(){
	}

}