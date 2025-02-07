/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
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
import hu.bme.mit.gamma.environment.analysis.Simulation
import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod

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
		var analysismethod = analysisComp.analysismethod as SimulationAnalysisMethod
		val debug=analysismethod.debug
		val jointSampling=analysismethod.jointSampling	
		'''
		if __name__ == "__main__":
			try:
				«IF debug»
					# dummy simulations for debugging
					for i in range(10):
						print(simulate())
				«ELSE»
					«IF analysisComp.analysismethod instanceof Simulation»
						«generateSimulationAnalysis(analysisComp)»
					«ELSE»
						«generateSimpleAnalysis(analysisComp)»
					«ENDIF»
				«ENDIF»
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
	
	def dataName(AnalysisAspect aspect){
		'''data_«aspect.pyroName»'''
	}
	
	
	dispatch def generateSimulationAnalysis(AnalysisComponent analysisComp){
		val simMethod = analysisComp.analysismethod as Simulation
	return '''
		# run simulation sampling
		
		print("Run simulation analysis...")
		t0=time.time()
		«FOR aspect : analysisComp.aspect»
			 «aspect.dataName» =  []
		«ENDFOR»
		for i in range(«simMethod.simulationNumber.value»):
			«FOR aspect : analysisComp.aspect SEPARATOR ", "»«aspect.pyroName»«ENDFOR»=simulate()
			«FOR aspect : analysisComp.aspect»
				«aspect.dataName».append(«aspect.pyroName»)
			«ENDFOR»
		t1=time.time()
		# visualize results
		print(f"Analysis is finished in {t1-t0} s")
		print("Results of the analysis: ")
		«FOR aspect : analysisComp.aspect»
			 print("Estimated «aspect.pyroName» = ",round(stats.mean(«aspect.dataName»),4))
		«ENDFOR»				
		print("visualize results...")
		«FOR aspect : analysisComp.aspect»
			 #visualizeMarginal(inference,«aspect.marginalName»,'«aspect.pyroName»')
		«ENDFOR»
		plt.show()
	'''
	}
	
	dispatch def generateSimpleAnalysis(AnalysisComponent analysisComp)'''
		
		# run inference algorithm
		«generateInference(analysisComp.analysismethod)»
		print("run inference algorithm...")
		t0=time.time()
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
			 #visualizeMarginal(inference,«aspect.marginalName»,'«aspect.pyroName»')
		«ENDFOR»
		plt.show()
	'''
	
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