package hu.bme.mit.gamma.environment.simulator.transformation.configgen;

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class StatechartConfigGenerator {
  public Object collectStatecharts(final ComponentInstance instance, final Set<StatechartDefinition> statecharts) {
    Object _xblockexpression = null;
    {
      Component component = EnvironmentModelDerivedFeatures.getDerivedType(instance);
      Object _xifexpression = null;
      if ((component instanceof StatechartDefinition)) {
        _xifexpression = Boolean.valueOf(statecharts.add(((StatechartDefinition)component)));
      } else {
        Object _xifexpression_1 = null;
        if ((component instanceof AsynchronousAdapter)) {
          _xifexpression_1 = this.collectStatecharts(((AsynchronousAdapter)component).getWrappedComponent(), statecharts);
        } else {
          if ((component instanceof CompositeComponent)) {
            List<? extends ComponentInstance> _derivedComponents = StatechartModelDerivedFeatures.getDerivedComponents(((CompositeComponent)component));
            for (final ComponentInstance inst : _derivedComponents) {
              this.collectStatecharts(inst, statecharts);
            }
          }
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }

  public String generate(final AnalysisComponent analysisComponent) {
    HashSet<StatechartDefinition> statecharts = new HashSet<StatechartDefinition>();
    this.collectStatecharts(analysisComponent.getAnalyzedComponent(), statecharts);
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final StatechartDefinition statechart : statecharts) {
        String _name = statechart.getName();
        _builder.append(_name);
        _builder.append(" : [");
        {
          Collection<Region> _allRegions = StatechartModelDerivedFeatures.getAllRegions(statechart);
          boolean _hasElements = false;
          for(final Region region : _allRegions) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            _builder.append("\"");
            String _name_1 = region.getName();
            _builder.append(_name_1);
            _builder.append("\"");
          }
        }
        _builder.append("]");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
