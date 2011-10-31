
public class FIFO{

	/*
	 *Attributes
	 */
	private int h;
	private int t;
	private int id;
	private int ra_id;
	private int size;

	/*
	 *Attribute of Return Method read
	 */
	public MSGS null;

	/*
	 *Attribute of Return Method get_header
	 */
	public MSGS null;

	/*
	 *Constructor
	 */
	public FIFO(){
		this.h; //WARNING: This attribute must be initialized
		this.t; //WARNING: This attribute must be initialized
		this.id; //WARNING: This attribute must be initialized
		this.ra_id; //WARNING: This attribute must be initialized
		this.size; //WARNING: This attribute must be initialized
	}


	/*
	 *Get
	 */
	public int getH(){
		return this.h;
	}

	public int getT(){
		return this.t;
	}

	public int getId(){
		return this.id;
	}

	public int getRa_id(){
		return this.ra_id;
	}

	public int getSize(){
		return this.size;
	}


	/*
	 *Set
	 */
	public void setH( int h ){
		 this.h = h;
	}

	public void setT( int t ){
		 this.t = t;
	}

	public void setId( int id ){
		 this.id = id;
	}

	public void setRa_id( int ra_id ){
		 this.ra_id = ra_id;
	}

	public void setSize( int size ){
		 this.size = size;
	}


	/*
	 *Method
	 */
	public void FIFO(int _ra_id, int _id, int _size){
	}

	public MSGS read(){
		return null;
	}

	public MSGS write(void _msg){
	}

	public MSGS get_header(){
		return null;
	}

	public void is_buffer_empty(){
	}

	public void is_buffer_full(){
	}

	public void get_remaining_space(){
	}

}