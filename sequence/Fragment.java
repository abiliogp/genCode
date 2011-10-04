import java.util.ArrayList;

public class Fragment extends DataSequence{

	/*
	 *Attributes
	 */
	
	private ArrayList<OperationEvent> event;
	
	private ArrayList<Message> message;

	/*
	 *Constructor
	 */
	public Fragment(){
		super();
		this.event = new ArrayList<OperationEvent>();
		this.message = new ArrayList<Message>();
	}


	/*
	 *Get
	 */
	public OperationEvent getEvent(){
		return this.event;
	}

	public Message getMessage(){
		return this.message;
	}


	/*
	 *Set
	 */
	public void setEvent( OperationEvent event ){
		 this.event = event;
	}

	public void setMessage( Message message ){
		 this.message = message;
	}

	/*
	 *Abstract Method of Super
	 */
	public void parser(){
	}

}