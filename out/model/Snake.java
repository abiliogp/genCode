
public class Snake extends Activity{
	/**Attributes */
	private SnakeView mSnakeView;
	private String ICICLE_KEY = "snake_view";

	/** Constructor */
	public Snake( SnakeView mSnakeView , String ICICLE_KEY ){
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
	public void setMSnakeView( SnakeView mSnakeView ){
		 this.mSnakeView = mSnakeView;
	}

	public void setICICLE_KEY( String ICICLE_KEY ){
		 this.ICICLE_KEY = ICICLE_KEY;
	}

	/** Methods */
	public  void onCreateonCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Snake);
	}

	protected  void onPauseonPause(){
		super.onPause();
	}

	public  void onSavedInstanceStateonSavedInstanceState(){
	}

}