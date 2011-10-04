package sequence;

import java.io.BufferedReader;
import java.io.IOException;


public abstract class DataSequence{

	/*
	 *Attributes
	 */
	protected String name;

	/*
	 *Get
	 */
	public String getName(){
		return this.name;
	}


	/*
	 *Set
	 */
	public void setName( String name ){
		 this.name = name;
	}


	
}