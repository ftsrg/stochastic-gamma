package hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces;

import java.util.List;
import hu.bme.mit.gamma.stochastic.casestudy.orion.*;

public interface StateMachine_Interface_For_OrionInterface {

	interface Provided extends Listener.Required {
		
		boolean isRaisedOrionKeepAlive();
		boolean isRaisedOrionDisconn();
		boolean isRaisedOrionDisconnCause();
		boolean isRaisedOrionAppData();
		boolean isRaisedOrionConnConf();
		boolean isRaisedOrionConnResp();
		boolean isRaisedOrionConnReq();
		
		void registerListener(Listener.Provided listener);
		List<Listener.Provided> getRegisteredListeners();
	}
	
	interface Required extends Listener.Provided {
		
		
		void registerListener(Listener.Required listener);
		List<Listener.Required> getRegisteredListeners();
	}
	
	interface Listener {
		
	interface Provided {
		void raiseOrionKeepAlive();
		void raiseOrionDisconn();
		void raiseOrionDisconnCause();
		void raiseOrionAppData();
		void raiseOrionConnConf();
		void raiseOrionConnResp();
		void raiseOrionConnReq();
		}
		
	interface Required {
		}
		
	}

}
