import java.util.ArrayList;

public class Principal{

	/*
	 *Attributes
	 */
	
	public ArrayList<Cliente> cliente;

	/*
	 *Attribute of Return Method read_option
	 */
	public int x;

	/*
	 *Constructor
	 */
	public Principal(){
		this.cliente = new ArrayList<Cliente>();
	}


	/*
	 *Method
	 */
	public void main(String args){
	}

	public int read_option(){
		return x;
	}

}