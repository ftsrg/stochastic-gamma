package hu.bme.mit.gamma.architecture.transformation.traceability

import java.util.HashMap
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.SoftwareComponent
import hu.bme.mit.gamma.architecture.model.StructuralElement

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*

class AllocationTrace {

	protected val HashMap<ArchitectureFunction,ArchitectureComponent> func2comp = newHashMap
	protected val HashMap<ArchitectureSubfunction,ArchitectureSubcompnent> sfunc2scomp = newHashMap
	
	protected val HashMap<SoftwareComponent,ArchitectureComponent> sw2comp = newHashMap
	protected val HashMap<ArchitectureSubcompnent,ArchitectureSubcompnent> ssw2scomp = newHashMap
	
	new (StructuralElement element){
		for (allocation : element.functionalAllocations){
			add(allocation.source as ArchitectureSubfunction, allocation.target as ArchitectureSubcompnent)
		}
		for (allocation : element.softwareAllocations){
			add(allocation.source as ArchitectureSubcompnent, allocation.target as ArchitectureSubcompnent)
		}
	}
	
	def add(ArchitectureSubfunction subfunction,ArchitectureSubcompnent subcompnent){
		sfunc2scomp.put(subfunction,subcompnent)
	}
	
	def add(ArchitectureSubcompnent software,ArchitectureSubcompnent hardware){
		ssw2scomp.put(software,hardware)
	}
	
	def get(ArchitectureSubfunction subfunction){
		return sfunc2scomp.get(subfunction)
	}
	
	
	def get(ArchitectureSubcompnent software){
		return sfunc2scomp.get(software)
	}
	
}