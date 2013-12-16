package generator;

import java.io.BufferedWriter;
import java.io.IOException;

import model.structure.DataStructure;

public interface GeneratorStrategy {
	
	public void codeGenerator(BufferedWriter out, int tab) throws IOException;

}
