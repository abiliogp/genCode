import java.util.ArrayList;

public class Cliente{

	/*
	 *Attributes
	 */
	public void Nome;
	/*
	 *You must have at least ONE
	 *Occurrence of Attribute: conta in this class
	 */
	
	public ArrayList<Conta> conta;

	/*
	 *Constructor
	 */
	public Cliente(){
		this.Nome; //WARNING: This attribute must be initialized
		this.conta = new ArrayList<Conta>();
	}

}