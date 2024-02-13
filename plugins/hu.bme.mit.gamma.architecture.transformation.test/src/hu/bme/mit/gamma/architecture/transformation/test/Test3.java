package hu.bme.mit.gamma.architecture.transformation.test;

import java.io.IOException;
import java.util.HashMap;

import hu.bme.mit.gamma.architecture.transformation.ElementTransformer;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.EAElementTransformation;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.EAUtils;
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace;
import hu.bme.mit.gamma.statechart.language.ui.serializer.StatechartLanguageSerializer;

public class Test3 {

	public static void main(String[] args) {
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

}
