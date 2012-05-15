import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class BluetoothChat extends Activity{
	/**Attributes */
	private BluetoothChatService nBluetoothAdapter;
	private DeviceListActivity nChatService;
	private Button mSendButton;
	private EditText mOutEditText;
	private ListView mConversationView;

	/** Constructor */
	public BluetoothChat( BluetoothChatService nBluetoothAdapter , DeviceListActivity nChatService , Button mSendButton , EditText mOutEditText , ListView mConversationView ){
		super();
		this.nBluetoothAdapter = nBluetoothAdapter;
		this.nChatService = nChatService;
		this.mSendButton = mSendButton;
		this.mOutEditText = mOutEditText;
		this.mConversationView = mConversationView;
	}

	/** Get */
	public BluetoothChatService getNBluetoothAdapter(){
		return this.nBluetoothAdapter;
	}

	public DeviceListActivity getNChatService(){
		return this.nChatService;
	}

	public Button getMSendButton(){
		return this.mSendButton;
	}

	public EditText getMOutEditText(){
		return this.mOutEditText;
	}

	public ListView getMConversationView(){
		return this.mConversationView;
	}

	/** Set */
	public void setNBluetoothAdapter( BluetoothChatService nBluetoothAdapter ){
		 this.nBluetoothAdapter = nBluetoothAdapter;
	}

	public void setNChatService( DeviceListActivity nChatService ){
		 this.nChatService = nChatService;
	}

	public void setMSendButton( Button mSendButton ){
		 this.mSendButton = mSendButton;
	}

	public void setMOutEditText( EditText mOutEditText ){
		 this.mOutEditText = mOutEditText;
	}

	public void setMConversationView( ListView mConversationView ){
		 this.mConversationView = mConversationView;
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