package hu.bme.mit.gamma.stochstic.casestudy.dualgps.voter_p;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;

public class VoterStatemachine {
	
	enum Main {__Inactive__, operational, failstop}
	private boolean Faults_failure_In;
	private boolean Sensor_failstop_In;
	private boolean Communication_failstop_Out;
	private Main main;
	private int sensor_num;
	private int sensorfailure;
	
	public VoterStatemachine(int sensor_num) {
		this.sensor_num = sensor_num;
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
		this.sensorfailure = 0;
		this.Faults_failure_In = false;
		this.Sensor_failstop_In = false;
		this.Communication_failstop_Out = false;
	}

	public void resetStateConfigurations() {
		this.main = Main.operational;
	}

	public void raiseEntryEvents() {
	}

	public void handleAfterReset() {
		// Empty
	}
	//
	public void setFaults_failure_In(boolean Faults_failure_In) {
		this.Faults_failure_In = Faults_failure_In;
	}
	
	public boolean getFaults_failure_In() {
		return Faults_failure_In;
	}
	
	public void setSensor_failstop_In(boolean Sensor_failstop_In) {
		this.Sensor_failstop_In = Sensor_failstop_In;
	}
	
	public boolean getSensor_failstop_In() {
		return Sensor_failstop_In;
	}
	
	public void setCommunication_failstop_Out(boolean Communication_failstop_Out) {
		this.Communication_failstop_Out = Communication_failstop_Out;
	}
	
	public boolean getCommunication_failstop_Out() {
		return Communication_failstop_Out;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public Main getMain() {
		return main;
	}
	
	public void setSensor_num(int sensor_num) {
		this.sensor_num = sensor_num;
	}
	
	public int getSensor_num() {
		return sensor_num;
	}
	
	public void setSensorfailure(int sensorfailure) {
		this.sensorfailure = sensorfailure;
	}
	
	public int getSensorfailure() {
		return sensorfailure;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if (((((this.main == Main.operational)) && (this.Faults_failure_In)) || (((this.main == Main.operational)) && (this.Sensor_failstop_In)))) {
			if ((((this.main == Main.operational)) && (this.Faults_failure_In))) {
				this.Communication_failstop_Out = true;
				this.main = Main.failstop;
			} else 
			if ((((this.main == Main.operational)) && (this.Sensor_failstop_In))) {
				this.main = Main.__Inactive__;
				if ((this.sensorfailure > (this.sensor_num - 1))) {
					this.Communication_failstop_Out = true;
					this.main = Main.failstop;
				}
				else {
					this.main = Main.operational;
				}
			}
		}
	}
	
	private void clearOutEvents() {
		Communication_failstop_Out = false;
	}
	
	private void clearInEvents() {
		Faults_failure_In = false;
		Sensor_failstop_In = false;
	}
	
	@Override
	public String toString() {
		return
			"Faults_failure_In = " + Faults_failure_In + System.lineSeparator() +
			"Sensor_failstop_In = " + Sensor_failstop_In + System.lineSeparator() +
			"Communication_failstop_Out = " + Communication_failstop_Out + System.lineSeparator() +
			"main = " + main + System.lineSeparator() +
			"sensor_num = " + sensor_num + System.lineSeparator() +
			"sensorfailure = " + sensorfailure
		;
	}
	
}
