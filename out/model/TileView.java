import java.util.ArrayList;
import android.content.Context;
import android.util.AttributeSet;

public class TileView extends View{

	/**Attributes */	
	protected static final int mTileSize;	
	protected static final int mXTileCount;	
	protected static final int mYTileCount;	
	private static final int mXOffset;	
	private static final int mYOffset;	
	private static Paint mPaint;	
	private ArrayList<Bitmap> mTileArray;	
	private ArrayList<int> mTileGrid;

	/** Constructor */
	public TileView( int mTileSize, int mXTileCount, int mYTileCount, int mXOffset, int mYOffset, Paint mPaint, ArrayList<Bitmap> mTileArray, ArrayList<int> mTileGrid){
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

	public ArrayList<Bitmap getMTileArray(){
		return this.mTileArray;
	}

	public ArrayList<int getMTileGrid(){
		return this.mTileGrid;
	}

	/** Set */
	public void setMTileArray(ArrayList<Bitmap> mTileArray){
		 this.mTileArray = mTileArray;
	}

	public void setMTileGrid(ArrayList<int> mTileGrid){
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

	public void onDraw(Canvas canvas){
		/** Specified from Sequence Diagram onDraw */
		for(int x = 0; x < mXTileCount; x++){
			for(int y = 0; y < mYTileCount; y++){
				if(mTileGrid[x][y]> 0){
					canvas.drawBitmap(mTileArray[mTileGrid[x][y]], mXOffset + x * mTileSize, mYOffset + y * mTileSize, mPaint));
				}
			}
		}
	}

}