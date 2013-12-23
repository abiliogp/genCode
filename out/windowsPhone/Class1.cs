using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace windowsPhone
{
	public class InnerClass2
	{
		//Attributes
		public int Att1{ get; set; }

		//Constructor
		public InnerClass2( int att1 )
		{
			this.att1 = att1;
		}

	}

	public class Class1
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