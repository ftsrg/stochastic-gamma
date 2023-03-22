package hu.bme.mit.gamma.analysis.transformation.pythonrunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

import com.google.common.io.CharStreams;

abstract class ProcessRunner{
	/**
	* @exception NullPointerException Iff any of the arguments is null.
	*/
	public ExecutionResult runProcess(ProcessBuilder builder, String standardInputContent){
		if(builder == null) throw new NullPointerException();
		if(standardInputContent == null) throw new NullPointerException();

		modifyBuilderConfiguration(builder);
		return runProperyConfiguredProcessUnsafe(builder, standardInputContent);
	}

	/**
	* WARNING: the function assumes without checking that the none of its arguments is not null and
	* builder is properly configured to run (including standard output redirected).
	*/
	private ExecutionResult runProperyConfiguredProcessUnsafe(ProcessBuilder builder, String standardInputContent){
		Process process = null;
		try{
			process = builder.start();
			//InputStream sout = process.getInputStream();
			//Reader reader = new InputStreamReader(sout);
			//sout.transferTo(System.out);
			//int code=process.waitFor();
			System.out.println("process has been finished with code:---------------------");
			//System.out.println(code);
			//String text = CharStreams.toString(reader);
			//System.out.println(text);
		}
		catch(Throwable t){
			onProcessStartError(t);
			return ExecutionResult.ERROR;
		}
		return manageIOAndResult(process, standardInputContent);
	}

	/**
	* @exception NullPointerException Iff builder is null.
	*/
	private void modifyBuilderConfiguration(ProcessBuilder builder){
		if(builder == null) throw new NullPointerException();

		builder.redirectErrorStream(true);
	}

	/**
	* @exception NullPointerException Iff error is null.
	*/
	abstract protected void onProcessStartError(Throwable error);

	/**
	* @exception NullPointerException Iff any of the arguments is null.
	*/
	private ExecutionResult manageIOAndResult(Process process, String standardInputContent){
		if(process == null) throw new NullPointerException();
		if(standardInputContent == null) throw new NullPointerException();

		writeInput(process, standardInputContent);
		processOutputWhilePossible(process);
		return waitForExecutionResult(process);
	}

	/**
	 * @exception NullPointerException Iff any of the arguments is null.
	 */
	private void writeInput(Process process, String input){
		try {
			process.getOutputStream().write(input.getBytes());
			process.getOutputStream().flush();
			process.getOutputStream().close();
		} catch (IOException e) {
			onProcessInputWritingError(process, input, e);
		}
	}

	/**
	 *
	 * @exception NullPointerException Iff any of the arguments is null.
	 */
	protected abstract void onProcessInputWritingError(Process process, String input, IOException e);

	/**
	* @exception NullPointerException Iff process is null.
	*/
	private ExecutionResult waitForExecutionResult(Process process){
		if(process == null) throw new NullPointerException();
		System.out.println("waitForExecutionResult");
		int exitCode = 0;
		try {
			process.waitFor(1000,TimeUnit.MILLISECONDS);
			exitCode = 0;
		} catch (InterruptedException e) {
			return ExecutionResult.ERROR;
		}
		return toExecutionResult(exitCode);
	}

	/**
	* @exception NullPointerException Iff process is null.
	*/
	private void processOutputWhilePossible(Process process){
		if(process == null) throw new NullPointerException();

		BufferedReader outputReader = outputOf(process);
		processOutputWhilePossible(process, outputReader);
	}

	/**
	* @exception NullPointerException Iff any of the arguments is null.
	*/
	private void processOutputWhilePossible(Process process, BufferedReader outputReader){
		if(process == null) throw new NullPointerException();
		if(outputReader == null) throw new NullPointerException();

		try{
			String line;
			while(true) {
				line = outputReader.readLine();
				if (line == null) { break; }
				onStartedProcessPrintsLine(process, line);
			}
		}
		catch(IOException e){
			onProcessOutputReadingError(process, e);
		}
	}

	/**
	* @exception NullPointerException Iff any of the arguments is null.
	*/
	abstract protected void onStartedProcessPrintsLine(Process process, String line);

	/**
	* @exception NullPointerException Iff any of the arguments is null.
	*/
	abstract protected void onProcessOutputReadingError(Process process, IOException e);
	
	/**
	* @exception NullPointerException Iff process is null.
	*/
	private BufferedReader outputOf(Process process){
		if(process == null) throw new NullPointerException();

		return new BufferedReader(new InputStreamReader(process.getInputStream()));
	}

	private ExecutionResult toExecutionResult(int exitCode){
		return exitCode == 0 ? ExecutionResult.SUCCESS : ExecutionResult.ERROR;
	}
}