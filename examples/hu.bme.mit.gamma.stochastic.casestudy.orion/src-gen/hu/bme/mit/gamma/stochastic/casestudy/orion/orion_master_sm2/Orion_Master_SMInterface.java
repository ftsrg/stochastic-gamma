package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm2;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public interface Orion_Master_SMInterface {

	public StateMachine_Interface_For_OrionInterface.Provided getSend_StateMachine_Port();
	public StateMachine_Interface_For_OrionInterface.Required getStateMachine_Port();
	public ConnectionStateInterface.Provided getStatus();
	
	void runCycle();
	void reset();

}
