package hu.bme.mit.gamma.analysis.transformation.pythonrunner;

public interface IOutputProvider{
	/**
	 * @exception NullPointerException Iff str is null.
	 */
	void println(String str);
}