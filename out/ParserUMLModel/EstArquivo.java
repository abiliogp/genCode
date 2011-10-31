import java.util.ArrayList;

public class EstArquivo{

	/*
	 *Atributos
	 */
	protected String name;
	private String dir;
	
	protected ArrayList<Pacote> listPacote;
	
	protected ArrayList<Classe> listClasse;

	/*
	 *Construtor
	 */
	public EstArquivo(){
		this.name = new String();
		this.dir = new String();
		this.listPacote = new ArrayList<Pacote>();
		this.listClasse = new ArrayList<Classe>();
	}


	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}

	public String getDir(){
		return this.dir;
	}

	public Pacote getListPacote(){
		return this.listPacote;
	}

	public Classe getListClasse(){
		return this.listClasse;
	}


	/*
	 *Set
	 */
	public void setName( String name ){
		 this.name = name;
	}

	public void setDir( String dir ){
		 this.dir = dir;
	}

	public void setListPacote( Pacote listPacote ){
		 this.listPacote = listPacote;
	}

	public void setListClasse( Classe listClasse ){
		 this.listClasse = listClasse;
	}


	/*
	 *Métodos
	 */
	public void printProp(){
	}

	public void genCode(){
	}

}