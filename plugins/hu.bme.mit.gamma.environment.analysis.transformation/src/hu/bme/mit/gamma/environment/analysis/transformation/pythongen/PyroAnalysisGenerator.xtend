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
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator

class PyroAnalysisGenerator {
	
	
	def generateMarginalVisualisation(){
		'''
		def visualizeMarginal(inference, marginal, name):
			sample_num=10000
			bin_num=100
			marginal_samples = torch.stack([torch.abs(marginal()) for _ in range(sample_num)])
			fig, a = plt.subplots()
			a.set_title( "Empirical marginal "+name+" (ESS:"+str(round(inference.get_ESS().item(),2))+", avg:"+str(round(marginal.mean.item(),2))+", stddev:"+str(round(marginal.variance.sqrt().item(),2))+")" )
			a.hist(marginal_samples.numpy(), color='b',bins=bin_num, density=1, label="Marginal of "+name)
			a.set_ylabel("Estimated density")
			a.set_xlabel("Value of "+name)
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
					t0=time.time()
					# run importance sampling
					«generateInference(analysisComp.analysismethod)»
					print("run inference algorithm...")
					inference.run()
					«FOR aspect : analysisComp.aspect»
						 «aspect.marginalName» = pyro.infer.EmpiricalMarginal(inference, "«aspect.pyroName»")
					«ENDFOR»
					t1=time.time()
					# visualize results
					print(f"Analysis is finished in {t1-t0} s")
					print("Results of the analysis: ")
					«FOR aspect : analysisComp.aspect»
						 print("Estimated «aspect.pyroName» = ",round(«aspect.marginalName».mean.item(),4))
					«ENDFOR»				
					print("visualize results...")
					«FOR aspect : analysisComp.aspect»
						 visualizeMarginal(inference,«aspect.marginalName»,'«aspect.pyroName»')
					«ENDFOR»
					plt.show()
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
			print ("analysis is finished successfully")
		'''
	}
	
	def marginalName(AnalysisAspect aspect){
		'''empirical_marginal_«aspect.pyroName»'''
	}
	
	
	dispatch def generateInference(ImportanceSampling analysisMethod){
		var expEval=ExpressionEvaluator.INSTANCE;
		'''inference=pyro.infer.Importance(model=simulate, num_samples=«expEval.evaluateInteger(analysisMethod.simulationNumber)»)'''
	}
	dispatch def generateInference(MCMC analysisMethod){
		var expEval=ExpressionEvaluator.INSTANCE;
		'''inference=pyro.infer.MCMC(kernel=«generateMCMCKernel(analysisMethod.kernel)», num_samples = «expEval.evaluateInteger(analysisMethod.simulationNumber)», warmup_steps = «expEval.evaluateInteger(analysisMethod.warmupStepNum)»)'''
	}
	dispatch def generateInference(AnalysisMethod analysisMethod){
		throw new UnsupportedOperationException("Only importance sampling and MCMC supported yet.")
	}
	
	dispatch def generateMCMCKernel(NUTS kernel)'''pyro.infer.NUTS(model=simulate)'''
	dispatch def generateMCMCKernel(HMC kernel)'''pyro.infer.HMC(model=simulate)'''
	
}