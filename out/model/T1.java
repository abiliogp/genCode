
public class T1{

	/**Attributes */
public void null;
public int x;
public int y;
public int r1;
public int r2;
public int r3;
public Lib lib;

	/**Attribute of Return Method calc */
	public int out;

	/**Attribute of Return Method dec */
	public int out;

	/** Constructor */
	public T1( void null , int x , int y , int r1 , int r2 , int r3 , Lib lib ){
		this.null = null;
		this.x = x;
		this.y = y;
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
		this.lib = lib;
	}

public int calc(int in){
		/** Specified from Sequence Diagram Interaction0 */
		getValue();
		getValue();
		calc();
		setX();
		setY();
		calc();

		return out;
}

public int dec(int in){
		return out;
}

}