
public class Operacao{

	/*
	 *Atributos
	 */
	private String name;
	private String type;
	private String visibility;

	/*
	 *Construtor
	 */
	public Operacao(){
		this.name = new String();
		this.type = new String();
		this.visibility = new String();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}

	public String getType(){
		return this.type;
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

	public void setType( String type ){
		 this.type = type;
	}

	public void setVisibility( String visibility ){
		 this.visibility = visibility;
	}


	/*
	 *Métodos
	 */
	public void printProp(){
	}

}