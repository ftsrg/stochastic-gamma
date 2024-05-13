package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm2;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public interface Orion_Slave_SMInterface {

	public StateMachine_Interface_For_OrionInterface.Provided getSend_StateMachine_Port();
	public StateMachine_Interface_For_OrionInterface.Required getStateMachine_Port();
	public ConnectionStateInterface.Provided getStatus();
	
	void runCycle();
	void reset();

}
