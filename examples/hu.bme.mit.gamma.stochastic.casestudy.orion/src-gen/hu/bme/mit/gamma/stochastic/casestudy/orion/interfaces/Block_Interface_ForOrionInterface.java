package hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces;

import java.util.List;
import hu.bme.mit.gamma.stochastic.casestudy.orion.*;

public interface Block_Interface_ForOrionInterface {

	interface Provided extends Listener.Required {
		
		boolean isRaisedOperation_Call_Invalid();
		boolean isRaisedOperation_Call_SendData();
		
		void registerListener(Listener.Provided listener);
		List<Listener.Provided> getRegisteredListeners();
	}
	
	interface Required extends Listener.Provided {
		
		
		void registerListener(Listener.Required listener);
		List<Listener.Required> getRegisteredListeners();
	}
	
	interface Listener {
		
	interface Provided {
		void raiseOperation_Call_Invalid();
		void raiseOperation_Call_SendData();
		}
		
	interface Required {
		}
		
	}

}
