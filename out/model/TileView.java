import java.util.ArrayList;
import android.content.Context;
import android.util.AttributeSet;

public class TileView extends View{
	/**Attributes */
	protected int mTileSize;
	protected int mXTileCount;
	protected int mYTileCount;
	private int mXOffset;
	private int mYOffset;
	private Paint mPaint;
	
	private ArrayList<Bitmap> mTileArray;
	
	private ArrayList<int> mTileGrid;

	/** Constructor */
	public TileView( int mTileSize , int mXTileCount , int mYTileCount , int mXOffset , int mYOffset , Paint mPaint ,,){
		super();
		this.mTileSize = mTileSize;
		this.mXTileCount = mXTileCount;
		this.mYTileCount = mYTileCount;
		this.mXOffset = mXOffset;
		this.mYOffset = mYOffset;
		this.mPaint = mPaint;
		this.mTileArray = new ArrayList<Bitmap>();
		this.mTileGrid = new ArrayList<int>();
	}

	/** Get */
	public int getMTileSize(){
		return this.mTileSize;
	}

	public int getMXTileCount(){
		return this.mXTileCount;
	}

	public int getMYTileCount(){
		return this.mYTileCount;
	}

	public int getMXOffset(){
		return this.mXOffset;
	}

	public int getMYOffset(){
		return this.mYOffset;
	}

	public Paint getMPaint(){
		return this.mPaint;
	}

	public Bitmap getMTileArray(){
		return this.mTileArray;
	}

	public int getMTileGrid(){
		return this.mTileGrid;
	}

	/** Set */
	public void setMTileSize( int mTileSize ){
		 this.mTileSize = mTileSize;
	}

	public void setMXTileCount( int mXTileCount ){
		 this.mXTileCount = mXTileCount;
	}

	public void setMYTileCount( int mYTileCount ){
		 this.mYTileCount = mYTileCount;
	}

	public void setMXOffset( int mXOffset ){
		 this.mXOffset = mXOffset;
	}

	public void setMYOffset( int mYOffset ){
		 this.mYOffset = mYOffset;
	}

	public void setMPaint( Paint mPaint ){
		 this.mPaint = mPaint;
	}

	public void setMTileArray( Bitmap mTileArray ){
		 this.mTileArray = mTileArray;
	}

	public void setMTileGrid( int mTileGrid ){
		 this.mTileGrid = mTileGrid;
	}

	/** Methods */
	public void TileView(Context context, AttributeSet attrs, int defStyle){
	}

	public void TileView(Context context, AttributeSet attrs){
	}

	public void resetTiles(int tilecount){
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh){
	}

	public void loadTile(int key, Drawable tile){
	}

	public void clearTiles(){
	}

	public void setTile(int tileindex, int x, int y){
	}

	public void onDraw(Canvas canvas){
	}

}