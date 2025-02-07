package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_adapter;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.Block_Interface_ForOrionInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StateMachine_Interface_For_OrionInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.Connection_Interface_For_OrionInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface Orion_Slave_AdapterInterface {
	
	SoftwareTimerInterface.Provided getTimeoutKapcsolodik_3_req();
	ConnectionStateInterface.Provided getStatus();
	Connection_Interface_For_OrionInterface.Required getConnection_Port();
	StateMachine_Interface_For_OrionInterface.Provided getProcess_StateMachine_Port();
	SoftwareTimerInterface.Required getTimeoutKeepAliveSendTimeout_0();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveSendTimeout_0_req();
	Block_Interface_ForOrionInterface.Required getBlock_Port();
	StateMachine_Interface_For_OrionInterface.Provided getHandle_StateMachine_Port();
	SoftwareTimerInterface.Required getTimeoutKapcsolodik_3();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveReceiveTimeout_4_req();
	StateMachine_Interface_For_OrionInterface.Required getStateMachine_Port();
	SoftwareTimerInterface.Required getTimeoutKeepAliveReceiveTimeout_4();
	StateMachine_Interface_For_OrionInterface.Provided getSend_StateMachine_Port();
	
	void reset();
	
	void start();
	
}
