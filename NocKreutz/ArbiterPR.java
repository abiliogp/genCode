
public class ArbiterPR{

	/*
	 *Attributes
	 */
	private int HIGH;
	private int MEDIUM;
	private int LOW;
	private int id;
	private boolean cb[0..*];
	private int P;
	private int ra_id;
	private int pr_order[0..*];

	/*
	 *Constructor
	 */
	public ArbiterPR(){
		this.HIGH; //WARNING: This attribute must be initialized
		this.MEDIUM; //WARNING: This attribute must be initialized
		this.LOW; //WARNING: This attribute must be initialized
		this.id; //WARNING: This attribute must be initialized
		this.cb[0..*]; //WARNING: This attribute must be initialized
		this.P; //WARNING: This attribute must be initialized
		this.ra_id; //WARNING: This attribute must be initialized
		this.pr_order[0..*]; //WARNING: This attribute must be initialized
	}


	/*
	 *Get
	 */
	public int getHIGH(){
		return this.HIGH;
	}

	public int getMEDIUM(){
		return this.MEDIUM;
	}

	public int getLOW(){
		return this.LOW;
	}

	public int getId(){
		return this.id;
	}

	public boolean getCb[0..*](){
		return this.cb[0..*];
	}

	public int getP(){
		return this.P;
	}

	public int getRa_id(){
		return this.ra_id;
	}

	public int getPr_order[0..*](){
		return this.pr_order[0..*];
	}


	/*
	 *Set
	 */
	public void setHIGH( int HIGH ){
		 this.HIGH = HIGH;
	}

	public void setMEDIUM( int MEDIUM ){
		 this.MEDIUM = MEDIUM;
	}

	public void setLOW( int LOW ){
		 this.LOW = LOW;
	}

	public void setId( int id ){
		 this.id = id;
	}

	public void setCb[0..*]( boolean cb[0..*] ){
		 this.cb[0..*] = cb[0..*];
	}

	public void setP( int P ){
		 this.P = P;
	}

	public void setRa_id( int ra_id ){
		 this.ra_id = ra_id;
	}

	public void setPr_order[0..*]( int pr_order[0..*] ){
		 this.pr_order[0..*] = pr_order[0..*];
	}


	/*
	 *Method
	 */
	public void ArbiterPR(void param_0, void param_1, void param_2, void param_3, void param_4){
	}

	public void set_cb(int _router_id){
	}

	public BufferIF set_buf_out(void _buf_out){
	}

	public void scheduling(){
	}

}