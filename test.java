import java.util.*;

public class test{
	public native void hello();
	
	static{
		System.loadLibrary("Hello");
	}
	
	public static void main(String[] args){
		new test().hello();
	}
}