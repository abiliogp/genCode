package sequence;

import java.io.BufferedReader;
import java.io.IOException;

import utilities.Tool;

public class GuardInt extends DataSequence{

	public GuardInt(String name) {
		super(name);
	}

	public void setValue(String value){
		this.value = value != null ? value : "0"; 
	}
	
	public void printProp() {
		System.out.println("gurad int:" + name);
		System.out.println("value int:" + value);
	}	
	
	
}
