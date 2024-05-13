package hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces;

import java.util.List;
import hu.bme.mit.gamma.stochastic.casestudy.orion.*;

public interface SoftwareTimerInterface {

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
