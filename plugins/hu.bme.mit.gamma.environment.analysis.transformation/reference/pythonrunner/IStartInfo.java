package hu.bme.mit.gamma.analysis.transformation.pythonrunner;

import java.io.File;

public interface IStartInfo{
	String getPythonCommand();
	File getWorkingDirectory();
}