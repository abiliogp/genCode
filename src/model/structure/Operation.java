package model.structure;

public class Operation {

	private String name;
	
	private String type;
	private String visibility;
	
	public Operation(String type){
		
		this.type = type;
		this.visibility = "public";
	}
	
	
	
	//set
	public void setName(String name){
		
		this.name = name;
	}
	
	public void setVisibility(String visibility){
		
		this.visibility = visibility;
	}
	
	
	//get
	public String getName(){
		
		return this.name;
	}
	
	public String getVisibility(){
		
		return this.visibility;
	}
	
	public String getType(){
		
		return this.type;
	}
	
	public void printProp(){	
		System.out.printf("\t\tOperação: %s Tipo: %s Visibilidade: %s \n" , 
							this.name, this.type, this.visibility);
	}
	
}
