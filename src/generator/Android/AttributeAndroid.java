package generator.Android;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.structure.Attribute;
import generator.GeneratorStrategy;

public class AttributeAndroid implements GeneratorStrategy {

	private Attribute attribute;
	private String tabInd;
	

	public AttributeAndroid(Attribute attribute) {
		this.attribute = attribute;
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {		
		tabInd = Tool.indentation(tab);
		if (attribute.getLowerValue() == '*'
				|| attribute.getUpperValue() == '*') {
			if (attribute.getLowerValue() == '1') {
				out.write("\n" + tabInd + "/** You must have at least ONE "
						+ "Occurrence of Attribute: " + attribute.getName()
						+ " in this class*/");
			}
		}
		out.write(tabInd);

		// otimização p android get e set visibilidade public
		if (attribute.hasGetMethod() || attribute.hasSetMethod()) {
			out.write("public ");
		} else {
			out.write(!(attribute.getVisibility().equals("package")) ? "\n\t"
					+ attribute.getVisibility() + " " : "");
		}

		// otimizações para android static final
		if (attribute.getDefaultValue() != null || attribute.isStatic()) {
			out.write("static ");
			if (attribute.isPrimitiveType()) {
				out.write("final ");
			}
		}

		if (attribute.getLowerValue() == '*' || attribute.getUpperValue() == '*') {
			out.write("ArrayList<" + attribute.getType() + "> " + attribute.getName());
		} else {
			out.write(attribute.getType() + " " + attribute.getName());
		}

		if (attribute.getDefaultValue() != null) {
			if (attribute.getDefaultValueType().equals("uml:LiteralString")) {
				out.write(" = \"" + attribute.getDefaultValue() + "\"");
			} else {
				out.write(" = " + attribute.getDefaultValue());
			}
		}
		out.write(";");

	}

	public void genCodeAtributteImplements(BufferedWriter out)
			throws IOException {
		if (attribute.getLowerValue() == '*' || attribute.getUpperValue() == '*') {
			if (attribute.getLowerValue() == '1') {
				out.write("\n" + tabInd + "/** You must have at least ONE "
						+ "Occurrence of Attribute: " + attribute.getName()
						+ " in this class*/");
			}
			out.write("\n\tArrayList<" + attribute.getType() + "> " + attribute.getName() + ";");

		} else {
			out.write("\n" + tabInd);
			out.write(attribute.getType() + " " + attribute.getName() + ";");
		}
	}

	public void generatorGet(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		// otimização p android
		if (attribute.hasGetMethod()) {
			return;
		}
		if (attribute.isPrivate()) {
			out.write("\n" + tabInd + "public "); 
			
			if (attribute.getLowerValue() == '*' || attribute.getUpperValue() == '*') {
				out.write("ArrayList<" + attribute.getType());
			} else{
				out.write(attribute.getType());
			}
			
			out.write(" get" + attribute.getName().substring(0, 1).toUpperCase()
					.concat(attribute.getName().substring(1)) + "(){");
			out.write("\n" + tabInd + "\treturn this." + attribute.getName() + ";\n\t}\n");
		}
	}

	public void genCodeSet(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(tab);
		// otimização p android
		if (attribute.hasSetMethod()) {
			return;
		}
		if (attribute.isPrivate()) {
			out.write("\n" + tabInd + "public void set"
					+ attribute.getName().substring(0, 1).toUpperCase()
							.concat(attribute.getName().substring(1)) + "(");
			
			if (attribute.getLowerValue() == '*' || attribute.getUpperValue() == '*') {
				out.write("ArrayList<" + attribute.getType() + "> " + attribute.getName());
			} else{
				out.write(attribute.getType() + " " + attribute.getName());
			}
			
			out.write("){");
			
			out.write("\n" + tabInd + "\t this." + attribute.getName() + " = "
					+ attribute.getName() + ";\n\t}\n");
		}
	}

	public void generatorConstructor(BufferedWriter out) throws IOException {
		if (attribute.getLowerValue() == '*'
				|| attribute.getUpperValue() == '*') {
			out.write("\n\t\tthis." + attribute.getName() + " = new ArrayList<"
					+ attribute.getType() + ">();");
		} else {
			out.write("\n\t\tthis." + attribute.getName() + " = "
					+ attribute.getName() + ";");
		}
	}

	public void generatorConstructorSignature(BufferedWriter out)
			throws IOException {
		if (attribute.getLowerValue() == '*' || attribute.getUpperValue() == '*') {
			out.write(" ArrayList<" + attribute.getType() + "> " + attribute.getName());
		} else{
			out.write(" " + attribute.getType() + " " + attribute.getName());
		}
		
	}

	public void generatorAndroidImports(BufferedWriter out) throws IOException {
		if (Android.os.contains(this.attribute.getName())) {
			out.write("import android.os." + attribute.getName() + ";\n");
		} else if (Android.Widget.contains(attribute.getName())) {
			out.write("import android.widget." + attribute.getName() + ";\n");
		} else if (Android.View.contains(attribute.getName())) {
			out.write("import android.view." + attribute.getName() + ";\n");
		} else if (Android.view.contains(attribute.getName())) {
			out.write("import android.view.View." + attribute.getName() + ";\n");
		} else if (Android.Content.contains(attribute.getName())) {
			out.write("import android.content." + attribute.getName() + ";\n");
		} else if (Android.res.contains(attribute.getName())) {
			out.write("import android.content.res." + attribute.getName() + ";\n");
		} else if (Android.adntroidUtil.contains(attribute.getName())) {
			out.write("import android.util." + attribute.getName() + ";\n");
		} else if (Android.javaUtil.contains(attribute.getName())) {
			out.write("import java.util." + attribute.getName() + ";\n");
		}
	}

}
