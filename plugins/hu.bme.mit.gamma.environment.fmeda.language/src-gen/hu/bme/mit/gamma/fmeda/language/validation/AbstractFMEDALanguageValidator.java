/*
 * generated by Xtext 2.23.0.M1
 */
package hu.bme.mit.gamma.fmeda.language.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public abstract class AbstractFMEDALanguageValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(EPackage.Registry.INSTANCE.getEPackage("www.mit.bme.hu/gamma/fmeda/Model"));
		return result;
	}
}
