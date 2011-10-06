
public class Fone extends Contato{

	/*
	 *Attributes
	 */
	private String numero;

	/*
	 *Constructor
	 */
	public Fone( String numero ){
		super();
		this.numero = numero;
	}


	/*
	 *Get
	 */
	public String getNumero(){
		return this.numero;
	}


	/*
	 *Set
	 */
	public void setNumero( String numero ){
		 this.numero = numero;
	}


	/*
	 *Method
	 */
	public void enviarSMS(){
	}

	/*
	 *Abstract Method of Super
	 */
	public void realizarContato(){
	}

}