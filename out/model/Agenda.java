import java.util.ArrayList;

public class Agenda{

	/**Attributes */	
	public ArrayList<Contato> contato;

	/** Constructor */
	public Agenda(){
		this.contato = new ArrayList<Contato>();
	}

	/** Methods */
	public void addContato(){
		/** Specified from Sequence Diagram addContato */

		contato.criaContato();
		for(Contato c : contato){
			if(n  ==  1){
				contato.criaContato();
			}
		}
	}

}