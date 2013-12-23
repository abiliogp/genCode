package generator.Csharp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utilities.Parser;
import utilities.Tool;
import generator.GeneratorStrategy;

public class XamlCsharp implements GeneratorStrategy {

	private String className;
	private String modelName;
	
	private String tabInd;
	
	public XamlCsharp(String className, String modelName){
		this.className = className;
		this.modelName = modelName;
	}
	
	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		tabInd = Tool.indentation(1);
		
		File cls = new File("out/" + Parser.getModel().getName(), 
				className.concat(".xaml"));
		
		out = new BufferedWriter(new FileWriter(cls));
		
		out.write("<phone:PhoneApplicationPage");
		out.write("\n" + tabInd + "x:Class=\"" + modelName + "." + className + "\"");
		out.write("\n" + tabInd + "xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\"");
		out.write("\n" + tabInd + "xmlns:x=\"http://schemas.microsoft.com/winfx/2006/xaml\"");
		out.write("\n" + tabInd + "xmlns:phone=\"clr-namespace:Microsoft.Phone.Controls;assembly=Microsoft.Phone\"");
		out.write("\n" + tabInd + "xmlns:shell=\"clr-namespace:Microsoft.Phone.Shell;assembly=Microsoft.Phone\"");
		out.write("\n" + tabInd + "xmlns:d=\"http://schemas.microsoft.com/expression/blend/2008\"");
		out.write("\n" + tabInd + "xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\"");
		out.write("\n" + tabInd + "FontFamily=\"{StaticResource PhoneFontFamilyNormal}\"");
		out.write("\n" + tabInd + "FontSize=\"{StaticResource PhoneFontSizeNormal}\"");
		out.write("\n" + tabInd + "Foreground=\"{StaticResource PhoneForegroundBrush}\"");
		out.write("\n" + tabInd + "SupportedOrientations=\"Portrait\" Orientation=\"Portrait\"");
		out.write("\n" + tabInd + "mc:Ignorable=\"d\"");
		out.write("\n" + tabInd + "shell:SystemTray.IsVisible=\"True\">");
		
		out.write("\n\n" + tabInd + "<!--LayoutRoot is the root grid where all page content is placed-->");
		out.write("\n" + tabInd + "<Grid x:Name=\"LayoutRoot\" Background=\"Transparent\">");
		
		out.write("\n"+ tabInd + tabInd + "<Grid.RowDefinitions>");
		out.write("\n"+ tabInd + tabInd + tabInd + "<RowDefinition Height=\"Auto\"/>");
		out.write("\n"+ tabInd + tabInd + tabInd + "<RowDefinition Height=\"*\"/>");
		out.write("\n"+ tabInd + tabInd + "</Grid.RowDefinitions>");
		
		out.write("\n\n" + tabInd + tabInd + "<!--TitlePanel contains the name of the application and page title-->");
		out.write("\n" + tabInd + tabInd + "<StackPanel Grid.Row=\"0\" Margin=\"12,17,0,28\">");
		out.write("\n" + tabInd + tabInd + tabInd + 
				"<TextBlock Text=\"MY APPLICATION\" Style=\"{StaticResource PhoneTextNormalStyle}\"/>");
		out.write("\n" + tabInd + tabInd + tabInd + 
				"<TextBlock Text=\"page name\" Margin=\"9,-7,0,0\" Style=\"{StaticResource PhoneTextTitle1Style}\"/>");
		out.write("\n" + tabInd + tabInd + "</StackPanel>");
		
		out.write("\n\n" + tabInd + tabInd + "<!--ContentPanel - place additional content here-->");
		out.write("\n" + tabInd + tabInd + "<Grid x:Name=\"ContentPanel\" Grid.Row=\"1\" Margin=\"12,0,12,0\">");
		
		out.write("\n\n" + tabInd + tabInd + "</Grid>");
		out.write("\n" + tabInd + "</Grid>");
		
		out.write("\n\n" + "</phone:PhoneApplicationPage>");
		
		out.close();
	}

}
