package hu.bme.mit.gamma.architecture.transformation.builder

import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.statechart.interface_.Package
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.statechart.interface_.Component
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.architecture.transformation.ElementTransformer
import hu.bme.mit.gamma.architecture.transformation.RelationTransfomer

abstract class PrimitiveFunctionBuilder {
	
	protected val extension ElementTransformer elementTransformer
	protected val extension RelationTransfomer relationTransformer
	
	
	def String getFunctionTypeID(int inputNum, int outputNum, Interface type){
		return '''_«inputNum»_«outputNum»_«type.name»_'''
	}
	
	def Port getInPort()
	def Port getOutPort()
	def Package getPackage()
	val ArchitectureTrace trace
	val ArchitectureSubfunction subfunction
	new (ArchitectureSubfunction subfunction, ArchitectureTrace trace){
		this.trace=trace
		this.subfunction=subfunction
		
		this.elementTransformer=new ElementTransformer(trace)
		this.relationTransformer=new RelationTransfomer(trace)
	}
	def Component build()
	
	static def createBuilder(ArchitectureSubfunction subfunction,ArchitectureTrace trace){
		if (subfunction.type.name == "MUX"){
			return new MUXBuilder(subfunction,trace) as PrimitiveFunctionBuilder
		}
	}
	
	
}