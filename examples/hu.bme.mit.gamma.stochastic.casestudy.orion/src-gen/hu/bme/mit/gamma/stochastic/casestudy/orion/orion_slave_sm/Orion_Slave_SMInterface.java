package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public interface Orion_Slave_SMInterface {

	public Block_Interface_ForOrionInterface.Required getBlock_Port();
	public Connection_Interface_For_OrionInterface.Required getConnection_Port();
	public StateMachine_Interface_For_OrionInterface.Required getStateMachine_Port();
	public StateMachine_Interface_For_OrionInterface.Provided getSend_StateMachine_Port();
	public StateMachine_Interface_For_OrionInterface.Provided getProcess_StateMachine_Port();
	public StateMachine_Interface_For_OrionInterface.Provided getHandle_StateMachine_Port();
	public ConnectionStateInterface.Provided getStatus();
	
	void runCycle();
	void reset();

}
