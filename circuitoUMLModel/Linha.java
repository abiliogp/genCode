
public class Linha{

	/*
	 *Atributos
	 */
	private OUT out;
	private IN in;

	/*
	 *Construtor
	 */
	public Linha(){
		this.out = new OUT();
		this.in = new IN();
	}


	/*
	 *Get
	 */
	public OUT getOut(){
		return this.out;
	}

	public IN getIn(){
		return this.in;
	}


	/*
	 *Set
	 */
	public void setOut( OUT out ){
		 this.out = out;
	}

	public void setIn( IN in ){
		 this.in = in;
	}

}