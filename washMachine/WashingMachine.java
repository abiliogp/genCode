
public class WashingMachine implements Machine{

	/**Attributes */
	private Engine engine;
	private WaterSensor waterSensor;
	private WashOption washOption;
	private Timer timer;
	private int washTime;
	private int rinseTime;
	private int spinTime;

	/** Constructor */
	public WashingMachine( Engine engine , WaterSensor waterSensor , WashOption washOption , Timer timer , int washTime , int rinseTime , int spinTime ){
		this.engine = engine;
		this.waterSensor = waterSensor;
		this.washOption = washOption;
		this.timer = timer;
		this.washTime = washTime;
		this.rinseTime = rinseTime;
		this.spinTime = spinTime;
	}


	/** Get */
	public Engine getEngine(){
		return this.engine;
	}

	public WaterSensor getWaterSensor(){
		return this.waterSensor;
	}

	public WashOption getWashOption(){
		return this.washOption;
	}

	public Timer getTimer(){
		return this.timer;
	}

	public int getWashTime(){
		return this.washTime;
	}

	public int getRinseTime(){
		return this.rinseTime;
	}

	public int getSpinTime(){
		return this.spinTime;
	}


	/** Set */
	public void setEngine( Engine engine ){
		 this.engine = engine;
	}

	public void setWaterSensor( WaterSensor waterSensor ){
		 this.waterSensor = waterSensor;
	}

	public void setWashOption( WashOption washOption ){
		 this.washOption = washOption;
	}

	public void setTimer( Timer timer ){
		 this.timer = timer;
	}

	public void setWashTime( int washTime ){
		 this.washTime = washTime;
	}

	public void setRinseTime( int rinseTime ){
		 this.rinseTime = rinseTime;
	}

	public void setSpinTime( int spinTime ){
		 this.spinTime = spinTime;
	}


	/** Methods */
	public static void main( String args[] ){
		/** Specified from Sequence Diagram Main */
		option = washOption.getwashSelection();
		switch(option){
			case 1:
				standardWash();
				break;

			case 2:
				twiceRinse();
				break;

			case 3:
				spin();
				break;

			default:
				break;
			}//endSwitch

	}

	private void wash(){
		/** Specified from Sequence Diagram Wash */
		engine.turnOn();
		timer.setDuration(washTime);

		/** Specified from Sequence Diagram Period */
		timer.start();
		time = timer.getValue();
		duration = timer.getDuration();
		while(time != duration){
			time = timer.count();
		}//endWhile
		engine.turnOff();

	}

	private void rinse(){
		/** Specified from Sequence Diagram Rinse */
		engine.turnOn();
		timer.setDuration(rinseTime);

		/** Specified from Sequence Diagram Period */
		timer.start();
		time = timer.getValue();
		duration = timer.getDuration();
		while(time != duration){
			time = timer.count();
		}//endWhile
		engine.turnOff();

	}

	private void spin(){
		/** Specified from Sequence Diagram Spin */
		engine.turnOn();
		timer.setDuration(spinTime);

		/** Specified from Sequence Diagram Period */
		timer.start();
		time = timer.getValue();
		duration = timer.getDuration();
		while(time != duration){
			time = timer.count();
		}//endWhile
		engine.turnOff();

	}

	private void fill(){
		/** Specified from Sequence Diagram Fill */
		full = waterSensor.check();
		while(!full){
			full = waterSensor.check();
		}//endWhile

	}

	private void empty(){
	}

	private void standardWash(){
	}

	private void twiceRinse(){
	}

	/** Methods From Realization of Machine */
	public void turnOn(){
	}

	public void turnOff(){
	}

}