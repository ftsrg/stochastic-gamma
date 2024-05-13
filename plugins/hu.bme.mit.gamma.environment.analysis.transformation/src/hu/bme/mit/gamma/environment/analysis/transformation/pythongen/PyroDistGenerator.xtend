package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

import hu.bme.mit.gamma.environment.stochastic.stochastic.BrownianKernel
import hu.bme.mit.gamma.environment.stochastic.stochastic.PeriodicKernel
import hu.bme.mit.gamma.environment.stochastic.stochastic.LinearKernel
import hu.bme.mit.gamma.environment.stochastic.stochastic.RBFKernel
import hu.bme.mit.gamma.environment.stochastic.stochastic.SumKernel
import hu.bme.mit.gamma.environment.stochastic.stochastic.PythonSimulation
import hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB
import hu.bme.mit.gamma.environment.stochastic.stochastic.DiracProcess
import hu.bme.mit.gamma.environment.stochastic.stochastic.FittedGaussianProcess
import hu.bme.mit.gamma.environment.stochastic.stochastic.BernoulliRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.ExponentialRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.WeibullRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.NormalRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.ContinouosRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.DiscreteRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.FittedNormalRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.FittedExponentialRandomVariable
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import hu.bme.mit.gamma.environment.model.StochasticRule
import hu.bme.mit.gamma.environment.model.PortFilter
import hu.bme.mit.gamma.environment.stochastic.stochastic.CategoricalProbabaility
import hu.bme.mit.gamma.environment.stochastic.stochastic.UniformRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.LogNormalRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.ParetoRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.BetaRandomVariable
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility
import hu.bme.mit.gamma.environment.analysis.transformation.util.EnvironmentConnections
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticProcess
import hu.bme.mit.gamma.environment.stochastic.stochastic.RandomVariable
import java.math.BigInteger
import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression
import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod

class PyroDistGenerator {	
	
	Integer distcntr=0
	final ExpressionEvaluator expEval=ExpressionEvaluator.INSTANCE;
	//final String packageName
	
	public static val random_vars= <String>newLinkedList
	

	
		
	def generateRandomVariableClass(IntegerLiteralExpression N){
		'''
		class RandomVariable():
			plate=pyro.plate("random_variable")
			def __init__(self,dist,name,N=«IF N === null»1«ELSE»«N.value»«ENDIF»):
				self.dist=dist
				self.name=name
				self.event_cntr=N-1
				self.meta_cntr=-1
				self.N=N
			def sampleb(self):
				return pyro.sample(self.name+"_sample_"+str(self.meta_cntr),self.dist.expand([self.N]))
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				if self.N>0:
					if self.event_cntr==self.N:
						self.event_cntr=0
						self.meta_cntr=self.meta_cntr+1
						with RandomVariable.plate:
							self.samples=self.sampleb()
					return self.samples[self.event_cntr].item()
				else:
					with RandomVariable.plate:
						return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
			def reset(self):
				self.event_cntr=-1 # self.N-1
				self.meta_cntr=0#-1
				with RandomVariable.plate:
					if self.N>0:
						self.samples=self.sampleb()
		'''
	}
	
			
	def generateJointRandomVariableClass(IntegerLiteralExpression N){
		'''
		class JointDistribution(TorchDistribution):
		
		    arg_constraints = {}  # nothing can be constrained
		
		    def __init__(self, dists, validate_args=None):
		        for dist in dists:
		            if dist.event_shape != dists[0].event_shape:
		                raise ValueError("components event_shape disagree: {} vs {}".format(
		                    dist.event_shape, dists[0].event_shape))
		        batch_shape = broadcast_shape(dists)
		        self.dists = dists
		        self.dnum = len(dists)
		        super().__init__(batch_shape, dists[0].event_shape, validate_args)
		
		    @property
		    def has_rsample(self):
		        return True

		    def expand(self, batch_shape):
		        new_dists=[]
		        for dist in self.dists:
		            new_dists.append(dist.expand(batch_shape))
		        return JointDistribution(new_dists)
		
		    def sample(self, sample_shape=torch.Size()):
		        samples=[]
		        for dist in self.dists:
		            samples.append(dist.sample(sample_shape))
		        return torch.stack(samples)
		
		    def rsample(self, sample_shape=torch.Size()):
		        samples=[]
		        for dist in self.dists:
		            samples.append(dist.sample(sample_shape))
		        return torch.stack(samples)
		
		    def log_prob(self, value):
		        log_prob=torch.tensor(0.0)
		        for i in range(len(self.dists)):
		            log_prob=log_prob+self.dists[i].log_prob(value[i]).sum()
		        return log_prob
		
		    def mean(self):
		        means=[]
		        for dist in self.dists:
		            means.append(dist.mean)
		        return torch.stack(means)
		
		    def variance(self):
		        variances=[]
		        for dist in self.dists:
		            variances.append(dist.variance)
		        return torch.stack(variances)
		
		
		class RandomVariable():
		
		    plate=pyro.plate("random_variable")
		    dists=[]
		    samples=[]
		    dist=None
		    insts=[]
		
		    def ginit():
		        RandomVariable.dist=JointDistribution(RandomVariable.dists)
		
		    def greset():
		        RandomVariable.cntr=-1
		        RandomVariable.gsample()
		
		    def gsample():
		        RandomVariable.cntr=RandomVariable.cntr+1
		        RandomVariable.samples=pyro.sample("samples_"+str(RandomVariable.cntr),RandomVariable.dist)
		        for dist in RandomVariable.insts:
		            dist.event_cntr=-1
		
		    def __init__(self,dist,name,N=«IF N === null»1«ELSE»«N.value»«ENDIF»):
		        self.dist=dist
		        self.name=name
		        self.event_cntr=-1
		        self.N=N
		        self.i=len(RandomVariable.dists)
		        RandomVariable.dists.append(dist.expand([N]))
		        RandomVariable.insts.append(self)
		    
		    def calc(self,event=0,time=0):
		        self.event_cntr=self.event_cntr+1
		        if self.event_cntr==self.N:
		            RandomVariable.gsample()
		        return RandomVariable.samples[self.i][int(self.event_cntr)].item()
		'''
	}
	
