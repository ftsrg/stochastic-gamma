package hu.bme.mit.gamma.architecture

interface ArchitectureRelation {
	def ArchitectureElement getSource()
	def ArchitectureElement getTarget()
	def ArchitectureElement getContainer()
	def String getName()
}