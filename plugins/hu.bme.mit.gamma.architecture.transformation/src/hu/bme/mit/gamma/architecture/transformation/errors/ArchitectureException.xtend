package hu.bme.mit.gamma.architecture.transformation.errors

import java.lang.Exception
import hu.bme.mit.gamma.architecture.model.ArchitectureElement

class ArchitectureException extends Exception {
	
	public val ArchitectureElement element
	
	new (String message){
		super(message)
		this.element=null
	}
	new (String message, ArchitectureElement element){
		super(message)
		this.element=element
	}
}