	def generateSimpleRandomVariableClass(){
		'''
		class RandomVariable():
			def __init__(self,dist,name):
				self.dist=dist
				self.name=name
				self.event_cntr=0
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
			def reset(self):
				self.event_cntr=0
		'''
	}
	
	def generateContinuousRandomVariableClass(){
		'''
		class ContinuousRandomVariable():
			def __init__(self,dist,name,N=30):
				self.dist=dist
				self.name=name
				self.event_cntr=N-1
				self.meta_cntr=-1
				self.N=N
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				if self.N>0:
					if self.event_cntr==self.N:
						self.event_cntr=0
						self.meta_cntr=self.meta_cntr+1
						self.samples=pyro.sample(self.name+"_sample_"+str(self.meta_cntr),self.dist.expand([self.N]))
					return self.samples[self.event_cntr].item()
				else:
					return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
			def reset(self):
				self.event_cntr=self.N-1
				self.meta_cntr=-1
		'''
	}
	
	def generateZerotimeRandomVariableClass(){
		'''
		class ZerotimeRandomVariable():
			def __init__(self,dist,name,N=1):
				self.dist=dist
				self.name=name
				self.event_cntr=N-1
				self.meta_cntr=-1
				self.N=N
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()-1.0
			def reset(self):
				self.event_cntr=self.N-1
				self.meta_cntr=-1
		'''
	}
	
	def generateContinuousRandomVariableClass_old(){
		'''
		class ContinuousRandomVariable():
			def __init__(self,dist,name):
				self.dist=dist
				self.name=name
				self.event_cntr=0
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
			def reset(self):
				self.event_cntr=0
		'''
	} 
	
	
	
	def generateDRandomVariableClass(){
		'''
		class ContinuousRandomVariable():
			def __init__(self,dist,name,N=1):
				self.dist=dist
				self.name=name
				self.event_cntr=N-1
				self.meta_cntr=-1
				self.N=N
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				if self.N>0:
					if self.event_cntr==self.N:
						self.event_cntr=0
						self.meta_cntr=self.meta_cntr+1
						self.samples=pyro.sample(self.name+"_sample_"+str(self.meta_cntr),self.dist.expand([self.N]))
					return self.samples[self.event_cntr].item()
				else:
					return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
			def reset(self):
				self.event_cntr=self.N-1
				self.meta_cntr=-1
		'''
	}
	
	def generateDiscreteRandomVariableClass(){
		'''
		class DiscreteRandomVariable():
			def __init__(self,dist,name):
				self.dist=dist
				self.name=name
				self.event_cntr=0
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()-1.0
		'''
	}
	
