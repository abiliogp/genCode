package generator.Android;

import java.io.BufferedWriter;
import java.io.IOException;

import utilities.Tool;
import model.sequence.Interaction;
import generator.GeneratorStrategy;

public class InteractionAndroid implements GeneratorStrategy{

	private Interaction interaction;
	private LifelineAndroid generatorLifeline;
	
	public InteractionAndroid(Interaction interaction){
		this.interaction = interaction;
	}
	
	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		String tabInd = Tool.indentation(tab);
		out.write("\n" + tabInd + "/** Specified from Sequence Diagram " + interaction.getName() + " */");
		if(!(interaction.getLifelines().isEmpty()))
			generatorLifeline = new LifelineAndroid(interaction.getLifelines().get(0));
			generatorLifeline.codeGenerator(out, tab);
			//interaction.getLifelines().get(0).genCode(out, tab);
		//for(int i = 0 ; i < listLifeline.size() ; i++){
		//	this.listLifeline.get(i).genCode(out,tab);
		//}
	}

}
