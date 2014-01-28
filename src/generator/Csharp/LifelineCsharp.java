package generator.Csharp;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.Model;
import model.sequence.Fragment;
import model.sequence.Lifeline;
import model.structure.Attribute;
import generator.GeneratorStrategy;

public class LifelineCsharp implements GeneratorStrategy{
	
	private Lifeline lifeline;
	private FragmentCsharp generatorFragment;
	
	public LifelineCsharp(Lifeline lifeline){
		this.lifeline = lifeline;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		for(Fragment order : lifeline.getOrder()){
			generatorFragment = new FragmentCsharp(order);
			generatorFragment.codeGenerator(out, tab);
		}		
	}



	public void genCodeAttribute(BufferedWriter out) throws IOException {
		if(lifeline.getParameter() != null){
			out.write(lifeline.getParameter() + ".");
		} else {
			if(lifeline.getRepresents() != null) {
				out.write(lifeline.getRepresents().getName() + ".");
			}
		}
	}

	public void genCodeAttributeGetSet(BufferedWriter out) throws IOException {
		if(lifeline.getParameter() != null){
			out.write(lifeline.getParameter());
		} else {
			if(lifeline.getRepresents() != null) {
				out.write(lifeline.getRepresents().getName());
			}
		}
	}

	public void genCodeCreate(BufferedWriter out) throws IOException {
		if(lifeline.getRepresents() != null){
			out.write(lifeline.getRepresents().getName() + " = new " + lifeline.getRepresents().getType() + "();");
		}
	}


}
