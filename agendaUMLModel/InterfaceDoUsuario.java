
public class InterfaceDoUsuario{

	/*
	 *Attributes
	 */
	private Agenda agenda;

	/*
	 *Constructor
	 */
	public InterfaceDoUsuario( Agenda agenda ){
		this.agenda = agenda;
	}


	/*
	 *Get
	 */
	public Agenda getAgenda(){
		return this.agenda;
	}


	/*
	 *Set
	 */
	public void setAgenda( Agenda agenda ){
		 this.agenda = agenda;
	}


	/*
	 *Method
	 */
	public static void main( String args[] ){
		/* 
		 *Specified from Sequence Diagram Main
		 */
		switch(opcao){
			case 1:
				/* 
				 *Specified from Sequence Diagram Adicionar
				 */
				contato = new Contato();
				break;

			case 2:
				/* 
				 *Specified from Sequence Diagram Excluir
				 */

				break;

			case 3:
				/* 
				 *Specified from Sequence Diagram Editar
				 */

				break;

			case 4:
				/* 
				 *Specified from Sequence Diagram Procurar
				 */

				break;

			default:
				break;
			}//endSwitch

	}

	public void visualizarContato(){
	}

}