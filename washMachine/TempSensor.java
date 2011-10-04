
public class TempSensor extends Sensor{

	/** Constructor */
	public TempSensor(){
		super();
	}

	/*
	 *Abstract Method of Super
	 */
	public boolean check(){
		return full;
	}

}