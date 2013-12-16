package model.sequence;

public abstract class DataSequence{

	/*Attributes*/
	protected String name;
	protected String type;
	protected String value;
	protected String visibility;
	
	/*Constructor*/
	public DataSequence(String name){
		this.name = name;
		visibility = "public";
	}
	
	/*Set */
	public void setName(String name){
		 this.name = name;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	/*Get*/
	public String getName(){
		return this.name;
	}

	public String getType(){
		return this.type;
	}
	
	public String getValue(){
		return this.value;
	}
	
}