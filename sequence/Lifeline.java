import java.util.ArrayList;

public class Lifeline extends DataSequence{

	/*
	 *Attributes
	 */
	private void representsIPattribute;
	
	private ArrayList<Fragment> fragment;

	/*
	 *Constructor
	 */
	public Lifeline(){
		super();
		this.representsIPattribute; //WARNING: This attribute must be initialized
		this.fragment = new ArrayList<Fragment>();
	}


	/*
	 *Get
	 */
	public void getRepresentsIPattribute(){
		return this.representsIPattribute;
	}

	public Fragment getFragment(){
		return this.fragment;
	}


	/*
	 *Set
	 */
	public void setRepresentsIPattribute( void representsIPattribute ){
		 this.representsIPattribute = representsIPattribute;
	}

	public void setFragment( Fragment fragment ){
		 this.fragment = fragment;
	}

	/*
	 *Abstract Method of Super
	 */
	public void parser(){
	}

}