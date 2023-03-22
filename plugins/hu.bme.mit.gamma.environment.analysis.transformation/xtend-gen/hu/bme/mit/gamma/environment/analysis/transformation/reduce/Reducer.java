package hu.bme.mit.gamma.environment.analysis.transformation.reduce;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.ElementaryEnvironmentComponentInstancePattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentAsynchronousCompositeComponentInstancePattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentAsynchronousCompositeComponentPattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentInstancePattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentPattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeComponentInstancePattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeComponentPattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.PackagePattern;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.expression.model.BasicConstraintDefinition;
import hu.bme.mit.gamma.expression.model.ConstantDeclaration;
import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition;
import hu.bme.mit.gamma.statechart.statechart.InitialState;
import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.State;
import hu.bme.mit.gamma.statechart.statechart.StateNode;
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory;
import hu.bme.mit.gamma.statechart.statechart.SynchronousStatechartDefinition;
import hu.bme.mit.gamma.statechart.statechart.Transition;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Reducer {
  /**
   * Transformation-related extensions
   */
  @Extension
  private BatchTransformation transformation;

  @Extension
  private BatchTransformationStatements statements;

  /**
   * Transformation rule-related extensions
   */
  @Extension
  private BatchTransformationRuleFactory _batchTransformationRuleFactory = new BatchTransformationRuleFactory();

  @Extension
  private IModelManipulations manipulation;

  @Extension
  protected final InterfaceModelFactory _interfaceModelFactory = InterfaceModelFactory.eINSTANCE;

  @Extension
  protected final StatechartModelFactory _statechartModelFactory = StatechartModelFactory.eINSTANCE;

  @Extension
  protected final CompositeModelFactory _compositeModelFactory = CompositeModelFactory.eINSTANCE;

  @Extension
  protected final EnvironmentModelFactory _environmentModelFactory = EnvironmentModelFactory.eINSTANCE;

  protected final hu.bme.mit.gamma.statechart.interface_.Package _package;

  protected Trace trace;

  protected ViatraQueryEngine engine;

  protected Resource resource;

  protected BatchTransformationRule<EnvironmentAsynchronousCompositeComponentPattern.Match, EnvironmentAsynchronousCompositeComponentPattern.Matcher> asynchronousCompositeComponentRule;

  protected BatchTransformationRule<EnvironmentSynchronousCompositeComponentPattern.Match, EnvironmentSynchronousCompositeComponentPattern.Matcher> synchronousCompositeComponentRule;

  protected BatchTransformationRule<EnvironmentCascadeCompositeComponentPattern.Match, EnvironmentCascadeCompositeComponentPattern.Matcher> cascadeCompositeComponentRule;

  protected BatchTransformationRule<EnvironmentAsynchronousCompositeComponentInstancePattern.Match, EnvironmentAsynchronousCompositeComponentInstancePattern.Matcher> asynchronousCompositeComponentInstanceRule;

  protected BatchTransformationRule<EnvironmentSynchronousCompositeComponentInstancePattern.Match, EnvironmentSynchronousCompositeComponentInstancePattern.Matcher> synchronousCompositeComponentInstanceRule;

  protected BatchTransformationRule<EnvironmentCascadeCompositeComponentInstancePattern.Match, EnvironmentCascadeCompositeComponentInstancePattern.Matcher> cascadeCompositeComponentInstanceRule;

  protected BatchTransformationRule<ElementaryEnvironmentComponentInstancePattern.Match, ElementaryEnvironmentComponentInstancePattern.Matcher> elementaryEnvironmentComponentInstanceRule;

  protected BatchTransformationRule<PackagePattern.Match, PackagePattern.Matcher> packagePatternRule;

  public Reducer(final hu.bme.mit.gamma.statechart.interface_.Package _package) {
    this._package = _package;
    final Resource resource = _package.eResource();
    final EMFScope scope = new EMFScope(resource);
    this.engine = ViatraQueryEngine.on(scope);
    this.trace = null;
    this.transformation = BatchTransformation.forEngine(this.engine).build();
    this.statements = this.transformation.getTransformationStatements();
  }

  public void execute() {
    this.statements.<EnvironmentAsynchronousCompositeComponentPattern.Match>fireAllCurrent(this.getAsynchronousCompositeComponentRule());
    this.statements.<EnvironmentSynchronousCompositeComponentPattern.Match>fireAllCurrent(this.getSynchronousCompositeComponentRule());
    this.statements.<EnvironmentCascadeCompositeComponentPattern.Match>fireAllCurrent(this.getCascadeCompositeComponentRule());
  }

  private BatchTransformationStatements createTransformation() {
    BatchTransformationStatements _xblockexpression = null;
    {
      SimpleModelManipulations _simpleModelManipulations = new SimpleModelManipulations(this.engine);
      this.manipulation = _simpleModelManipulations;
      this.transformation = BatchTransformation.forEngine(this.engine).build();
      _xblockexpression = this.statements = this.transformation.getTransformationStatements();
    }
    return _xblockexpression;
  }

  private BatchTransformationRule<PackagePattern.Match, PackagePattern.Matcher> getPackagePatternRule() {
    if ((this.packagePatternRule == null)) {
      final Consumer<PackagePattern.Match> _function = (PackagePattern.Match it) -> {
        Preconditions.checkState((this.trace == null));
        final hu.bme.mit.gamma.statechart.interface_.Package source = it.getPackage();
        hu.bme.mit.gamma.statechart.interface_.Package _createPackage = this._interfaceModelFactory.createPackage();
        final Procedure1<hu.bme.mit.gamma.statechart.interface_.Package> _function_1 = (hu.bme.mit.gamma.statechart.interface_.Package it_1) -> {
          it_1.setName(source.getName());
          EList<ConstantDeclaration> _constantDeclarations = it_1.getConstantDeclarations();
          EList<ConstantDeclaration> _constantDeclarations_1 = source.getConstantDeclarations();
          Iterables.<ConstantDeclaration>addAll(_constantDeclarations, _constantDeclarations_1);
          EList<BasicConstraintDefinition> _basicConstraintDefinitions = it_1.getBasicConstraintDefinitions();
          EList<BasicConstraintDefinition> _basicConstraintDefinitions_1 = source.getBasicConstraintDefinitions();
          Iterables.<BasicConstraintDefinition>addAll(_basicConstraintDefinitions, _basicConstraintDefinitions_1);
          EList<Interface> _interfaces = it_1.getInterfaces();
          EList<Interface> _interfaces_1 = source.getInterfaces();
          Iterables.<Interface>addAll(_interfaces, _interfaces_1);
          EList<hu.bme.mit.gamma.statechart.interface_.Package> _imports = it_1.getImports();
          EList<hu.bme.mit.gamma.statechart.interface_.Package> _imports_1 = source.getImports();
          Iterables.<hu.bme.mit.gamma.statechart.interface_.Package>addAll(_imports, _imports_1);
          EList<ParameterDeclaration> _parameterDeclarations = it_1.getParameterDeclarations();
          EList<ParameterDeclaration> _parameterDeclarations_1 = source.getParameterDeclarations();
          Iterables.<ParameterDeclaration>addAll(_parameterDeclarations, _parameterDeclarations_1);
        };
        final hu.bme.mit.gamma.statechart.interface_.Package target = ObjectExtensions.<hu.bme.mit.gamma.statechart.interface_.Package>operator_doubleArrow(_createPackage, _function_1);
        Trace _trace = new Trace(source, target);
        this.trace = _trace;
        this.trace.put(source, target);
      };
      this.packagePatternRule = this._batchTransformationRuleFactory.<PackagePattern.Match, PackagePattern.Matcher>createRule(PackagePattern.instance()).action(_function).build();
    }
    return this.packagePatternRule;
  }

  private BatchTransformationRule<EnvironmentAsynchronousCompositeComponentPattern.Match, EnvironmentAsynchronousCompositeComponentPattern.Matcher> getAsynchronousCompositeComponentRule() {
    BatchTransformationRule<EnvironmentAsynchronousCompositeComponentPattern.Match, EnvironmentAsynchronousCompositeComponentPattern.Matcher> _xifexpression = null;
    boolean _equals = Objects.equal(this.asynchronousCompositeComponentRule, null);
    if (_equals) {
      final Consumer<EnvironmentAsynchronousCompositeComponentPattern.Match> _function = (EnvironmentAsynchronousCompositeComponentPattern.Match it) -> {
        Preconditions.checkState((this.trace == null));
        final EnvironmentAsynchronousCompositeComponent source = it.getComponent();
        AsynchronousCompositeComponent _createAsynchronousCompositeComponent = this._compositeModelFactory.createAsynchronousCompositeComponent();
        final Procedure1<AsynchronousCompositeComponent> _function_1 = (AsynchronousCompositeComponent it_1) -> {
          it_1.setName(source.getName());
          it_1.getAnnotations().addAll(source.getAnnotations());
          it_1.getPorts().addAll(source.getPorts());
          it_1.getParameterDeclarations().addAll(source.getParameterDeclarations());
          it_1.getFunctionDeclarations().addAll(source.getFunctionDeclarations());
        };
        final AsynchronousCompositeComponent target = ObjectExtensions.<AsynchronousCompositeComponent>operator_doubleArrow(_createAsynchronousCompositeComponent, _function_1);
        EObject _eContainer = source.eContainer();
        final hu.bme.mit.gamma.statechart.interface_.Package sourcePackage = ((hu.bme.mit.gamma.statechart.interface_.Package) _eContainer);
        hu.bme.mit.gamma.statechart.interface_.Package _createPackage = this._interfaceModelFactory.createPackage();
        final Procedure1<hu.bme.mit.gamma.statechart.interface_.Package> _function_2 = (hu.bme.mit.gamma.statechart.interface_.Package it_1) -> {
          it_1.setName(sourcePackage.getName());
        };
        final hu.bme.mit.gamma.statechart.interface_.Package targetPackage = ObjectExtensions.<hu.bme.mit.gamma.statechart.interface_.Package>operator_doubleArrow(_createPackage, _function_2);
        EList<Component> _components = targetPackage.getComponents();
        _components.add(target);
        this.trace.put(sourcePackage, targetPackage);
        this.trace.put(source, target);
      };
      _xifexpression = this.asynchronousCompositeComponentRule = this._batchTransformationRuleFactory.<EnvironmentAsynchronousCompositeComponentPattern.Match, EnvironmentAsynchronousCompositeComponentPattern.Matcher>createRule(EnvironmentAsynchronousCompositeComponentPattern.instance()).action(_function).build();
    }
    return _xifexpression;
  }

  private BatchTransformationRule<EnvironmentSynchronousCompositeComponentPattern.Match, EnvironmentSynchronousCompositeComponentPattern.Matcher> getSynchronousCompositeComponentRule() {
    BatchTransformationRule<EnvironmentSynchronousCompositeComponentPattern.Match, EnvironmentSynchronousCompositeComponentPattern.Matcher> _xifexpression = null;
    boolean _equals = Objects.equal(this.synchronousCompositeComponentRule, null);
    if (_equals) {
      final Consumer<EnvironmentSynchronousCompositeComponentPattern.Match> _function = (EnvironmentSynchronousCompositeComponentPattern.Match it) -> {
        Preconditions.checkState((this.trace == null));
        final EnvironmentSynchronousCompositeComponent source = it.getComponent();
        SynchronousCompositeComponent _createSynchronousCompositeComponent = this._compositeModelFactory.createSynchronousCompositeComponent();
        final Procedure1<SynchronousCompositeComponent> _function_1 = (SynchronousCompositeComponent it_1) -> {
          it_1.setName(source.getName());
          it_1.getAnnotations().addAll(source.getAnnotations());
          it_1.getPorts().addAll(source.getPorts());
          it_1.getParameterDeclarations().addAll(source.getParameterDeclarations());
          it_1.getFunctionDeclarations().addAll(source.getFunctionDeclarations());
        };
        final SynchronousCompositeComponent target = ObjectExtensions.<SynchronousCompositeComponent>operator_doubleArrow(_createSynchronousCompositeComponent, _function_1);
        EObject _eContainer = source.eContainer();
        final hu.bme.mit.gamma.statechart.interface_.Package sourcePackage = ((hu.bme.mit.gamma.statechart.interface_.Package) _eContainer);
        hu.bme.mit.gamma.statechart.interface_.Package _createPackage = this._interfaceModelFactory.createPackage();
        final Procedure1<hu.bme.mit.gamma.statechart.interface_.Package> _function_2 = (hu.bme.mit.gamma.statechart.interface_.Package it_1) -> {
          it_1.setName(sourcePackage.getName());
        };
        final hu.bme.mit.gamma.statechart.interface_.Package targetPackage = ObjectExtensions.<hu.bme.mit.gamma.statechart.interface_.Package>operator_doubleArrow(_createPackage, _function_2);
        EList<Component> _components = targetPackage.getComponents();
        _components.add(target);
        this.trace.put(sourcePackage, targetPackage);
        this.trace.put(source, target);
      };
      _xifexpression = this.synchronousCompositeComponentRule = this._batchTransformationRuleFactory.<EnvironmentSynchronousCompositeComponentPattern.Match, EnvironmentSynchronousCompositeComponentPattern.Matcher>createRule(EnvironmentSynchronousCompositeComponentPattern.instance()).action(_function).build();
    }
    return _xifexpression;
  }

  private BatchTransformationRule<EnvironmentCascadeCompositeComponentPattern.Match, EnvironmentCascadeCompositeComponentPattern.Matcher> getCascadeCompositeComponentRule() {
    BatchTransformationRule<EnvironmentCascadeCompositeComponentPattern.Match, EnvironmentCascadeCompositeComponentPattern.Matcher> _xifexpression = null;
    boolean _equals = Objects.equal(this.cascadeCompositeComponentRule, null);
    if (_equals) {
      final Consumer<EnvironmentCascadeCompositeComponentPattern.Match> _function = (EnvironmentCascadeCompositeComponentPattern.Match it) -> {
        Preconditions.checkState((this.trace == null));
        final EnvironmentCascadeCompositeComponent source = it.getComponent();
        CascadeCompositeComponent _createCascadeCompositeComponent = this._compositeModelFactory.createCascadeCompositeComponent();
        final Procedure1<CascadeCompositeComponent> _function_1 = (CascadeCompositeComponent it_1) -> {
          it_1.setName(source.getName());
          it_1.getAnnotations().addAll(source.getAnnotations());
          it_1.getPorts().addAll(source.getPorts());
          it_1.getParameterDeclarations().addAll(source.getParameterDeclarations());
          it_1.getFunctionDeclarations().addAll(source.getFunctionDeclarations());
        };
        final CascadeCompositeComponent target = ObjectExtensions.<CascadeCompositeComponent>operator_doubleArrow(_createCascadeCompositeComponent, _function_1);
        EObject _eContainer = source.eContainer();
        final hu.bme.mit.gamma.statechart.interface_.Package sourcePackage = ((hu.bme.mit.gamma.statechart.interface_.Package) _eContainer);
        hu.bme.mit.gamma.statechart.interface_.Package _createPackage = this._interfaceModelFactory.createPackage();
        final Procedure1<hu.bme.mit.gamma.statechart.interface_.Package> _function_2 = (hu.bme.mit.gamma.statechart.interface_.Package it_1) -> {
          it_1.setName(sourcePackage.getName());
        };
        final hu.bme.mit.gamma.statechart.interface_.Package targetPackage = ObjectExtensions.<hu.bme.mit.gamma.statechart.interface_.Package>operator_doubleArrow(_createPackage, _function_2);
        EList<Component> _components = targetPackage.getComponents();
        _components.add(target);
        this.trace.put(sourcePackage, targetPackage);
        this.trace.put(source, target);
      };
      _xifexpression = this.cascadeCompositeComponentRule = this._batchTransformationRuleFactory.<EnvironmentCascadeCompositeComponentPattern.Match, EnvironmentCascadeCompositeComponentPattern.Matcher>createRule(EnvironmentCascadeCompositeComponentPattern.instance()).action(_function).build();
    }
    return _xifexpression;
  }

  private BatchTransformationRule<EnvironmentAsynchronousCompositeComponentInstancePattern.Match, EnvironmentAsynchronousCompositeComponentInstancePattern.Matcher> getAsynchronousCompositeComponentInstanceRule() {
    BatchTransformationRule<EnvironmentAsynchronousCompositeComponentInstancePattern.Match, EnvironmentAsynchronousCompositeComponentInstancePattern.Matcher> _xifexpression = null;
    boolean _equals = Objects.equal(this.asynchronousCompositeComponentInstanceRule, null);
    if (_equals) {
      final Consumer<EnvironmentAsynchronousCompositeComponentInstancePattern.Match> _function = (EnvironmentAsynchronousCompositeComponentInstancePattern.Match it) -> {
        Preconditions.checkState((this.trace == null));
        final EnvironmentAsynchronousCompositeComponentInstance source = it.getComponent();
        final EnvironmentAsynchronousCompositeComponent sourceType = source.getType();
        EObject _eContainer = source.eContainer();
        final EnvironmentAsynchronousCompositeComponent sourceComponent = ((EnvironmentAsynchronousCompositeComponent) _eContainer);
        final AsynchronousCompositeComponent targetType = this.trace.getTargetAsynchronousComponent(sourceType);
        final AsynchronousCompositeComponent targetContainer = this.trace.getTargetAsynchronousComponent(sourceComponent);
        AsynchronousComponentInstance _createAsynchronousComponentInstance = this._compositeModelFactory.createAsynchronousComponentInstance();
        final Procedure1<AsynchronousComponentInstance> _function_1 = (AsynchronousComponentInstance it_1) -> {
          it_1.setName(source.getName());
          it_1.setType(targetType);
          EList<Expression> _arguments = it_1.getArguments();
          EList<Expression> _arguments_1 = source.getArguments();
          Iterables.<Expression>addAll(_arguments, _arguments_1);
        };
        final AsynchronousComponentInstance target = ObjectExtensions.<AsynchronousComponentInstance>operator_doubleArrow(_createAsynchronousComponentInstance, _function_1);
        EList<AsynchronousComponentInstance> _components = targetContainer.getComponents();
        _components.add(target);
        this.trace.put(source, target);
      };
      _xifexpression = this.asynchronousCompositeComponentInstanceRule = this._batchTransformationRuleFactory.<EnvironmentAsynchronousCompositeComponentInstancePattern.Match, EnvironmentAsynchronousCompositeComponentInstancePattern.Matcher>createRule(EnvironmentAsynchronousCompositeComponentInstancePattern.instance()).action(_function).build();
    }
    return _xifexpression;
  }

  private BatchTransformationRule<EnvironmentSynchronousCompositeComponentInstancePattern.Match, EnvironmentSynchronousCompositeComponentInstancePattern.Matcher> getSynchronousCompositeComponentInstanceRule() {
    BatchTransformationRule<EnvironmentSynchronousCompositeComponentInstancePattern.Match, EnvironmentSynchronousCompositeComponentInstancePattern.Matcher> _xifexpression = null;
    boolean _equals = Objects.equal(this.synchronousCompositeComponentInstanceRule, null);
    if (_equals) {
      final Consumer<EnvironmentSynchronousCompositeComponentInstancePattern.Match> _function = (EnvironmentSynchronousCompositeComponentInstancePattern.Match it) -> {
        Preconditions.checkState((this.trace == null));
        final EnvironmentSynchronousCompositeComponentInstance source = it.getComponent();
        final EnvironmentSynchronousCompositeComponent sourceType = source.getType();
        EObject _eContainer = source.eContainer();
        final EnvironmentSynchronousCompositeComponent sourceComponent = ((EnvironmentSynchronousCompositeComponent) _eContainer);
        final SynchronousCompositeComponent targetType = this.trace.getTargetSynchronousComponent(sourceType);
        final SynchronousCompositeComponent targetContainer = this.trace.getTargetSynchronousComponent(sourceComponent);
        SynchronousComponentInstance _createSynchronousComponentInstance = this._compositeModelFactory.createSynchronousComponentInstance();
        final Procedure1<SynchronousComponentInstance> _function_1 = (SynchronousComponentInstance it_1) -> {
          it_1.setName(source.getName());
          it_1.setType(targetType);
          EList<Expression> _arguments = it_1.getArguments();
          EList<Expression> _arguments_1 = source.getArguments();
          Iterables.<Expression>addAll(_arguments, _arguments_1);
        };
        final SynchronousComponentInstance target = ObjectExtensions.<SynchronousComponentInstance>operator_doubleArrow(_createSynchronousComponentInstance, _function_1);
        EList<SynchronousComponentInstance> _components = targetContainer.getComponents();
        _components.add(target);
        this.trace.put(source, target);
      };
      _xifexpression = this.synchronousCompositeComponentInstanceRule = this._batchTransformationRuleFactory.<EnvironmentSynchronousCompositeComponentInstancePattern.Match, EnvironmentSynchronousCompositeComponentInstancePattern.Matcher>createRule(EnvironmentSynchronousCompositeComponentInstancePattern.instance()).action(_function).build();
    }
    return _xifexpression;
  }

  private BatchTransformationRule<EnvironmentCascadeCompositeComponentInstancePattern.Match, EnvironmentCascadeCompositeComponentInstancePattern.Matcher> getCascadeCompositeComponentInstanceRule() {
    BatchTransformationRule<EnvironmentCascadeCompositeComponentInstancePattern.Match, EnvironmentCascadeCompositeComponentInstancePattern.Matcher> _xifexpression = null;
    boolean _equals = Objects.equal(this.cascadeCompositeComponentInstanceRule, null);
    if (_equals) {
      final Consumer<EnvironmentCascadeCompositeComponentInstancePattern.Match> _function = (EnvironmentCascadeCompositeComponentInstancePattern.Match it) -> {
        Preconditions.checkState((this.trace == null));
        final EnvironmentCascadeCompositeComponentInstance source = it.getComponent();
        final EnvironmentCascadeCompositeComponent sourceType = source.getType();
        EObject _eContainer = source.eContainer();
        final EnvironmentCascadeCompositeComponent sourceComponent = ((EnvironmentCascadeCompositeComponent) _eContainer);
        final CascadeCompositeComponent targetType = this.trace.getTargetCascadeComponent(sourceType);
        final CascadeCompositeComponent targetContainer = this.trace.getTargetCascadeComponent(sourceComponent);
        SynchronousComponentInstance _createSynchronousComponentInstance = this._compositeModelFactory.createSynchronousComponentInstance();
        final Procedure1<SynchronousComponentInstance> _function_1 = (SynchronousComponentInstance it_1) -> {
          it_1.setName(source.getName());
          it_1.setType(targetType);
          EList<Expression> _arguments = it_1.getArguments();
          EList<Expression> _arguments_1 = source.getArguments();
          Iterables.<Expression>addAll(_arguments, _arguments_1);
        };
        final SynchronousComponentInstance target = ObjectExtensions.<SynchronousComponentInstance>operator_doubleArrow(_createSynchronousComponentInstance, _function_1);
        EList<SynchronousComponentInstance> _components = targetContainer.getComponents();
        _components.add(target);
        this.trace.put(source, target);
      };
      _xifexpression = this.cascadeCompositeComponentInstanceRule = this._batchTransformationRuleFactory.<EnvironmentCascadeCompositeComponentInstancePattern.Match, EnvironmentCascadeCompositeComponentInstancePattern.Matcher>createRule(EnvironmentCascadeCompositeComponentInstancePattern.instance()).action(_function).build();
    }
    return _xifexpression;
  }

  private BatchTransformationRule<ElementaryEnvironmentComponentInstancePattern.Match, ElementaryEnvironmentComponentInstancePattern.Matcher> getElementaryEnvironmentComponentInstanceRule() {
    BatchTransformationRule<ElementaryEnvironmentComponentInstancePattern.Match, ElementaryEnvironmentComponentInstancePattern.Matcher> _xifexpression = null;
    boolean _equals = Objects.equal(this.elementaryEnvironmentComponentInstanceRule, null);
    if (_equals) {
      final Consumer<ElementaryEnvironmentComponentInstancePattern.Match> _function = (ElementaryEnvironmentComponentInstancePattern.Match it) -> {
        Preconditions.checkState((this.trace == null));
        final ElementaryEnvironmentComponentInstance source = it.getInstance();
        EObject _eContainer = source.eContainer();
        if ((_eContainer instanceof EnvironmentAsynchronousCompositeComponent)) {
          EObject _eContainer_1 = source.eContainer();
          final EnvironmentAsynchronousCompositeComponent sourceComponent = ((EnvironmentAsynchronousCompositeComponent) _eContainer_1);
          final AsynchronousCompositeComponent targetContainer = this.trace.getTargetAsynchronousComponent(sourceComponent);
          AsynchronousStatechartDefinition _createAsynchronousStatechartDefinition = this._statechartModelFactory.createAsynchronousStatechartDefinition();
          final Procedure1<AsynchronousStatechartDefinition> _function_1 = (AsynchronousStatechartDefinition it_1) -> {
            String _name = source.getName();
            String _plus = (_name + "Type");
            it_1.setName(_plus);
            EList<Port> _ports = it_1.getPorts();
            EList<Port> _inports = source.getInports();
            Iterables.<Port>addAll(_ports, _inports);
            EList<Port> _ports_1 = it_1.getPorts();
            EList<Port> _outports = source.getOutports();
            Iterables.<Port>addAll(_ports_1, _outports);
            State _createState = this._statechartModelFactory.createState();
            final Procedure1<State> _function_2 = (State it_2) -> {
              it_2.setName("state1");
            };
            final State state1 = ObjectExtensions.<State>operator_doubleArrow(_createState, _function_2);
            InitialState _createInitialState = this._statechartModelFactory.createInitialState();
            final Procedure1<InitialState> _function_3 = (InitialState it_2) -> {
              it_2.setName("init1");
            };
            final InitialState init1 = ObjectExtensions.<InitialState>operator_doubleArrow(_createInitialState, _function_3);
            EList<Region> _regions = it_1.getRegions();
            Region _createRegion = this._statechartModelFactory.createRegion();
            final Procedure1<Region> _function_4 = (Region it_2) -> {
              it_2.setName("mainRegion");
              EList<StateNode> _stateNodes = it_2.getStateNodes();
              _stateNodes.add(init1);
              EList<StateNode> _stateNodes_1 = it_2.getStateNodes();
              _stateNodes_1.add(state1);
            };
            Region _doubleArrow = ObjectExtensions.<Region>operator_doubleArrow(_createRegion, _function_4);
            _regions.add(_doubleArrow);
            EList<Transition> _transitions = it_1.getTransitions();
            Transition _createTransition = this._statechartModelFactory.createTransition();
            final Procedure1<Transition> _function_5 = (Transition it_2) -> {
              it_2.setSourceState(init1);
              it_2.setTargetState(state1);
            };
            Transition _doubleArrow_1 = ObjectExtensions.<Transition>operator_doubleArrow(_createTransition, _function_5);
            _transitions.add(_doubleArrow_1);
          };
          final AsynchronousStatechartDefinition targetType = ObjectExtensions.<AsynchronousStatechartDefinition>operator_doubleArrow(_createAsynchronousStatechartDefinition, _function_1);
          EObject _eContainer_2 = source.eContainer();
          final hu.bme.mit.gamma.statechart.interface_.Package sourcePackage = ((hu.bme.mit.gamma.statechart.interface_.Package) _eContainer_2);
          hu.bme.mit.gamma.statechart.interface_.Package _createPackage = this._interfaceModelFactory.createPackage();
          final Procedure1<hu.bme.mit.gamma.statechart.interface_.Package> _function_2 = (hu.bme.mit.gamma.statechart.interface_.Package it_1) -> {
            it_1.setName(sourcePackage.getName());
          };
          final hu.bme.mit.gamma.statechart.interface_.Package targetPackage = ObjectExtensions.<hu.bme.mit.gamma.statechart.interface_.Package>operator_doubleArrow(_createPackage, _function_2);
          EList<Component> _components = targetPackage.getComponents();
          _components.add(targetType);
          this.trace.put(sourcePackage, targetPackage);
          AsynchronousComponentInstance _createAsynchronousComponentInstance = this._compositeModelFactory.createAsynchronousComponentInstance();
          final Procedure1<AsynchronousComponentInstance> _function_3 = (AsynchronousComponentInstance it_1) -> {
            it_1.setName(source.getName());
            it_1.setType(targetType);
          };
          final AsynchronousComponentInstance targetInstance = ObjectExtensions.<AsynchronousComponentInstance>operator_doubleArrow(_createAsynchronousComponentInstance, _function_3);
          EList<AsynchronousComponentInstance> _components_1 = targetContainer.getComponents();
          _components_1.add(targetInstance);
          this.trace.put(source, targetInstance, targetType);
        }
        EObject _eContainer_3 = source.eContainer();
        if ((_eContainer_3 instanceof EnvironmentSynchronousCompositeComponent)) {
          EObject _eContainer_4 = source.eContainer();
          final EnvironmentSynchronousCompositeComponent sourceComponent_1 = ((EnvironmentSynchronousCompositeComponent) _eContainer_4);
          final SynchronousCompositeComponent targetContainer_1 = this.trace.getTargetSynchronousComponent(sourceComponent_1);
          SynchronousStatechartDefinition _createSynchronousStatechartDefinition = this._statechartModelFactory.createSynchronousStatechartDefinition();
          final Procedure1<SynchronousStatechartDefinition> _function_4 = (SynchronousStatechartDefinition it_1) -> {
            String _name = source.getName();
            String _plus = (_name + "Type");
            it_1.setName(_plus);
            EList<Port> _ports = it_1.getPorts();
            EList<Port> _inports = source.getInports();
            Iterables.<Port>addAll(_ports, _inports);
            EList<Port> _ports_1 = it_1.getPorts();
            EList<Port> _outports = source.getOutports();
            Iterables.<Port>addAll(_ports_1, _outports);
            State _createState = this._statechartModelFactory.createState();
            final Procedure1<State> _function_5 = (State it_2) -> {
              it_2.setName("state1");
            };
            final State state1 = ObjectExtensions.<State>operator_doubleArrow(_createState, _function_5);
            InitialState _createInitialState = this._statechartModelFactory.createInitialState();
            final Procedure1<InitialState> _function_6 = (InitialState it_2) -> {
              it_2.setName("init1");
            };
            final InitialState init1 = ObjectExtensions.<InitialState>operator_doubleArrow(_createInitialState, _function_6);
            EList<Region> _regions = it_1.getRegions();
            Region _createRegion = this._statechartModelFactory.createRegion();
            final Procedure1<Region> _function_7 = (Region it_2) -> {
              it_2.setName("mainRegion");
              EList<StateNode> _stateNodes = it_2.getStateNodes();
              _stateNodes.add(init1);
              EList<StateNode> _stateNodes_1 = it_2.getStateNodes();
              _stateNodes_1.add(state1);
            };
            Region _doubleArrow = ObjectExtensions.<Region>operator_doubleArrow(_createRegion, _function_7);
            _regions.add(_doubleArrow);
            EList<Transition> _transitions = it_1.getTransitions();
            Transition _createTransition = this._statechartModelFactory.createTransition();
            final Procedure1<Transition> _function_8 = (Transition it_2) -> {
              it_2.setSourceState(init1);
              it_2.setTargetState(state1);
            };
            Transition _doubleArrow_1 = ObjectExtensions.<Transition>operator_doubleArrow(_createTransition, _function_8);
            _transitions.add(_doubleArrow_1);
          };
          final SynchronousStatechartDefinition targetType_1 = ObjectExtensions.<SynchronousStatechartDefinition>operator_doubleArrow(_createSynchronousStatechartDefinition, _function_4);
          EObject _eContainer_5 = source.eContainer();
          final hu.bme.mit.gamma.statechart.interface_.Package sourcePackage_1 = ((hu.bme.mit.gamma.statechart.interface_.Package) _eContainer_5);
          hu.bme.mit.gamma.statechart.interface_.Package _createPackage_1 = this._interfaceModelFactory.createPackage();
          final Procedure1<hu.bme.mit.gamma.statechart.interface_.Package> _function_5 = (hu.bme.mit.gamma.statechart.interface_.Package it_1) -> {
            it_1.setName(sourcePackage_1.getName());
          };
          final hu.bme.mit.gamma.statechart.interface_.Package targetPackage_1 = ObjectExtensions.<hu.bme.mit.gamma.statechart.interface_.Package>operator_doubleArrow(_createPackage_1, _function_5);
          EList<Component> _components_2 = targetPackage_1.getComponents();
          _components_2.add(targetType_1);
          this.trace.put(sourcePackage_1, targetPackage_1);
          SynchronousComponentInstance _createSynchronousComponentInstance = this._compositeModelFactory.createSynchronousComponentInstance();
          final Procedure1<SynchronousComponentInstance> _function_6 = (SynchronousComponentInstance it_1) -> {
            it_1.setName(source.getName());
            it_1.setType(targetType_1);
          };
          final SynchronousComponentInstance targetInstance_1 = ObjectExtensions.<SynchronousComponentInstance>operator_doubleArrow(_createSynchronousComponentInstance, _function_6);
          EList<SynchronousComponentInstance> _components_3 = targetContainer_1.getComponents();
          _components_3.add(targetInstance_1);
          this.trace.put(source, targetInstance_1, targetType_1);
        }
        EObject _eContainer_6 = source.eContainer();
        if ((_eContainer_6 instanceof EnvironmentCascadeCompositeComponent)) {
          EObject _eContainer_7 = source.eContainer();
          final EnvironmentCascadeCompositeComponent sourceComponent_2 = ((EnvironmentCascadeCompositeComponent) _eContainer_7);
          final CascadeCompositeComponent targetContainer_2 = this.trace.getTargetCascadeComponent(sourceComponent_2);
          SynchronousStatechartDefinition _createSynchronousStatechartDefinition_1 = this._statechartModelFactory.createSynchronousStatechartDefinition();
          final Procedure1<SynchronousStatechartDefinition> _function_7 = (SynchronousStatechartDefinition it_1) -> {
            String _name = source.getName();
            String _plus = (_name + "Type");
            it_1.setName(_plus);
            EList<Port> _ports = it_1.getPorts();
            EList<Port> _inports = source.getInports();
            Iterables.<Port>addAll(_ports, _inports);
            EList<Port> _ports_1 = it_1.getPorts();
            EList<Port> _outports = source.getOutports();
            Iterables.<Port>addAll(_ports_1, _outports);
            State _createState = this._statechartModelFactory.createState();
            final Procedure1<State> _function_8 = (State it_2) -> {
              it_2.setName("state1");
            };
            final State state1 = ObjectExtensions.<State>operator_doubleArrow(_createState, _function_8);
            InitialState _createInitialState = this._statechartModelFactory.createInitialState();
            final Procedure1<InitialState> _function_9 = (InitialState it_2) -> {
              it_2.setName("init1");
            };
            final InitialState init1 = ObjectExtensions.<InitialState>operator_doubleArrow(_createInitialState, _function_9);
            EList<Region> _regions = it_1.getRegions();
            Region _createRegion = this._statechartModelFactory.createRegion();
            final Procedure1<Region> _function_10 = (Region it_2) -> {
              it_2.setName("mainRegion");
              EList<StateNode> _stateNodes = it_2.getStateNodes();
              _stateNodes.add(init1);
              EList<StateNode> _stateNodes_1 = it_2.getStateNodes();
              _stateNodes_1.add(state1);
            };
            Region _doubleArrow = ObjectExtensions.<Region>operator_doubleArrow(_createRegion, _function_10);
            _regions.add(_doubleArrow);
            EList<Transition> _transitions = it_1.getTransitions();
            Transition _createTransition = this._statechartModelFactory.createTransition();
            final Procedure1<Transition> _function_11 = (Transition it_2) -> {
              it_2.setSourceState(init1);
              it_2.setTargetState(state1);
            };
            Transition _doubleArrow_1 = ObjectExtensions.<Transition>operator_doubleArrow(_createTransition, _function_11);
            _transitions.add(_doubleArrow_1);
          };
          final SynchronousStatechartDefinition targetType_2 = ObjectExtensions.<SynchronousStatechartDefinition>operator_doubleArrow(_createSynchronousStatechartDefinition_1, _function_7);
          EObject _eContainer_8 = source.eContainer();
          final hu.bme.mit.gamma.statechart.interface_.Package sourcePackage_2 = ((hu.bme.mit.gamma.statechart.interface_.Package) _eContainer_8);
          hu.bme.mit.gamma.statechart.interface_.Package _createPackage_2 = this._interfaceModelFactory.createPackage();
          final Procedure1<hu.bme.mit.gamma.statechart.interface_.Package> _function_8 = (hu.bme.mit.gamma.statechart.interface_.Package it_1) -> {
            it_1.setName(sourcePackage_2.getName());
          };
          final hu.bme.mit.gamma.statechart.interface_.Package targetPackage_2 = ObjectExtensions.<hu.bme.mit.gamma.statechart.interface_.Package>operator_doubleArrow(_createPackage_2, _function_8);
          EList<Component> _components_4 = targetPackage_2.getComponents();
          _components_4.add(targetType_2);
          this.trace.put(sourcePackage_2, targetPackage_2);
          SynchronousComponentInstance _createSynchronousComponentInstance_1 = this._compositeModelFactory.createSynchronousComponentInstance();
          final Procedure1<SynchronousComponentInstance> _function_9 = (SynchronousComponentInstance it_1) -> {
            it_1.setName(source.getName());
            it_1.setType(targetType_2);
          };
          final SynchronousComponentInstance targetInstance_2 = ObjectExtensions.<SynchronousComponentInstance>operator_doubleArrow(_createSynchronousComponentInstance_1, _function_9);
          EList<SynchronousComponentInstance> _components_5 = targetContainer_2.getComponents();
          _components_5.add(targetInstance_2);
          this.trace.put(source, targetInstance_2, targetType_2);
        }
      };
      _xifexpression = this.elementaryEnvironmentComponentInstanceRule = this._batchTransformationRuleFactory.<ElementaryEnvironmentComponentInstancePattern.Match, ElementaryEnvironmentComponentInstancePattern.Matcher>createRule(ElementaryEnvironmentComponentInstancePattern.instance()).action(_function).build();
    }
    return _xifexpression;
  }

  public void dispose() {
    boolean _notEquals = (!Objects.equal(this.transformation, null));
    if (_notEquals) {
      this.transformation.getRuleEngine().dispose();
    }
    this.transformation = null;
    return;
  }
}
