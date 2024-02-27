package hu.bme.mit.gamma.architecture.transformation.errors

import java.lang.Exception
import hu.bme.mit.gamma.expression.model.NamedElement

class GammaTransformationException extends Exception {
	
	public val NamedElement element
	
	new (String message){
		super(message)
		this.element=null
	}
	new (String message, NamedElement element){
		super(message)
		
		this.element=element
	}
}