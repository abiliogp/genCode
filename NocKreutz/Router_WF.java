
public class Router_WF{

	/*
	 *Attributes
	 */
	private int id;
	private int ra_id;
	private int P;
	/*
	 *Atributte fromRealization of RouterIF
	 */
	public int LOCAL;
	public int NORTH;
	public int EAST;
	public int WEST;
	public int SOUTH;
	
	public ArrayList<null> bufferIF;

	/*
	 *Constructor
	 */
	public Router_WF( int id , int ra_id , int P ,){
		this.id = id;
		this.ra_id = ra_id;
		this.P = P;
	}


	/*
	 *Get
	 */
	public int getId(){
		return this.id;
	}

	public int getRa_id(){
		return this.ra_id;
	}

	public int getP(){
		return this.P;
	}


	/*
	 *Set
	 */
	public void setId( int id ){
		 this.id = id;
	}

	public void setRa_id( int ra_id ){
		 this.ra_id = ra_id;
	}

	public void setP( int P ){
		 this.P = P;
	}


	/*
	 *Method
	 */
	public void Router_WF(null param_0, null _buf, null param_2, int _ra_id, int _id, int _P){
	}

	/*
	 *Methods fromRealization of RouterIF
	 */
	public void routing(){
	}

}