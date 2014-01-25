import android.app.Activity;

public class Start extends Activity{

	/**Attributes */	
	private Test test;	
	private PhoneBook agenda;

	/** Constructor */
	public Start( Test test , PhoneBook agenda ){
		super();
		this.test = test;
		this.agenda = agenda;
	}

	/** Get */
	public Test getTest(){
		return this.test;
	}

	public PhoneBook getAgenda(){
		return this.agenda;
	}

	/** Set */
	public void setTest( Test test ){
		 this.test = test;
	}

	public void setAgenda( PhoneBook agenda ){
		 this.agenda = agenda;
	}

	/** Methods */
	public void onCreateOptionsMenu(){
	}

}