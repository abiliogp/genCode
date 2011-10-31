import java.util.ArrayList;

public class Metodo extends DadoModelo{

	/*
	 *Atributos
	 */
	private boolean isAbstract;
	
	public ArrayList<Operacao> operacao;
	
	private ArrayList<Parametro> listParametro;
	
	private ArrayList<Parametro> listRetorno;

	/*
	 *Construtor
	 */
	public Metodo(){
		super();
		this.isAbstract; //ATENÇÃO: Este Atributo deve ser inicializado
		this.operacao = new ArrayList<Operacao>();
		this.listParametro = new ArrayList<Parametro>();
		this.listRetorno = new ArrayList<Parametro>();
	}


	/*
	 *Get
	 */
	public boolean getIsAbstract(){
		return this.isAbstract;
	}

	public Parametro getListParametro(){
		return this.listParametro;
	}

	public Parametro getListRetorno(){
		return this.listRetorno;
	}


	/*
	 *Set
	 */
	public void setIsAbstract( boolean isAbstract ){
		 this.isAbstract = isAbstract;
	}

	public void setListParametro( Parametro listParametro ){
		 this.listParametro = listParametro;
	}

	public void setListRetorno( Parametro listRetorno ){
		 this.listRetorno = listRetorno;
	}


	/*
	 *Métodos
	 */
	public void printProp(){
	}

	public void genCode(String out){
	}

	public void genCodeMetodo(String out){
	}

	public void genCodeSuper(String out){
	}

	/*
	 *Método Abstrato da Super
	 */
	public void printProp(){
	}

}