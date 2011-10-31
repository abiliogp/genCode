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
	public void main(String args){		/* 		 *Specified from Sequence Diagram DiagMain		 */		if(x == 1){			/* 		 *Specified from Sequence Diagram for_if		 */		for(int i = 1; i < 5; i++){			if(x == 0){		}			reservar_veiculo();			}		}		read_option();
	}

	public int read_option(){
		return x;
	}

}