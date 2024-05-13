package hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces;

import java.util.List;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;

public interface EventStreamInterface {

	interface Provided extends Listener.Required {
		
		boolean isRaisedNewEvent();
		
		void registerListener(Listener.Provided listener);
		List<Listener.Provided> getRegisteredListeners();
	}
	
	interface Required extends Listener.Provided {
		
		
		void registerListener(Listener.Required listener);
		List<Listener.Required> getRegisteredListeners();
	}
	
	interface Listener {
		
	interface Provided {
		void raiseNewEvent();
		}
		
	interface Required {
		}
		
	}

}
