import android.app.Activity;
import android.os.Bundle;

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
	public void onCreate(Bundle savedInstanceState, Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Snake);
		/** Specified from Sequence Diagram onCreate */
		mSnakeView.setTextView(R.id.snake);
		if(savedInstanceState  ==  null){
			mSnakeView.setMode(SnakeView.READY);
		} else {
			Bundle map  = savedInstanceState.getBundle(SnakeView.PAUSE);
			if(map  !=  null){
				mSnakeView.restoreState(map);
			} else {
				mSnakeView.setMode(SnakeView.PAUSE);
			}
		}
	}

	protected void onPause(){
		super.onPause();
	}

	public void onSaveInstanceState(Bundle outState){
		outState.putBundle();
	}

}
