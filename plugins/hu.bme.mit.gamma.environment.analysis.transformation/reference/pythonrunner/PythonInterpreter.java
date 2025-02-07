package hu.bme.mit.gamma.analysis.transformation.pythonrunner;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PythonInterpreter extends ProcessRunner{
	private final IStartInfo startInfo;
	private final IOutputProvider outputProvider;
	private final Logger logger = Logger.getLogger("GammaLogger");

	/**
	* @exception NullPointerException Iff any of the arguments is null.
	*/
	public PythonInterpreter(IStartInfo startInfo, IOutputProvider outputProvider){
		if(startInfo==null) throw new NullPointerException();
		if(outputProvider==null) throw new NullPointerException();

		this.startInfo = startInfo;
		this.outputProvider = outputProvider;
	}

	/**
	* @exception NullPointerException Iff executionInfo is null.
	*/
	@SuppressWarnings("UnusedReturnValue")
	public ExecutionResult runCode(String code){
		if(code==null) throw new NullPointerException();

		ProcessBuilder builder = newPythonProcessBuilder();
		return runProcess(builder, code);
	}

	@Override
	protected void onProcessStartError(Throwable error) {
		logger.log(Level.WARNING, "Could not start Python interpreter. Error: "+error.toString());
	}

	@Override
	protected void onProcessInputWritingError(Process process, String input, IOException error) {
		logger.log(Level.WARNING, "Could send input to Pyhton interpreter. Input: "+input+" Error: "+error.toString());
	}

	private ProcessBuilder newPythonProcessBuilder(){
		ProcessBuilder builder = new ProcessBuilder(
           		startInfo.getPythonCommand());
		builder.directory(startInfo.getWorkingDirectory());
		
		return builder;
	}

	@Override
	protected void onStartedProcessPrintsLine(Process process, String line){
		if(process == null) throw new NullPointerException();
		if(line == null) throw new NullPointerException();

		outputProvider.println(line);
	}

	@Override
	protected void onProcessOutputReadingError(Process process, IOException error){
		logger.log(Level.WARNING, "Error at reading python process output. Error: "+error.toString());
	}
}