package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public class Orion_Slave_SM_extStatemachine {
	
	enum Main_region_of_Orion_Slave_SM {__Inactive__, Kapcsolodva_1, Zarva_2, Kapcsolodik_3}
	enum Region_2_in_Kapcsolodva_1 {__Inactive__, KeepAliveReceiveTimeout_4}
	enum Region_1_in_Kapcsolodva_1 {__Inactive__, KeepAliveSendTimeout_0}
	private boolean StateMachine_Port_OrionDisconn_In;
	private boolean StateMachine_Port_OrionDisconnCause_In;
	private boolean TimeoutKeepAliveReceiveTimeout_4_newEvent_In;
	private boolean Block_Port_Operation_Call_Invalid_In;
	private boolean TimeoutKeepAliveSendTimeout_0_newEvent_In;
	private boolean Block_Port_Operation_Call_SendData_In;
	private boolean TimeoutKapcsolodik_3_newEvent_In;
	private boolean StateMachine_Port_OrionKeepAlive_In;
	private boolean Connection_Port_Operation_Call_Connect_In;
	private boolean StateMachine_Port_OrionConnResp_In;
	private boolean StateMachine_Port_OrionConnConf_In;
	private boolean StateMachine_Port_OrionAppData_In;
	private boolean StateMachine_Port_OrionConnReq_In;
	private boolean Connection_Port_Operation_Call_Disconn_In;
	private boolean TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out;
	private boolean Handle_StateMachine_Port_OrionConnReq_Out;
	private boolean Handle_StateMachine_Port_OrionDisconn_Out;
	private boolean Process_StateMachine_Port_OrionConnReq_Out;
	private boolean Process_StateMachine_Port_OrionKeepAlive_Out;
	private boolean Send_StateMachine_Port_OrionDisconn_Out;
	private boolean Process_StateMachine_Port_OrionConnConf_Out;
	private boolean TimeoutKeepAliveSendTimeout_0_req_newEvent_Out;
	private boolean Handle_StateMachine_Port_OrionKeepAlive_Out;
	private boolean Handle_StateMachine_Port_OrionDisconnCause_Out;
	private boolean TimeoutKapcsolodik_3_req_newEvent_Out;
	private boolean Status_conn_Out;
	private boolean Send_StateMachine_Port_OrionConnReq_Out;
	private boolean Send_StateMachine_Port_OrionAppData_Out;
	private boolean Handle_StateMachine_Port_OrionConnConf_Out;
	private boolean Send_StateMachine_Port_OrionConnConf_Out;
	private boolean Send_StateMachine_Port_OrionConnResp_Out;
	private boolean Status_disconn_Out;
	private boolean Send_StateMachine_Port_OrionDisconnCause_Out;
	private boolean Process_StateMachine_Port_OrionConnResp_Out;
	private boolean Send_StateMachine_Port_OrionKeepAlive_Out;
	private boolean Process_StateMachine_Port_OrionDisconn_Out;
	private boolean Process_StateMachine_Port_OrionAppData_Out;
	private boolean Process_StateMachine_Port_OrionDisconnCause_Out;
	private boolean Handle_StateMachine_Port_OrionAppData_Out;
	private boolean Handle_StateMachine_Port_OrionConnResp_Out;
	private Main_region_of_Orion_Slave_SM main_region_of_Orion_Slave_SM;
	private Region_2_in_Kapcsolodva_1 region_2_in_Kapcsolodva_1;
	private Region_1_in_Kapcsolodva_1 region_1_in_Kapcsolodva_1;
	
	public Orion_Slave_SM_extStatemachine() {
	}
	
	public void reset() {
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}

	public void handleBeforeReset() {
		this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.__Inactive__;
		this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
		this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
		clearOutEvents();
		clearInEvents();
	}
	public void resetVariables() {
		this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
		this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
		this.StateMachine_Port_OrionDisconn_In = false;
		this.StateMachine_Port_OrionDisconnCause_In = false;
		this.TimeoutKeepAliveReceiveTimeout_4_newEvent_In = false;
		this.Block_Port_Operation_Call_Invalid_In = false;
		this.TimeoutKeepAliveSendTimeout_0_newEvent_In = false;
		this.Block_Port_Operation_Call_SendData_In = false;
		this.TimeoutKapcsolodik_3_newEvent_In = false;
		this.StateMachine_Port_OrionKeepAlive_In = false;
		this.Connection_Port_Operation_Call_Connect_In = false;
		this.StateMachine_Port_OrionConnResp_In = false;
		this.StateMachine_Port_OrionConnConf_In = false;
		this.StateMachine_Port_OrionAppData_In = false;
		this.StateMachine_Port_OrionConnReq_In = false;
		this.Connection_Port_Operation_Call_Disconn_In = false;
		this.TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out = false;
		this.Handle_StateMachine_Port_OrionConnReq_Out = false;
		this.Handle_StateMachine_Port_OrionDisconn_Out = false;
		this.Process_StateMachine_Port_OrionConnReq_Out = false;
		this.Process_StateMachine_Port_OrionKeepAlive_Out = false;
		this.Send_StateMachine_Port_OrionDisconn_Out = false;
		this.Process_StateMachine_Port_OrionConnConf_Out = false;
		this.TimeoutKeepAliveSendTimeout_0_req_newEvent_Out = false;
		this.Handle_StateMachine_Port_OrionKeepAlive_Out = false;
		this.Handle_StateMachine_Port_OrionDisconnCause_Out = false;
		this.TimeoutKapcsolodik_3_req_newEvent_Out = false;
		this.Status_conn_Out = false;
		this.Send_StateMachine_Port_OrionConnReq_Out = false;
		this.Send_StateMachine_Port_OrionAppData_Out = false;
		this.Handle_StateMachine_Port_OrionConnConf_Out = false;
		this.Send_StateMachine_Port_OrionConnConf_Out = false;
		this.Send_StateMachine_Port_OrionConnResp_Out = false;
		this.Status_disconn_Out = false;
		this.Send_StateMachine_Port_OrionDisconnCause_Out = false;
		this.Process_StateMachine_Port_OrionConnResp_Out = false;
		this.Send_StateMachine_Port_OrionKeepAlive_Out = false;
		this.Process_StateMachine_Port_OrionDisconn_Out = false;
		this.Process_StateMachine_Port_OrionAppData_Out = false;
		this.Process_StateMachine_Port_OrionDisconnCause_Out = false;
		this.Handle_StateMachine_Port_OrionAppData_Out = false;
		this.Handle_StateMachine_Port_OrionConnResp_Out = false;
	}

	public void resetStateConfigurations() {
		this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
	}

	public void raiseEntryEvents() {
	}

	public void handleAfterReset() {
		// Empty
	}
	//
	public void setStateMachine_Port_OrionDisconn_In(boolean StateMachine_Port_OrionDisconn_In) {
		this.StateMachine_Port_OrionDisconn_In = StateMachine_Port_OrionDisconn_In;
	}
	
	public boolean getStateMachine_Port_OrionDisconn_In() {
		return StateMachine_Port_OrionDisconn_In;
	}
	
	public void setStateMachine_Port_OrionDisconnCause_In(boolean StateMachine_Port_OrionDisconnCause_In) {
		this.StateMachine_Port_OrionDisconnCause_In = StateMachine_Port_OrionDisconnCause_In;
	}
	
	public boolean getStateMachine_Port_OrionDisconnCause_In() {
		return StateMachine_Port_OrionDisconnCause_In;
	}
	
	public void setTimeoutKeepAliveReceiveTimeout_4_newEvent_In(boolean TimeoutKeepAliveReceiveTimeout_4_newEvent_In) {
		this.TimeoutKeepAliveReceiveTimeout_4_newEvent_In = TimeoutKeepAliveReceiveTimeout_4_newEvent_In;
	}
	
	public boolean getTimeoutKeepAliveReceiveTimeout_4_newEvent_In() {
		return TimeoutKeepAliveReceiveTimeout_4_newEvent_In;
	}
	
	public void setBlock_Port_Operation_Call_Invalid_In(boolean Block_Port_Operation_Call_Invalid_In) {
		this.Block_Port_Operation_Call_Invalid_In = Block_Port_Operation_Call_Invalid_In;
	}
	
	public boolean getBlock_Port_Operation_Call_Invalid_In() {
		return Block_Port_Operation_Call_Invalid_In;
	}
	
	public void setTimeoutKeepAliveSendTimeout_0_newEvent_In(boolean TimeoutKeepAliveSendTimeout_0_newEvent_In) {
		this.TimeoutKeepAliveSendTimeout_0_newEvent_In = TimeoutKeepAliveSendTimeout_0_newEvent_In;
	}
	
	public boolean getTimeoutKeepAliveSendTimeout_0_newEvent_In() {
		return TimeoutKeepAliveSendTimeout_0_newEvent_In;
	}
	
	public void setBlock_Port_Operation_Call_SendData_In(boolean Block_Port_Operation_Call_SendData_In) {
		this.Block_Port_Operation_Call_SendData_In = Block_Port_Operation_Call_SendData_In;
	}
	
	public boolean getBlock_Port_Operation_Call_SendData_In() {
		return Block_Port_Operation_Call_SendData_In;
	}
	
	public void setTimeoutKapcsolodik_3_newEvent_In(boolean TimeoutKapcsolodik_3_newEvent_In) {
		this.TimeoutKapcsolodik_3_newEvent_In = TimeoutKapcsolodik_3_newEvent_In;
	}
	
	public boolean getTimeoutKapcsolodik_3_newEvent_In() {
		return TimeoutKapcsolodik_3_newEvent_In;
	}
	
	public void setStateMachine_Port_OrionKeepAlive_In(boolean StateMachine_Port_OrionKeepAlive_In) {
		this.StateMachine_Port_OrionKeepAlive_In = StateMachine_Port_OrionKeepAlive_In;
	}
	
	public boolean getStateMachine_Port_OrionKeepAlive_In() {
		return StateMachine_Port_OrionKeepAlive_In;
	}
	
	public void setConnection_Port_Operation_Call_Connect_In(boolean Connection_Port_Operation_Call_Connect_In) {
		this.Connection_Port_Operation_Call_Connect_In = Connection_Port_Operation_Call_Connect_In;
	}
	
	public boolean getConnection_Port_Operation_Call_Connect_In() {
		return Connection_Port_Operation_Call_Connect_In;
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
	
	public void setStateMachine_Port_OrionConnReq_In(boolean StateMachine_Port_OrionConnReq_In) {
		this.StateMachine_Port_OrionConnReq_In = StateMachine_Port_OrionConnReq_In;
	}
	
	public boolean getStateMachine_Port_OrionConnReq_In() {
		return StateMachine_Port_OrionConnReq_In;
	}
	
	public void setConnection_Port_Operation_Call_Disconn_In(boolean Connection_Port_Operation_Call_Disconn_In) {
		this.Connection_Port_Operation_Call_Disconn_In = Connection_Port_Operation_Call_Disconn_In;
	}
	
	public boolean getConnection_Port_Operation_Call_Disconn_In() {
		return Connection_Port_Operation_Call_Disconn_In;
	}
	
	public void setTimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out(boolean TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out) {
		this.TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out = TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out;
	}
	
	public boolean getTimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out() {
		return TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out;
	}
	
	public void setHandle_StateMachine_Port_OrionConnReq_Out(boolean Handle_StateMachine_Port_OrionConnReq_Out) {
		this.Handle_StateMachine_Port_OrionConnReq_Out = Handle_StateMachine_Port_OrionConnReq_Out;
	}
	
	public boolean getHandle_StateMachine_Port_OrionConnReq_Out() {
		return Handle_StateMachine_Port_OrionConnReq_Out;
	}
	
	public void setHandle_StateMachine_Port_OrionDisconn_Out(boolean Handle_StateMachine_Port_OrionDisconn_Out) {
		this.Handle_StateMachine_Port_OrionDisconn_Out = Handle_StateMachine_Port_OrionDisconn_Out;
	}
	
	public boolean getHandle_StateMachine_Port_OrionDisconn_Out() {
		return Handle_StateMachine_Port_OrionDisconn_Out;
	}
	
	public void setProcess_StateMachine_Port_OrionConnReq_Out(boolean Process_StateMachine_Port_OrionConnReq_Out) {
		this.Process_StateMachine_Port_OrionConnReq_Out = Process_StateMachine_Port_OrionConnReq_Out;
	}
	
	public boolean getProcess_StateMachine_Port_OrionConnReq_Out() {
		return Process_StateMachine_Port_OrionConnReq_Out;
	}
	
	public void setProcess_StateMachine_Port_OrionKeepAlive_Out(boolean Process_StateMachine_Port_OrionKeepAlive_Out) {
		this.Process_StateMachine_Port_OrionKeepAlive_Out = Process_StateMachine_Port_OrionKeepAlive_Out;
	}
	
	public boolean getProcess_StateMachine_Port_OrionKeepAlive_Out() {
		return Process_StateMachine_Port_OrionKeepAlive_Out;
	}
	
	public void setSend_StateMachine_Port_OrionDisconn_Out(boolean Send_StateMachine_Port_OrionDisconn_Out) {
		this.Send_StateMachine_Port_OrionDisconn_Out = Send_StateMachine_Port_OrionDisconn_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionDisconn_Out() {
		return Send_StateMachine_Port_OrionDisconn_Out;
	}
	
	public void setProcess_StateMachine_Port_OrionConnConf_Out(boolean Process_StateMachine_Port_OrionConnConf_Out) {
		this.Process_StateMachine_Port_OrionConnConf_Out = Process_StateMachine_Port_OrionConnConf_Out;
	}
	
	public boolean getProcess_StateMachine_Port_OrionConnConf_Out() {
		return Process_StateMachine_Port_OrionConnConf_Out;
	}
	
	public void setTimeoutKeepAliveSendTimeout_0_req_newEvent_Out(boolean TimeoutKeepAliveSendTimeout_0_req_newEvent_Out) {
		this.TimeoutKeepAliveSendTimeout_0_req_newEvent_Out = TimeoutKeepAliveSendTimeout_0_req_newEvent_Out;
	}
	
	public boolean getTimeoutKeepAliveSendTimeout_0_req_newEvent_Out() {
		return TimeoutKeepAliveSendTimeout_0_req_newEvent_Out;
	}
	
	public void setHandle_StateMachine_Port_OrionKeepAlive_Out(boolean Handle_StateMachine_Port_OrionKeepAlive_Out) {
		this.Handle_StateMachine_Port_OrionKeepAlive_Out = Handle_StateMachine_Port_OrionKeepAlive_Out;
	}
	
	public boolean getHandle_StateMachine_Port_OrionKeepAlive_Out() {
		return Handle_StateMachine_Port_OrionKeepAlive_Out;
	}
	
	public void setHandle_StateMachine_Port_OrionDisconnCause_Out(boolean Handle_StateMachine_Port_OrionDisconnCause_Out) {
		this.Handle_StateMachine_Port_OrionDisconnCause_Out = Handle_StateMachine_Port_OrionDisconnCause_Out;
	}
	
	public boolean getHandle_StateMachine_Port_OrionDisconnCause_Out() {
		return Handle_StateMachine_Port_OrionDisconnCause_Out;
	}
	
	public void setTimeoutKapcsolodik_3_req_newEvent_Out(boolean TimeoutKapcsolodik_3_req_newEvent_Out) {
		this.TimeoutKapcsolodik_3_req_newEvent_Out = TimeoutKapcsolodik_3_req_newEvent_Out;
	}
	
	public boolean getTimeoutKapcsolodik_3_req_newEvent_Out() {
		return TimeoutKapcsolodik_3_req_newEvent_Out;
	}
	
	public void setStatus_conn_Out(boolean Status_conn_Out) {
		this.Status_conn_Out = Status_conn_Out;
	}
	
	public boolean getStatus_conn_Out() {
		return Status_conn_Out;
	}
	
	public void setSend_StateMachine_Port_OrionConnReq_Out(boolean Send_StateMachine_Port_OrionConnReq_Out) {
		this.Send_StateMachine_Port_OrionConnReq_Out = Send_StateMachine_Port_OrionConnReq_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionConnReq_Out() {
		return Send_StateMachine_Port_OrionConnReq_Out;
	}
	
	public void setSend_StateMachine_Port_OrionAppData_Out(boolean Send_StateMachine_Port_OrionAppData_Out) {
		this.Send_StateMachine_Port_OrionAppData_Out = Send_StateMachine_Port_OrionAppData_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionAppData_Out() {
		return Send_StateMachine_Port_OrionAppData_Out;
	}
	
	public void setHandle_StateMachine_Port_OrionConnConf_Out(boolean Handle_StateMachine_Port_OrionConnConf_Out) {
		this.Handle_StateMachine_Port_OrionConnConf_Out = Handle_StateMachine_Port_OrionConnConf_Out;
	}
	
	public boolean getHandle_StateMachine_Port_OrionConnConf_Out() {
		return Handle_StateMachine_Port_OrionConnConf_Out;
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
	
	public void setStatus_disconn_Out(boolean Status_disconn_Out) {
		this.Status_disconn_Out = Status_disconn_Out;
	}
	
	public boolean getStatus_disconn_Out() {
		return Status_disconn_Out;
	}
	
	public void setSend_StateMachine_Port_OrionDisconnCause_Out(boolean Send_StateMachine_Port_OrionDisconnCause_Out) {
		this.Send_StateMachine_Port_OrionDisconnCause_Out = Send_StateMachine_Port_OrionDisconnCause_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionDisconnCause_Out() {
		return Send_StateMachine_Port_OrionDisconnCause_Out;
	}
	
	public void setProcess_StateMachine_Port_OrionConnResp_Out(boolean Process_StateMachine_Port_OrionConnResp_Out) {
		this.Process_StateMachine_Port_OrionConnResp_Out = Process_StateMachine_Port_OrionConnResp_Out;
	}
	
	public boolean getProcess_StateMachine_Port_OrionConnResp_Out() {
		return Process_StateMachine_Port_OrionConnResp_Out;
	}
	
	public void setSend_StateMachine_Port_OrionKeepAlive_Out(boolean Send_StateMachine_Port_OrionKeepAlive_Out) {
		this.Send_StateMachine_Port_OrionKeepAlive_Out = Send_StateMachine_Port_OrionKeepAlive_Out;
	}
	
	public boolean getSend_StateMachine_Port_OrionKeepAlive_Out() {
		return Send_StateMachine_Port_OrionKeepAlive_Out;
	}
	
	public void setProcess_StateMachine_Port_OrionDisconn_Out(boolean Process_StateMachine_Port_OrionDisconn_Out) {
		this.Process_StateMachine_Port_OrionDisconn_Out = Process_StateMachine_Port_OrionDisconn_Out;
	}
	
	public boolean getProcess_StateMachine_Port_OrionDisconn_Out() {
		return Process_StateMachine_Port_OrionDisconn_Out;
	}
	
	public void setProcess_StateMachine_Port_OrionAppData_Out(boolean Process_StateMachine_Port_OrionAppData_Out) {
		this.Process_StateMachine_Port_OrionAppData_Out = Process_StateMachine_Port_OrionAppData_Out;
	}
	
	public boolean getProcess_StateMachine_Port_OrionAppData_Out() {
		return Process_StateMachine_Port_OrionAppData_Out;
	}
	
	public void setProcess_StateMachine_Port_OrionDisconnCause_Out(boolean Process_StateMachine_Port_OrionDisconnCause_Out) {
		this.Process_StateMachine_Port_OrionDisconnCause_Out = Process_StateMachine_Port_OrionDisconnCause_Out;
	}
	
	public boolean getProcess_StateMachine_Port_OrionDisconnCause_Out() {
		return Process_StateMachine_Port_OrionDisconnCause_Out;
	}
	
	public void setHandle_StateMachine_Port_OrionAppData_Out(boolean Handle_StateMachine_Port_OrionAppData_Out) {
		this.Handle_StateMachine_Port_OrionAppData_Out = Handle_StateMachine_Port_OrionAppData_Out;
	}
	
	public boolean getHandle_StateMachine_Port_OrionAppData_Out() {
		return Handle_StateMachine_Port_OrionAppData_Out;
	}
	
	public void setHandle_StateMachine_Port_OrionConnResp_Out(boolean Handle_StateMachine_Port_OrionConnResp_Out) {
		this.Handle_StateMachine_Port_OrionConnResp_Out = Handle_StateMachine_Port_OrionConnResp_Out;
	}
	
	public boolean getHandle_StateMachine_Port_OrionConnResp_Out() {
		return Handle_StateMachine_Port_OrionConnResp_Out;
	}
	
	public void setMain_region_of_Orion_Slave_SM(Main_region_of_Orion_Slave_SM main_region_of_Orion_Slave_SM) {
		this.main_region_of_Orion_Slave_SM = main_region_of_Orion_Slave_SM;
	}
	
	public Main_region_of_Orion_Slave_SM getMain_region_of_Orion_Slave_SM() {
		return main_region_of_Orion_Slave_SM;
	}
	
	public void setRegion_2_in_Kapcsolodva_1(Region_2_in_Kapcsolodva_1 region_2_in_Kapcsolodva_1) {
		this.region_2_in_Kapcsolodva_1 = region_2_in_Kapcsolodva_1;
	}
	
	public Region_2_in_Kapcsolodva_1 getRegion_2_in_Kapcsolodva_1() {
		return region_2_in_Kapcsolodva_1;
	}
	
	public void setRegion_1_in_Kapcsolodva_1(Region_1_in_Kapcsolodva_1 region_1_in_Kapcsolodva_1) {
		this.region_1_in_Kapcsolodva_1 = region_1_in_Kapcsolodva_1;
	}
	
	public Region_1_in_Kapcsolodva_1 getRegion_1_in_Kapcsolodva_1() {
		return region_1_in_Kapcsolodva_1;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.Block_Port_Operation_Call_Invalid_In))) {
			if ((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) {
				this.Status_disconn_Out = true;
			}
			this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
			this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.StateMachine_Port_OrionConnResp_In))) {
			if ((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) {
				this.Status_disconn_Out = true;
			}
			this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
			this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.StateMachine_Port_OrionConnReq_In))) {
			if ((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) {
				this.Status_disconn_Out = true;
			}
			this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
			this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.StateMachine_Port_OrionConnConf_In))) {
			if ((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) {
				this.Status_disconn_Out = true;
			}
			this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
			this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.StateMachine_Port_OrionDisconnCause_In))) {
			if ((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) {
				this.Status_disconn_Out = true;
			}
			this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
			this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.Connection_Port_Operation_Call_Disconn_In))) {
			if ((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) {
				this.Status_disconn_Out = true;
			}
			this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
			this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Zarva_2)) && (this.StateMachine_Port_OrionConnReq_In))) {
			this.Send_StateMachine_Port_OrionConnResp_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Kapcsolodik_3;
			this.TimeoutKapcsolodik_3_req_newEvent_Out = true;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionConnConf_In))) {
			this.Handle_StateMachine_Port_OrionConnConf_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Kapcsolodva_1;
			this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.KeepAliveSendTimeout_0;
			this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.KeepAliveReceiveTimeout_4;
			this.Status_conn_Out = true;
			this.TimeoutKeepAliveSendTimeout_0_req_newEvent_Out = true;
			this.TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out = true;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.TimeoutKapcsolodik_3_newEvent_In))) {
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionDisconnCause_In))) {
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionConnResp_In))) {
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionConnReq_In))) {
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionAppData_In))) {
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.Block_Port_Operation_Call_Invalid_In))) {
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if ((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionKeepAlive_In))) {
			this.Send_StateMachine_Port_OrionDisconn_Out = true;
			this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
		} else 
		if (!(((((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.Block_Port_Operation_Call_Invalid_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.StateMachine_Port_OrionConnResp_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.StateMachine_Port_OrionConnReq_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.StateMachine_Port_OrionConnConf_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.StateMachine_Port_OrionDisconnCause_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) && (this.Connection_Port_Operation_Call_Disconn_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Zarva_2)) && (this.StateMachine_Port_OrionConnReq_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionConnConf_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.TimeoutKapcsolodik_3_newEvent_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionDisconnCause_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionConnResp_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionConnReq_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionAppData_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.Block_Port_Operation_Call_Invalid_In)) || (((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodik_3)) && (this.StateMachine_Port_OrionKeepAlive_In))))) {
			if ((((this.region_1_in_Kapcsolodva_1 == Region_1_in_Kapcsolodva_1.KeepAliveSendTimeout_0)) && (this.TimeoutKeepAliveSendTimeout_0_newEvent_In))) {
				this.Send_StateMachine_Port_OrionKeepAlive_Out = true;
				this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.KeepAliveSendTimeout_0;
				this.TimeoutKeepAliveSendTimeout_0_req_newEvent_Out = true;
			} else 
			if ((((this.region_1_in_Kapcsolodva_1 == Region_1_in_Kapcsolodva_1.KeepAliveSendTimeout_0)) && (this.Block_Port_Operation_Call_SendData_In))) {
				this.Send_StateMachine_Port_OrionAppData_Out = true;
				this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.KeepAliveSendTimeout_0;
				this.TimeoutKeepAliveSendTimeout_0_req_newEvent_Out = true;
			}
			if ((((this.region_2_in_Kapcsolodva_1 == Region_2_in_Kapcsolodva_1.KeepAliveReceiveTimeout_4)) && (this.TimeoutKeepAliveReceiveTimeout_4_newEvent_In))) {
				if ((this.main_region_of_Orion_Slave_SM == Main_region_of_Orion_Slave_SM.Kapcsolodva_1)) {
					this.Status_disconn_Out = true;
				}
				this.region_1_in_Kapcsolodva_1 = Region_1_in_Kapcsolodva_1.__Inactive__;
				this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.__Inactive__;
				this.Send_StateMachine_Port_OrionDisconn_Out = true;
				this.main_region_of_Orion_Slave_SM = Main_region_of_Orion_Slave_SM.Zarva_2;
			} else 
			if ((((this.region_2_in_Kapcsolodva_1 == Region_2_in_Kapcsolodva_1.KeepAliveReceiveTimeout_4)) && (this.StateMachine_Port_OrionAppData_In))) {
				this.Process_StateMachine_Port_OrionAppData_Out = true;
				this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.KeepAliveReceiveTimeout_4;
				this.TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out = true;
			} else 
			if ((((this.region_2_in_Kapcsolodva_1 == Region_2_in_Kapcsolodva_1.KeepAliveReceiveTimeout_4)) && (this.StateMachine_Port_OrionKeepAlive_In))) {
				this.region_2_in_Kapcsolodva_1 = Region_2_in_Kapcsolodva_1.KeepAliveReceiveTimeout_4;
				this.TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out = true;
			}
		}
	}
	
	private void clearOutEvents() {
		TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out = false;
		Handle_StateMachine_Port_OrionConnReq_Out = false;
		Handle_StateMachine_Port_OrionDisconn_Out = false;
		Process_StateMachine_Port_OrionConnReq_Out = false;
		Process_StateMachine_Port_OrionKeepAlive_Out = false;
		Send_StateMachine_Port_OrionDisconn_Out = false;
		Process_StateMachine_Port_OrionConnConf_Out = false;
		TimeoutKeepAliveSendTimeout_0_req_newEvent_Out = false;
		Handle_StateMachine_Port_OrionKeepAlive_Out = false;
		Handle_StateMachine_Port_OrionDisconnCause_Out = false;
		TimeoutKapcsolodik_3_req_newEvent_Out = false;
		Status_conn_Out = false;
		Send_StateMachine_Port_OrionConnReq_Out = false;
		Send_StateMachine_Port_OrionAppData_Out = false;
		Handle_StateMachine_Port_OrionConnConf_Out = false;
		Send_StateMachine_Port_OrionConnConf_Out = false;
		Send_StateMachine_Port_OrionConnResp_Out = false;
		Status_disconn_Out = false;
		Send_StateMachine_Port_OrionDisconnCause_Out = false;
		Process_StateMachine_Port_OrionConnResp_Out = false;
		Send_StateMachine_Port_OrionKeepAlive_Out = false;
		Process_StateMachine_Port_OrionDisconn_Out = false;
		Process_StateMachine_Port_OrionAppData_Out = false;
		Process_StateMachine_Port_OrionDisconnCause_Out = false;
		Handle_StateMachine_Port_OrionAppData_Out = false;
		Handle_StateMachine_Port_OrionConnResp_Out = false;
	}
	
	private void clearInEvents() {
		StateMachine_Port_OrionDisconn_In = false;
		StateMachine_Port_OrionDisconnCause_In = false;
		TimeoutKeepAliveReceiveTimeout_4_newEvent_In = false;
		Block_Port_Operation_Call_Invalid_In = false;
		TimeoutKeepAliveSendTimeout_0_newEvent_In = false;
		Block_Port_Operation_Call_SendData_In = false;
		TimeoutKapcsolodik_3_newEvent_In = false;
		StateMachine_Port_OrionKeepAlive_In = false;
		Connection_Port_Operation_Call_Connect_In = false;
		StateMachine_Port_OrionConnResp_In = false;
		StateMachine_Port_OrionConnConf_In = false;
		StateMachine_Port_OrionAppData_In = false;
		StateMachine_Port_OrionConnReq_In = false;
		Connection_Port_Operation_Call_Disconn_In = false;
	}
	
	@Override
	public String toString() {
		return
			"StateMachine_Port_OrionDisconn_In = " + StateMachine_Port_OrionDisconn_In + System.lineSeparator() +
			"StateMachine_Port_OrionDisconnCause_In = " + StateMachine_Port_OrionDisconnCause_In + System.lineSeparator() +
			"TimeoutKeepAliveReceiveTimeout_4_newEvent_In = " + TimeoutKeepAliveReceiveTimeout_4_newEvent_In + System.lineSeparator() +
			"Block_Port_Operation_Call_Invalid_In = " + Block_Port_Operation_Call_Invalid_In + System.lineSeparator() +
			"TimeoutKeepAliveSendTimeout_0_newEvent_In = " + TimeoutKeepAliveSendTimeout_0_newEvent_In + System.lineSeparator() +
			"Block_Port_Operation_Call_SendData_In = " + Block_Port_Operation_Call_SendData_In + System.lineSeparator() +
			"TimeoutKapcsolodik_3_newEvent_In = " + TimeoutKapcsolodik_3_newEvent_In + System.lineSeparator() +
			"StateMachine_Port_OrionKeepAlive_In = " + StateMachine_Port_OrionKeepAlive_In + System.lineSeparator() +
			"Connection_Port_Operation_Call_Connect_In = " + Connection_Port_Operation_Call_Connect_In + System.lineSeparator() +
			"StateMachine_Port_OrionConnResp_In = " + StateMachine_Port_OrionConnResp_In + System.lineSeparator() +
			"StateMachine_Port_OrionConnConf_In = " + StateMachine_Port_OrionConnConf_In + System.lineSeparator() +
			"StateMachine_Port_OrionAppData_In = " + StateMachine_Port_OrionAppData_In + System.lineSeparator() +
			"StateMachine_Port_OrionConnReq_In = " + StateMachine_Port_OrionConnReq_In + System.lineSeparator() +
			"Connection_Port_Operation_Call_Disconn_In = " + Connection_Port_Operation_Call_Disconn_In + System.lineSeparator() +
			"TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out = " + TimeoutKeepAliveReceiveTimeout_4_req_newEvent_Out + System.lineSeparator() +
			"Handle_StateMachine_Port_OrionConnReq_Out = " + Handle_StateMachine_Port_OrionConnReq_Out + System.lineSeparator() +
			"Handle_StateMachine_Port_OrionDisconn_Out = " + Handle_StateMachine_Port_OrionDisconn_Out + System.lineSeparator() +
			"Process_StateMachine_Port_OrionConnReq_Out = " + Process_StateMachine_Port_OrionConnReq_Out + System.lineSeparator() +
			"Process_StateMachine_Port_OrionKeepAlive_Out = " + Process_StateMachine_Port_OrionKeepAlive_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionDisconn_Out = " + Send_StateMachine_Port_OrionDisconn_Out + System.lineSeparator() +
			"Process_StateMachine_Port_OrionConnConf_Out = " + Process_StateMachine_Port_OrionConnConf_Out + System.lineSeparator() +
			"TimeoutKeepAliveSendTimeout_0_req_newEvent_Out = " + TimeoutKeepAliveSendTimeout_0_req_newEvent_Out + System.lineSeparator() +
			"Handle_StateMachine_Port_OrionKeepAlive_Out = " + Handle_StateMachine_Port_OrionKeepAlive_Out + System.lineSeparator() +
			"Handle_StateMachine_Port_OrionDisconnCause_Out = " + Handle_StateMachine_Port_OrionDisconnCause_Out + System.lineSeparator() +
			"TimeoutKapcsolodik_3_req_newEvent_Out = " + TimeoutKapcsolodik_3_req_newEvent_Out + System.lineSeparator() +
			"Status_conn_Out = " + Status_conn_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionConnReq_Out = " + Send_StateMachine_Port_OrionConnReq_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionAppData_Out = " + Send_StateMachine_Port_OrionAppData_Out + System.lineSeparator() +
			"Handle_StateMachine_Port_OrionConnConf_Out = " + Handle_StateMachine_Port_OrionConnConf_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionConnConf_Out = " + Send_StateMachine_Port_OrionConnConf_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionConnResp_Out = " + Send_StateMachine_Port_OrionConnResp_Out + System.lineSeparator() +
			"Status_disconn_Out = " + Status_disconn_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionDisconnCause_Out = " + Send_StateMachine_Port_OrionDisconnCause_Out + System.lineSeparator() +
			"Process_StateMachine_Port_OrionConnResp_Out = " + Process_StateMachine_Port_OrionConnResp_Out + System.lineSeparator() +
			"Send_StateMachine_Port_OrionKeepAlive_Out = " + Send_StateMachine_Port_OrionKeepAlive_Out + System.lineSeparator() +
			"Process_StateMachine_Port_OrionDisconn_Out = " + Process_StateMachine_Port_OrionDisconn_Out + System.lineSeparator() +
			"Process_StateMachine_Port_OrionAppData_Out = " + Process_StateMachine_Port_OrionAppData_Out + System.lineSeparator() +
			"Process_StateMachine_Port_OrionDisconnCause_Out = " + Process_StateMachine_Port_OrionDisconnCause_Out + System.lineSeparator() +
			"Handle_StateMachine_Port_OrionAppData_Out = " + Handle_StateMachine_Port_OrionAppData_Out + System.lineSeparator() +
			"Handle_StateMachine_Port_OrionConnResp_Out = " + Handle_StateMachine_Port_OrionConnResp_Out + System.lineSeparator() +
			"main_region_of_Orion_Slave_SM = " + main_region_of_Orion_Slave_SM + System.lineSeparator() +
			"region_2_in_Kapcsolodva_1 = " + region_2_in_Kapcsolodva_1 + System.lineSeparator() +
			"region_1_in_Kapcsolodva_1 = " + region_1_in_Kapcsolodva_1
		;
	}
	
}
