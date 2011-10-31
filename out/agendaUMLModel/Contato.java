
public abstract class Contato{

	/*
	 *Attributes
	 */
	protected String nome;

	/*
	 *Get
	 */
	public String getNome(){
		return this.nome;
	}


	/*
	 *Set
	 */
	public void setNome( String nome ){
		 this.nome = nome;
	}


	/*
	 *Method
	 */
	public abstract void realizarContato();
}