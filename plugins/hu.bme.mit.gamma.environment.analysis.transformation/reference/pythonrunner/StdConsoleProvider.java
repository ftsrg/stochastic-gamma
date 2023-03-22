package hu.bme.mit.gamma.analysis.transformation.pythonrunner;

public class StdConsoleProvider implements IOutputProvider {
	@Override
	public void println(String str){
		if(str == null) throw new NullPointerException();

		System.out.println(str);
	}
}