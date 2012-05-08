
public class BluetoothChat extends Activity{
	/**Attributes */
	private BluetoothChatService nBluetoothAdapter;
	private DeviceListActivity nChatService;

	/** Constructor */
	public BluetoothChat( BluetoothChatService nBluetoothAdapter , DeviceListActivity nChatService ){
		super();
		this.nBluetoothAdapter = nBluetoothAdapter;
		this.nChatService = nChatService;
	}

	/** Methods */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.BluetoothChat);
	}

	public void onStart(){
		super.onStart();
	}

	public void onResume(){
		super.onResume();
	}

	private void setupChat(){
	}

	public void onPause(){
		super.onPause();
	}

	public void onStop(){
		super.onStop();
	}

	public void onDestroy(){
		super.onDestroy();
	}

	private void sendMessage(){
	}

	public void setStatus(){
	}

	public void onActivityResult(){
	}

	public void connectDevice(){
	}

	public void onCreateOptionsMenu(){
	}

	public void onOptionsItemSelected(){
	}

}