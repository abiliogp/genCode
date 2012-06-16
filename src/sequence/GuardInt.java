package sequence;

import java.io.BufferedReader;
import java.io.IOException;

import utilities.Tool;

public class GuardInt extends DataSequence{

	public GuardInt(String name) {
		super(name);
		value = "0";
	}

	public void setValue(String value){
		if(!value.isEmpty())
			this.value = value;
	}
	
	public void printProp() {
		System.out.println("gurad int:" + name);
		System.out.println("value int:" + value);
	}	
	
	
}
