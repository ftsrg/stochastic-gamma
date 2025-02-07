# Environment Semantic


## Elementary environment classes

1.) EnvironmentSwitch: modeling data loss

  * only one input port
  * any number of output port
  * only categorical probabilities
  * only port filter
  

2.) EnvironmentEventSource: modeling one-time event

  * no input port
  * any number of output port
  * any continuous random variable - time when occurs
  * any discrete random variable - probability of this event at zero time
  * any filter

3.) EnvironmentPeriodicEventSource: modeling repeating events 

  * no input port
  * any number of output port
  * any stochastic model
  * continuous random variable
  * simulation compatible 
  * any filter

4.) EnvironmentDelay: modeling communication delay and reaction time

  * only one input port
  * only one output port
  * any continuous random variable
  * any stochastic process
  * simulation compatible
  * any filter

5.) EnvironmentSample: modeling white noise and observations 

  * only one input port
  * only one output port
  * any stochastic model
  * simulation compatible
  * any filter

6.) EnvironmentExternSimulation: modeling any complex behavior with Python interface 

  * any number of input port
  * any number of output port
  * no filter is not applicable
  * only simulation
  * SVI algorithm 


## Additional semantic rules
  
  * **Stochastic rules and models can be applied to those elementary components whose have only one outgoing port**

  * **Only one stochastic port rule can be applied to port!**
  * **Only one stochastic event rule can be applied to event!**
  * **Only one stochastic component rule can be applied to component!**

  * **If more rule is applied to an event (i.e.: port and an event) then the more specific will be applied (event).**
  * **Periodic event source does not support Bernoulli distribution**
  * **Every event of an SampleComponent must have a rational parameter, which must be the first**
  