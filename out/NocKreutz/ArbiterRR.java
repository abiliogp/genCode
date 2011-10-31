
public class ArbiterRR{

	/*
	 *Attributes
	 */
	private int id;
	private boolean cb[0..*];
	private int ap;
	private int P;
	private int ra_id;

	/*
	 *Constructor
	 */
	public ArbiterRR(){
		this.id; //WARNING: This attribute must be initialized
		this.cb[0..*]; //WARNING: This attribute must be initialized
		this.ap; //WARNING: This attribute must be initialized
		this.P; //WARNING: This attribute must be initialized
		this.ra_id; //WARNING: This attribute must be initialized
	}


	/*
	 *Get
	 */
	public int getId(){
		return this.id;
	}

	public boolean getCb[0..*](){
		return this.cb[0..*];
	}

	public int getAp(){
		return this.ap;
	}

	public int getP(){
		return this.P;
	}

	public int getRa_id(){
		return this.ra_id;
	}


	/*
	 *Set
	 */
	public void setId( int id ){
		 this.id = id;
	}

	public void setCb[0..*]( boolean cb[0..*] ){
		 this.cb[0..*] = cb[0..*];
	}

	public void setAp( int ap ){
		 this.ap = ap;
	}

	public void setP( int P ){
		 this.P = P;
	}

	public void setRa_id( int ra_id ){
		 this.ra_id = ra_id;
	}


	/*
	 *Method
	 */
	public Control_FlowIF ArbiterRR(int _P, int _ra_id, int _id, void _buf, void _cf){
	}

	public void set_cb(int _router_id){
	}

	public BufferIF set_buf_out(void _buf_out){
	}

	public void scheduling(){
	}

}