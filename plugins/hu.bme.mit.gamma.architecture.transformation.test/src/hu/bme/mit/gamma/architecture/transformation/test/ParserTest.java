package hu.bme.mit.gamma.architecture.transformation.test;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.Test;

import com.google.inject.Inject;
import com.google.inject.Injector;

import hu.bme.mit.gamma.environment.language.EnvironmentLanguageStandaloneSetup;
import hu.bme.mit.gamma.environment.language.services.EnvironmentLanguageGrammarAccess;

public class ParserTest {

	//@Inject
	//IParser parser; // inject the parser
	//@Inject
	//EnvironmentLanguageGrammarAccess grammar; // inject your IGrammarAccess

	@Test
	public void test() {
		var expression = "1.2";
		/*
		Injector injector=new EnvironmentLanguageStandaloneSetup().createInjectorAndDoEMFRegistration();
		var resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		
		Resource resource = resourceSet.createResource(URI.createURI("/test.txt"));
		InputStream in = new ByteArrayInputStream(expression.getBytes());
		resource.load(in, resourceSet.getLoadOptions());
		//IParseResult result = parser.parse(grammar.getDe)cimalLiteralExpressionRule(), new StringReader(expression));
		//System.out.println(result.getRootASTElement().toString());
*/
	}

}
