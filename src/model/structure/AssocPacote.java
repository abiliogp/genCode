package model.structure;

public class AssocPacote {
	
	private String name;
	private String supplier;
	private String visibility;
	
	public AssocPacote(String name){
		this.name = name;
	}
	
	//set
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	//get
	public String getName(){
		return this.name;
	}
	
	public String getVisibility() {
		return this.visibility;
	}
	
	public String getSupplier() {
		return this.supplier;
	}
	
	
	public void printProp() {
		System.out.println( "\tAssoc: " + this.name );
		System.out.println( "\t\tSupplier: " + this.supplier );
	}
}
