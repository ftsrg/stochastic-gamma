---
title: Gamma modeling
subtitle: Stochastic Gammma Documentation
header: Stochastic Gammma Documentation
---

The Gamma Language Family consists of Gamma Expression Language, Gamma Action Language, Gamma Statechart Language, and Gamma Composition Language. The former three languages can model the behavior of atomic components in the system, and the fourth one can describe the connections between the components. Using Gamma Expression Language, one can define variables, parameters, and arithmetic and logic expressions. Using Gamma Action Language one can define sequence statements which can be expressions, procedure calls and control statements such as, *if*, *for* and *while*. Gamma Statechart Language is similar to UML statecharts. Gamma statecharts can specify state-based component behavior with composite and pseudo-states. One can use the Gamma Expression Language and the Gamma Action Language to define the component behavior within states and state transitions of the Gamma statecharts.


Using Gamma Composition Language, one can model the structure of the system by hierarchically composing the atomic components using composite components. 
In Gamma Composition Language, composite components model the hierarchic containment and interaction between other components. A composite component may include subcomponents, which are instances of other atomic or other composite components. In composite components, one can model the interaction and communication between the subcomponents with channels and event-based interfaces. Gamma supports three types of interaction semantics among the components, synchronous-reactive, synchronous-cascade, and asynchronous.
Gamma Composition Language uses three types of composite components to model the semantics of the interaction, synchronous reactive, synchronous cascade, and asynchronous Gamma composite components. 
Gamma also supports modeling with mixed semantics. In Gamma, one can mix the synchronous and asynchronous components in composition models. The detailed documentation of Gamma is available here: [LINK](http://inf.mit.bme.hu/sites/default/files/gamma/documents/MSc2018_Graics.pdf).

# Synchronous components

One can use synchronous reactive and cascade composite components to model systems, where the components can change states or interact with other components only if a dedicated trigger event arrives. The triggered execution of the synchronous component is called a cycle.
This trigger event may be an external signal or the execution of a software component. 
If a trigger event occurs, the synchronous composite component systematically sends the trigger signal to its subcomponents. In synchronous reactive composite components, the subcomponents can perceive input signals, only if they are generated in the previous execution cycle. As a result, synchronous reactive composition model components where the interaction between the components is delayed by a cycle. In contrast, a cascade synchronous component specifies a linear execution or scheduling of the subcomponents. In cascade composite components, the execution order of the subcomponents is defined and, subcomponents can perceive signals even in the same cycle if the signals are generated before the execution of the component.


# Asynchronous components

In Gamma Composition Language, one can use asynchronous components to define instantaneous interaction between the components. In asynchronous components, the state change and the output signal generation are not related to trigger signals and execution cycles. If the input of an asynchronous component is changed, then it might change the output or the state of the component instantly. In asynchronous composite components, there is no delay in the interaction between the subcomponents. In addition, GCL supports also mixed semantics in composition models. In Gamma, one can use an asynchronous adapter to wrap a synchronous component. Then the wrapped synchronous component can be placed into an asynchronous composite component. The adapter precisely defines the interaction between the synchronous component and its asynchronous environment.