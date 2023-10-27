package hu.bme.mit.gamma.codegeneration.java;

import hu.bme.mit.gamma.codegeneration.java.queries.AbstractSynchronousCompositeComponents;
import hu.bme.mit.gamma.codegeneration.java.queries.AsynchronousCompositeComponents;
import hu.bme.mit.gamma.codegeneration.java.queries.SimpleGammaComponents;
import hu.bme.mit.gamma.codegeneration.java.queries.SynchronousComponentWrappers;
import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility;
import hu.bme.mit.gamma.codegeneration.java.util.EnvironmentVirtualTimerServiceCodeGenerator;
import hu.bme.mit.gamma.codegeneration.java.util.Namings;
import hu.bme.mit.gamma.environment.xsts.codegeneration.java.CommonizedVariableActionSerializer;
import hu.bme.mit.gamma.environment.xsts.codegeneration.java.StatechartToJavaCodeGenerator;
import hu.bme.mit.gamma.expression.model.VariableDeclaration;
import hu.bme.mit.gamma.lowlevel.xsts.transformation.LowlevelToXstsTransformer;
import hu.bme.mit.gamma.lowlevel.xsts.transformation.TransitionMerging;
import hu.bme.mit.gamma.lowlevel.xsts.transformation.traceability.L2STrace;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.lowlevel.transformation.GammaToLowlevelTransformer;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import hu.bme.mit.gamma.statechart.statechart.TimeoutDeclaration;
import hu.bme.mit.gamma.xsts.model.XSTS;
import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
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

  private EnvironmentVirtualTimerServiceCodeGenerator environmentVirtualTimerServiceCodeGenerator;

  private EnvironmentTimedObjectInterfaceGenerator environmentTimedObjectInterfaceGenerator;

  private EnvironmentSchedulingInterfaceGenerator environmentSchedulingInterfaceGenerator;

  private ArrayList<String> URIs = CollectionLiterals.<String>newArrayList();

  public EnvironmentGlueCodeGenerator(final ResourceSet resourceSet, final String basePackageName, final String srcGenFolderUri) {
    super(resourceSet, basePackageName, srcGenFolderUri);
    final Trace trace = new Trace(super.engine);
    EnvironmentSchedulingInterfaceGenerator _environmentSchedulingInterfaceGenerator = new EnvironmentSchedulingInterfaceGenerator(super.BASE_PACKAGE_NAME);
    this.environmentSchedulingInterfaceGenerator = _environmentSchedulingInterfaceGenerator;
    EnvironmentSynchronousCompositeComponentCodeGenerator _environmentSynchronousCompositeComponentCodeGenerator = new EnvironmentSynchronousCompositeComponentCodeGenerator(super.BASE_PACKAGE_NAME, super.YAKINDU_PACKAGE_NAME, trace);
    this.environmentSynchronousCompositeComponentCodeGenerator = _environmentSynchronousCompositeComponentCodeGenerator;
    EnvironmentAsynchronousCompositeComponentCodeGenerator _environmentAsynchronousCompositeComponentCodeGenerator = new EnvironmentAsynchronousCompositeComponentCodeGenerator(this.BASE_PACKAGE_NAME, trace);
    this.environmentAsynchronousCompositeComponentCodeGenerator = _environmentAsynchronousCompositeComponentCodeGenerator;
    EnvironmentAsynchronousAdapterCodeGenerator _environmentAsynchronousAdapterCodeGenerator = new EnvironmentAsynchronousAdapterCodeGenerator(this.BASE_PACKAGE_NAME, trace);
    this.environmentAsynchronousAdapterCodeGenerator = _environmentAsynchronousAdapterCodeGenerator;
    EnvironmentVirtualTimerServiceCodeGenerator _environmentVirtualTimerServiceCodeGenerator = new EnvironmentVirtualTimerServiceCodeGenerator(this.BASE_PACKAGE_NAME);
    this.environmentVirtualTimerServiceCodeGenerator = _environmentVirtualTimerServiceCodeGenerator;
    EnvironmentTimedObjectInterfaceGenerator _environmentTimedObjectInterfaceGenerator = new EnvironmentTimedObjectInterfaceGenerator(this.BASE_PACKAGE_NAME);
    this.environmentTimedObjectInterfaceGenerator = _environmentTimedObjectInterfaceGenerator;
    final EnvironmentSchedulingInterfaceGenerator envSchGen = new EnvironmentSchedulingInterfaceGenerator(this.BASE_PACKAGE_NAME);
    final EnvironmentTimedObjectInterfaceGenerator envTimedObjGen = new EnvironmentTimedObjectInterfaceGenerator(this.BASE_PACKAGE_NAME);
    final ElementaryEnvironmentComponentUtility envUtil = ElementaryEnvironmentComponentUtility.INSTANCE;
    CharSequence _schedulingInterfacePackage = envUtil.getSchedulingInterfacePackage();
    String _plus = ((("Java Scheduling IF URI: " + this.BASE_PACKAGE_URI) + File.separator) + _schedulingInterfacePackage);
    String _plus_1 = (_plus + File.separator);
    String _schedulingInterfaceName = envUtil.getSchedulingInterfaceName();
    String _plus_2 = (_plus_1 + _schedulingInterfaceName);
    String _plus_3 = (_plus_2 + ".java");
    InputOutput.<String>println(_plus_3);
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

  protected HashMap<TimeoutDeclaration, VariableDeclaration> generateTimeoutMap(final StatechartDefinition statechart, final hu.bme.mit.gamma.statechart.lowlevel.transformation.Trace trace) {
    HashMap<TimeoutDeclaration, VariableDeclaration> timeoutMap = new HashMap<TimeoutDeclaration, VariableDeclaration>();
    EList<TimeoutDeclaration> _timeoutDeclarations = statechart.getTimeoutDeclarations();
    for (final TimeoutDeclaration timeout : _timeoutDeclarations) {
      timeoutMap.put(timeout, trace.get(timeout));
    }
    return timeoutMap;
  }

  /**
   * Creates a Java class for each component transformed from Gamma statechart components given in the component model.
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
      final hu.bme.mit.gamma.statechart.lowlevel.transformation.Trace traces = transformer.getTraces();
      final HashMap<TimeoutDeclaration, VariableDeclaration> timeoutMap = this.generateTimeoutMap(gammaStatechart, traces);
      final LowlevelToXstsTransformer lowlevelTransformer = new LowlevelToXstsTransformer(lowlevelPackage, false, TransitionMerging.HIERARCHICAL);
      AbstractMap.SimpleEntry<XSTS, L2STrace> _execute = lowlevelTransformer.execute();
      final AbstractMap.SimpleEntry<XSTS, L2STrace> resultModels = ((AbstractMap.SimpleEntry<XSTS, L2STrace>) _execute);
      final XSTS xSts = resultModels.getKey();
      final StatechartToJavaCodeGenerator codeGenerator = new StatechartToJavaCodeGenerator(super.BASE_FOLDER_URI, super.BASE_PACKAGE_NAME, gammaStatechart, xSts, javaActionSerializer, timeoutMap);
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

  /**
   * Creates and saves the message class that is responsible for informing the statecharts about the event that has to be raised (with the given value).
   */
  @Override
  protected void generateTimerClasses() {
    final CharSequence timedObjectClassCode = this.environmentTimedObjectInterfaceGenerator.generate();
    this.saveCode(timedObjectClassCode, ((this.BASE_PACKAGE_URI + File.separator) + "TimedObject.java"));
    final CharSequence schedulingInterfaceClassCode = this.environmentSchedulingInterfaceGenerator.generate();
    this.saveCode(schedulingInterfaceClassCode, ((((this.BASE_PACKAGE_URI + File.separator) + "scheduling") + File.separator) + "ElementaryComponentSchedulingInterface.java"));
    final CharSequence virtualTimerClassCode = this.environmentVirtualTimerServiceCodeGenerator.createVirtualTimerClassCode();
    String _className = this.environmentVirtualTimerServiceCodeGenerator.getClassName();
    String _plus = ((this.BASE_PACKAGE_URI + File.separator) + _className);
    String _plus_1 = (_plus + ".java");
    this.saveCode(virtualTimerClassCode, _plus_1);
    final CharSequence timerInterfaceCode = this.timerInterfaceGenerator.createITimerInterfaceCode();
    String _yakinduInterfaceName = this.timerInterfaceGenerator.getYakinduInterfaceName();
    String _plus_2 = ((this.BASE_PACKAGE_URI + File.separator) + _yakinduInterfaceName);
    String _plus_3 = (_plus_2 + ".java");
    this.saveCode(timerInterfaceCode, _plus_3);
    final CharSequence timerCallbackInterface = this.timerCallbackInterfaceGenerator.createITimerCallbackInterfaceCode();
    String _interfaceName = this.timerCallbackInterfaceGenerator.getInterfaceName();
    String _plus_4 = ((this.BASE_PACKAGE_URI + File.separator) + _interfaceName);
    String _plus_5 = (_plus_4 + ".java");
    this.saveCode(timerCallbackInterface, _plus_5);
    final CharSequence timerServiceClass = this.timerServiceCodeGenerator.createTimerServiceClassCode();
    String _yakinduClassName = this.timerServiceCodeGenerator.getYakinduClassName();
    String _plus_6 = ((this.BASE_PACKAGE_URI + File.separator) + _yakinduClassName);
    String _plus_7 = (_plus_6 + ".java");
    this.saveCode(timerServiceClass, _plus_7);
    final CharSequence gammaTimerInterface = this.timerInterfaceGenerator.createGammaTimerInterfaceCode();
    String _gammaInterfaceName = this.timerInterfaceGenerator.getGammaInterfaceName();
    String _plus_8 = ((this.BASE_PACKAGE_URI + File.separator) + _gammaInterfaceName);
    String _plus_9 = (_plus_8 + ".java");
    this.saveCode(gammaTimerInterface, _plus_9);
    final CharSequence gammaTimerClass = this.timerServiceCodeGenerator.createGammaTimerClassCode();
    String _gammaClassName = this.timerServiceCodeGenerator.getGammaClassName();
    String _plus_10 = ((this.BASE_PACKAGE_URI + File.separator) + _gammaClassName);
    String _plus_11 = (_plus_10 + ".java");
    this.saveCode(gammaTimerClass, _plus_11);
    final CharSequence unifiedTimerInterface = this.timerInterfaceGenerator.createUnifiedTimerInterfaceCode();
    String _unifiedInterfaceName = this.timerInterfaceGenerator.getUnifiedInterfaceName();
    String _plus_12 = ((this.BASE_PACKAGE_URI + File.separator) + _unifiedInterfaceName);
    String _plus_13 = (_plus_12 + ".java");
    this.saveCode(unifiedTimerInterface, _plus_13);
    final CharSequence unifiedTimerClass = this.timerServiceCodeGenerator.createUnifiedTimerClassCode();
    String _unifiedClassName = this.timerServiceCodeGenerator.getUnifiedClassName();
    String _plus_14 = ((this.BASE_PACKAGE_URI + File.separator) + _unifiedClassName);
    String _plus_15 = (_plus_14 + ".java");
    this.saveCode(unifiedTimerClass, _plus_15);
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
