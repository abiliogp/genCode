
public class WaterSensor extends Sensor{

	/**Attributes */
	public int currentLevel;
	public int desiredLevel;

	/**Attribute of Return Method levelTest */
	public boolean value;

	/** Constructor */
	public WaterSensor( int currentLevel , int desiredLevel ){
		super();
		this.currentLevel = currentLevel;
		this.desiredLevel = desiredLevel;
	}


	/** Methods */
	public boolean levelTest(int level){
		return value;
	}

	/*
	 *Abstract Method of Super
	 */
	public boolean check(){
		return full;
	}

}