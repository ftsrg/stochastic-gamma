package hu.bme.mit.gamma.codegeneration.java;

import hu.bme.mit.gamma.codegeneration.java.queries.AbstractSynchronousCompositeComponents;
import hu.bme.mit.gamma.codegeneration.java.queries.AsynchronousCompositeComponents;
import hu.bme.mit.gamma.codegeneration.java.queries.SimpleGammaComponents;
import hu.bme.mit.gamma.codegeneration.java.queries.SynchronousComponentWrappers;
import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility;
import hu.bme.mit.gamma.codegeneration.java.util.Namings;
import hu.bme.mit.gamma.lowlevel.xsts.transformation.LowlevelToXstsTransformer;
import hu.bme.mit.gamma.lowlevel.xsts.transformation.TransitionMerging;
import hu.bme.mit.gamma.lowlevel.xsts.transformation.traceability.L2STrace;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.lowlevel.transformation.GammaToLowlevelTransformer;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import hu.bme.mit.gamma.xsts.codegeneration.java.CommonizedVariableActionSerializer;
import hu.bme.mit.gamma.xsts.codegeneration.java.StatechartToJavaCodeGenerator;
import hu.bme.mit.gamma.xsts.model.XSTS;
import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EnvironmentGlueCodeGenerator extends GlueCodeGenerator {
  public String generateWrappedComponentName(final AsynchronousAdapter wrapper) {
    String _firstLower = StringExtensions.toFirstLower(wrapper.getWrappedComponent().getName());
    return (_firstLower + "Statechart");
  }

  private EnvironmentSynchronousCompositeComponentCodeGenerator environmentSynchronousCompositeComponentCodeGenerator;

  private EnvironmentAsynchronousCompositeComponentCodeGenerator environmentAsynchronousCompositeComponentCodeGenerator;

  private EnvironmentAsynchronousAdapterCodeGenerator environmentAsynchronousAdapterCodeGenerator;

  private ArrayList<String> URIs = CollectionLiterals.<String>newArrayList();

  public EnvironmentGlueCodeGenerator(final ResourceSet resourceSet, final String basePackageName, final String srcGenFolderUri) {
    super(resourceSet, basePackageName, srcGenFolderUri);
    final Trace trace = new Trace(super.engine);
    EnvironmentSynchronousCompositeComponentCodeGenerator _environmentSynchronousCompositeComponentCodeGenerator = new EnvironmentSynchronousCompositeComponentCodeGenerator(super.BASE_PACKAGE_NAME, super.YAKINDU_PACKAGE_NAME, trace);
    this.environmentSynchronousCompositeComponentCodeGenerator = _environmentSynchronousCompositeComponentCodeGenerator;
    EnvironmentAsynchronousCompositeComponentCodeGenerator _environmentAsynchronousCompositeComponentCodeGenerator = new EnvironmentAsynchronousCompositeComponentCodeGenerator(this.BASE_PACKAGE_NAME, trace);
    this.environmentAsynchronousCompositeComponentCodeGenerator = _environmentAsynchronousCompositeComponentCodeGenerator;
    EnvironmentAsynchronousAdapterCodeGenerator _environmentAsynchronousAdapterCodeGenerator = new EnvironmentAsynchronousAdapterCodeGenerator(this.BASE_PACKAGE_NAME, trace);
    this.environmentAsynchronousAdapterCodeGenerator = _environmentAsynchronousAdapterCodeGenerator;
    final EnvironmentSchedulingInterfaceGenerator envSchGen = new EnvironmentSchedulingInterfaceGenerator(this.BASE_PACKAGE_NAME);
    final ElementaryEnvironmentComponentUtility envUtil = ElementaryEnvironmentComponentUtility.INSTANCE;
    CharSequence _schedulingInterfacePackage = envUtil.getSchedulingInterfacePackage();
    String _plus = ((("Java Scheduling IF URI: " + this.BASE_PACKAGE_URI) + File.separator) + _schedulingInterfacePackage);
    String _plus_1 = (_plus + File.separator);
    String _schedulingInterfaceName = envUtil.getSchedulingInterfaceName();
    String _plus_2 = (_plus_1 + _schedulingInterfaceName);
    String _plus_3 = (_plus_2 + ".java");
    InputOutput.<String>println(_plus_3);
    CharSequence _generate = envSchGen.generate();
    CharSequence _schedulingInterfacePackage_1 = envUtil.getSchedulingInterfacePackage();
    String _plus_4 = ((this.BASE_PACKAGE_URI + File.separator) + _schedulingInterfacePackage_1);
    String _plus_5 = (_plus_4 + File.separator);
    String _schedulingInterfaceName_1 = envUtil.getSchedulingInterfaceName();
    String _plus_6 = (_plus_5 + _schedulingInterfaceName_1);
    String _plus_7 = (_plus_6 + ".java");
    this.saveCode(_generate, _plus_7);
  }

  @Override
  public void execute() {
    this.checkUniqueInterfaceNames();
    this.generateEventClass();
    this.generateTimerClasses();
    this.statements.fireAllCurrent(this.getTypeDeclarationRule());
    this.statements.fireAllCurrent(this.getPortInterfaceRule());
    this.generateReflectiveInterfaceRule();
    this.statements.fireAllCurrent(this.getSimpleComponentReflectionRule());
    this.statements.fireAllCurrent(this.getSimpleComponentDeclarationRule());
    this.statements.fireAllCurrent(this.getSynchronousCompositeComponentsRule());
    boolean _hasSynchronousWrapper = this.hasSynchronousWrapper();
    if (_hasSynchronousWrapper) {
      this.generateLinkedBlockingMultiQueueClasses();
    }
    this.statements.fireAllCurrent(this.getAsynchronousAdapterRule());
    boolean _hasAsynchronousComposite = this.hasAsynchronousComposite();
    if (_hasAsynchronousComposite) {
      this.statements.fireAllCurrent(this.getChannelsRule());
    }
    this.statements.fireAllCurrent(this.getAsynchronousCompositeComponentsRule());
  }

  @Override
  protected BatchTransformationRule<? extends IPatternMatch, ? extends ViatraQueryMatcher<?>> getSynchronousCompositeComponentsRule() {
    if ((this.synchronousCompositeComponentsRule == null)) {
      final Consumer<AbstractSynchronousCompositeComponents.Match> _function = (AbstractSynchronousCompositeComponents.Match it) -> {
        String _lowerCase = this.nameGenerator.getContainingPackage(it.getSynchronousCompositeComponent()).getName().toLowerCase();
        final String compositeSystemUri = ((this.BASE_PACKAGE_URI + File.separator) + _lowerCase);
        final CharSequence code = this.environmentSynchronousCompositeComponentCodeGenerator.createEnvironmentCompositeComponentClass(it.getSynchronousCompositeComponent());
        String _generateComponentClassName = this.nameGenerator.generateComponentClassName(it.getSynchronousCompositeComponent());
        String _plus = ((compositeSystemUri + File.separator) + _generateComponentClassName);
        String _plus_1 = (_plus + ".java");
        this.saveCode(code, _plus_1);
        final String interfaceCode = this.componentInterfaceGenerator.generateComponentInterface(it.getSynchronousCompositeComponent());
        String _generatePortOwnerInterfaceName = this.nameGenerator.generatePortOwnerInterfaceName(it.getSynchronousCompositeComponent());
        String _plus_2 = ((compositeSystemUri + File.separator) + _generatePortOwnerInterfaceName);
        String _plus_3 = (_plus_2 + ".java");
        this.saveCode(interfaceCode, _plus_3);
        final CharSequence reflectiveCode = this.reflectiveComponentCodeGenerator.generateReflectiveClass(it.getSynchronousCompositeComponent());
        String _reflectiveClassName = Namings.getReflectiveClassName(it.getSynchronousCompositeComponent());
        String _plus_4 = ((compositeSystemUri + File.separator) + _reflectiveClassName);
        String _plus_5 = (_plus_4 + ".java");
        this.saveCode(reflectiveCode, _plus_5);
      };
      this.synchronousCompositeComponentsRule = this._batchTransformationRuleFactory.<AbstractSynchronousCompositeComponents.Match, AbstractSynchronousCompositeComponents.Matcher>createRule(AbstractSynchronousCompositeComponents.instance()).action(_function).build();
    }
    return this.synchronousCompositeComponentsRule;
  }

  /**
   * Creates a Java class for each component transformed from Yakindu given in the component model.
   */
  @Override
  protected BatchTransformationRule<? extends IPatternMatch, ? extends ViatraQueryMatcher<?>> getSimpleComponentDeclarationRule() {
    final Consumer<SimpleGammaComponents.Match> _function = (SimpleGammaComponents.Match it) -> {
      String _lowerCase = this.nameGenerator.getContainingPackage(it.getStatechartDefinition()).getName().toLowerCase();
      final String componentUri = ((this.BASE_PACKAGE_URI + File.separator) + _lowerCase);
      final CommonizedVariableActionSerializer javaActionSerializer = new CommonizedVariableActionSerializer();
      StatechartDefinition _statechartDefinition = it.getStatechartDefinition();
      final StatechartDefinition gammaStatechart = ((StatechartDefinition) _statechartDefinition);
      final GammaToLowlevelTransformer transformer = new GammaToLowlevelTransformer();
      hu.bme.mit.gamma.statechart.lowlevel.model.Package _transformAndWrap = transformer.transformAndWrap(gammaStatechart);
      final hu.bme.mit.gamma.statechart.lowlevel.model.Package lowlevelPackage = ((hu.bme.mit.gamma.statechart.lowlevel.model.Package) _transformAndWrap);
      final LowlevelToXstsTransformer lowlevelTransformer = new LowlevelToXstsTransformer(lowlevelPackage, false, TransitionMerging.HIERARCHICAL);
      AbstractMap.SimpleEntry<XSTS, L2STrace> _execute = lowlevelTransformer.execute();
      final AbstractMap.SimpleEntry<XSTS, L2STrace> resultModels = ((AbstractMap.SimpleEntry<XSTS, L2STrace>) _execute);
      final XSTS xSts = resultModels.getKey();
      final StatechartToJavaCodeGenerator codeGenerator = new StatechartToJavaCodeGenerator(super.BASE_FOLDER_URI, super.BASE_PACKAGE_NAME, gammaStatechart, xSts, javaActionSerializer);
      codeGenerator.execute();
    };
    this.simpleComponentsRule = this._batchTransformationRuleFactory.<SimpleGammaComponents.Match, SimpleGammaComponents.Matcher>createRule(SimpleGammaComponents.instance()).action(_function).build();
    return this.simpleComponentsRule;
  }

  @Override
  protected BatchTransformationRule<? extends IPatternMatch, ? extends ViatraQueryMatcher<?>> getAsynchronousCompositeComponentsRule() {
    if ((this.asynchronousCompositeComponentsRule == null)) {
      final Consumer<AsynchronousCompositeComponents.Match> _function = (AsynchronousCompositeComponents.Match it) -> {
        String _lowerCase = this.nameGenerator.getContainingPackage(it.getAsynchronousCompositeComponent()).getName().toLowerCase();
        final String compositeSystemUri = ((this.BASE_PACKAGE_URI + File.separator) + _lowerCase);
        final CharSequence code = this.environmentAsynchronousCompositeComponentCodeGenerator.createEnvironmentAsynchronousCompositeComponentClass(it.getAsynchronousCompositeComponent(), 0, 0);
        String _generateComponentClassName = this.nameGenerator.generateComponentClassName(it.getAsynchronousCompositeComponent());
        String _plus = ((compositeSystemUri + File.separator) + _generateComponentClassName);
        String _plus_1 = (_plus + ".java");
        this.saveCode(code, _plus_1);
        final String interfaceCode = this.componentInterfaceGenerator.generateComponentInterface(it.getAsynchronousCompositeComponent());
        String _generatePortOwnerInterfaceName = this.nameGenerator.generatePortOwnerInterfaceName(it.getAsynchronousCompositeComponent());
        String _plus_2 = ((compositeSystemUri + File.separator) + _generatePortOwnerInterfaceName);
        String _plus_3 = (_plus_2 + ".java");
        this.saveCode(interfaceCode, _plus_3);
      };
      this.asynchronousCompositeComponentsRule = this._batchTransformationRuleFactory.<AsynchronousCompositeComponents.Match, AsynchronousCompositeComponents.Matcher>createRule(AsynchronousCompositeComponents.instance()).action(_function).build();
    }
    return this.asynchronousCompositeComponentsRule;
  }

  @Override
  protected BatchTransformationRule<? extends IPatternMatch, ? extends ViatraQueryMatcher<?>> getAsynchronousAdapterRule() {
    if ((this.synchronousComponentWrapperRule == null)) {
      final Consumer<SynchronousComponentWrappers.Match> _function = (SynchronousComponentWrappers.Match it) -> {
        String _lowerCase = this.nameGenerator.getContainingPackage(it.getSynchronousComponentWrapper()).getName().toLowerCase();
        final String compositeSystemUri = ((this.BASE_PACKAGE_URI + File.separator) + _lowerCase);
        final CharSequence code = this.environmentAsynchronousAdapterCodeGenerator.createAsynchronousAdapterClass(it.getSynchronousComponentWrapper());
        String _generateComponentClassName = this.nameGenerator.generateComponentClassName(it.getSynchronousComponentWrapper());
        String _plus = ((compositeSystemUri + File.separator) + _generateComponentClassName);
        String _plus_1 = (_plus + ".java");
        this.saveCode(code, _plus_1);
        final String interfaceCode = this.componentInterfaceGenerator.generateComponentInterface(it.getSynchronousComponentWrapper());
        String _generatePortOwnerInterfaceName = this.nameGenerator.generatePortOwnerInterfaceName(it.getSynchronousComponentWrapper());
        String _plus_2 = ((compositeSystemUri + File.separator) + _generatePortOwnerInterfaceName);
        String _plus_3 = (_plus_2 + ".java");
        this.saveCode(interfaceCode, _plus_3);
        final CharSequence reflectiveCode = this.reflectiveComponentCodeGenerator.generateReflectiveClass(it.getSynchronousComponentWrapper());
        String _reflectiveClassName = Namings.getReflectiveClassName(it.getSynchronousComponentWrapper());
        String _plus_4 = ((compositeSystemUri + File.separator) + _reflectiveClassName);
        String _plus_5 = (_plus_4 + ".java");
        this.saveCode(reflectiveCode, _plus_5);
      };
      this.synchronousComponentWrapperRule = this._batchTransformationRuleFactory.<SynchronousComponentWrappers.Match, SynchronousComponentWrappers.Matcher>createRule(SynchronousComponentWrappers.instance()).action(_function).build();
    }
    return this.synchronousComponentWrapperRule;
  }

  public String getBasePackageURI() {
    return this.BASE_PACKAGE_URI;
  }

  @Override
  protected void saveCode(final CharSequence code, final String uri) {
    this.URIs.add(uri);
    super.saveCode(code, uri);
  }

  public ArrayList<String> getURIs() {
    return this.URIs;
  }
}
