package generator.Android;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.sequence.Fragment;
import generator.GeneratorStrategy;

public class FragmentAndroid implements GeneratorStrategy{

	private Fragment fragment;
	private LifelineAndroid generatorLifeline;
	private MessageAndroid generatorMessage;
	private String tabInd;
	
	private enum Operator{
		opt, alt, loop, seq, par, ref;
	}
	
	
	public FragmentAndroid(Fragment fragment){
		this.fragment = fragment;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		if(fragment.getType().equals("uml:MessageOccurrenceSpecification")){
			genCodeMessage(out, tab);
		}
		if(fragment.getType().equals("uml:CombinedFragment")){
			genCodeCombined(out, tab);
		}
		
		if(fragment.getType().equals("uml:InteractionUse")){
			if(fragment.getRefersTo() != null){
				InteractionAndroid generatorInteraction = new InteractionAndroid(fragment.getRefersTo());
				generatorInteraction.codeGenerator(out, tab);
			}
		}
	}
	
	public void genCodeCombined(BufferedWriter out, int tab) throws IOException {
		if(fragment.getMessage() != null){
			generatorMessage = new MessageAndroid(fragment.getMessage());
			generatorMessage.codeGenerator(out, tab);
		}
		if(fragment.getOperator() != null){
			switch(Operator.valueOf(fragment.getOperator())){
				case opt:
					genCodeOpt(out, tab);
					break;
				case alt:
					genCodeAlt(out, tab);
					break;
				case loop:
					genCodeLoop(out, tab);
					break;		
			}
		}		
	}


	



	public void genCodeMessage(BufferedWriter out, int tab) throws IOException {
		if(fragment.isSend()){
			genCodeMessageNormal(out, tab);
		}
//		if(event.getType().equals("uml:SendOperationEvent")){
//			if(event.getOperation().isSet()){
//				genCodeMessageSet(out, tab);
//			} else if(event.getOperation().isGet()){
//				genCodeMessageGet(out, tab);
//			} else{
//				genCodeMessageNormal(out, tab);
//			}
//		}
	}

	/**
	 * Geração de chamadas de método:
	 * 	para métodos get e set pertencentes ao modelo avaliado
	 * 	realiza geração de acordo com as otimizações Android
	 * @param out
	 * @param tab
	 * @throws IOException
	 */
	private void genCodeMessageSet(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		generatorMessage = new MessageAndroid(fragment.getMessage());
		generatorMessage.genCodeAtributteGetSet(out);
		out.write(" = ");
		generatorMessage.genCodeArguments(out);
		out.write(";");
	}	
	
	private void genCodeMessageGet(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		generatorMessage = new MessageAndroid(fragment.getMessage());
		generatorMessage.genCodeVariable(out);
		out.write(" = ");
		generatorMessage.genCodeAtributteGetSet(out);
		out.write(".");
		fragment.getEvent().genCodeGet(out);
		out.write(";");
	}
	
	private void genCodeMessageNormal(BufferedWriter out, int tab) throws IOException {
		generatorMessage = new MessageAndroid(fragment.getMessage());
		generatorMessage.codeGenerator(out, tab);
		out.write(fragment.getName() +"(");
		if(fragment.getMessage().getSort()!= null)
		if(fragment.getMessage().getSort().equals("createMessage"))
			return;
		//event.genCode(out);
		generatorMessage.genCodeArguments(out);
		out.write(");");
	}
	
	private void genCodeOpt(BufferedWriter out, int tab) throws IOException {
		fragment.getOperands().get(0).genCodeOpt(out, tab);
	}

	private void genCodeAlt(BufferedWriter out, int tab) throws IOException {
		String tabSubInd;
		tabInd = Tool.indentation(tab);
		tabSubInd = Tool.indentation(tab + 1);
		if(fragment.getOperands().size() > 2){
			if(fragment.getOperands().get(0).getGuard().isExpressionLogic()){
				out.write(tabInd + "switch(");
				fragment.getOperands().get(0).getGuard().genCodeVariable(out);
				out.write("){");
				int i = 0;
				for(; i < fragment.getOperands().size() ; i++){
					if( !(fragment.getOperands().get(i).getGuard().isExpressionLogic()) ){
						break;
					}
					out.write("\n" + tabSubInd);
					fragment.getOperands().get(i).genCodeCaseSwitch(out, tab + 1);
				}
				out.write("\n" + tabSubInd + "default:");
				out.write("\n\t" + tabSubInd + "break;");
				out.write("\n" + tabSubInd + "}//endSwitch\n");
				for(; i < fragment.getOperands().size() ; i++){
					fragment.getOperands().get(i).genCodeOpt(out, tab);
				}
			}
		} else{
			fragment.getOperands().get(0).genCodeOpt(out, tab);
			out.write(" else ");
			fragment.getOperands().get(1).genCodeOpt(out, tab);
		}
		
	}
	
	private void genCodeLoop(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		if(fragment.getOperands().get(0).getGuard().getMinint() != null){
			genCodeFor(out,tab);
		}
		else {
			 genCodeWhile(out,tab);
		}
		out.write("){");
		fragment.getOperands().get(0).genCode(out, tab);
		out.write("\n" + tabInd + "}");
	}

	
	private void genCodeWhile(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		out.write(tabInd + "while(");
		if(fragment.getOperands().get(0).getGuard().getSpecification().isLogic()){
			if((fragment.getOperands().get(0).getGuard().getSpecification().getValue().equals("false")) ||
			   (fragment.getOperands().get(0).getGuard().getSpecification().getExpression().equals("!=")))
			{
				out.write("!");
			}
			fragment.getOperands().get(0).getGuard().genCodeVariable(out);
		} else{
			fragment.getOperands().get(0).getGuard().getSpecification().genCode(out);
		}
	}

	private void genCodeFor(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		if(fragment.getCovered().getRepresents().getUpperValue() == '*'){//for		
			if(fragment.getCovered().getRepresents().isPrimitiveType()){
				out.write("for(int i=" + fragment.getOperands().get(0).getGuard().getMinint() + "; " +
						  "i < " + fragment.getCovered().getRepresents().getName() + "length() ; i++");
			} else{
				out.write("for(" + fragment.getCovered().getRepresents().getType() + " c : " 
						+ fragment.getCovered().getRepresents().getName());
			}
		}
		else if(fragment.getOperands().get(0).getGuard().getMinint() != null) {
			fragment.getOperands().get(0).getGuard().genCodeForNormal(out);
		}
		
	}
	
	public void genCodeAttribute(BufferedWriter out) throws IOException {
		if(fragment.getCovered() != null){
			generatorLifeline = new LifelineAndroid(fragment.getCovered());
			generatorLifeline.genCodeAttribute(out);
		}
	}


	public void genCodeAttributeGetSet(BufferedWriter out) throws IOException {
		if(fragment.getCovered() != null){
			generatorLifeline = new LifelineAndroid(fragment.getCovered());
			generatorLifeline.genCodeAttributeGetSet(out);
		}
	}

	public void genCodeCreate(BufferedWriter out) throws IOException {
		if(fragment.getCovered() != null){
			generatorLifeline = new LifelineAndroid(fragment.getCovered());
			generatorLifeline.genCodeCreate(out);
		}
	}
}
