package generator.Csharp;

public final class WindowsPhone {

	private WindowsPhone() {
		throw new AssertionError();
	}

	/*Windows Phone General Classes*/
	public enum General {
		EventArgs, PhoneApplicationPage;
	}
	
	public enum Method {
		_MouseLeftButton, _MouseRightButton, _MouseUpButton, _MouseLeftButtonDown, 
		_Click, _ManipulationStarted, _ManipulationDelta, _ManipulationCompleted;
	}
}
