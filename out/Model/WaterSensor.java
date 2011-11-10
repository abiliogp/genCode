
public class WaterSensor extends Sensor{

	/**Attributes */
	public int currentLevel;
	public int desiredLevel;

	/** Constructor */
	public WaterSensor( int currentLevel , int desiredLevel ){
	super();
		this.currentLevel = currentLevel;
		this.desiredLevel = desiredLevel;
	}

	/** Abstract Method of Super*/
	public boolean check(){
		return b;
	}

}