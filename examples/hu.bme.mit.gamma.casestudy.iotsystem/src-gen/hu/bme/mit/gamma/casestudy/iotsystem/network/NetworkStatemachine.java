package hu.bme.mit.gamma.casestudy.iotsystem.network;

import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;

public class NetworkStatemachine {
	
	enum Main {__Inactive__, mainstate}
	private boolean ImageOut_newData_Out;
	private double ImageOut_newData_Out_blurred;
	private boolean ImageOut_newData_Out_car;
	private boolean ImageIn_newData_In;
	private boolean ImageLoss_newData_In;
	private double ImageIn_newData_In_blurred;
	private boolean ImageIn_newData_In_car;
	private double ImageLoss_newData_In_blurred;
	private boolean ImageLoss_newData_In_car;
	private Main main;
	
	public NetworkStatemachine() {
	}
	
	public void reset() {
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}

	public void handleBeforeReset() {
		this.main = Main.__Inactive__;
		clearOutEvents();
		clearInEvents();
	}
	public void resetVariables() {
		this.ImageIn_newData_In = false;
		this.ImageLoss_newData_In = false;
		this.ImageOut_newData_Out = false;
		this.ImageIn_newData_In_blurred = 0;
		this.ImageIn_newData_In_car = false;
		this.ImageLoss_newData_In_blurred = 0;
		this.ImageLoss_newData_In_car = false;
		this.ImageOut_newData_Out_blurred = 0;
		this.ImageOut_newData_Out_car = false;
	}

	public void resetStateConfigurations() {
		this.main = Main.mainstate;
	}

	public void raiseEntryEvents() {
	}

	public void handleAfterReset() {
		// Empty
	}
	//
	public void setImageOut_newData_Out(boolean ImageOut_newData_Out) {
		this.ImageOut_newData_Out = ImageOut_newData_Out;
	}
	
	public boolean getImageOut_newData_Out() {
		return ImageOut_newData_Out;
	}
	
	public void setImageOut_newData_Out_blurred(double ImageOut_newData_Out_blurred) {
		this.ImageOut_newData_Out_blurred = ImageOut_newData_Out_blurred;
	}
	
	public double getImageOut_newData_Out_blurred() {
		return ImageOut_newData_Out_blurred;
	}
	
	public void setImageOut_newData_Out_car(boolean ImageOut_newData_Out_car) {
		this.ImageOut_newData_Out_car = ImageOut_newData_Out_car;
	}
	
	public boolean getImageOut_newData_Out_car() {
		return ImageOut_newData_Out_car;
	}
	
	public void setImageIn_newData_In(boolean ImageIn_newData_In) {
		this.ImageIn_newData_In = ImageIn_newData_In;
	}
	
	public boolean getImageIn_newData_In() {
		return ImageIn_newData_In;
	}
	
	public void setImageLoss_newData_In(boolean ImageLoss_newData_In) {
		this.ImageLoss_newData_In = ImageLoss_newData_In;
	}
	
	public boolean getImageLoss_newData_In() {
		return ImageLoss_newData_In;
	}
	
	public void setImageIn_newData_In_blurred(double ImageIn_newData_In_blurred) {
		this.ImageIn_newData_In_blurred = ImageIn_newData_In_blurred;
	}
	
	public double getImageIn_newData_In_blurred() {
		return ImageIn_newData_In_blurred;
	}
	
	public void setImageIn_newData_In_car(boolean ImageIn_newData_In_car) {
		this.ImageIn_newData_In_car = ImageIn_newData_In_car;
	}
	
	public boolean getImageIn_newData_In_car() {
		return ImageIn_newData_In_car;
	}
	
	public void setImageLoss_newData_In_blurred(double ImageLoss_newData_In_blurred) {
		this.ImageLoss_newData_In_blurred = ImageLoss_newData_In_blurred;
	}
	
	public double getImageLoss_newData_In_blurred() {
		return ImageLoss_newData_In_blurred;
	}
	
	public void setImageLoss_newData_In_car(boolean ImageLoss_newData_In_car) {
		this.ImageLoss_newData_In_car = ImageLoss_newData_In_car;
	}
	
	public boolean getImageLoss_newData_In_car() {
		return ImageLoss_newData_In_car;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public Main getMain() {
		return main;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if ((((this.main == Main.mainstate)) && (this.ImageIn_newData_In))) {
			this.ImageOut_newData_Out_blurred = this.ImageIn_newData_In_blurred;
			this.ImageOut_newData_Out_car = this.ImageIn_newData_In_car;
			this.ImageOut_newData_Out = true;
			this.main = Main.mainstate;
		}
	}
	
	private void clearOutEvents() {
		ImageOut_newData_Out = false;
	}
	
	private void clearInEvents() {
		ImageIn_newData_In = false;
		ImageLoss_newData_In = false;
	}
	
	@Override
	public String toString() {
		return
			"ImageOut_newData_Out = " + ImageOut_newData_Out + System.lineSeparator() +
			"ImageOut_newData_Out_blurred = " + ImageOut_newData_Out_blurred + System.lineSeparator() +
			"ImageOut_newData_Out_car = " + ImageOut_newData_Out_car + System.lineSeparator() +
			"ImageIn_newData_In = " + ImageIn_newData_In + System.lineSeparator() +
			"ImageLoss_newData_In = " + ImageLoss_newData_In + System.lineSeparator() +
			"ImageIn_newData_In_blurred = " + ImageIn_newData_In_blurred + System.lineSeparator() +
			"ImageIn_newData_In_car = " + ImageIn_newData_In_car + System.lineSeparator() +
			"ImageLoss_newData_In_blurred = " + ImageLoss_newData_In_blurred + System.lineSeparator() +
			"ImageLoss_newData_In_car = " + ImageLoss_newData_In_car + System.lineSeparator() +
			"main = " + main
		;
	}
	
}
