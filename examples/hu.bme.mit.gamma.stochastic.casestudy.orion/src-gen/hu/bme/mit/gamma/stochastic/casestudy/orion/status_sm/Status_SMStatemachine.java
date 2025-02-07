package hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public class Status_SMStatemachine {
	
	enum Main {__Inactive__, no_conn, master_conn, slave_conn, system_conn}
	private boolean systemStatus_conn_Out;
	private boolean systemStatus_disconn_Out;
	private boolean masterStatus_disconn_In;
	private boolean masterStatus_conn_In;
	private boolean slaveStatus_disconn_In;
	private boolean slaveStatus_conn_In;
	private Main main;
	
	public Status_SMStatemachine() {
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
		this.masterStatus_disconn_In = false;
		this.masterStatus_conn_In = false;
		this.slaveStatus_disconn_In = false;
		this.slaveStatus_conn_In = false;
		this.systemStatus_conn_Out = false;
		this.systemStatus_disconn_Out = false;
	}

	public void resetStateConfigurations() {
		this.main = Main.no_conn;
	}

	public void raiseEntryEvents() {
	}

	public void handleAfterReset() {
		// Empty
	}
	//
	public void setSystemStatus_conn_Out(boolean systemStatus_conn_Out) {
		this.systemStatus_conn_Out = systemStatus_conn_Out;
	}
	
	public boolean getSystemStatus_conn_Out() {
		return systemStatus_conn_Out;
	}
	
	public void setSystemStatus_disconn_Out(boolean systemStatus_disconn_Out) {
		this.systemStatus_disconn_Out = systemStatus_disconn_Out;
	}
	
	public boolean getSystemStatus_disconn_Out() {
		return systemStatus_disconn_Out;
	}
	
	public void setMasterStatus_disconn_In(boolean masterStatus_disconn_In) {
		this.masterStatus_disconn_In = masterStatus_disconn_In;
	}
	
	public boolean getMasterStatus_disconn_In() {
		return masterStatus_disconn_In;
	}
	
	public void setMasterStatus_conn_In(boolean masterStatus_conn_In) {
		this.masterStatus_conn_In = masterStatus_conn_In;
	}
	
	public boolean getMasterStatus_conn_In() {
		return masterStatus_conn_In;
	}
	
	public void setSlaveStatus_disconn_In(boolean slaveStatus_disconn_In) {
		this.slaveStatus_disconn_In = slaveStatus_disconn_In;
	}
	
	public boolean getSlaveStatus_disconn_In() {
		return slaveStatus_disconn_In;
	}
	
	public void setSlaveStatus_conn_In(boolean slaveStatus_conn_In) {
		this.slaveStatus_conn_In = slaveStatus_conn_In;
	}
	
	public boolean getSlaveStatus_conn_In() {
		return slaveStatus_conn_In;
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
		if (((((this.main == Main.no_conn)) && (this.masterStatus_conn_In) && (this.slaveStatus_conn_In)) || (((this.main == Main.no_conn)) && (this.masterStatus_conn_In)) || (((this.main == Main.no_conn)) && (this.slaveStatus_conn_In)) || (((this.main == Main.master_conn)) && (this.masterStatus_disconn_In)) || (((this.main == Main.master_conn)) && (this.slaveStatus_conn_In)) || (((this.main == Main.slave_conn)) && (this.slaveStatus_disconn_In)) || (((this.main == Main.slave_conn)) && (this.masterStatus_conn_In)))) {
			if ((((this.main == Main.no_conn)) && (this.masterStatus_conn_In) && (this.slaveStatus_conn_In))) {
				this.systemStatus_conn_Out = true;
				this.main = Main.system_conn;
			} else 
			if ((((this.main == Main.no_conn)) && (this.masterStatus_conn_In))) {
				this.main = Main.master_conn;
			} else 
			if ((((this.main == Main.no_conn)) && (this.slaveStatus_conn_In))) {
				this.main = Main.slave_conn;
			} else 
			if ((((this.main == Main.master_conn)) && (this.masterStatus_disconn_In))) {
				this.main = Main.no_conn;
			} else 
			if ((((this.main == Main.master_conn)) && (this.slaveStatus_conn_In))) {
				this.systemStatus_conn_Out = true;
				this.main = Main.system_conn;
			} else 
			if ((((this.main == Main.slave_conn)) && (this.slaveStatus_disconn_In))) {
				this.main = Main.no_conn;
			} else 
			if ((((this.main == Main.slave_conn)) && (this.masterStatus_conn_In))) {
				this.systemStatus_conn_Out = true;
				this.main = Main.system_conn;
			}
		}
	}
	
	private void clearOutEvents() {
		systemStatus_conn_Out = false;
		systemStatus_disconn_Out = false;
	}
	
	private void clearInEvents() {
		masterStatus_disconn_In = false;
		masterStatus_conn_In = false;
		slaveStatus_disconn_In = false;
		slaveStatus_conn_In = false;
	}
	
	@Override
	public String toString() {
		return
			"systemStatus_conn_Out = " + systemStatus_conn_Out + System.lineSeparator() +
			"systemStatus_disconn_Out = " + systemStatus_disconn_Out + System.lineSeparator() +
			"masterStatus_disconn_In = " + masterStatus_disconn_In + System.lineSeparator() +
			"masterStatus_conn_In = " + masterStatus_conn_In + System.lineSeparator() +
			"slaveStatus_disconn_In = " + slaveStatus_disconn_In + System.lineSeparator() +
			"slaveStatus_conn_In = " + slaveStatus_conn_In + System.lineSeparator() +
			"main = " + main
		;
	}
	
}
