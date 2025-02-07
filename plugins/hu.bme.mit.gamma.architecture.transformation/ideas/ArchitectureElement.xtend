package hu.bme.mit.gamma.architecture

import java.util.List

interface ArchitectureElement {
	def String getName()
	def ArchitectureElement getType()
	def ArchitectureElement getContainer()
	def List<ArchitectureElement> getPorts()
	def List<ArchitectureElement> getParts()
}