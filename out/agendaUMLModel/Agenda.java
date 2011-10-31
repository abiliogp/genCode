import java.util.ArrayList;

public class Agenda{

	/*
	 *Attributes
	 */
	/*
	 *You must have at least ONE
	 *Occurrence of Attribute: contato in this class
	 */
	
	private ArrayList<Contato> contato;

	/*
	 *Attribute of Return Method procurar
	 */
	public Contato addressEntry;

	/*
	 *Constructor
	 */
	public Agenda(){
		this.contato = new ArrayList<Contato>();
	}


	/*
	 *Get
	 */
	public Contato getContato(){
		return this.contato;
	}


	/*
	 *Set
	 */
	public void setContato( Contato contato ){
		 this.contato = contato;
	}


	/*
	 *Method
	 */
	public void adicionar(){
	}

	public void excluir(){
	}

	public Contato procurar(String name){
		return addressEntry;
	}

	public void editar(){
	}

}