
public class WashingMachine{

	/**Attributes */
	private int washTime;
	private int rinseTime;
	private int spinTime;
	private Engine engine;
	private Timer timer;
	private WashOption washOption;
	private WaterSensor waterSensor;

	/** Constructor */
	public WashingMachine( int washTime , int rinseTime , int spinTime , Engine engine , Timer timer , WashOption washOption , WaterSensor waterSensor ){
		this.washTime = washTime;
		this.rinseTime = rinseTime;
		this.spinTime = spinTime;
		this.engine = engine;
		this.timer = timer;
		this.washOption = washOption;
		this.waterSensor = waterSensor;
	}


	/** Methods */
	public static void main( String args[] ){
	}

	private void wash(){
	}

	private void rinse(){
	}

	private void spin(){
	}

	private void fill(){
	}

	private void empty(){
	}

	private void standardWash(){
	}

	private void twiceRinse(){
	}

}