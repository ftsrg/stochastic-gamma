package hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public class SummarizerStatemachine {
	
	enum Main {__Inactive__, main}
	private boolean outPort_disconn_Out;
	private boolean outPort_conn_Out;
	private boolean inPort_disconn_In;
	private boolean inPort_conn_In;
	private Main main;
	
	public SummarizerStatemachine() {
	}
	
	public void reset() {
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}

	public void handleBeforeReset() {
		this.main = Main.__Inactive__;
		clearOutEvents();
		clearInEvents();
	}
	public void resetVariables() {
		this.inPort_disconn_In = false;
		this.inPort_conn_In = false;
		this.outPort_disconn_Out = false;
		this.outPort_conn_Out = false;
	}

	public void resetStateConfigurations() {
		this.main = Main.main;
	}

	public void raiseEntryEvents() {
	}

	public void handleAfterReset() {
		// Empty
	}
	//
	public void setOutPort_disconn_Out(boolean outPort_disconn_Out) {
		this.outPort_disconn_Out = outPort_disconn_Out;
	}
	
	public boolean getOutPort_disconn_Out() {
		return outPort_disconn_Out;
	}
	
	public void setOutPort_conn_Out(boolean outPort_conn_Out) {
		this.outPort_conn_Out = outPort_conn_Out;
	}
	
	public boolean getOutPort_conn_Out() {
		return outPort_conn_Out;
	}
	
	public void setInPort_disconn_In(boolean inPort_disconn_In) {
		this.inPort_disconn_In = inPort_disconn_In;
	}
	
	public boolean getInPort_disconn_In() {
		return inPort_disconn_In;
	}
	
	public void setInPort_conn_In(boolean inPort_conn_In) {
		this.inPort_conn_In = inPort_conn_In;
	}
	
	public boolean getInPort_conn_In() {
		return inPort_conn_In;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public Main getMain() {
		return main;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if ((((this.main == Main.main)) && (this.inPort_conn_In))) {
			this.outPort_conn_Out = true;
			this.main = Main.main;
		}
	}
	
	private void clearOutEvents() {
		outPort_disconn_Out = false;
		outPort_conn_Out = false;
	}
	
	private void clearInEvents() {
		inPort_disconn_In = false;
		inPort_conn_In = false;
	}
	
	@Override
	public String toString() {
		return
			"outPort_disconn_Out = " + outPort_disconn_Out + System.lineSeparator() +
			"outPort_conn_Out = " + outPort_conn_Out + System.lineSeparator() +
			"inPort_disconn_In = " + inPort_disconn_In + System.lineSeparator() +
			"inPort_conn_In = " + inPort_conn_In + System.lineSeparator() +
			"main = " + main
		;
	}
	
}
