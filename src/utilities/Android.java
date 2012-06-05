package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Android {

	public enum Classes {
		Activity, ListActivity, MapActivity, R, Thread, View, Handler;
	}
	
	public enum Methods{
		onCreate, onStop, onPause, onResume, onDestroy, onStart, onClick, onSaveInstanceState;
	}
	

	//Imports
	public static Set<String> Widget = new HashSet<String>(Arrays.asList(new String[]{ 
		"ListView", "EditText", "Button", "TextView", "ProgressBar", "LinearLayout", "ImageView", "ImageButton" 
	})); 
	
	public static Set<String> View = new HashSet<String>(Arrays.asList(new String[]{
		"Menu", "SubMenu", "MenuItem", "MenuInflater", "Window", "View", "KeyEvent"
	}));
	
	//import android.view.View.OnClickListener;
	public static Set<String> view = new HashSet<String>(Arrays.asList(new String[]{
		"OnClickListener"
	}));
	
	public static Set<String> Content = new HashSet<String>(Arrays.asList(new String[]{
		"Intent", "Context"
	}));
	
	public static Set<String> res = new HashSet<String>(Arrays.asList(new String[]{
		"Resources"
	}));
	
	public static Set<String> os = new HashSet<String>(Arrays.asList(new String[]{
		"Bundle", "Handler", "Message"
	}));
	
	//import android.app.ActionBar;
	public static Set<String> app = new HashSet<String>(Arrays.asList(new String[]{
		"ActionBar"
	}));
	
	public static Set<String> adntroidUtil = new HashSet<String>(Arrays.asList(new String[]{
		"Log", "AttributeSet"
	}));
	
	public static Set<String> javaUtil = new HashSet<String>(Arrays.asList(new String[]{
		"Random"
	}));
	
}
