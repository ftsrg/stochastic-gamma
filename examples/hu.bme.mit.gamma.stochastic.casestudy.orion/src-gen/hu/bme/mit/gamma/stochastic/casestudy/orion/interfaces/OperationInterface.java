package hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import java.util.List;

public interface OperationInterface {
	
	interface Provided extends Listener.Required {
		
		public boolean isRaisedRecover();
		public boolean isRaisedFail();
		
		void registerListener(Listener.Provided listener);
		List<Listener.Provided> getRegisteredListeners();
	}
	
	interface Required extends Listener.Provided {
		
		
		void registerListener(Listener.Required listener);
		List<Listener.Required> getRegisteredListeners();
	}
	
	interface Listener {
		
		interface Provided  {
			void raiseRecover();
			void raiseFail();
		}
		
		interface Required  {
		}
		
	}
}
