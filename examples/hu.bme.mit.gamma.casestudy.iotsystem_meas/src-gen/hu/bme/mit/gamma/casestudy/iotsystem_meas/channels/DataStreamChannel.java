package hu.bme.mit.gamma.casestudy.iotsystem_meas.channels;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.DataStreamInterface;
import java.util.List;
import java.util.LinkedList;

public class DataStreamChannel implements DataStreamChannelInterface {
	
	private DataStreamInterface.Provided providedPort;
	private List<DataStreamInterface.Required> requiredPorts = new LinkedList<DataStreamInterface.Required>();
	
	public DataStreamChannel() {}
	
	public DataStreamChannel(DataStreamInterface.Provided providedPort) {
		this.providedPort = providedPort;
	}
	
	public void registerPort(DataStreamInterface.Provided providedPort) {
		// Former port is forgotten
		this.providedPort = providedPort;
		// Registering the listeners
		for (DataStreamInterface.Required requiredPort : requiredPorts) {
			providedPort.registerListener(requiredPort);
			requiredPort.registerListener(providedPort);
		}
	}
	
	public void registerPort(DataStreamInterface.Required requiredPort) {
		requiredPorts.add(requiredPort);
		// Checking whether a provided port is already given
		if (providedPort != null) {
			providedPort.registerListener(requiredPort);
			requiredPort.registerListener(providedPort);
		}
	}

}
