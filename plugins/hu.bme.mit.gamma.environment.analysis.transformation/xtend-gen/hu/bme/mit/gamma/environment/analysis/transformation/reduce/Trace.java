package hu.bme.mit.gamma.environment.analysis.transformation.reduce;

import com.google.common.base.Preconditions;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AbstractTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTraceFactory;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentAsynchronousCompositeCopmponentTracePattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentTracePattern;
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeCopmponentTracePattern;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.SimpleChannel;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Trace {
  @Extension
  protected final ReducerTraceFactory traceabilityFactory = ReducerTraceFactory.eINSTANCE;

  protected final ReducerTrace trace;

  protected final ViatraQueryEngine tracingEngine;

  public Trace(final hu.bme.mit.gamma.statechart.interface_.Package sourcePackage, final hu.bme.mit.gamma.statechart.interface_.Package targetPackage) {
    ReducerTrace _createReducerTrace = this.traceabilityFactory.createReducerTrace();
    final Procedure1<ReducerTrace> _function = (ReducerTrace it) -> {
      it.setSource(it.getSource());
      it.setTarget(it.getTarget());
    };
    ReducerTrace _doubleArrow = ObjectExtensions.<ReducerTrace>operator_doubleArrow(_createReducerTrace, _function);
    this.trace = _doubleArrow;
    EMFScope _eMFScope = new EMFScope(this.trace);
    this.tracingEngine = ViatraQueryEngine.on(_eMFScope);
  }

  public hu.bme.mit.gamma.statechart.interface_.Package getSource() {
    return this.trace.getSource();
  }

  public hu.bme.mit.gamma.statechart.interface_.Package getTarget() {
    return this.trace.getTarget();
  }

  public boolean put(final hu.bme.mit.gamma.statechart.interface_.Package source, final hu.bme.mit.gamma.statechart.interface_.Package target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      PackageTrace _createPackageTrace = this.traceabilityFactory.createPackageTrace();
      final Procedure1<PackageTrace> _function = (PackageTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      PackageTrace _doubleArrow = ObjectExtensions.<PackageTrace>operator_doubleArrow(_createPackageTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final EnvironmentAsynchronousCompositeComponent source, final AsynchronousCompositeComponent target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      AsynchronousCompositeComponentTrace _createAsynchronousCompositeComponentTrace = this.traceabilityFactory.createAsynchronousCompositeComponentTrace();
      final Procedure1<AsynchronousCompositeComponentTrace> _function = (AsynchronousCompositeComponentTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      AsynchronousCompositeComponentTrace _doubleArrow = ObjectExtensions.<AsynchronousCompositeComponentTrace>operator_doubleArrow(_createAsynchronousCompositeComponentTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final EnvironmentSynchronousCompositeComponent source, final SynchronousCompositeComponent target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      SynchronousCompositeComponentTrace _createSynchronousCompositeComponentTrace = this.traceabilityFactory.createSynchronousCompositeComponentTrace();
      final Procedure1<SynchronousCompositeComponentTrace> _function = (SynchronousCompositeComponentTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      SynchronousCompositeComponentTrace _doubleArrow = ObjectExtensions.<SynchronousCompositeComponentTrace>operator_doubleArrow(_createSynchronousCompositeComponentTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final EnvironmentCascadeCompositeComponent source, final CascadeCompositeComponent target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      CascadeCompositeComponentTrace _createCascadeCompositeComponentTrace = this.traceabilityFactory.createCascadeCompositeComponentTrace();
      final Procedure1<CascadeCompositeComponentTrace> _function = (CascadeCompositeComponentTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      CascadeCompositeComponentTrace _doubleArrow = ObjectExtensions.<CascadeCompositeComponentTrace>operator_doubleArrow(_createCascadeCompositeComponentTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final EnvironmentAsynchronousCompositeComponentInstance source, final AsynchronousComponentInstance target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      AsynchronousCompositeInstanceTrace _createAsynchronousCompositeInstanceTrace = this.traceabilityFactory.createAsynchronousCompositeInstanceTrace();
      final Procedure1<AsynchronousCompositeInstanceTrace> _function = (AsynchronousCompositeInstanceTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      AsynchronousCompositeInstanceTrace _doubleArrow = ObjectExtensions.<AsynchronousCompositeInstanceTrace>operator_doubleArrow(_createAsynchronousCompositeInstanceTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final EnvironmentSynchronousCompositeComponentInstance source, final SynchronousComponentInstance target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      SynchronousCompositeInstanceTrace _createSynchronousCompositeInstanceTrace = this.traceabilityFactory.createSynchronousCompositeInstanceTrace();
      final Procedure1<SynchronousCompositeInstanceTrace> _function = (SynchronousCompositeInstanceTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      SynchronousCompositeInstanceTrace _doubleArrow = ObjectExtensions.<SynchronousCompositeInstanceTrace>operator_doubleArrow(_createSynchronousCompositeInstanceTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final EnvironmentCascadeCompositeComponentInstance source, final SynchronousComponentInstance target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      CascadeCompositeInstanceTrace _createCascadeCompositeInstanceTrace = this.traceabilityFactory.createCascadeCompositeInstanceTrace();
      final Procedure1<CascadeCompositeInstanceTrace> _function = (CascadeCompositeInstanceTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      CascadeCompositeInstanceTrace _doubleArrow = ObjectExtensions.<CascadeCompositeInstanceTrace>operator_doubleArrow(_createCascadeCompositeInstanceTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final ElementaryEnvironmentComponentInstance source, final ComponentInstance instanceTarget, final StatechartDefinition typeTarget) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      hu.bme.mit.gamma.statechart.interface_.Package _target = this.getTarget();
      boolean _tripleNotEquals = (_target != null);
      Preconditions.checkArgument(_tripleNotEquals);
      EList<AbstractTrace> _traces = this.trace.getTraces();
      ElementaryTrace _createElementaryTrace = this.traceabilityFactory.createElementaryTrace();
      final Procedure1<ElementaryTrace> _function = (ElementaryTrace it) -> {
        it.setSource(source);
        it.setInstanceTarget(instanceTarget);
        it.setTypeTarget(typeTarget);
      };
      ElementaryTrace _doubleArrow = ObjectExtensions.<ElementaryTrace>operator_doubleArrow(_createElementaryTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final PortBinding source, final PortBinding target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      BindingTrace _createBindingTrace = this.traceabilityFactory.createBindingTrace();
      final Procedure1<BindingTrace> _function = (BindingTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      BindingTrace _doubleArrow = ObjectExtensions.<BindingTrace>operator_doubleArrow(_createBindingTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final SimpleChannel source, final SimpleChannel target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      SimpleChannelTrace _createSimpleChannelTrace = this.traceabilityFactory.createSimpleChannelTrace();
      final Procedure1<SimpleChannelTrace> _function = (SimpleChannelTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      SimpleChannelTrace _doubleArrow = ObjectExtensions.<SimpleChannelTrace>operator_doubleArrow(_createSimpleChannelTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public boolean put(final BroadcastChannel source, final BroadcastChannel target) {
    boolean _xblockexpression = false;
    {
      Preconditions.checkArgument((source != null));
      Preconditions.checkArgument((target != null));
      EList<AbstractTrace> _traces = this.trace.getTraces();
      BroadcastChannelTrace _createBroadcastChannelTrace = this.traceabilityFactory.createBroadcastChannelTrace();
      final Procedure1<BroadcastChannelTrace> _function = (BroadcastChannelTrace it) -> {
        it.setSource(source);
        it.setTarget(target);
      };
      BroadcastChannelTrace _doubleArrow = ObjectExtensions.<BroadcastChannelTrace>operator_doubleArrow(_createBroadcastChannelTrace, _function);
      _xblockexpression = _traces.add(_doubleArrow);
    }
    return _xblockexpression;
  }

  public AsynchronousCompositeComponent getTargetAsynchronousComponent(final EnvironmentAsynchronousCompositeComponent source) {
    Preconditions.checkArgument((source != null));
    final Set<AsynchronousCompositeComponent> matches = EnvironmentAsynchronousCompositeCopmponentTracePattern.Matcher.on(this.tracingEngine).getAllValuesOftarget(source);
    int _size = matches.size();
    boolean _equals = (_size == 1);
    Preconditions.checkState(_equals, Integer.valueOf(matches.size()));
    return IterableExtensions.<AsynchronousCompositeComponent>head(matches);
  }

  public SynchronousCompositeComponent getTargetSynchronousComponent(final EnvironmentSynchronousCompositeComponent source) {
    Preconditions.checkArgument((source != null));
    final Set<SynchronousCompositeComponent> matches = EnvironmentSynchronousCompositeCopmponentTracePattern.Matcher.on(this.tracingEngine).getAllValuesOftarget(source);
    int _size = matches.size();
    boolean _equals = (_size == 1);
    Preconditions.checkState(_equals, Integer.valueOf(matches.size()));
    return IterableExtensions.<SynchronousCompositeComponent>head(matches);
  }

  public CascadeCompositeComponent getTargetCascadeComponent(final EnvironmentCascadeCompositeComponent source) {
    Preconditions.checkArgument((source != null));
    final Set<CascadeCompositeComponent> matches = EnvironmentCascadeCompositeComponentTracePattern.Matcher.on(this.tracingEngine).getAllValuesOftarget(source);
    int _size = matches.size();
    boolean _equals = (_size == 1);
    Preconditions.checkState(_equals, Integer.valueOf(matches.size()));
    return IterableExtensions.<CascadeCompositeComponent>head(matches);
  }
}
