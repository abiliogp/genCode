import android.app.Activity;
import java.util.ArrayList;

public class Agenda extends Activity{

	/**Attributes */	
	public ArrayList<Contato> contato;

	/** Constructor */
	public Agenda(){
		super();
		this.contato = new ArrayList<Contato>();
	}

	/** Methods */
	public void addContato(){
		/** Specified from Sequence Diagram addContato */
		contato.criaContato();
		for(Contato c : contato){
			if(n == 1){
				contato.criaContato();
			}
		}
	}

	public void onCreate(void savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Agenda);
	}

}