package hu.bme.mit.gamma.environment.language.linking;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.nodemodel.INode;

import com.google.inject.Inject;
import hu.bme.mit.gamma.language.util.linking.GammaLanguageLinker;
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;
import hu.bme.mit.gamma.statechart.interface_.Package;




public class EnvironmentLanguageLinker extends GammaLanguageLinker {

	
	
	@Override
	public Map<Class<? extends EObject>, Collection<EReference>> getContext() {
		return Collections.singletonMap(Package.class, Collections.singletonList(InterfaceModelPackage.eINSTANCE.getPackage_Imports()));
	}
	
}