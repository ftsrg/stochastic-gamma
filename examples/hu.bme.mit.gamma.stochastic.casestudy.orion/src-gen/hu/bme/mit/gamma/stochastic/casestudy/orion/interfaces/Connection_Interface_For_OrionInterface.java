package hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces;

import java.util.List;
import hu.bme.mit.gamma.stochastic.casestudy.orion.*;

public interface Connection_Interface_For_OrionInterface {

	interface Provided extends Listener.Required {
		
		boolean isRaisedOperation_Call_Disconn();
		boolean isRaisedOperation_Call_Connect();
		
		void registerListener(Listener.Provided listener);
		List<Listener.Provided> getRegisteredListeners();
	}
	
	interface Required extends Listener.Provided {
		
		
		void registerListener(Listener.Required listener);
		List<Listener.Required> getRegisteredListeners();
	}
	
	interface Listener {
		
	interface Provided {
		void raiseOperation_Call_Disconn();
		void raiseOperation_Call_Connect();
		}
		
	interface Required {
		}
		
	}

}
