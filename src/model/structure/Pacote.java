package model.structure;

public class Pacote {
	
	private String name;
	
	private AssocPacote assoc;

	
	private String visibility;
	
	
	public Pacote(String name){
		this.name = name;
		this.visibility = "public";
	}
	
	//set
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public void setAssocPacote(AssocPacote assoc){
		this.assoc = assoc;
	}
	
	//get
	public String getName(){
		return this.name;
	}
	
	public String getVisibility() {
		return this.visibility;
	}
	
	public AssocPacote getAssocPacote(){
		return this.assoc;
	}

	public void printProp() {

		System.out.println( "\tNome: " + this.name );
		System.out.println("\t\tVisibilidade: " + this.visibility);
		if(this.assoc != null){
			this.assoc.printProp();
		}
	
	}
}
