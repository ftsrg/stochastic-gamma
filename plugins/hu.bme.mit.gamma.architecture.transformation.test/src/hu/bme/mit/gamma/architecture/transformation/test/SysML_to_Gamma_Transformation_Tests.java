package hu.bme.mit.gamma.architecture.transformation.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import hu.bme.mit.gamma.architecture.transformation.ElementTransformer;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.EAElementTransformation;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.EAUtils;
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.language.ui.serializer.StatechartLanguageSerializer;

public class SysML_to_Gamma_Transformation_Tests {

	
	//@DisplayName("InterfaceBock to Gamma Interface Transformation Test")

	
	@Test
	public void interfaceTransformationTest() {
		var pkgID=77;
		var loader = EAUtils.createDataLoader();
		var data=loader.loadAllInterfaceFromPackage(pkgID);
		var eaTrace=new ElementTrace();
		var archTansformer=new EAElementTransformation(eaTrace, new HashMap<>());
		var gammaTrace=new ArchitectureTrace();
		var gammaTransformer=new ElementTransformer(gammaTrace);
		var archInterface=archTansformer.transformInterface(data.get(0));
		var gammaInterface=gammaTransformer.transformInterface(archInterface);
		StatechartLanguageSerializer serializer = new StatechartLanguageSerializer();
		try {
			serializer.serialize(gammaInterface, "C:\\Users\\simon.nagy\\git\\stochastic-gamma\\plugins\\hu.bme.mit.gamma.architecture.transformation.test\\outputs",gammaInterface.getName()+".gcd" );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

/*
	@Test
	@DisplayName("Block of a primitive function into Gamma Statechart Transformation Test")
	public void primitiveFunctionTransformationTest() {
		fail("Not yet implemented");
	}
	
	

	@Test
	@DisplayName("Block of a component function into Gamma Composite Component Transformation Test")
	public void componentFunctionTransformationTest() {
		fail("Not yet implemented");
	}
	

	@Test
	@DisplayName("Block of a system into Gamma Composite Component Transformation Test")
	public void systemTransformationTest() {
		fail("Not yet implemented");
	}
	
*/	

}
