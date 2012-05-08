
public class DeviceListActivity extends Activity{

	/** Constructor */
	public DeviceListActivity(){
		super();
	}

	/** Methods */
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.DeviceListActivity);
	}

	protected void onDestroy(){
		super.onDestroy();
	}

	private void doDiscovery(){
	}

}