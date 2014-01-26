package generator.Android;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.sequence.Fragment;
import generator.GeneratorStrategy;

public class FragmentAndroid implements GeneratorStrategy {

	private Fragment fragment;

	private LifelineAndroid generatorLifeline;
	private MessageAndroid generatorMessage;
	private OperandAndroid generatorOperand;
	private MethodAndroid generatorMethod;
	private GuardAndroid generatorGuard;

	private String tabInd;

	private enum Operator {
		opt, alt, loop, seq, par, ref;
	}

	public FragmentAndroid(Fragment fragment) {
		this.fragment = fragment;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		if (fragment.getType().equals("uml:MessageOccurrenceSpecification")) {
			genCodeMessage(out, tab);
		}
		if (fragment.getType().equals("uml:CombinedFragment")) {
			genCodeCombined(out, tab);
		}

		if (fragment.getType().equals("uml:InteractionUse")) {
			if (fragment.getRefersTo() != null) {
				InteractionAndroid generatorInteraction = new InteractionAndroid(
						fragment.getRefersTo());
				generatorInteraction.codeGenerator(out, tab);
			}
		}
	}

	public void genCodeCombined(BufferedWriter out, int tab) throws IOException {
		if (fragment.getMessage() != null) {
			generatorMessage = new MessageAndroid(fragment.getMessage());
			generatorMessage.codeGenerator(out, tab);
		}
		if (fragment.getOperator() != null) {
			switch (Operator.valueOf(fragment.getOperator())) {
			case opt:
				genCodeOpt(out, tab);
				break;
			case alt:
				genCodeAlt(out, tab);
				break;
			case loop:
				genCodeLoop(out, tab);
				break;
			default:
				break;
			}
		}
	}

	public void genCodeMessage(BufferedWriter out, int tab) throws IOException {
		if (fragment.isSend()) {
			genCodeMessageNormal(out, tab);
		}
		// if(event.getType().equals("uml:SendOperationEvent")){
		// if(event.getOperation().isSet()){
		// genCodeMessageSet(out, tab);
		// } else if(event.getOperation().isGet()){
		// genCodeMessageGet(out, tab);
		// } else{
		// genCodeMessageNormal(out, tab);
		// }
		// }
	}

