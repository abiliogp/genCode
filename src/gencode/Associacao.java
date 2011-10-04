package gencode;

public class Associacao extends DataModel {

	private String member;
	private String aggregation;
	
	private String member2;
	private String aggregation2;
	private char upperValue2;
	private char lowerValue2;
	private String visibility2;
	
	private String visibilityAssoc;

	public Associacao(String nameAssoc) {

		super(nameAssoc);
		this.visibilityAssoc = "public";
		this.aggregation = "simple";
		this.visibility2 = "public";
		this.aggregation2 = "simple";
	}
	
	//set
	public void setVisibilityAssoc(String visibilityAssoc) {
		this.visibilityAssoc = visibilityAssoc;
	}

	public void setVisibility2(String visibility2) {
		this.visibility2 = visibility2;
	}
	
	public void setMember2(String member2) {

		this.member2 = member2;
	}

	public void setMember(String member) {

		this.member = member;
	}
	
	public void setAggregation(String aggregation) {
		this.aggregation = aggregation;
	}

	public void setAggregation2(String aggregation2) {
		this.aggregation2 = aggregation2;
	}
	
	public void setUpperValue2(char upperValue2) {
		this.upperValue2 = upperValue2;
	}

	public void setLowerValue2(char lowerValue2) {
		this.lowerValue2 = lowerValue2;
	}
	
	
	//get
	public String getMember() {

		return this.member;
	}

	public String getMember2() {

		return this.member2;
	}
	
	public char getUpperValue2() {
		return this.upperValue2;
	}

	public char getLowerValue2() {
		return this.lowerValue2;
	}
	
	public String getVisibility2() {
		return this.visibility2;
	}
	
	public String getAggregation() {
		return this.aggregation;
	}

	public String getAggregation2() {
		return this.aggregation2;
	}

	public String getVisibilityAssoc() {
		return this.visibilityAssoc;
	}
	
	public void printProp() {

		System.out.println( "\tNome da associação: " + this.name );
		System.out.println("\t\tVisibilidade: " + this.visibility);

		System.out.println( "\t\tClasse Associada: " + this.member );
		System.out.println( "\t\t\tTipo Associação: " + this.aggregation );
		System.out.printf( "\t\t\tUpper Value: %s\n", 
							this.upperValue == -1 ? "*": this.upperValue);
		System.out.printf("\t\t\tLower Value: %s\n", 
							this.lowerValue == -1 ? "*" : this.lowerValue);

		System.out.println( "\t\tClasse Associada2: " + this.member2 );
		System.out.println( "\t\t\tTipo Associação2: " + this.aggregation2 );
		System.out.printf( "\t\t\tUpper Value2: %s\n", 
							this.upperValue2 == -1 ? "*": this.upperValue2);
		System.out.printf("\t\t\tLower Value2: %s\n", 
							this.lowerValue2 == -1 ? "*" : this.lowerValue2);
	
	}

}
