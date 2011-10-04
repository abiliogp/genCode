
public class Cliente{

	/*
	 *Attributes
	 */
	
	public ArrayList<Veiculo> veiculo;
	private String nome_cliente;

	/*
	 *Attribute of Return Method reserva_veiculo
	 */
	public void null;

	/*
	 *Constructor
	 */
	public Cliente(, String nome_cliente ,){
		this.veiculo = new ArrayList<Veiculo>();
		this.nome_cliente = nome_cliente;
	}


	/*
	 *Get
	 */
	public String getNome_cliente(){
		return this.nome_cliente;
	}


	/*
	 *Set
	 */
	public void setNome_cliente( String nome_cliente ){
		 this.nome_cliente = nome_cliente;
	}


	/*
	 *Method
	 */
	public abstract void reserva_veiculo();
}