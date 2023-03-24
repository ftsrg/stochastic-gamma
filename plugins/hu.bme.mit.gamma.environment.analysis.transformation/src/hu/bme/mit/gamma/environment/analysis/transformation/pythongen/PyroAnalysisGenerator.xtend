package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import hu.bme.mit.gamma.environment.analysis.AnalysisAspect
import hu.bme.mit.gamma.environment.analysis.ImportanceSampling
import hu.bme.mit.gamma.environment.analysis.MCMC
import hu.bme.mit.gamma.environment.analysis.AnalysisMethod
import hu.bme.mit.gamma.environment.analysis.NUTS
import hu.bme.mit.gamma.environment.analysis.HMC
import hu.bme.mit.gamma.environment.analysis.Frequency
import hu.bme.mit.gamma.environment.analysis.Probability
import hu.bme.mit.gamma.environment.analysis.MeanTime
import hu.bme.mit.gamma.environment.analysis.MeanParameter
import hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents

import static extension hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility.*

class PyroAnalysisGenerator {
	
	
	def generateMarginalVisualisation(){
		'''
		def visualizeMarginal(inference, marginal, name):
			sample_num=20000
			bin_num=100
			marginal_samples = torch.stack([torch.abs(marginal()) for _ in range(sample_num)])
			fig, a = plt.subplots()
			a.set_title( "Empirical marginal "+name+" (ESS:"+str(round(inference.get_ESS().item(),2))+", avg:"+str(round(marginal.mean.item(),2))+", stddev:"+str(round(marginal.variance.sqrt().item(),2))+")" )
			a.hist(marginal_samples.numpy(), color='b',bins=bin_num, density=1, label="Marginal of "+name)
			plt.ylabel("Estimated density")
			plt.xlabel("Value of "+name)
			plt.show()
		'''
	}
	
	def generateMain(AnalysisComponent analysisComp){
		'''
		if __name__ == "__main__":
			try:
				# dummy simulations for debugging
				if DEBUG:
					for i in range(10):
						print(simulate())
				else:
					# run importance sampling
					«generateInference(analysisComp.analysismethod)»
					print("run inference algorithm...")
					inference.run()
					«FOR aspect : analysisComp.aspect»
						 «aspect.marginalName» = pyro.infer.EmpiricalMarginal(inference, "«aspect.pyroName»")
					«ENDFOR»
					# visualize results
					print("Results of the analysis: ")
					«FOR aspect : analysisComp.aspect»
						 print("Estimated «aspect.pyroName» = ",round(«aspect.marginalName».mean.item(),2))
					«ENDFOR»				
					print("visualize results...")
					«FOR aspect : analysisComp.aspect»
						 visualizeMarginal(inference,«aspect.marginalName»,'«aspect.pyroName»')
					«ENDFOR»
			except java.lang.RuntimeException as ex:
				print("Caught Java runtime exception : ", str(ex))
				print(ex.stacktrace())
			except jpype.JException as ex:
				print("Caught Jpype exception : ", str(ex))
				print(ex.stacktrace())
			except Exception as err:
				print("Caught Python exception : ", err)
				traceback.print_exc()
			finally:
				print("shutting down JVM...")
				shutdownJVM()
			print ("anylsis is finished successfully")
		'''
	}
	
	def marginalName(AnalysisAspect aspect){
		'''empirical_marginal_«aspect.pyroName»'''
	}
	
	
	dispatch def generateInference(ImportanceSampling analysisMethod){
		'''inference=pyro.infer.Importance(model=simulate, num_samples=«analysisMethod.simulationNumber»)'''
	}
	dispatch def generateInference(MCMC analysisMethod){
		'''inference=pyro.infer.MCMC(kernel=«generateMCMCKernel(analysisMethod.kernel)», num_samples = «analysisMethod.simulationNumber», warmup_steps = «analysisMethod.warmupStepNum»)'''
	}
	dispatch def generateInference(AnalysisMethod analysisMethod){
		throw new UnsupportedOperationException("Only importance sampling and MCMC supported yet.")
	}
	
	dispatch def generateMCMCKernel(NUTS kernel)'''kernel=pyro.infer.NUTS(model=simulate)'''
	dispatch def generateMCMCKernel(HMC kernel)'''kernel=pyro.infer.HMC(model=simulate)'''
	
}