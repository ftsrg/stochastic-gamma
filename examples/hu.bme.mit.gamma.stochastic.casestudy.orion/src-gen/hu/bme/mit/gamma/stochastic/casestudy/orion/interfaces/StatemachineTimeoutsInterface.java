package hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import java.util.List;

public interface StatemachineTimeoutsInterface {
	
	interface Provided extends Listener.Required {
		
		public boolean isRaisedKeepAliveTimeout();
		public boolean isRaisedConnectedTimeout();
		public boolean isRaisedConnectingTimeout();
		public boolean isRaisedClosedTimeout();
		
		void registerListener(Listener.Provided listener);
		List<Listener.Provided> getRegisteredListeners();
	}
	
	interface Required extends Listener.Provided {
		
		
		void registerListener(Listener.Required listener);
		List<Listener.Required> getRegisteredListeners();
	}
	
	interface Listener {
		
		interface Provided  {
			void raiseKeepAliveTimeout();
			void raiseConnectedTimeout();
			void raiseConnectingTimeout();
			void raiseClosedTimeout();
		}
		
		interface Required  {
		}
		
	}
}
