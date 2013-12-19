package generator.Csharp;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.structure.Method;
import model.structure.Parameter;
import generator.GeneratorStrategy;

public class MethodCsharp implements GeneratorStrategy{

	private Method method;

	private String tabInd;
	private int tab;
	
	public MethodCsharp(Method method){
		this.method = method;
	}
	
	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
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
			if (method.getReturns().get(0).getUpperValue() == '*' 
				|| method.getReturns().get(0).getLowerValue() == '*') {
				out.write("ArrayList<" + method.getReturns().get(0).getType() + "> ");
			} else {
				out.write(method.getReturns().get(0).getType() + " ");
			}
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
			//genCodeCpMet(out);
			out.write("\n" + tabInd + "}\n");
		}
	}

	

	// Gera os Parametros de Entrada
	private void genCodePmtIn(BufferedWriter out) throws IOException {
		out.write("(");
		int i = 0;
		for (Parameter parameter : method.getParameters()) {
			if ((parameter.getDirection().equals("in"))
					|| (parameter.getDirection().equals("inout"))) {
				if (parameter.getUpperValue() == '*' || parameter.getLowerValue() == '*') {
					out.write("ArrayList<" + parameter.getType() + "> ");
				} else {
					out.write(parameter.getType() + " ");
				}
				out.write(parameter.getName());
			}
			i++;
			if (method.getParameters().size() > (i + 1)) {
				if (!method.getParameters().get(i + 1).getDirection().equals("out")) {
					out.write(", ");
				}
			}
		}
		out.write(")\n"+ tabInd + "{");
	}


	// gera declaração dos parametros out dentro do método
	private void genCodeDeclPmtOut(BufferedWriter out) throws IOException {
		for (Parameter parameter : method.getParameters()) {
			if (parameter.getDirection().equals("out")) {
				out.write(tabInd);
				if (parameter.getUpperValue() == '*' || parameter.getLowerValue() == '*') {
					out.write("ArrayList<" + parameter.getType() + "> ");
				} else {
					out.write(parameter.getType() + " ");
				}
				out.write(parameter.getName());
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


}
