
public class Timer{

	/**Attributes */
	private int value;
	private int duration;

	/**Attribute of Return Method count */
	public int value;

	/** Constructor */
	public Timer( int value , int duration ){
		this.value = value;
		this.duration = duration;
	}


	/** Get */
	public int getValue(){
		return this.value;
	}

	public int getDuration(){
		return this.duration;
	}


	/** Set */
	public void setValue( int value ){
		 this.value = value;
	}

	public void setDuration( int duration ){
		 this.duration = duration;
	}


	/** Methods */
	public void start(){
	}

	public int count(){
		return value;
	}

}