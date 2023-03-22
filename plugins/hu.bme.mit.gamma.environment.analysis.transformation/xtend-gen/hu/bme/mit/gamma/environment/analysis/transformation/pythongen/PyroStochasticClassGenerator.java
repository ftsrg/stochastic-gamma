package hu.bme.mit.gamma.environment.analysis.transformation.pythongen;

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.analysis.transformation.util.ElementaryComponentCollector;
import hu.bme.mit.gamma.environment.analysis.transformation.util.EnvironmentConnections;
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticExpression;
import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class PyroStochasticClassGenerator {
  public static CharSequence generate(final AnalysisComponent analysis_component, final String packageName) {
    CharSequence _xblockexpression = null;
    {
      EObject _xifexpression = null;
      EnvironmentAsynchronousCompositeComponentInstance _analyzedComponent = analysis_component.getAnalyzedComponent();
      if ((_analyzedComponent instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
        EnvironmentAsynchronousCompositeComponentInstance _analyzedComponent_1 = analysis_component.getAnalyzedComponent();
        _xifexpression = ((EnvironmentAsynchronousCompositeComponentInstance) _analyzedComponent_1).getType();
      } else {
        EnvironmentAsynchronousCompositeComponentInstance _analyzedComponent_2 = analysis_component.getAnalyzedComponent();
        _xifexpression = ((EnvironmentSynchronousCompositeComponentInstance) _analyzedComponent_2).getType();
      }
      EObject component = ((EObject)_xifexpression);
      ArrayList<ComponentInstance> stack = CollectionLiterals.<ComponentInstance>newArrayList();
      List<EnvironmentConnections> connections = ElementaryComponentCollector.collect(analysis_component.getAnalyzedComponent(), stack);
      ExpressionEvaluator expEval = ExpressionEvaluator.INSTANCE;
      PyroComponentInstanceGenerator componentGenerator = new PyroComponentInstanceGenerator(packageName);
      PyroDistGenerator distGenerator = new PyroDistGenerator(packageName);
      Integer param_cntr = new Integer(0);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class StochasticEventGenerator():");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def __init__(self,detmodel):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.detmodel=detmodel");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.time=0.0");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events=[]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.dists=[]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("# create Python objects from elementary stochastic components");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.components=dict()");
      _builder.newLine();
      {
        EList<Expression> _arguments = analysis_component.getAnalyzedComponent().getArguments();
        boolean _hasElements = false;
        for(final Expression arg : _arguments) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(",", "\t\t");
          }
          {
            if ((arg instanceof StochasticExpression)) {
              _builder.append("\t\t");
              _builder.append("self.");
              String _name = analysis_component.getAnalyzedComponent().getType().getParameterDeclarations().get((param_cntr).intValue()).getName();
              _builder.append(_name, "\t\t");
              _builder.append("=torch.tensor([0.0])");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              _builder.append("#");
              Integer _plusPlus = param_cntr++;
              _builder.append(_plusPlus, "\t\t");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("\t\t");
      _builder.append("#");
      _builder.append(param_cntr = Integer.valueOf(0), "\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("# definition of elementary stochastic components");
      _builder.newLine();
      _builder.append("\t\t");
      CharSequence _gennerateInstances = componentGenerator.gennerateInstances(connections);
      _builder.append(_gennerateInstances, "\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("# register input interfaces of elementary stochastic components");
      _builder.newLine();
      _builder.append("\t\t");
      CharSequence _generateConfigurations = componentGenerator.generateConfigurations(connections);
      _builder.append(_generateConfigurations, "\t\t");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def reset(self):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.time=0");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events.clear()");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("for dist in self.dists:");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("dist.reset()");
      _builder.newLine();
      {
        EList<Expression> _arguments_1 = analysis_component.getAnalyzedComponent().getArguments();
        boolean _hasElements_1 = false;
        for(final Expression arg_1 : _arguments_1) {
          if (!_hasElements_1) {
            _hasElements_1 = true;
          } else {
            _builder.appendImmediate(",", "\t\t");
          }
          {
            if ((arg_1 instanceof StochasticExpression)) {
              _builder.append("\t\t");
              _builder.append("self.");
              String _name_1 = analysis_component.getAnalyzedComponent().getType().getParameterDeclarations().get((param_cntr).intValue()).getName();
              _builder.append(_name_1, "\t\t");
              _builder.append("[0]=pyro.sample(\"param_");
              String _string = param_cntr.toString();
              _builder.append(_string, "\t\t");
              _builder.append("\",");
              CharSequence _generateDitribution = distGenerator.generateDitribution(((StochasticExpression) arg_1).getRandomvariable());
              _builder.append(_generateDitribution, "\t\t");
              _builder.append(")");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t\t");
          _builder.append("#");
          Integer _plusPlus_1 = param_cntr++;
          _builder.append(_plusPlus_1, "\t\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t");
      _builder.append("self.detmodel.reset(");
      CharSequence _generateDetmodelParamsNew = TransformationUtility.generateDetmodelParamsNew(analysis_component);
      _builder.append(_generateDetmodelParamsNew, "\t\t");
      _builder.append(")");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def generateEvents(self):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("for component in list(self.components.values()):");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("component.generateEvents()");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def popEvent(self):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("mintime=10000000000.0");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("min_i=0");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("for i in range (len(self.events)):");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("if self.events[i].eventTime<mintime:");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("min_i=i");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("mintime=self.events[min_i].eventTime");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("event=self.events[min_i]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events.remove(event)");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return event");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
}
