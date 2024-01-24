package hu.bme.mit.gamma.architecture.transformation.test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import hu.bme.mit.gamma.architecture.model.ArchitectureElement;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.EADataLoader;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.EnterpriseArchitectTransformation;

public class ImportTest1 {

	final String functionPackageGUID="{23EA1150-4C4D-4953-9B9E-ED570432EE58}";
	final String electricalPackageGUID="{F757BAB2-6BFA-46cc-9298-9DCA089A0184}";
	final String mechanicalPackageGUID="{D672DBD4-9E3D-4e57-861F-345129915978}";
	final String softwarePackageGUID="{DBC57BAE-E98E-4f56-8B97-2A6559618316}";
	final String systemPackageGUID="{7635EDB4-BC69-4392-AC30-C60FF7EECB92}";

	@Test
	public void test() {
		var transformation = new EnterpriseArchitectTransformation(functionPackageGUID,electricalPackageGUID,mechanicalPackageGUID,softwarePackageGUID,systemPackageGUID);
		var root_pkg=transformation.execute();
		for (ArchitectureElement element : root_pkg.getArchitectureelement()) {
			System.out.println(element.getName());
			
		}
	}

}
