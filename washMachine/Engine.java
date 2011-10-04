
public class Engine implements Machine{

	/**Attributes */
	private int rotation;

	/** Constructor */
	public Engine( int rotation ){
		this.rotation = rotation;
	}


	/** Get */
	public int getRotation(){
		return this.rotation;
	}


	/** Set */
	public void setRotation( int rotation ){
		 this.rotation = rotation;
	}

	/** Methods From Realization of Machine */
	public void turnOn(){
	}

	public void turnOff(){
	}

}