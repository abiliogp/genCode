import java.util.ArrayList;
import android.widget.TextView;
import java.util.Random;
import android.os.Bundle;
import android.view.KeyEvent;

public class SnakeView extends TileView{
	/**Attributes */
	private String TAG = "SnakeView";
	private int mMode;
	public int PAUSE;
	public int READY = 1;
	public int RUNNING = 2;
	public int LOSE = 3;
	public int mDirection;
	public int mNextDirection;
	private int NORTH = 1;
	private int SOUTH = 2;
	private int EAST = 3;
	private int WEST = 4;
	private int RED_STAR = 1;
	private int YELLOW_STAR = 2;
	private int GREEN_STAR = 3;
	private int mScore;
	private int mMoveDelay = 600;
	private int mLastMove;
	private TextView mStatusText;
	private Random RNG;
	
	private ArrayList<Coordinate> mSnakeTrail;
	
	private ArrayList<Coordinate> mAppleList;
	
	private ArrayList<RefreshHandler> mRedrawHandler;
/**Attribute of Return Method coordArrayList */int ;
/**Attribute of Return Method saveState */Bundle ;
/**Attribute of Return Method coordArrayToArrayList */ArrayList<int> ;
/**Attribute of Return Method onKeyDown */boolean ;

	/** Constructor */
	public SnakeView( String TAG , int mMode , int PAUSE , int READY , int RUNNING , int LOSE , int mDirection , int mNextDirection , int NORTH , int SOUTH , int EAST , int WEST , int RED_STAR , int YELLOW_STAR , int GREEN_STAR , int mScore , int mMoveDelay , int mLastMove , TextView mStatusText , Random RNG ,,,){
		super();
		this.TAG = TAG;
		this.mMode = mMode;
		this.PAUSE = PAUSE;
		this.READY = READY;
		this.RUNNING = RUNNING;
		this.LOSE = LOSE;
		this.mDirection = mDirection;
		this.mNextDirection = mNextDirection;
		this.NORTH = NORTH;
		this.SOUTH = SOUTH;
		this.EAST = EAST;
		this.WEST = WEST;
		this.RED_STAR = RED_STAR;
		this.YELLOW_STAR = YELLOW_STAR;
		this.GREEN_STAR = GREEN_STAR;
		this.mScore = mScore;
		this.mMoveDelay = mMoveDelay;
		this.mLastMove = mLastMove;
		this.mStatusText = mStatusText;
		this.RNG = RNG;
		this.mSnakeTrail = new ArrayList<Coordinate>();
		this.mAppleList = new ArrayList<Coordinate>();
		this.mRedrawHandler = new ArrayList<RefreshHandler>();
	}

	/** Get */
	public String getTAG(){
		return this.TAG;
	}

	public int getMMode(){
		return this.mMode;
	}

	public int getNORTH(){
		return this.NORTH;
	}

	public int getSOUTH(){
		return this.SOUTH;
	}

	public int getEAST(){
		return this.EAST;
	}

	public int getWEST(){
		return this.WEST;
	}

	public int getRED_STAR(){
		return this.RED_STAR;
	}

	public int getYELLOW_STAR(){
		return this.YELLOW_STAR;
	}

	public int getGREEN_STAR(){
		return this.GREEN_STAR;
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

	public TextView getMStatusText(){
		return this.mStatusText;
	}

	public Random getRNG(){
		return this.RNG;
	}

	public Coordinate getMSnakeTrail(){
		return this.mSnakeTrail;
	}

	public Coordinate getMAppleList(){
		return this.mAppleList;
	}

	public RefreshHandler getMRedrawHandler(){
		return this.mRedrawHandler;
	}

	/** Set */
	public void setTAG( String TAG ){
		 this.TAG = TAG;
	}

	public void setMMode( int mMode ){
		 this.mMode = mMode;
	}

	public void setNORTH( int NORTH ){
		 this.NORTH = NORTH;
	}

	public void setSOUTH( int SOUTH ){
		 this.SOUTH = SOUTH;
	}

	public void setEAST( int EAST ){
		 this.EAST = EAST;
	}

	public void setWEST( int WEST ){
		 this.WEST = WEST;
	}

	public void setRED_STAR( int RED_STAR ){
		 this.RED_STAR = RED_STAR;
	}

	public void setYELLOW_STAR( int YELLOW_STAR ){
		 this.YELLOW_STAR = YELLOW_STAR;
	}

	public void setGREEN_STAR( int GREEN_STAR ){
		 this.GREEN_STAR = GREEN_STAR;
	}

	public void setMScore( int mScore ){
		 this.mScore = mScore;
	}

	public void setMMoveDelay( int mMoveDelay ){
		 this.mMoveDelay = mMoveDelay;
	}

	public void setMLastMove( int mLastMove ){
		 this.mLastMove = mLastMove;
	}

	public void setMStatusText( TextView mStatusText ){
		 this.mStatusText = mStatusText;
	}

	public void setRNG( Random RNG ){
		 this.RNG = RNG;
	}

	public void setMSnakeTrail( Coordinate mSnakeTrail ){
		 this.mSnakeTrail = mSnakeTrail;
	}

	public void setMAppleList( Coordinate mAppleList ){
		 this.mAppleList = mAppleList;
	}

	public void setMRedrawHandler( RefreshHandler mRedrawHandler ){
		 this.mRedrawHandler = mRedrawHandler;
	}

	/** Methods */
	public  void SnakeViewSnakeView(Context context, AttributeSet attrs){
	}

	public  void SnakeViewSnakeView(Context context, AttributeSet attrs, int defStyle){
	}

	private  void initSnakeViewinitSnakeView(){
	}

	private  void initNewGameinitNewGame(){
	}

	private int coordArrayList(Coordinate cvec){
		return rawArray;
	}

	public Bundle saveState(){
		return map;
	}

	private ArrayList<int> coordArrayToArrayList(ArrayList<Coordinate> cvec){
		return rawArray;
	}

	public  void restoreStaterestoreState(Bundle icicle){
	}

	public boolean onKeyDown(int keyCode, KeyEvent msg){
		return boolean;
	}

	public  void setTextViewsetTextView(TextView newView){
	}

	public  void setModesetMode(int newMode){
	}

	private  void addRandomAppleaddRandomApple(){
	}

	public  void updateupdate(){
	}

	private  void updateWallsupdateWalls(){
	}

	private  void updateApplesupdateApples(){
	}

	private  void updateSnakeupdateSnake(){
	}

	public class RefreshHandler extends Handler{

		/** Constructor */
		public RefreshHandler(){
			super();
		}

		/** Methods */
		public  void handleMessagehandleMessage(Message msg){
		}

		public  void sleepsleep(long delayMillis){
		}

	}
	private class Coordinate{
		/**Attributes */
		public int x;
		public int y;
	/**Attribute of Return Method equals */boolean ;
	/**Attribute of Return Method toString */String ;

		/** Constructor */
		public Coordinate( int x , int y ){
		this.x = x;
		this.y = y;
		}

		/** Methods */
		public boolean equals(Coordinate other){
		return boolean;
		}

		public String toString(){
		return string;
		}

	}
}