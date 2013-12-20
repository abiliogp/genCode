package model.structure;

import java.io.BufferedReader;
import java.io.IOException;

import model.Model;
import utilities.Parser;
import utilities.Tool;

public class Parameter extends DataStructure {

	/*Attributes*/
	private String direction;

	/*Constructor*/
	public Parameter(String name) {
		super(name);
		this.direction = "in";
	}

	public Parameter(String name, String type) {
		super(name);
		this.type = type;
		this.direction = "in";
	}

	/*Set*/
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/*Get*/
	public String getDirection() {
		return this.direction;
	}

	public void printProp() {
		System.out
				.printf("\t\tParâmetro: %s Tipo: %s Direção: %s Visibilidade: %s Upper: %s Lower: %s \n",
						this.name, this.type, this.direction, this.visibility,
						this.upperValue, this.lowerValue);
	}



	public void parser(BufferedReader bf, String line) throws IOException {
		String value = null;
		if (line.contains("visibility=")) {
			visibility = Tool.manipulate(line, "visibility=");
		}
		if (line.contains("type=")) {
			value = Tool.manipulate(line, "type=");
			type = Model.getTrieID(value);
		}

		if (line.contains("direction=")) {
			direction = Tool.manipulate(line, "direction=");
		}

		if (line.contains("/>")) {
			line = "</ownedParameter>";
		} else {
			for (; !(line.contains("</ownedParameter>")); line = bf.readLine()) {
				if (line.contains("uml:Stereotype")) {
					value = Tool.manipulate(line, "pathmap:", "#", "\"");
					type = Model.getTrieID(value);
				}
				if (line.contains("uml:PrimitiveType")) {
					type = parserType(value, line);
				}
				if (line.contains("upperValue")) {
					value = Tool.manipulate(line, "value");
					upperValue = value.charAt(0);
					if (value.substring(0, 1).equals("*")) {
						Parser.getModel().getLastClasse().setNeedImport(true);
					}
				}
				if (line.contains("lowerValue")) {
					value = Tool.manipulate(line, "value");
					if (!value.isEmpty()) {
						lowerValue = value.charAt(0);
						if (value.substring(0, 1).equals("*")) {
							Parser.getModel().getLastClasse()
									.setNeedImport(true);
						}
					}
				}
			}
		}
	}

}
