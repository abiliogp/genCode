package generator.Csharp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utilities.Parser;
import utilities.Tool;
import model.structure.Attribute;
import model.structure.Classe;
import model.structure.Method;
import model.structure.RealizationInterface;
import generator.GeneratorStrategy;
import generator.Android.Android;
import generator.Android.AttributeAndroid;
import generator.Android.MethodAndroid;
import generator.Android.RealizationAndroid;

public class ClasseCsharp implements GeneratorStrategy {

	private Classe classe;
	private String modelName;

	private AttributeCsharp generatorAttribute;
	private MethodCsharp generatorMethod;
	private XamlCsharp generatorXaml;

	private String tab1, tab2, tab3;
	
	public ClasseCsharp(Classe classe, String modelName) {
		this.classe = classe;
		this.modelName = modelName;
		this.tab1 = Tool.indentation(1);
		this.tab2 = Tool.indentation(2);
		this.tab3 = Tool.indentation(3);
	}

	@Override
	public void codeGenerator(BufferedWriter out, int tab) throws IOException {
		boolean isPartial;
		try {
			if (WindowsPhone.General.valueOf(classe.getName()) != null) {
				return;
			}
		} catch (java.lang.IllegalArgumentException ex) {
			isPartial = classe.getName().contains("&lt;&lt;Partial>>");
			if(isPartial){
				classe.setName(classe.getName().substring(0, classe.getName().indexOf("&lt;") - 1));
				generatorXaml = new XamlCsharp(classe.getName(), modelName);
				generatorXaml.codeGenerator(null, 0);
			}
			
			File cls = new File("out/" + Parser.getModel().getName(), 
					(isPartial == true ? classe.getName().concat(".xaml.cs") : 
						classe.getName().concat(".cs")));
			out = new BufferedWriter(new FileWriter(cls));
	
			//Imports
			generatorUsing(out);
			
			out.write("\nnamespace " + modelName + "\n{");
			//Class
			for(Classe inner : classe.getInnerClasses()){
				generatorClass(inner, out, tab, false);
			}
			
			generatorClass(classe, out, tab, isPartial);

			out.write("}");
			out.close();
		}
	}

	
	private void generatorClass(Classe classe, BufferedWriter out, int tab, boolean isPartial) throws IOException {
		String tabInd = Tool.indentation(tab);
		
		// Name Class and General
		out.write("\n" + tabInd + classe.getVisibility()
				+ (classe.isAbstract() == true ? " abstract " : " ")
				+ (isPartial == true ? "partial " : "") 
				+ "class " + classe.getName()
				+ (classe.getGeneral().isEmpty() == false ? " : " + classe.getGeneral() : ""));

		out.write("\n" + tabInd + "{");

		// Atributos
		if (classe.getAttributes().size() > 0) {
			out.write("\n" + tabInd + "\t//Attributes");
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeCsharp(atr);
				generatorAttribute.codeGenerator(out, tab + 1);
			}
			out.write("\n");
		}

