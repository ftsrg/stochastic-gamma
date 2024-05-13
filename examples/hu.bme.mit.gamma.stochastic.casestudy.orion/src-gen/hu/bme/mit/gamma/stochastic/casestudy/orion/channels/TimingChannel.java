package hu.bme.mit.gamma.stochastic.casestudy.orion.channels;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.TimingInterface;
import java.util.List;
import java.util.LinkedList;

public class TimingChannel implements TimingChannelInterface {
	
	private TimingInterface.Provided providedPort;
	private List<TimingInterface.Required> requiredPorts = new LinkedList<TimingInterface.Required>();
	
	public TimingChannel() {}
	
	public TimingChannel(TimingInterface.Provided providedPort) {
		this.providedPort = providedPort;
	}
	
	public void registerPort(TimingInterface.Provided providedPort) {
		// Former port is forgotten
		this.providedPort = providedPort;
		// Registering the listeners
		for (TimingInterface.Required requiredPort : requiredPorts) {
			providedPort.registerListener(requiredPort);
			requiredPort.registerListener(providedPort);
		}
	}
	
	public void registerPort(TimingInterface.Required requiredPort) {
		requiredPorts.add(requiredPort);
		// Checking whether a provided port is already given
		if (providedPort != null) {
			providedPort.registerListener(requiredPort);
			requiredPort.registerListener(providedPort);
		}
	}

}
