import java.util.ArrayList;
import android.widget.TextView;
import android.os.Bundle;
import android.view.KeyEvent;

public class SnakeView extends TileView{

	/**Attributes */	
	private ArrayList<RefreshHandler> refreshHandler;	
	private ArrayList<Coordinate> mAppleList;	
	private ArrayList<Coordinate> mSnakeTrail;	
	private int mScore;	
	private int mMoveDelay;	
	private int mLastMove;	public TextView textView;	
	private ArrayList<int> mNextDirectionArray;

	/** Constructor */
	public SnakeView( ArrayList<RefreshHandler> refreshHandler, ArrayList<Coordinate> mAppleList, ArrayList<Coordinate> mSnakeTrail, int mScore, int mMoveDelay, int mLastMove, TextView textView, ArrayList<int> mNextDirectionArray){
		super();
		this.refreshHandler = new ArrayList<RefreshHandler>();
		this.mAppleList = new ArrayList<Coordinate>();
		this.mSnakeTrail = new ArrayList<Coordinate>();
		this.mScore = mScore;
		this.mMoveDelay = mMoveDelay;
		this.mLastMove = mLastMove;
		this.textView = textView;
		this.mNextDirectionArray = new ArrayList<int>();
	}

	/** Get */
	public ArrayList<RefreshHandler getRefreshHandler(){
		return this.refreshHandler;
	}

	public ArrayList<Coordinate getMAppleList(){
		return this.mAppleList;
	}

	public ArrayList<Coordinate getMSnakeTrail(){
		return this.mSnakeTrail;
	}

	public int getMScore(){
		return this.mScore;
	}

	public int getMMoveDelay(){
		return this.mMoveDelay;
	}

	public int getMLastMove(){
		return this.mLastMove;
	}

	public TextView getTextView(){
		return this.textView;
	}

	public ArrayList<int getMNextDirectionArray(){
		return this.mNextDirectionArray;
	}

	/** Set */
	public void setRefreshHandler(ArrayList<RefreshHandler> refreshHandler){
		 this.refreshHandler = refreshHandler;
	}

	public void setMAppleList(ArrayList<Coordinate> mAppleList){
		 this.mAppleList = mAppleList;
	}

	public void setMSnakeTrail(ArrayList<Coordinate> mSnakeTrail){
		 this.mSnakeTrail = mSnakeTrail;
	}

	public void setMScore(int mScore){
		 this.mScore = mScore;
	}

	public void setMMoveDelay(int mMoveDelay){
		 this.mMoveDelay = mMoveDelay;
	}

	public void setMLastMove(int mLastMove){
		 this.mLastMove = mLastMove;
	}

	public void setMNextDirectionArray(ArrayList<int> mNextDirectionArray){
		 this.mNextDirectionArray = mNextDirectionArray;
	}

	/** Methods */
	public void restoreState(Bundle icicle){
	}

	public void onKeyDown(int keyCode, KeyEvent msg){
	}

	private void enqueueDirection(){
	}

	public class Coordinate{

		/** Constructor */
		public Coordinate(){
		}

	}
	public class RefreshHandler extends Handler{

		/** Constructor */
		public RefreshHandler(){
			super();
		}

	}
}