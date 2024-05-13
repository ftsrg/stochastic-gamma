package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public interface Orion_Master_SM_extInterface {

	public Block_Interface_ForOrionInterface.Required getBlock_Port();
	public Connection_Interface_For_OrionInterface.Required getConnection_Port();
	public StateMachine_Interface_For_OrionInterface.Required getStateMachine_Port();
	public StateMachine_Interface_For_OrionInterface.Provided getSend_StateMachine_Port();
	public StateMachine_Interface_For_OrionInterface.Provided getProcess_StateMachine_Port();
	public SoftwareTimerInterface.Required getTimoeutKeepAliveReceiveTimeout_3();
	public SoftwareTimerInterface.Required getTimeoutKapcsolodik_2();
	public SoftwareTimerInterface.Required getTimeoutZarva_0();
	public SoftwareTimerInterface.Required getTimeoutKeepAliveSendTimeout_1();
	public SoftwareTimerInterface.Provided getTimoeutKeepAliveReceiveTimeout_3_req();
	public SoftwareTimerInterface.Provided getTimeoutKapcsolodik_2_req();
	public SoftwareTimerInterface.Provided getTimeoutZarva_0_req();
	public SoftwareTimerInterface.Provided getTimeoutKeepAliveSendTimeout_1_req();
	public ConnectionStateInterface.Provided getStatus();
	
	void runCycle();
	void reset();

}
