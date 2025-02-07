package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm2;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public class Orion_Slave_SMStatemachine {
	
	enum Main_region_1 {__Inactive__, Closed, Connecting, Connected}
	enum RecieveRegion {__Inactive__, KeepAliveRecieveTimeout}
	enum SendRegion {__Inactive__, KeepAliveSendTimeout}
	private boolean Send_StateMachine_Port_OrionDisconnCause_Out;
	private boolean Send_StateMachine_Port_OrionKeepAlive_Out;
	private boolean Status_disconn_Out;
	private boolean Send_StateMachine_Port_OrionConnReq_Out;
	private boolean Send_StateMachine_Port_OrionConnConf_Out;
	private boolean Send_StateMachine_Port_OrionConnResp_Out;
	private boolean Send_StateMachine_Port_OrionDisconn_Out;
	private boolean Status_conn_Out;
	private boolean Send_StateMachine_Port_OrionAppData_Out;
	private boolean StateMachine_Port_OrionKeepAlive_In;
	private boolean StateMachine_Port_OrionConnReq_In;
	private boolean StateMachine_Port_OrionDisconn_In;
	private boolean StateMachine_Port_OrionConnResp_In;
	private boolean StateMachine_Port_OrionConnConf_In;
	private boolean StateMachine_Port_OrionAppData_In;
	private boolean StateMachine_Port_OrionDisconnCause_In;
	private Main_region_1 main_region_1;
	private RecieveRegion recieveRegion;
	private SendRegion sendRegion;
	
	public Orion_Slave_SMStatemachine() {
	}
	
	public void reset() {
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}

	public void handleBeforeReset() {
		this.main_region_1 = Main_region_1.__Inactive__;
		this.recieveRegion = RecieveRegion.__Inactive__;
		this.sendRegion = SendRegion.__Inactive__;
		clearOutEvents();
		clearInEvents();
	}
	public void resetVariables() {
		this.recieveRegion = RecieveRegion.__Inactive__;
		this.sendRegion = SendRegion.__Inactive__;
		this.StateMachine_Port_OrionKeepAlive_In = false;
		this.StateMachine_Port_OrionConnReq_In = false;
		this.StateMachine_Port_OrionDisconn_In = false;
		this.StateMachine_Port_OrionConnResp_In = false;
		this.StateMachine_Port_OrionConnConf_In = false;
		this.StateMachine_Port_OrionAppData_In = false;
		this.StateMachine_Port_OrionDisconnCause_In = false;
		this.Send_StateMachine_Port_OrionDisconnCause_Out = false;
		this.Send_StateMachine_Port_OrionKeepAlive_Out = false;
		this.Status_disconn_Out = false;
		this.Send_StateMachine_Port_OrionConnReq_Out = false;
		this.Send_StateMachine_Port_OrionConnConf_Out = false;
		this.Send_StateMachine_Port_OrionConnResp_Out = false;
		this.Send_StateMachine_Port_OrionDisconn_Out = false;
		this.Status_conn_Out = false;
		this.Send_StateMachine_Port_OrionAppData_Out = false;
	}

	public void resetStateConfigurations() {
		this.main_region_1 = Main_region_1.Closed;
	}

	public void raiseEntryEvents() {
	}

	public void handleAfterReset() {
		// Empty
	}
	//
	public void setSend_StateMachine_Port_OrionDisconnCause_Out(boolean Send_StateMachine_Port_OrionDisconnCause_Out) {
		this.Send_StateMachine_Port_OrionDisconnCause_Out = Send_StateMachine_Port_OrionDisconnCause_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionDisconnCause_Out() {
		return Send_StateMachine_Port_OrionDisconnCause_Out;
	}
	
	public void setSend_StateMachine_Port_OrionKeepAlive_Out(boolean Send_StateMachine_Port_OrionKeepAlive_Out) {
		this.Send_StateMachine_Port_OrionKeepAlive_Out = Send_StateMachine_Port_OrionKeepAlive_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionKeepAlive_Out() {
		return Send_StateMachine_Port_OrionKeepAlive_Out;
	}
	
	public void setStatus_disconn_Out(boolean Status_disconn_Out) {
		this.Status_disconn_Out = Status_disconn_Out;
	}
	
	public boolean getStatus_disconn_Out() {
		return Status_disconn_Out;
	}
	
	public void setSend_StateMachine_Port_OrionConnReq_Out(boolean Send_StateMachine_Port_OrionConnReq_Out) {
		this.Send_StateMachine_Port_OrionConnReq_Out = Send_StateMachine_Port_OrionConnReq_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionConnReq_Out() {
		return Send_StateMachine_Port_OrionConnReq_Out;
	}
	
	public void setSend_StateMachine_Port_OrionConnConf_Out(boolean Send_StateMachine_Port_OrionConnConf_Out) {
		this.Send_StateMachine_Port_OrionConnConf_Out = Send_StateMachine_Port_OrionConnConf_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionConnConf_Out() {
		return Send_StateMachine_Port_OrionConnConf_Out;
	}
	
	public void setSend_StateMachine_Port_OrionConnResp_Out(boolean Send_StateMachine_Port_OrionConnResp_Out) {
		this.Send_StateMachine_Port_OrionConnResp_Out = Send_StateMachine_Port_OrionConnResp_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionConnResp_Out() {
		return Send_StateMachine_Port_OrionConnResp_Out;
	}
	
	public void setSend_StateMachine_Port_OrionDisconn_Out(boolean Send_StateMachine_Port_OrionDisconn_Out) {
		this.Send_StateMachine_Port_OrionDisconn_Out = Send_StateMachine_Port_OrionDisconn_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionDisconn_Out() {
		return Send_StateMachine_Port_OrionDisconn_Out;
	}
	
	public void setStatus_conn_Out(boolean Status_conn_Out) {
		this.Status_conn_Out = Status_conn_Out;
	}
	
	public boolean getStatus_conn_Out() {
		return Status_conn_Out;
	}
	
	public void setSend_StateMachine_Port_OrionAppData_Out(boolean Send_StateMachine_Port_OrionAppData_Out) {
		this.Send_StateMachine_Port_OrionAppData_Out = Send_StateMachine_Port_OrionAppData_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionAppData_Out() {
		return Send_StateMachine_Port_OrionAppData_Out;
	}
	
	public void setStateMachine_Port_OrionKeepAlive_In(boolean StateMachine_Port_OrionKeepAlive_In) {
		this.StateMachine_Port_OrionKeepAlive_In = StateMachine_Port_OrionKeepAlive_In;
	}
	
	public boolean getStateMachine_Port_OrionKeepAlive_In() {
		return StateMachine_Port_OrionKeepAlive_In;
	}
	
	public void setStateMachine_Port_OrionConnReq_In(boolean StateMachine_Port_OrionConnReq_In) {
		this.StateMachine_Port_OrionConnReq_In = StateMachine_Port_OrionConnReq_In;
	}
	
	public boolean getStateMachine_Port_OrionConnReq_In() {
		return StateMachine_Port_OrionConnReq_In;
	}
	
	public void setStateMachine_Port_OrionDisconn_In(boolean StateMachine_Port_OrionDisconn_In) {
		this.StateMachine_Port_OrionDisconn_In = StateMachine_Port_OrionDisconn_In;
	}
	
	public boolean getStateMachine_Port_OrionDisconn_In() {
		return StateMachine_Port_OrionDisconn_In;
	}
	
	public void setStateMachine_Port_OrionConnResp_In(boolean StateMachine_Port_OrionConnResp_In) {
		this.StateMachine_Port_OrionConnResp_In = StateMachine_Port_OrionConnResp_In;
	}
	
	public boolean getStateMachine_Port_OrionConnResp_In() {
		return StateMachine_Port_OrionConnResp_In;
	}
	
	public void setStateMachine_Port_OrionConnConf_In(boolean StateMachine_Port_OrionConnConf_In) {
		this.StateMachine_Port_OrionConnConf_In = StateMachine_Port_OrionConnConf_In;
	}
	
	public boolean getStateMachine_Port_OrionConnConf_In() {
		return StateMachine_Port_OrionConnConf_In;
	}
	
	public void setStateMachine_Port_OrionAppData_In(boolean StateMachine_Port_OrionAppData_In) {
		this.StateMachine_Port_OrionAppData_In = StateMachine_Port_OrionAppData_In;
	}
	
	public boolean getStateMachine_Port_OrionAppData_In() {
		return StateMachine_Port_OrionAppData_In;
	}
	
	public void setStateMachine_Port_OrionDisconnCause_In(boolean StateMachine_Port_OrionDisconnCause_In) {
		this.StateMachine_Port_OrionDisconnCause_In = StateMachine_Port_OrionDisconnCause_In;
	}
	
	public boolean getStateMachine_Port_OrionDisconnCause_In() {
		return StateMachine_Port_OrionDisconnCause_In;
	}
	
	public void setMain_region_1(Main_region_1 main_region_1) {
		this.main_region_1 = main_region_1;
	}
	
	public Main_region_1 getMain_region_1() {
		return main_region_1;
	}
	
	public void setRecieveRegion(RecieveRegion recieveRegion) {
		this.recieveRegion = recieveRegion;
	}
	
	public RecieveRegion getRecieveRegion() {
		return recieveRegion;
	}
	
	public void setSendRegion(SendRegion sendRegion) {
		this.sendRegion = sendRegion;
	}
	
	public SendRegion getSendRegion() {
		return sendRegion;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if ((((this.main_region_1 == Main_region_1.Closed)) && (this.StateMachine_Port_OrionConnReq_In))) {
			this.Send_StateMachine_Port_OrionConnResp_Out = true;
			this.main_region_1 = Main_region_1.Connecting;
		}
		else {
			if ((((this.main_region_1 == Main_region_1.Connecting)) && (this.StateMachine_Port_OrionConnConf_In))) {
				this.main_region_1 = Main_region_1.Connected;
				this.sendRegion = SendRegion.KeepAliveSendTimeout;
				this.recieveRegion = RecieveRegion.KeepAliveRecieveTimeout;
				this.Status_conn_Out = true;
			}
		}
	}
	
	private void clearOutEvents() {
		Send_StateMachine_Port_OrionDisconnCause_Out = false;
		Send_StateMachine_Port_OrionKeepAlive_Out = false;
		Status_disconn_Out = false;
		Send_StateMachine_Port_OrionConnReq_Out = false;
		Send_StateMachine_Port_OrionConnConf_Out = false;
		Send_StateMachine_Port_OrionConnResp_Out = false;
		Send_StateMachine_Port_OrionDisconn_Out = false;
		Status_conn_Out = false;
		Send_StateMachine_Port_OrionAppData_Out = false;
	}
	
	private void clearInEvents() {
		StateMachine_Port_OrionKeepAlive_In = false;
		StateMachine_Port_OrionConnReq_In = false;
		StateMachine_Port_OrionDisconn_In = false;
		StateMachine_Port_OrionConnResp_In = false;
		StateMachine_Port_OrionConnConf_In = false;
		StateMachine_Port_OrionAppData_In = false;
		StateMachine_Port_OrionDisconnCause_In = false;
	}
	
	@Override
	public String toString() {
		return
			"Send_StateMachine_Port_OrionDisconnCause_Out = " + Send_StateMachine_Port_OrionDisconnCause_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionKeepAlive_Out = " + Send_StateMachine_Port_OrionKeepAlive_Out + System.lineSeparator() +
			"Status_disconn_Out = " + Status_disconn_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionConnReq_Out = " + Send_StateMachine_Port_OrionConnReq_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionConnConf_Out = " + Send_StateMachine_Port_OrionConnConf_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionConnResp_Out = " + Send_StateMachine_Port_OrionConnResp_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionDisconn_Out = " + Send_StateMachine_Port_OrionDisconn_Out + System.lineSeparator() +
			"Status_conn_Out = " + Status_conn_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionAppData_Out = " + Send_StateMachine_Port_OrionAppData_Out + System.lineSeparator() +
			"StateMachine_Port_OrionKeepAlive_In = " + StateMachine_Port_OrionKeepAlive_In + System.lineSeparator() +
			"StateMachine_Port_OrionConnReq_In = " + StateMachine_Port_OrionConnReq_In + System.lineSeparator() +
			"StateMachine_Port_OrionDisconn_In = " + StateMachine_Port_OrionDisconn_In + System.lineSeparator() +
			"StateMachine_Port_OrionConnResp_In = " + StateMachine_Port_OrionConnResp_In + System.lineSeparator() +
			"StateMachine_Port_OrionConnConf_In = " + StateMachine_Port_OrionConnConf_In + System.lineSeparator() +
			"StateMachine_Port_OrionAppData_In = " + StateMachine_Port_OrionAppData_In + System.lineSeparator() +
			"StateMachine_Port_OrionDisconnCause_In = " + StateMachine_Port_OrionDisconnCause_In + System.lineSeparator() +
			"main_region_1 = " + main_region_1 + System.lineSeparator() +
			"recieveRegion = " + recieveRegion + System.lineSeparator() +
			"sendRegion = " + sendRegion
		;
	}
	
}
