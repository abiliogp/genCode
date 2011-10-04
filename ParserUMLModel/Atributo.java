
public class Atributo extends DadoModelo{

	/*
	 *Atributos
	 */
	private String name;

	/*
	 *Construtor
	 */
	public Atributo(){
		super();
		this.name = new String();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}


	/*
	 *Set
	 */
	public void setName( String name ){
		 this.name = name;
	}


	/*
	 *Métodos
	 */
	public void printProp(){
	}

	public void genCode(String out){
	}

	public void genCodeGet(String out){
	}

	public void genCodeSet(String out){
	}

	public void genCodeConst(String out){
	}

	/*
	 *Método Abstrato da Super
	 */
	public void printProp(){
	}

}