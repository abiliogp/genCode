import java.util.ArrayList;

public class Pacote extends EstArquivo{

	/*
	 *Atributos
	 */
	private String visibility;
	
	public ArrayList<Atributo> atributo;

	/*
	 *Construtor
	 */
	public Pacote(){
		super();
		this.visibility = new String();
		this.atributo = new ArrayList<Atributo>();
	}


	/*
	 *Get
	 */
	public String getVisibility(){
		return this.visibility;
	}


	/*
	 *Set
	 */
	public void setVisibility( String visibility ){
		 this.visibility = visibility;
	}


	/*
	 *Métodos
	 */
	public void printProp(){
	}

}