	def generateGaussProcessClass(){
		'''
		class GaussProcess():
			def __init__(self, dataset, kernel, lr, name):
				self.name=name
				self.event_cntr=0
				points=dataset.points
				x = []
				t = []
				y = []
				i = 0
				t0 = 0
				for p in points:
					if i == 0:
						t0 = datetime.datetime.strptime(p.pop("time"), '%Y-%m-%dT%H:%M:%S.%fZ')
						t.append(t0)
					else:
						t.append(datetime.datetime.strptime(p.pop("time"), '%Y-%m-%dT%H:%M:%S.%fZ'))
					t[i] = t[i] - t0
					x.append(t[i].total_seconds())
					yi = list(p.values())
					if len(yi) == 1:
						y.append(yi[0])
					else:
						y.append(yi)
					i = i + 1
				
				x = torch.tensor(x)
				y = torch.tensor(y)
				X = x
				
				# initialize the inducing inputs
				Xu = torch.arange(1.) / 6.0
				
				#kernel = gp.kernels.Sum(gp.kernels.Periodic(input_dim=1), gp.kernels.Brownian(input_dim=1))
				# we increase the jitter for better numerical stability
				sgpr = gp.models.SparseGPRegression(X=X, y=y, kernel=kernel, Xu=Xu, jitter=1.0e-5)
				
				# the way we setup inference is similar to above
				optimizer = torch.optim.Adam(sgpr.parameters(), lr=lr)
				loss_fn = pyro.infer.Trace_ELBO().differentiable_loss
				losses = []
				num_steps = 2000
				for i in range(num_steps):
					optimizer.zero_grad()
					loss = loss_fn(sgpr.model, sgpr.guide)
					if i % 20 == 0:
						print("Step: ", i, " Loss: ", loss)
					loss.backward()
					optimizer.step()
					losses.append(loss.item())
				self.gp=sgpr
				
			def calc(self,event,time):
				self.event_cntr=self.event_cntr+1
				mu,sig=self.gp.forward(torch.tensor([time]), full_cov=False, noiseless=False)
				return pyro.sample(self.name+"_sample_GP_"+str(self.event_cntr),pyro.distributions.Normal(mu,sig)).item()
		'''
	}
	
	
	def generateFittedExponentialRandomVariableClass(){
		'''
		class FittedExponentialRandomVariable():
			def model(self,data):
				rate = pyro.param("alpha_q", torch.tensor(1.0),
					constraint=constraints.positive)
				for i in range(len(data)):
					# observe datapoint i using the bernoulli likelihood
					pyro.sample("obs_{}".format(i), dist.Exponential(rate), obs=(torch.tensor(data[i])))
			
			def guide(self,data):
				pass
							
			def __init__(self,name,source,lr):
				self.name=name
				self.event_cntr=0
				#preprocess data
				data=[float(p["data"]) for p in list(source.points)]
				md=min(data)
				data=[d-md for d in data]
				# setup the optimizer
				adam_params = {"lr": lr, "betas": (0.99, 0.999)}
				optimizer = Adam(adam_params)
				n_steps=2000
				# setup the inference algorithm
				svi = SVI(self.model, self.guide, optimizer, loss=Trace_ELBO())
				
				losses=list()
				ploss=100000000.0
				# do gradient steps
				for step in range(n_steps):
					loss=svi.step(data)
					losses.append(loss)
					if step % 10 == 0:
						print("loss=",loss)
					if ploss-loss<0.001:
						break
					ploss=loss
				
				# grab the learned variational parameters
				m = pyro.param("alpha_q").item()
				print(m)
				
				xx = list(np.arange(0, 4/m, 0.1/m))
				yy = []
				d=dist.Exponential(m)
				self.dist=d
				
				for x in xx:
					y=float(exp(dist.Exponential(m).log_prob(torch.tensor(x))))
					yy.append(y)
				fig1, a1 = plt.subplots()
				fig2, a2 = plt.subplots()
				
				a1.plot(losses)
				a1.set_ylabel('loss')
				a1.set_xlabel('step')
				a1.set_title("model fitting process")
				a2.hist(data,bins=10)
				a2.plot(xx,yy)
				a2.set_ylabel('frequency')
				a2.set_xlabel('delay (s)')
				a2.set_title("model fitting results")
				
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
		'''
	}
	
	
	def generateFittedNormalRandomVariableClass(){
		'''
		class FittedNormalRandomVariable():
			def model(self,data):
				alpha_q = pyro.param("alpha_q", torch.tensor(1.0),
					constraint=constraints.positive)
				beta_q = pyro.param("beta_q", torch.tensor(1.0),
						constraint=constraints.positive)
				for i in range(len(data)):
					# observe datapoint i using the bernoulli likelihood
					pyro.sample("obs_{}".format(i), dist.Normal(alpha_q,beta_q), obs=torch.tensor(data[i]))
			
			def guide(self,data):
				pass
			
			def __init__(self,name,source,lr):
				self.name=name
				self.event_cntr=0
				data=[float(p["data"]) for p in list(source.points)]
				# setup the optimizer
				adam_params = {"lr": lr, "betas": (0.99, 0.999)}
				optimizer = Adam(adam_params)
				n_steps=2000
				# setup the inference algorithm
				svi = SVI(self.model, self.guide, optimizer, loss=Trace_ELBO())
				
				losses=list()
				ploss=100000000.0
				# do gradient steps
				for step in range(n_steps):
					loss=svi.step(data)
					losses.append(loss)
					if step % 10 == 0:
						print("loss=",loss)
					if ploss-loss<0.001:
						break
					ploss=loss
				
				# grab the learned variational parameters
				m = pyro.param("alpha_q").item()
				s = pyro.param("beta_q").item()
				print(m)
				print(s)
				
				xx = list(np.arange(m-2*s, m+2*s, s/10))
				yy = []
				d=dist.Normal(loc=torch.tensor(m),scale=torch.tensor(s))
				self.dist=d
				
				for x in xx:
					y=float(exp(dist.Normal(loc=torch.tensor(m),scale=torch.tensor(s)).log_prob(torch.tensor(x))))
					yy.append(y)
				fig1, a1 = plt.subplots()
				fig2, a2 = plt.subplots()
				
				a1.plot(losses)
				a1.set_ylabel('loss')
				a1.set_xlabel('step')
				a1.set_title("model fitting process")
				a2.hist(data,bins=10)
				a2.plot(xx,yy)
				a2.set_ylabel('frequency')
				a2.set_xlabel('delay (s)')
				a2.set_title("model fitting results")
				
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
		'''
	}
	
	
	dispatch def generateStochasticModel(FittedExponentialRandomVariable variable,String name){
		'''FittedExponentialRandomVariable(
			source=«generateDatasetInstance(variable.source)»,
			name="FittedExpVariable«name»«(distcntr++).toString»",
			lr=«variable.lr.toString»
		)'''
	}
	dispatch def generateStochasticModel(FittedNormalRandomVariable variable,String name){
		'''FittedNormalRandomVariable(
			source=«generateDatasetInstance(variable.source)»,
			name="FittedNormVariable«name»«(distcntr++).toString»",
			lr=«variable.lr.toString»
		)'''
	}
	
