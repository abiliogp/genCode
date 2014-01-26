import android.app.Activity;

public class Snake extends Activity{

	/**Attributes */	
	private SnakeView mSnakeView;	
	private static final String ICICLE_KEY = "snake_view";

	/** Constructor */
	public Snake( SnakeView mSnakeView, String ICICLE_KEY){
		super();
		this.mSnakeView = mSnakeView;
		this.ICICLE_KEY = ICICLE_KEY;
	}

	/** Get */
	public SnakeView getMSnakeView(){
		return this.mSnakeView;
	}

	public String getICICLE_KEY(){
		return this.ICICLE_KEY;
	}

	/** Set */
	public void setMSnakeView(SnakeView mSnakeView){
		 this.mSnakeView = mSnakeView;
	}

	/** Methods */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Snake);
	}

	protected void onPause(){
		super.onPause();
	}

	public void onSavedInstanceState(){
	}

	public void onBackPressed(){
		/** Specified from Sequence Diagram onBackPressed */
		ACTION_MAIN
	}

}