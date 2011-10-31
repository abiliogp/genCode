
public class OperationEvent extends DataSequence{

	/*
	 *Attributes
	 */
	private void operationIPoperation;

	/*
	 *Constructor
	 */
	public OperationEvent(){
		super();
		this.operationIPoperation; //WARNING: This attribute must be initialized
	}


	/*
	 *Get
	 */
	public void getOperationIPoperation(){
		return this.operationIPoperation;
	}


	/*
	 *Set
	 */
	public void setOperationIPoperation( void operationIPoperation ){
		 this.operationIPoperation = operationIPoperation;
	}

	/*
	 *Abstract Method of Super
	 */
	public void parser(){
	}

}