
public class UserInterface{

	/*
	 *Attributes
	 */
	private AddressBook addressBook;

	/*
	 *Attribute of Return Method readName
	 */
	public String name;

	/*
	 *Attribute of Return Method readContact
	 */
	public String contact;

	/*
	 *Constructor
	 */
	public UserInterface( AddressBook addressBook ){
		this.addressBook = addressBook;
	}


	/*
	 *Get
	 */
	public AddressBook getAddressBook(){
		return this.addressBook;
	}


	/*
	 *Set
	 */
	public void setAddressBook( AddressBook addressBook ){
		 this.addressBook = addressBook;
	}


	/*
	 *Method
	 */
	public String readName(){
		return name;
	}

	public String readContact(){
		return contact;
	}

	public void getSelectedOption(){
	}

	public void visualizeAddresses(){
	}

}