import java.util.ArrayList;

public class Classe{

	/*
	 *Atributos
	 */
	private String name;
	private String general;
	private String visibility;
	private boolean abstrata;
	private boolean ativa;
	
	private ArrayList<Atributo> listAtributo;
	
	public ArrayList<Metodo> parametro;
	
	private ArrayList<Operacao> listOperacao;
	
	private ArrayList<Metodo> listMetodo;

	/*
	 *Construtor
	 */
	public Classe(){
		this.name = new String();
		this.general = new String();
		this.visibility = new String();
		this.abstrata; //ATENÇÃO: Este Atributo deve ser inicializado
		this.ativa; //ATENÇÃO: Este Atributo deve ser inicializado
		this.listAtributo = new ArrayList<Atributo>();
		this.parametro = new ArrayList<Metodo>();
		this.listOperacao = new ArrayList<Operacao>();
		this.listMetodo = new ArrayList<Metodo>();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}

	public String getGeneral(){
		return this.general;
	}

	public String getVisibility(){
		return this.visibility;
	}

	public boolean getAbstrata(){
		return this.abstrata;
	}

	public boolean getAtiva(){
		return this.ativa;
	}

	public Atributo getListAtributo(){
		return this.listAtributo;
	}

	public Operacao getListOperacao(){
		return this.listOperacao;
	}

	public Metodo getListMetodo(){
		return this.listMetodo;
	}


	/*
	 *Set
	 */
	public void setName( String name ){
		 this.name = name;
	}

	public void setGeneral( String general ){
		 this.general = general;
	}

	public void setVisibility( String visibility ){
		 this.visibility = visibility;
	}

	public void setAbstrata( boolean abstrata ){
		 this.abstrata = abstrata;
	}

	public void setAtiva( boolean ativa ){
		 this.ativa = ativa;
	}

	public void setListAtributo( Atributo listAtributo ){
		 this.listAtributo = listAtributo;
	}

	public void setListOperacao( Operacao listOperacao ){
		 this.listOperacao = listOperacao;
	}

	public void setListMetodo( Metodo listMetodo ){
		 this.listMetodo = listMetodo;
	}


	/*
	 *Métodos
	 */
	public void printProp(){
	}

	public void genCode(String out){
	}

}