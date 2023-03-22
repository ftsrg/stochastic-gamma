 SEPARATOR ", " hiányzik a hu.bme.mit.gamma.codegeneration.java.PortInterfaceGenerator-ban 
 Ha két interface leszármazottja egy interface akkor hibás.
 hiányzik a hu.bme.mit.gamma.codegeneration.java.TypeTransformer protected->public
 Statechart kódgenerátorban a full step nincs implementálva
 package hu.bme.mit.gamma.codegeneration.java.util.InterfaceCodeGenerator hiányzik a SEPARATOR ", " az interface leszármazásnál
 	ÉS Hiányzik az ős interface-ek eseményinek összegyűjtése
  java code generator-ba  getter-ek kellenek a statemachine-nak
			public «component.statemachineClassName» get«component.generateStatemachineInstanceName.toFirstUpper»(){
				return  «component.generateStatemachineInstanceName»;
			}
és xsts java code generator-ba is kell ilyen getter 
			public «gammaStatechart.wrappedStatemachineClassName» get«CLASS_NAME.toFirstUpper»(){
				return  «CLASS_NAME.toFirstLower»;
			}