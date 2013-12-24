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
	
	private String tab1, tab2, tab3;
	
	public XamlCsharp(String className, String modelName){
		this.className = className;
		this.modelName = modelName;
		this.tab1 = Tool.indentation(1);
		this.tab2 = Tool.indentation(2);
		this.tab3 = Tool.indentation(3);
	}
	
	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		
		File cls = new File("out/" + Parser.getModel().getName(), 
				className.concat(".xaml"));
		
		out = new BufferedWriter(new FileWriter(cls));
		
		out.write("<phone:PhoneApplicationPage");
		out.write("\n" + tab1 + "x:Class=\"" + modelName + "." + className + "\"");
		out.write("\n" + tab1 + "xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\"");
		out.write("\n" + tab1 + "xmlns:x=\"http://schemas.microsoft.com/winfx/2006/xaml\"");
		out.write("\n" + tab1 + "xmlns:phone=\"clr-namespace:Microsoft.Phone.Controls;assembly=Microsoft.Phone\"");
		out.write("\n" + tab1 + "xmlns:shell=\"clr-namespace:Microsoft.Phone.Shell;assembly=Microsoft.Phone\"");
		out.write("\n" + tab1 + "xmlns:d=\"http://schemas.microsoft.com/expression/blend/2008\"");
		out.write("\n" + tab1 + "xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\"");
		out.write("\n" + tab1 + "FontFamily=\"{StaticResource PhoneFontFamilyNormal}\"");
		out.write("\n" + tab1 + "FontSize=\"{StaticResource PhoneFontSizeNormal}\"");
		out.write("\n" + tab1 + "Foreground=\"{StaticResource PhoneForegroundBrush}\"");
		out.write("\n" + tab1 + "SupportedOrientations=\"Portrait\" Orientation=\"Portrait\"");
		out.write("\n" + tab1 + "mc:Ignorable=\"d\"");
		out.write("\n" + tab1 + "shell:SystemTray.IsVisible=\"True\">");
		
		out.write("\n\n" + tab1 + "<!--LayoutRoot is the root grid where all page content is placed-->");
		out.write("\n" + tab1 + "<Grid x:Name=\"LayoutRoot\" Background=\"Transparent\">");
		
		out.write("\n"+ tab2 + "<Grid.RowDefinitions>");
		out.write("\n"+ tab3 + "<RowDefinition Height=\"Auto\"/>");
		out.write("\n"+ tab3 + "<RowDefinition Height=\"*\"/>");
		out.write("\n"+ tab2 + "</Grid.RowDefinitions>");
		
		out.write("\n\n" + tab2 + "<!--TitlePanel contains the name of the application and page title-->");
		out.write("\n" + tab2 + "<StackPanel Grid.Row=\"0\" Margin=\"12,17,0,28\">");
		out.write("\n" + tab3 + 
				"<TextBlock Text=\"MY APPLICATION\" Style=\"{StaticResource PhoneTextNormalStyle}\"/>");
		out.write("\n" + tab3 + 
				"<TextBlock Text=\"page name\" Margin=\"9,-7,0,0\" Style=\"{StaticResource PhoneTextTitle1Style}\"/>");
		out.write("\n" + tab2 + "</StackPanel>");
		
		out.write("\n\n" + tab2 + "<!--ContentPanel - place additional content here-->");
		out.write("\n" + tab2 + "<Grid x:Name=\"ContentPanel\" Grid.Row=\"1\" Margin=\"12,0,12,0\">");
		
		out.write("\n\n" + tab2 + "</Grid>");
		out.write("\n" + tab1 + "</Grid>");
		
		out.write("\n\n" + "</phone:PhoneApplicationPage>");
		
		out.close();
	}
	
	
	public void generatorApp() throws IOException {
		File cls = new File("out/" + Parser.getModel().getName(), "App.xaml");
		
		BufferedWriter out = new BufferedWriter(new FileWriter(cls));
		
		out.write("<Application");
		out.write("\n" + tab1 + "x:Class=\"" + modelName + ".App\"");
		out.write("\n" + tab1 + "xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\"");
		out.write("\n" + tab1 + "xmlns:x=\"http://schemas.microsoft.com/winfx/2006/xaml\"");
		out.write("\n" + tab1 + "xmlns:phone=\"clr-namespace:Microsoft.Phone.Controls;assembly=Microsoft.Phone\"");
		out.write("\n" + tab1 + "xmlns:shell=\"clr-namespace:Microsoft.Phone.Shell;assembly=Microsoft.Phone\">");

		out.write("\n\n" + tab1 + "<!--Application Resources-->");
		out.write("\n" + tab1 + "<Application.Resources>");
		out.write("\n" + tab2 + "<local:LocalizedStrings xmlns:local=\"clr-namespace:PureProject\" x:Key=\"LocalizedStrings\"/>");
		out.write("\n" + tab1 + "</Application.Resources>");

		out.write("\n\n" + tab1 + "<Application.ApplicationLifetimeObjects>");
		out.write("\n" + tab2 + "<!--Required object that handles lifetime events for the application-->");
		out.write("\n" + tab2 + "<shell:PhoneApplicationService");
		out.write("\n" + tab3 + "Launching=\"Application_Launching\" Closing=\"Application_Closing\"");
		out.write("\n" + tab3 + "Activated=\"Application_Activated\" Deactivated=\"Application_Deactivated\"/>");
		out.write("\n" + tab1 + "</Application.ApplicationLifetimeObjects>");

		out.write("\n\n</Application>");
		out.close();
	}

}