		// Get
		if (classe.needGetSet) {
			out.write("\n" + tabInd + "\t//Get and Set");
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeCsharp(atr);
				generatorAttribute.generatorGetSet(out, tab + 1);
			}
			out.write("\n");
		}

		// Construtor
		if (!(classe.isAbstract())) {
			out.write("\n" + tabInd + "\t//Constructor");
			out.write("\n" + tabInd + "\tpublic " + classe.getName() + "(");
			int i = 0;
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeCsharp(atr);
				generatorAttribute.generatorConstructorSignature(out);
				if (i++ < classe.getAttributes().size() - 1) {
					out.write(",");
				}
			}
			out.write(")\n" +tabInd + "\t{");
			
			if(classe.getGeneral().equals("PhoneApplicationPage")){
				out.write("\n" + tabInd + "\t\tInitializeComponent();");
			}
			
			for (Attribute atr : classe.getAttributes()) {
				generatorAttribute = new AttributeCsharp(atr);
				generatorAttribute.generatorConstructor(out);
			}
			out.write("\n" + tabInd + "\t}\n");
		}

		
		// Metodo 
		if (classe.getMethods().size() > 0) {
			out.write("\n" + tabInd + "\t//Methods");
			for (Method method : classe.getMethods()) {
				generatorMethod = new MethodCsharp(method);
				generatorMethod.codeGenerator(out, tab + 1);
			}
		}
		
		out.write("\n" + tabInd + "}\n");

	}
	
	
	private void generatorUsing(BufferedWriter out) throws IOException{
		out.write("using System;\n");
		out.write("using System.Collections.Generic;\n");
		out.write("using System.Linq;\n");
		if(classe.getGeneral().equals("PhoneApplicationPage")){
			out.write("using System.Net;\n");
			out.write("using System.Windows;\n");
			out.write("using System.Windows.Controls;\n");
			out.write("using System.Windows.Navigation;\n");
			out.write("using Microsoft.Phone.Controls;\n");
			out.write("using Microsoft.Phone.Shell;\n");
			if(classe.getName().equals("MainPage")){
				out.write("using "+ modelName +".Resources;\n");
			}
		} else{
			out.write("using System.Text;\n");
			out.write("using System.Threading.Tasks;\n");
		}
		//gerar import dos atributos
	}
	
	
	public void generatorAppClass(BufferedWriter out) throws IOException{
		generatorXaml = new XamlCsharp("App", modelName);
		generatorXaml.generatorApp();
		
		String tab4 = Tool.indentation(4);
		File cls = new File("out/" + Parser.getModel().getName(), "App.xaml.cs");
		
		out = new BufferedWriter(new FileWriter(cls));
		
		out.write("using System;\n");
		out.write("using System.Diagnostics;\n");
		out.write("using System.Resources;\n");
		out.write("using System.Windows;\n");
		out.write("using System.Windows.Markup;\n");
		out.write("using System.Windows.Navigation;\n");
		out.write("using Microsoft.Phone.Controls;\n");
		out.write("using Microsoft.Phone.Shell;\n");
		out.write("using PureProject.Resources;\n");
		
		out.write("\nnamespace " + modelName + "\n{\n");
		out.write(tab1 + "public partial class App : Application\n" + tab1 + "{\n");
		
		out.write(tab2 + "public static PhoneApplicationFrame RootFrame { get; private set; }\n\n");
		
		out.write(tab2 + "public App()\n" + tab2 + "{\n");
		
		out.write(tab3 + "UnhandledException += Application_UnhandledException;\n");
		out.write(tab3 + "InitializeComponent();\n");
		out.write(tab3 + "InitializePhoneApplication();\n");
		out.write(tab3 + "InitializeLanguage();\n");

		out.write(tab3 + "if (Debugger.IsAttached)\n" + tab3 + "{\n");
		out.write(tab4 + "Application.Current.Host.Settings.EnableFrameRateCounter = true;\n");
		out.write(tab4 + "PhoneApplicationService.Current.UserIdleDetectionMode = IdleDetectionMode.Disabled;\n");
		out.write(tab3 + "}\n" + tab2 + "}\n\n");
        
		out.write(tab2 + "private void Application_Launching(object sender, LaunchingEventArgs e)\n" +
				tab2 + "{\n" + tab2 + "}\n\n" );
		
		out.write(tab2 + "private void Application_Activated(object sender, ActivatedEventArgs e)\n" +
				tab2 + "{\n" + tab2 + "}\n\n" );
		
		out.write(tab2 + "private void Application_Deactivated(object sender, DeactivatedEventArgs e)\n" +
				tab2 + "{\n" + tab2 + "}\n\n" );
		
		out.write(tab2 + "private void Application_Closing(object sender, ClosingEventArgs e)\n" +
				tab2 + "{\n" + tab2 + "}\n\n" );
		
		out.write(tab2 + "private void RootFrame_NavigationFailed(object sender, NavigationFailedEventArgs e)\n" +
				tab2 + "{\n" );
		out.write(tab3 + "if (Debugger.IsAttached)\n" + tab3 + "{\n");
		out.write(tab4 + "Debugger.Break();\n");
		out.write(tab3 + "}\n" + tab2 + "}\n\n");
		
		
		out.write(tab2 + "private void Application_UnhandledException(object sender, ApplicationUnhandledExceptionEventArgs e)\n" +
				tab2 + "{\n" );
		out.write(tab3 + "if (Debugger.IsAttached)\n" + tab3 + "{\n");
		out.write(tab4 + "Debugger.Break();\n");
		out.write(tab3 + "}\n" + tab2 + "}\n\n");
		
		
		out.write(tab2 + "#region Phone application initialization\n\n");
		
		out.write(tab2 + "private bool phoneApplicationInitialized = false;\n\n");
		
		out.write(tab2 + "private void InitializePhoneApplication()\n" + tab2 + "{\n");
		out.write(tab3 + "if (phoneApplicationInitialized)\n");
		out.write(tab4 + "return;\n");
		
		out.write(tab3 + "RootFrame = new PhoneApplicationFrame();\n");
		out.write(tab3 + "RootFrame.Navigated += CompleteInitializePhoneApplication;\n");
		out.write(tab3 + "RootFrame.NavigationFailed += RootFrame_NavigationFailed;\n");
		out.write(tab3 + "RootFrame.Navigated += CheckForResetNavigation;\n");
		out.write(tab3 + "phoneApplicationInitialized = true;\n");
		out.write(tab2 + "}\n\n");
		
		
		out.write(tab2 + "private void CompleteInitializePhoneApplication(object sender, NavigationEventArgs e)"
				+ "\n" + tab2 + "{\n");
		out.write(tab3 + "if (RootVisual != RootFrame)\n");
		out.write(tab4 + "RootVisual = RootFrame;\n");
		out.write(tab3 + "RootFrame.Navigated -= CompleteInitializePhoneApplication;\n");
		out.write(tab2 + "}\n\n");
		
		
		out.write(tab2 + "private void CheckForResetNavigation(object sender, NavigationEventArgs e)"
				+ "\n" + tab2 + "{\n");
		out.write(tab3 + "if (e.NavigationMode == NavigationMode.Reset)\n");
		out.write(tab4 + "RootFrame.Navigated += ClearBackStackAfterReset;\n");
		out.write(tab2 + "}\n\n");

		
		out.write(tab2 + "private void ClearBackStackAfterReset(object sender, NavigationEventArgs e)"
				+ "\n" + tab2 + "{\n");
		out.write(tab3 + "RootFrame.Navigated -= ClearBackStackAfterReset;\n");
		out.write(tab3 + "if (e.NavigationMode != NavigationMode.New && e.NavigationMode != NavigationMode.Refresh)\n");
		out.write(tab4 + "return;\n");
		out.write(tab3 + "while (RootFrame.RemoveBackEntry() != null)\n" + tab3 +"{\n" + tab4 + ";\n" + tab3 + "}\n");
		
		out.write(tab2 + "}\n\n");
		
		out.write(tab2 + "#endregion\n\n");
		
		
		out.write(tab2 + "private void InitializeLanguage()" + "\n" + tab2 + "{\n");
		out.write(tab3 + "try\n" + tab3 + "{\n");
		out.write(tab4 + "RootFrame.Language = XmlLanguage.GetLanguage(AppResources.ResourceLanguage);\n");
		out.write(tab4 + "FlowDirection flow = (FlowDirection)Enum.Parse(typeof(FlowDirection), AppResources.ResourceFlowDirection);\n");
		out.write(tab4 + "RootFrame.FlowDirection = flow;\n" + tab3 + "}\n");
		
		out.write(tab3 + "catch\n" + tab3 + "{\n");
		out.write(tab4 + "if (Debugger.IsAttached)\n" + tab4 + "{\n");
		out.write(tab4 + tab1 + "Debugger.Break();\n");
		out.write(tab4 + "}\n");
		out.write(tab4 + "throw;\n");
		out.write(tab3 + "}\n");
		out.write(tab2 + "}\n");
		out.write(tab1 + "}\n");
		out.write("}");
		
		out.close();
	}
	
	
}