	dispatch def generateZerotimeStochasticModel(DiscreteRandomVariable variable,String name){
		val str1='''ZerotimeRandomVariable(«generateDitribution(variable)»,"ZerotimeRandomVarriable«name»«(distcntr++).toString»")'''
		random_vars.add(str1)
		return str1
	}
	
	dispatch def generateStochasticModel(ContinouosRandomVariable variable,String name){
		val str1='''RandomVariable(«generateDitribution(variable)»,"ContRandomVarriable«name»«(distcntr++).toString»")'''
		random_vars.add(str1)
		return str1
	}
	
	dispatch def generateStochasticModel(DiscreteRandomVariable variable,String name){
		val str1='''RandomVariable(«generateDitribution(variable)»,"DiscRandomVarriable«name»«(distcntr++).toString»")'''
		random_vars.add(str1)
		return str1
	}
	
	dispatch def generateStochasticModel(FittedGaussianProcess variable,String name){
		'''
		GaussProcess(
			dataset=«generateDatasetInstance(variable.source)»,
			kernel=«generateKernel(variable.kernel)»,
			lr=«Double.toString(variable.lr)»,
			name="FittedGaussianProcess«name»«(distcntr++).toString»"
		)
		'''
	}
	
	
	static dispatch def generateDitribution(NormalRandomVariable dist){
		'''pyro.distributions.Normal(loc=«TransformationUtility.evaluateMixedExpression((dist.mean))»,scale=«TransformationUtility.evaluateMixedExpression((dist.scale))»)'''
	}
	
	static dispatch def generateDitribution(WeibullRandomVariable dist){
		'''pyro.distributions.Weibull(concentration=«TransformationUtility.evaluateMixedExpression((dist.shape))»,shape=«TransformationUtility.evaluateMixedExpression((dist.scale))»)'''
	}
	static dispatch def generateDitribution(GammaRandomVariable dist){
		'''pyro.distributions.Gamma(concentration=«TransformationUtility.evaluateMixedExpression((dist.shape))»,rate=«TransformationUtility.evaluateMixedExpression((dist.scale))»)'''
	}
	static dispatch def generateDitribution(UniformRandomVariable dist){
		'''pyro.distributions.Uniform(low=«TransformationUtility.evaluateMixedExpression((dist.lowerBound))»,high=«TransformationUtility.evaluateMixedExpression((dist.upperBound))»)'''
	}
	static dispatch def generateDitribution(LogNormalRandomVariable dist){
		'''pyro.distributions.LogNormal(loc=«TransformationUtility.evaluateMixedExpression((dist.mean))»,scale=«TransformationUtility.evaluateMixedExpression((dist.scale))»)'''
	}
	static dispatch def generateDitribution(ParetoRandomVariable dist){
		'''pyro.distributions.Pareto(alpha=«TransformationUtility.evaluateMixedExpression((dist.alpha))»,scale=«TransformationUtility.evaluateMixedExpression((dist.scale))»)'''
	}
	static dispatch def generateDitribution(BetaRandomVariable dist){
		'''pyro.distributions.Beta(concentration1=«TransformationUtility.evaluateMixedExpression((dist.apha))»,concentration0=«TransformationUtility.evaluateMixedExpression((dist.beta))»)'''
	}
	
