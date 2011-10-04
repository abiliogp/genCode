import java.util.ArrayList;

public class Sequence extends DataSequence{

	/*
	 *Attributes
	 */
	
	private ArrayList<Lifeline> lifeline;

	/*
	 *Constructor
	 */
	public Sequence(){
		super();
		this.lifeline = new ArrayList<Lifeline>();
	}


	/*
	 *Get
	 */
	public Lifeline getLifeline(){
		return this.lifeline;
	}


	/*
	 *Set
	 */
	public void setLifeline( Lifeline lifeline ){
		 this.lifeline = lifeline;
	}

	/*
	 *Abstract Method of Super
	 */
	public void parser(){
	}

}