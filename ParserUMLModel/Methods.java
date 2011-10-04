import java.util.ArrayList;

public class Methods extends DataModel{

	/*
	 *Attributes
	 */
	private boolean isAbstract;
	
	public ArrayList<Operation> listOperation;
	
	private ArrayList<Parameters> listParameters;
	
	private ArrayList<Parameters> listReturn;

	/*
	 *Constructor
	 */
	public Methods(){
		super();
		this.isAbstract; //WARNING: This attribute must be initialized
		this.listOperation = new ArrayList<Operation>();
		this.listParameters = new ArrayList<Parameters>();
		this.listReturn = new ArrayList<Parameters>();
	}


	/*
	 *Get
	 */
	public boolean getIsAbstract(){
		return this.isAbstract;
	}

	public Parameters getListParameters(){
		return this.listParameters;
	}

	public Parameters getListReturn(){
		return this.listReturn;
	}


	/*
	 *Set
	 */
	public void setIsAbstract( boolean isAbstract ){
		 this.isAbstract = isAbstract;
	}

	public void setListParameters( Parameters listParameters ){
		 this.listParameters = listParameters;
	}

	public void setListReturn( Parameters listReturn ){
		 this.listReturn = listReturn;
	}


	/*
	 *Method
	 */
	public void printProp(){
	}

	public void genCode(String out){
	}

	public void genCodemethod(String out){
	}

	public void genCodeSuper(String out){
	}

	/*
	 *Abstract Method of Super
	 */
	public void printProp(){
	}

}