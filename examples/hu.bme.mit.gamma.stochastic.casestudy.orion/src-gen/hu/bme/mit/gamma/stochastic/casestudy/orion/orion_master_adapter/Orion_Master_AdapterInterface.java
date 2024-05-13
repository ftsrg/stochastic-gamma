package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_adapter;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.Block_Interface_ForOrionInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StateMachine_Interface_For_OrionInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.Connection_Interface_For_OrionInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface Orion_Master_AdapterInterface {
	
	SoftwareTimerInterface.Provided getTimoeutKeepAliveReceiveTimeout_3_req();
	SoftwareTimerInterface.Provided getTimeoutZarva_0_req();
	SoftwareTimerInterface.Required getTimeoutZarva_0();
	StateMachine_Interface_For_OrionInterface.Provided getProcess_StateMachine_Port();
	Block_Interface_ForOrionInterface.Required getBlock_Port();
	StateMachine_Interface_For_OrionInterface.Provided getSend_StateMachine_Port();
	SoftwareTimerInterface.Required getTimoeutKeepAliveReceiveTimeout_3();
	SoftwareTimerInterface.Required getTimeoutKeepAliveSendTimeout_1();
	SoftwareTimerInterface.Provided getTimeoutKapcsolodik_2_req();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveSendTimeout_1_req();
	StateMachine_Interface_For_OrionInterface.Required getStateMachine_Port();
	Connection_Interface_For_OrionInterface.Required getConnection_Port();
	SoftwareTimerInterface.Required getTimeoutKapcsolodik_2();
	ConnectionStateInterface.Provided getStatus();
	
	void reset();
	
	void start();
	
}
