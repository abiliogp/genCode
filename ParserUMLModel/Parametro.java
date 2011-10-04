
public class Parametro extends DadoModelo{

	/*
	 *Atributos
	 */
	private String type;
	private String direction;

	/*
	 *Construtor
	 */
	public Parametro(){
		super();
		this.type = new String();
		this.direction = new String();
	}


	/*
	 *Get
	 */
	public String getType(){
		return this.type;
	}

	public String getDirection(){
		return this.direction;
	}


	/*
	 *Set
	 */
	public void setType( String type ){
		 this.type = type;
	}

	public void setDirection( String direction ){
		 this.direction = direction;
	}


	/*
	 *Métodos
	 */
	public void printProp(){
	}

	private void genCode(String out){
	}

	/*
	 *Método Abstrato da Super
	 */
	public void printProp(){
	}

}