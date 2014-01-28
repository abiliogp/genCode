package generator.Csharp;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.sequence.Fragment;
import model.sequence.Operand;
import generator.GeneratorStrategy;

public class OperandCsharp implements GeneratorStrategy{

	private Operand operand;
	
	private FragmentCsharp generatorFragment;
	private GuardCsharp generatorGuard;
	
	private String tabInd;
	
	public OperandCsharp(Operand operand){
		this.operand = operand;
	}
	
	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		for(Fragment fragment : operand.getFragments()){
			generatorFragment = new FragmentCsharp(fragment);
			generatorFragment.codeGenerator(out, tab + 1);
		}
	}



	public void genCodeOpt(BufferedWriter out, int tab) throws IOException {		
		tabInd = Tool.indentation(tab);
		if( !(operand.getGuard().getSpecification().getBody().equals("else")) ){
			out.write("\n" + tabInd + "if (");
			generatorGuard = new GuardCsharp(operand.getGuard());
			generatorGuard.codeGenerator(out, tab);	
			out.write(")");
		} 
		out.write("\n" + tabInd + "{");
		codeGenerator(out, tab);
		out.write("\n" + tabInd + "}");
	}
	
	
	
	public void genCodeCaseSwitch(BufferedWriter out, int tab) throws IOException {
		out.write("case ");
		generatorGuard = new GuardCsharp(operand.getGuard());
		generatorGuard.genCodeValue(out);
		out.write(":\n");
		for(Fragment fragment : operand.getFragments()){
			generatorFragment = new FragmentCsharp(fragment);
			generatorFragment.codeGenerator(out, tab + 1);
		}
		tabInd = Tool.indentation(tab);
		out.write("\t" + tabInd +"break;\n");
	}

}
