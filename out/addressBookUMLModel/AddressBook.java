import java.util.ArrayList;

public class AddressBook{

	/*
	 *Attributes
	 */
	/*
	 *You must have at least ONE
	 *Occurrence of Attribute: entries in this class
	 */
	
	private ArrayList<AddressEntry> entries;

	/*
	 *Attribute of Return Method search
	 */
	public AddressEntry addressEntry;

	/*
	 *Constructor
	 */
	public AddressBook(){
		this.entries = new ArrayList<AddressEntry>();
	}


	/*
	 *Get
	 */
	public AddressEntry getEntries(){
		return this.entries;
	}


	/*
	 *Set
	 */
	public void setEntries( AddressEntry entries ){
		 this.entries = entries;
	}


	/*
	 *Method
	 */
	public void addAddress(String name){
	}

	public void deleteAddress(String name){
	}

	public void clearAllAddress(){
	}

	public void viewAllAddresses(){
	}

	public AddressEntry search(String name){
		return addressEntry;
	}

	public void editAddresses(){
	}

}