package hu.bme.mit.gamma.casestudy.iotsystem.interfaces;

import java.util.List;
import hu.bme.mit.gamma.casestudy.iotsystem.*;

public interface DataStreamInterface {

	interface Provided extends Listener.Required {
		
		boolean isRaisedNewData();
		double getBlurred();
		boolean getCar();
		
		void registerListener(Listener.Provided listener);
		List<Listener.Provided> getRegisteredListeners();
	}
	
	interface Required extends Listener.Provided {
		
		
		void registerListener(Listener.Required listener);
		List<Listener.Required> getRegisteredListeners();
	}
	
	interface Listener {
		
	interface Provided {
		void raiseNewData(double blurred, boolean car);
		}
		
	interface Required {
		}
		
	}

}
