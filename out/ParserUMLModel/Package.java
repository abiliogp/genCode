import java.util.ArrayList;

public class Package extends Model{

	/*
	 *Attributes
	 */
	private String visibility;
	
	public ArrayList<Attribute> attribute;

	/*
	 *Constructor
	 */
	public Package(){
		super();
		this.visibility = new String();
		this.attribute = new ArrayList<Attribute>();
	}


	/*
	 *Get
	 */
	public String getVisibility(){
		return this.visibility;
	}


	/*
	 *Set
	 */
	public void setVisibility( String visibility ){
		 this.visibility = visibility;
	}


	/*
	 *Method
	 */
	public void printProp(){
	}

}