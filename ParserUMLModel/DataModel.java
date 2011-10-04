
public class DataModel{

	/*
	 *Attributes
	 */
	protected String name;
	protected String upperValue;
	protected String lowerValue;
	protected String visibility;

	/*
	 *Constructor
	 */
	public DataModel(){
		this.name = new String();
		this.upperValue = new String();
		this.lowerValue = new String();
		this.visibility = new String();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}

	public String getUpperValue(){
		return this.upperValue;
	}

	public String getLowerValue(){
		return this.lowerValue;
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

	public void setUpperValue( String upperValue ){
		 this.upperValue = upperValue;
	}

	public void setLowerValue( String lowerValue ){
		 this.lowerValue = lowerValue;
	}

	public void setVisibility( String visibility ){
		 this.visibility = visibility;
	}


	/*
	 *Method
	 */
	public abstract void printProp();
}