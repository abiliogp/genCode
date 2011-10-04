
public class DoorSensor extends Sensor{

	/** Constructor */
	public DoorSensor(){
		super();
	}

	/*
	 *Abstract Method of Super
	 */
	public boolean check(){
		return full;
	}

}