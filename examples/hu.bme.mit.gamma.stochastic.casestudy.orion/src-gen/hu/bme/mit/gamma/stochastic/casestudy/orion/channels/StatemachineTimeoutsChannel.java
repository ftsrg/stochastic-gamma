package hu.bme.mit.gamma.stochastic.casestudy.orion.channels;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StatemachineTimeoutsInterface;
import java.util.List;
import java.util.LinkedList;

public class StatemachineTimeoutsChannel implements StatemachineTimeoutsChannelInterface {
	
	private StatemachineTimeoutsInterface.Provided providedPort;
	private List<StatemachineTimeoutsInterface.Required> requiredPorts = new LinkedList<StatemachineTimeoutsInterface.Required>();
	
	public StatemachineTimeoutsChannel() {}
	
	public StatemachineTimeoutsChannel(StatemachineTimeoutsInterface.Provided providedPort) {
		this.providedPort = providedPort;
	}
	
	public void registerPort(StatemachineTimeoutsInterface.Provided providedPort) {
		// Former port is forgotten
		this.providedPort = providedPort;
		// Registering the listeners
		for (StatemachineTimeoutsInterface.Required requiredPort : requiredPorts) {
			providedPort.registerListener(requiredPort);
			requiredPort.registerListener(providedPort);
		}
	}
	
	public void registerPort(StatemachineTimeoutsInterface.Required requiredPort) {
		requiredPorts.add(requiredPort);
		// Checking whether a provided port is already given
		if (providedPort != null) {
			providedPort.registerListener(requiredPort);
			requiredPort.registerListener(providedPort);
		}
	}

}