	static dispatch def generateDitribution(ExponentialRandomVariable dist){
		'''pyro.distributions.Exponential(«TransformationUtility.evaluateMixedExpression((dist.rate))»)'''
	}
	
	static dispatch def generateDitribution(BernoulliRandomVariable dist){
		'''pyro.distributions.Bernoulli(«TransformationUtility.evaluateMixedExpression((dist.probability))»)'''
	}
	
	static dispatch def generateDitribution(RandomVariable dist){
		throw new UnsupportedOperationException("Stochastic Processes are not supported yet.")
	}
	
	static dispatch def generateStochasticProcess(FittedGaussianProcess gp){
		'''
		MLGaussProcess(
			lr=float(«gp.lr.toString»),
			dataset=«generateDatasetInstance(gp.source)»,
			kernel=«generateKernel(gp.kernel)»
		),dist=None,dirac=None
		'''
	}
	
	static dispatch def generateDitribution(DiracProcess dirac){
		'''
		dirac=«generateDatasetInstance(dirac.source)»,dist=None,mlgp=None
		'''
	}
	
	static dispatch def generateDatasetInstance(InfluxDB dataset){
		'''
		Dataset(
			dbname="«dataset.dbname»",
			ip="«dataset.ip»",
			port=«dataset.port»,
			query="""«dataset.query»""" ,
			script=None
		)
		'''
	}
	
	def generateDatasetClass(){
		'''
		class Dataset():
		
			def __init__(self,dbname,ip,port,query=None,script=None):
				if query is not None:
					client = InfluxDBClient(ip, int(port), database=dbname)
					result = client.query(query)
					points = result.get_points()
					self.points=points
				elif script is not None:
					exec(script)
		'''
	}
	
	static dispatch def generateDatasetInstance(PythonSimulation dataset){
'''Dataset(
			dbname=None,
			query=None,
			ip=None,
			port=None, 
			script=r"""
«dataset.script»
"""
)'''
	}
	
	static dispatch def generateKernel(BrownianKernel kernel){
		return '''gp.kernels.Brownian(input_dim=1)'''
	}
	static dispatch def generateKernel(PeriodicKernel kernel){
		return '''gp.kernels.Periodic(input_dim=1)'''
	}
	static dispatch def generateKernel(LinearKernel kernel){
		return '''gp.kernels.Linear(input_dim=1) '''
	}
	static dispatch def generateKernel(RBFKernel kernel){
		return '''gp.kernels.RBF(input_dim=1)'''
	}
	static dispatch def generateKernel(SumKernel kernel){
		return '''gp.kernels.Sum(«generateKernel(kernel.kernels.get(0))»,«generateKernel(kernel.kernels.get(1))»)'''
	}
	
	
	def generateCategorical(EnvironmentConnections connection){
		val str1='''
		«var rules=connection.component.behaviorRules
			.map[r|r as StochasticRule]»
		RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
				«FOR port : connection.component.outports SEPARATOR ", "»
					«rules.filter[r|r.filter.map[f|(f as PortFilter).port].contains(port)]
						.map[r|TransformationUtility.evaluateMixedExpression((r.stochasticModel as CategoricalProbabaility).probability)].get(0)»
				«ENDFOR»])),
			name="«connection.component.name.toFirstUpper»«(distcntr++).toString»")
		'''
		random_vars.add(str1)
		return str1
	}
	
	def generateClasses(SimulationAnalysisMethod analysisMethod){
		return 
			'''
			«generateDatasetClass»
			
			
			# stochastic model classes
			
			«generateDiscreteRandomVariableClass»
			
			«IF analysisMethod.jointSampling»
				«generateJointRandomVariableClass(analysisMethod.samplingBatchSize)»
			«ELSE»
				«generateRandomVariableClass(analysisMethod.samplingBatchSize)»
			«ENDIF»
			
			'''
	}
}