	/**
	 * Geração de chamadas de método: para métodos get e set pertencentes ao
	 * modelo avaliado realiza geração de acordo com as otimizações Android
	 * 
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
		if (fragment.getEvent().getType().equals("uml:SendOperationEvent")) {
			generatorMethod = new MethodAndroid(fragment.getEvent().getOperation());
			generatorMethod.genCodeCallGet(out);
		}
		out.write(";");
	}

	private void genCodeMessageNormal(BufferedWriter out, int tab)
			throws IOException {
		generatorMessage = new MessageAndroid(fragment.getMessage());
		generatorMessage.codeGenerator(out, tab);
		if(fragment.getMessage() != null){
			out.write(fragment.getMessage().getName());
		}
		if (fragment.getMessage().getSort() != null)
			if (fragment.getMessage().getSort().equals("createMessage"))
				return;
		// event.genCode(out);
		generatorMessage.genCodeArguments(out);
		out.write(");");
	}

	private void genCodeOpt(BufferedWriter out, int tab) throws IOException {
		generatorOperand = new OperandAndroid(fragment.getOperands().get(0));
		generatorOperand.genCodeOpt(out, tab);
	}

	private void genCodeAlt(BufferedWriter out, int tab) throws IOException {
		String tabSubInd;
		tabInd = Tool.indentation(tab);
		tabSubInd = Tool.indentation(tab + 1);
		if (fragment.getOperands().size() > 2) {
			if (fragment.getOperands().get(0).getGuard().isExpressionLogic()) {
				out.write(tabInd + "switch(");
				generatorGuard = new GuardAndroid(fragment.getOperands().get(0)
						.getGuard());
				generatorGuard.genCodeVariable(out);
				out.write("){");
				int i = 0;
				for (; i < fragment.getOperands().size(); i++) {
					if (!(fragment.getOperands().get(i).getGuard()
							.isExpressionLogic())) {
						break;
					}
					out.write("\n" + tabSubInd);
					generatorOperand = new OperandAndroid(fragment
							.getOperands().get(i));
					generatorOperand.genCodeCaseSwitch(out, tab + 1);
				}
				out.write("\n" + tabSubInd + "default:");
				out.write("\n\t" + tabSubInd + "break;");
				out.write("\n" + tabSubInd + "}//endSwitch\n");
				for (; i < fragment.getOperands().size(); i++) {
					generatorOperand = new OperandAndroid(fragment
							.getOperands().get(i));
					generatorOperand.genCodeOpt(out, tab);
				}
			}
		} else {
			generatorOperand = new OperandAndroid(fragment.getOperands().get(0));
			generatorOperand.genCodeOpt(out, tab);
			out.write(" else ");
			generatorOperand = new OperandAndroid(fragment.getOperands().get(1));
			generatorOperand.genCodeOpt(out, tab);
		}

	}

	private void genCodeLoop(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		if (fragment.getOperands().get(0).getGuard().getMinint() != null) {
			genCodeFor(out, tab);
		} else {
			genCodeWhile(out, tab);
		}
		out.write("){");
		generatorOperand = new OperandAndroid(fragment.getOperands().get(0));
		generatorOperand.codeGenerator(out, tab);
		out.write("\n" + tabInd + "}");
	}

	private void genCodeWhile(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		out.write(tabInd + "while(");
		if (fragment.getOperands().get(0).getGuard().getSpecification()
				.isLogic()) {
			if ((fragment.getOperands().get(0).getGuard().getSpecification()
					.getValue().equals("false"))
					|| (fragment.getOperands().get(0).getGuard()
							.getSpecification().getExpression().equals("!="))) {
				out.write("!");
			}
			generatorGuard = new GuardAndroid(fragment.getOperands().get(0)
					.getGuard());
			generatorGuard.genCodeVariable(out);
		} else {
			generatorGuard = new GuardAndroid(fragment.getOperands().get(0)
					.getGuard());
			generatorGuard.codeGenerator(out, tab);
		}
	}

	private void genCodeFor(BufferedWriter out, int tab) throws IOException {
		String variable;
		tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd);
		if (fragment.getOperands().get(0).getGuard().getMaxint().equals("*")) {// for
			if (fragment.getOperands().get(0).getGuard().getSpecification().getExpression().equals("<")) {
				variable = fragment.getOperands().get(0).getGuard().getSpecification().getVariable();
				out.write("for(int " + variable + " = "
						+ fragment.getOperands().get(0).getGuard().getMinint()
						+ "; " 
						+ fragment.getOperands().get(0).getGuard().getSpecification().getBody()
						+ "; " + variable + "++");
			} else {
				out.write("for("
						+ fragment.getCovered().getRepresents().getType()
						+ " c : "
						+ fragment.getCovered().getRepresents().getName());
			}
		} else if (fragment.getOperands().get(0).getGuard().getMinint() != null) {
			generatorGuard = new GuardAndroid(fragment.getOperands().get(0)
					.getGuard());
			generatorGuard.genCodeForNormal(out);
		}

	}

	public void genCodeAttribute(BufferedWriter out) throws IOException {
		if (fragment.getCovered() != null) {
			generatorLifeline = new LifelineAndroid(fragment.getCovered());
			generatorLifeline.genCodeAttribute(out);
		}
	}

	public void genCodeAttributeGetSet(BufferedWriter out) throws IOException {
		if (fragment.getCovered() != null) {
			generatorLifeline = new LifelineAndroid(fragment.getCovered());
			generatorLifeline.genCodeAttributeGetSet(out);
		}
	}

	public void genCodeCreate(BufferedWriter out) throws IOException {
		System.out.println("++" + fragment.getMessage().getName());
		if (fragment.getCovered() != null) {
			generatorLifeline = new LifelineAndroid(fragment.getCovered());
			generatorLifeline.genCodeCreate(out);
		}
	}
}
