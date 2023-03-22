package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

class PyroAnalysisGenerator {
	
	val INSTANCE = new PyroAnalysisGenerator
	
	def generateMain(){
		'''
		def __main__(args):
			
		'''
	}
}