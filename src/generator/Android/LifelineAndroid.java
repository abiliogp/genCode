package generator.Android;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.sequence.Fragment;
import model.sequence.Lifeline;
import model.structure.Attribute;
import generator.GeneratorStrategy;

public class LifelineAndroid implements GeneratorStrategy{
	
	private Lifeline lifeline;
	private FragmentAndroid generatorFragment;
	
	public LifelineAndroid(Lifeline lifeline){
		this.lifeline = lifeline;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		for(Fragment order : lifeline.getOrder()){
			generatorFragment = new FragmentAndroid(order);
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
		if(lifeline.getName().contains("Intent")){
			int n;
			n = lifeline.getName().indexOf(":");
			lifeline.setRepresents(new Attribute(lifeline.getName().substring(0, n - 1)));
			lifeline.getRepresents().setType(lifeline.getName().substring(n + 2));
			out.write(lifeline.getName().substring(n + 2) + " ");
			out.write(lifeline.getName().substring(0, n - 1) + " = new " );
			out.write(lifeline.getName().substring(n + 2) + "(");
			out.write(lifeline.getOrder().get(0).getMessage().getName());
			out.write(");");
		}
		else if(lifeline.getRepresents() != null){
			out.write(lifeline.getRepresents().getName() + " = new " + lifeline.getRepresents().getType() + "();");
		}
	}


}
