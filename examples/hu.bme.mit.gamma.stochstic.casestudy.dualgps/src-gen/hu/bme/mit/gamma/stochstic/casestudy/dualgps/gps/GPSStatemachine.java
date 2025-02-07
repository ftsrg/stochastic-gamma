package hu.bme.mit.gamma.stochstic.casestudy.dualgps.gps;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;

public class GPSStatemachine {
	
	enum Main {__Inactive__, operation_, failstop}
	private boolean Communication_failstop_Out;
	private boolean Faults_failure_In;
	private Main main;
	
	public GPSStatemachine() {
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
		this.Faults_failure_In = false;
		this.Communication_failstop_Out = false;
	}

	public void resetStateConfigurations() {
		this.main = Main.operation_;
	}

	public void raiseEntryEvents() {
	}

	public void handleAfterReset() {
		// Empty
	}
	//
	public void setCommunication_failstop_Out(boolean Communication_failstop_Out) {
		this.Communication_failstop_Out = Communication_failstop_Out;
	}
	
	public boolean getCommunication_failstop_Out() {
		return Communication_failstop_Out;
	}
	
	public void setFaults_failure_In(boolean Faults_failure_In) {
		this.Faults_failure_In = Faults_failure_In;
	}
	
	public boolean getFaults_failure_In() {
		return Faults_failure_In;
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
		if ((((this.main == Main.operation_)) && (this.Faults_failure_In))) {
			this.Communication_failstop_Out = true;
			this.main = Main.failstop;
		}
	}
	
	private void clearOutEvents() {
		Communication_failstop_Out = false;
	}
	
	private void clearInEvents() {
		Faults_failure_In = false;
	}
	
	@Override
	public String toString() {
		return
			"Communication_failstop_Out = " + Communication_failstop_Out + System.lineSeparator() +
			"Faults_failure_In = " + Faults_failure_In + System.lineSeparator() +
			"main = " + main
		;
	}
	
}
