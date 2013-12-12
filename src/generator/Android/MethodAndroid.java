package generator.Android;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.structure.Method;
import model.structure.Parameter;
import generator.GeneratorStrategy;

public class MethodAndroid implements GeneratorStrategy {

	private Method method;
	
	private InteractionAndroid generatorInteraction;
	
	private String tabInd;
	private int tab;

	public MethodAndroid(Method method) {
		this.method = method;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		if (method.isSet() || method.isGet()) {
			return;
		}
		genCodeH(out, tab);
		genCodePmtCp(out, tab);
	}

	public void genCodeMtImplements(BufferedWriter out) throws IOException {
		out.write("\n\t" + method.getType() + " " + method.getName());
		genCodePmtIn(out);
		out.write(";");
	}

	

	public void genCodeH(BufferedWriter out, int tab) throws IOException {
		this.tab = tab;
		tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd + method.getVisibility());
		if ((method.isStatic())) {
			out.write(" static");
		}
		if ((method.isAbstract())) {
			out.write(" abstract");
		}
		out.write(" ");
		if (!method.getReturns().isEmpty()) {
			method.getReturns().get(0).genCodeMult(out);
		} else {
			out.write(method.getType() + " ");
		}
		out.write(method.getName());
	}

	private void genCodePmtCp(BufferedWriter out, int tab) throws IOException {
		if (method.getName().equals("main")) {
			out.write("( String args[] )");
		} else {
			genCodePmtIn(out);
		}
		if ((method.isAbstract())) {
			out.write(";");
		} else {
			genCodeCpMet(out);
			out.write("\n" + tabInd + "}\n");
		}
	}

	public void genCodeAndroid(String nameClass, BufferedWriter out, int tab)
			throws IOException {
		this.tab = tab;
		tabInd = Tool.indentation(tab);
		genCodeH(out, tab);
		if (method.getName().equals("onCreate")) {
			method.addParameter("savedInstanceState", "Bundle");
			genCodePmtIn(out);
			out.write("\n" + tabInd + "\tsuper.onCreate(savedInstanceState);");
			out.write("\n" + tabInd + "\tsetContentView(R.layout." + nameClass
					+ ");");
		} else if (method.getName().equals("onSaveInstanceState")) {
			method.addParameter("outState", "Bundle");
			genCodePmtIn(out);
			out.write("\n" + tabInd + "\toutState.putBundle();");
		} else if (method.getName().equals("onClick")) {
			method.addParameter("v", "View");
			genCodePmtIn(out);
			out.write("\n" + tabInd + "finish();");
		} else {
			genCodePmtIn(out);
			out.write("\n" + tabInd + "\tsuper." + method.getName() + "();");
		}
		if (method.getInteraction() != null) {
			generatorInteraction = new InteractionAndroid(method.getInteraction());
			generatorInteraction.codeGenerator(out, tab + 1);
		}
		out.write("\n" + tabInd + "}\n");
	}

	// Gera os Parametros de Entrada
	private void genCodePmtIn(BufferedWriter out) throws IOException {
		out.write("(");
		int i = 0;
		for (Parameter parameter : method.getParameters()) {
			if ((parameter.getDirection().equals("in"))
					|| (parameter.getDirection().equals("inout"))) {
				parameter.genCode(out);
			}
			i++;
			if (method.getParameters().size() > (i + 1)) {
				if (!method.getParameters().get(i + 1).getDirection().equals("out")) {
					out.write(", ");
				}
			}
		}
		out.write("){");
	}

	// gera o corpo do método
	private void genCodeCpMet(BufferedWriter out) throws IOException {
		// gera declaração dos out dentro do método
		this.genCodeDeclPmtOut(out);
		if (method.getInteraction() != null) {
			generatorInteraction = new InteractionAndroid(method.getInteraction());
			generatorInteraction.codeGenerator(out, tab + 1);
		}
		this.genCodeReturnPmtOut(out);
		this.genCodeReturn(out, tab);

	}

	// gera declaração dos parametros out dentro do método
	private void genCodeDeclPmtOut(BufferedWriter out) throws IOException {
		for (Parameter parameter : method.getParameters()) {
			if (parameter.getDirection().equals("out")) {
				out.write(tabInd);
				parameter.genCode(out);
				out.write(";");
			}
		}
	}

	// gera return dos out dentro do método
	private void genCodeReturnPmtOut(BufferedWriter out) throws IOException {
		for (Parameter parameter : method.getParameters()) {
			if (parameter.getDirection().equals("out")) {
				if (!(parameter.getType().equals(method.getType()))) {
					out.write("\n" + tabInd
							+ "\t/**WARNING: The Type of Return <"
							+ parameter.getType()
							+ "> *disagree of type the Method*/");
				}
				out.write(tabInd);
				out.write("return " + parameter.getName() + ";");
			}
		}
	}

	// gera return
	private void genCodeReturn(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		for (Parameter parameterReturn : method.getReturns()) {
			if (!(parameterReturn.getType().equals(method.getType()))) {
				out.write("\n" + tabInd + "\t/**WARNING: The Type of Return <"
						+ parameterReturn.getType()
						+ "> disagree of type the Method*/");
			}
			out.write("\n\t\treturn " + parameterReturn.getName() + ";");
		}
	}

	public void genCodeMtSuper(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd + "/** Abstract Method of Super*/");
		out.write("\n\t" + method.getVisibility());
		out.write(" " + method.getType() + " " + method.getName());
		this.genCodePmtIn(out);
		this.genCodeCpMet(out);
		out.write("\n" + tabInd + "}\n");
	}

	public void genCodeCall(BufferedWriter out) throws IOException {
		out.write(method.getName() + "(");
	}

	public void genCodeCallGet(BufferedWriter out) throws IOException {
		String aux;
		aux = method.getName().substring(3).toLowerCase();
		out.write(aux);
	}

}
