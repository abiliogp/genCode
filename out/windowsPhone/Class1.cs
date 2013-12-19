using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace windowsPhone
{
	public class InnerClass2 : 
	{
		//Attributes
		public int Att1{ get; set; }

		//Constructor
		public InnerClass2( int att1 )
		{
			this.att1 = att1;
		}

	}

	public class Class1 : 
	{
		//Attributes
		private int att1;
		private int att2;

		//Get and Set
		public int Att1{ get; set; }
		public int Att2{ get; set; }

		//Constructor
		public Class1( int att1 , int att2 )
		{
			this.att1 = att1;
			this.att2 = att2;
		}

		//Methods
		public void Operation1()
		{
		}

	}
}