import java.util.ArrayList;

public class Bloco{

	/*
	 *Atributos
	 */
	/*
	 *É necessário que tenha pelo menos UMA
	 *Ocorrência do Atributo: vetPorta nessa classe
	 */
	
	protected ArrayList<Porta> vetPorta;
	
	public ArrayList<int> Property_0;
	public int Property_1;

	/*
	 *Atributo Return do Método Criar_porta
	 */
	protected boolean retorno;

	/*
	 *Construtor
	 */
	public Bloco(){
		this.vetPorta = new ArrayList<Porta>();
		this.Property_0 = new ArrayList<int>();
		this.Property_1; //ATENÇÃO: Este Atributo deve ser inicializado
	}


	/*
	 *Get
	 */
	public Porta getVetPorta(){
		return this.vetPorta;
	}


	/*
	 *Set
	 */
	public void setVetPorta( Porta vetPorta ){
		 this.vetPorta = vetPorta;
	}


	/*
	 *Métodos
	 */
	public boolean Criar_porta(boolean parametro){
		return retorno;
	}

}