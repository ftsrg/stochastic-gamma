package hu.bme.mit.gamma.analysis.transformation;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.analysis.AnalysisAspect;
import hu.bme.mit.gamma.analysis.AnalysisComponent;
import hu.bme.mit.gamma.analysis.EndCondition;
import hu.bme.mit.gamma.analysis.MeanParameter;
import hu.bme.mit.gamma.analysis.ParameterDistribution;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.EventDeclaration;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class Py4JGatewayGenerator {
  public CharSequence generate(final AnalysisComponent component, final String packageName) {
    CharSequence _xblockexpression = null;
    {
      EObject _eContainer = component.eContainer();
      hu.bme.mit.gamma.statechart.interface_.Package pack = ((hu.bme.mit.gamma.statechart.interface_.Package) _eContainer);
      String _xifexpression = null;
      EnvironmentAsynchronousCompositeComponentInstance _analyzedComponent = component.getAnalyzedComponent();
      if ((_analyzedComponent instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
        EnvironmentAsynchronousCompositeComponentInstance _analyzedComponent_1 = component.getAnalyzedComponent();
        _xifexpression = StringExtensions.toFirstUpper(((EnvironmentAsynchronousCompositeComponentInstance) _analyzedComponent_1).getType().getName());
      } else {
        EnvironmentAsynchronousCompositeComponentInstance _analyzedComponent_2 = component.getAnalyzedComponent();
        _xifexpression = StringExtensions.toFirstUpper(((EnvironmentSynchronousCompositeComponentInstance) _analyzedComponent_2).getType().getName());
      }
      String compName = _xifexpression;
      int i = 0;
      final Function1<AnalysisAspect, Interface> _function = (AnalysisAspect a) -> {
        return a.getEvent().getPort().getInterfaceRealization().getInterface();
      };
      List<Interface> interfaces = IterableExtensions.<Interface>toList(IterableExtensions.<Interface>toSet(ListExtensions.<AnalysisAspect, Interface>map(component.getAspect(), _function)));
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package javaenv;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import ");
      _builder.append(packageName);
      _builder.append(".");
      String _lowerCase = pack.getName().toLowerCase();
      _builder.append(_lowerCase);
      _builder.append(".");
      _builder.append(compName);
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      {
        EList<AnalysisAspect> _aspect = component.getAspect();
        for(final AnalysisAspect a : _aspect) {
          _builder.append("import ");
          _builder.append(packageName);
          _builder.append(".interfaces.");
          String _firstUpper = StringExtensions.toFirstUpper(a.getEvent().getPort().getInterfaceRealization().getInterface().getName());
          _builder.append(_firstUpper);
          _builder.append("Interface;");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("//import py4j.GatewayServer; great old times...");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("public class DetModelEntryPoint  {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public ");
      _builder.append(compName, "\t\t\t");
      _builder.append(" detModel=new ");
      _builder.append(compName, "\t\t\t");
      _builder.append("(");
      CharSequence _generateDetmodelParamsInit = TransformationUtility.generateDetmodelParamsInit(component);
      _builder.append(_generateDetmodelParamsInit, "\t\t\t");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      {
        EList<AnalysisAspect> _aspect_1 = component.getAspect();
        for(final AnalysisAspect a_1 : _aspect_1) {
          _builder.append("\t\t\t");
          _builder.append("public MonitorOf");
          String _generateAspectName = TransformationUtility.generateAspectName(a_1);
          _builder.append(_generateAspectName, "\t\t\t");
          _builder.append(" monitorOf");
          String _generateAspectName_1 = TransformationUtility.generateAspectName(a_1);
          _builder.append(_generateAspectName_1, "\t\t\t");
          _builder.append("=new MonitorOf");
          String _generateAspectName_2 = TransformationUtility.generateAspectName(a_1);
          _builder.append(_generateAspectName_2, "\t\t\t");
          _builder.append("();");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<EndCondition> _endcondition = component.getEndcondition();
        for(final EndCondition endCondition : _endcondition) {
          _builder.append("\t\t\t");
          _builder.append("public MonitorOf");
          String _generateEndConditionName = TransformationUtility.generateEndConditionName(endCondition);
          _builder.append(_generateEndConditionName, "\t\t\t");
          _builder.append(" monitorOf");
          String _generateEndConditionName_1 = TransformationUtility.generateEndConditionName(endCondition);
          _builder.append(_generateEndConditionName_1, "\t\t\t");
          _builder.append("= new MonitorOf");
          String _generateEndConditionName_2 = TransformationUtility.generateEndConditionName(endCondition);
          _builder.append(_generateEndConditionName_2, "\t\t\t");
          _builder.append("();");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public DetModelEntryPoint(){");
      _builder.newLine();
      {
        EList<AnalysisAspect> _aspect_2 = component.getAspect();
        for(final AnalysisAspect a_2 : _aspect_2) {
          _builder.append("\t\t\t\t");
          _builder.append("detModel.get");
          String _firstUpper_1 = StringExtensions.toFirstUpper(a_2.getEvent().getPort().getName());
          _builder.append(_firstUpper_1, "\t\t\t\t");
          _builder.append("().registerListener(monitorOf");
          String _generateAspectName_3 = TransformationUtility.generateAspectName(a_2);
          _builder.append(_generateAspectName_3, "\t\t\t\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<EndCondition> _endcondition_1 = component.getEndcondition();
        for(final EndCondition endCondition_1 : _endcondition_1) {
          _builder.append("\t\t\t\t");
          _builder.append("detModel.get");
          String _firstUpper_2 = StringExtensions.toFirstUpper(endCondition_1.getEvent().getPort().getName());
          _builder.append(_firstUpper_2, "\t\t\t\t");
          _builder.append("().registerListener(monitorOf");
          String _generateEndConditionName_3 = TransformationUtility.generateEndConditionName(endCondition_1);
          _builder.append(_generateEndConditionName_3, "\t\t\t\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public void reset(");
      CharSequence _generateDetmodelParamsReset = TransformationUtility.generateDetmodelParamsReset(component);
      _builder.append(_generateDetmodelParamsReset, "\t\t\t");
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("detModel=null;");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("System.gc();");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("detModel=new ");
      _builder.append(compName, "\t\t\t\t");
      _builder.append("(");
      CharSequence _generateDetmodelParamsResetInit = TransformationUtility.generateDetmodelParamsResetInit(component);
      _builder.append(_generateDetmodelParamsResetInit, "\t\t\t\t");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      {
        EList<AnalysisAspect> _aspect_3 = component.getAspect();
        for(final AnalysisAspect a_3 : _aspect_3) {
          _builder.append("\t\t\t\t");
          _builder.append("detModel.get");
          String _firstUpper_3 = StringExtensions.toFirstUpper(a_3.getEvent().getPort().getName());
          _builder.append(_firstUpper_3, "\t\t\t\t");
          _builder.append("().registerListener(monitorOf");
          String _generateAspectName_4 = TransformationUtility.generateAspectName(a_3);
          _builder.append(_generateAspectName_4, "\t\t\t\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<EndCondition> _endcondition_2 = component.getEndcondition();
        for(final EndCondition endCondition_2 : _endcondition_2) {
          _builder.append("\t\t\t\t");
          _builder.append("detModel.get");
          String _firstUpper_4 = StringExtensions.toFirstUpper(endCondition_2.getEvent().getPort().getName());
          _builder.append(_firstUpper_4, "\t\t\t\t");
          _builder.append("().registerListener(monitorOf");
          String _generateEndConditionName_4 = TransformationUtility.generateEndConditionName(endCondition_2);
          _builder.append(_generateEndConditionName_4, "\t\t\t\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t\t\t");
      _builder.append("*/");
      _builder.newLine();
      {
        EList<AnalysisAspect> _aspect_4 = component.getAspect();
        for(final AnalysisAspect a_4 : _aspect_4) {
          _builder.append("\t\t\t\t");
          _builder.append("monitorOf");
          String _generateAspectName_5 = TransformationUtility.generateAspectName(a_4);
          _builder.append(_generateAspectName_5, "\t\t\t\t");
          _builder.append(".reset();");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<EndCondition> _endcondition_3 = component.getEndcondition();
        for(final EndCondition endCondition_3 : _endcondition_3) {
          _builder.append("\t\t\t\t");
          _builder.append("monitorOf");
          String _generateEndConditionName_5 = TransformationUtility.generateEndConditionName(endCondition_3);
          _builder.append(_generateEndConditionName_5, "\t\t\t\t");
          _builder.append(".reset();");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t\t\t");
      _builder.append("detModel.reset();");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public ");
      _builder.append(compName, "\t\t\t");
      _builder.append(" getDetModel(){");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("return detModel;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("public ");
      _builder.append(compName, "\t\t\t");
      _builder.append(" get");
      String _firstUpper_5 = StringExtensions.toFirstUpper(component.getAnalyzedComponent().getName());
      _builder.append(_firstUpper_5, "\t\t\t");
      _builder.append("(){");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("return detModel;");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}   ");
      _builder.newLine();
      _builder.append("\t\t\t\t\t\t\t\t");
      _builder.newLine();
      {
        EList<AnalysisAspect> _aspect_5 = component.getAspect();
        for(final AnalysisAspect a_5 : _aspect_5) {
          _builder.append("\t\t\t");
          CharSequence _generateMonitor = this.generateMonitor(a_5);
          _builder.append(_generateMonitor, "\t\t\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t\t");
      _builder.newLine();
      {
        EList<EndCondition> _endcondition_4 = component.getEndcondition();
        for(final EndCondition endCondition_4 : _endcondition_4) {
          _builder.append("\t\t\t");
          CharSequence _generateMonitor_1 = this.generateMonitor(endCondition_4);
          _builder.append(_generateMonitor_1, "\t\t\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence generateMonitor(final AnalysisAspect a) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public class MonitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(a);
    _builder.append(_generateAspectName, "\t");
    _builder.append(" implements ");
    String _firstUpper = StringExtensions.toFirstUpper(a.getEvent().getPort().getInterfaceRealization().getInterface().getName());
    _builder.append(_firstUpper, "\t");
    _builder.append("Interface.Listener.Provided {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public String state=\"run\";");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public int freq=0;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public double parameter=0.0/0.0;//NaN is the initial value intentionally ");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public double meanParameter=0.0;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public double sumParameter=0.0;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public void getValue(){}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public void reset(){");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("state=\"run\";");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("freq=0;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("parameter=0.0/0.0;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("sumParameter=0.0;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("meanParameter=0.0;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t\t");
    _builder.newLine();
    {
      EList<EventDeclaration> _events = a.getEvent().getPort().getInterfaceRealization().getInterface().getEvents();
      for(final EventDeclaration event : _events) {
        _builder.append("\t\t\t\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("public void raise");
        String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
        _builder.append(_firstUpper_1, "\t\t\t\t");
        _builder.append("(");
        CharSequence _generateParameters = TransformationUtility.generateParameters(event.getEvent());
        _builder.append(_generateParameters, "\t\t\t\t");
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        {
          Event _event = event.getEvent();
          Event _event_1 = a.getEvent().getEvent();
          boolean _equals = Objects.equal(_event, _event_1);
          if (_equals) {
            _builder.append("\t\t\t\t");
            _builder.append("\t");
            _builder.append("state=\"stop\";");
            _builder.newLine();
            _builder.append("\t\t\t\t");
            _builder.append("\t");
            _builder.append("freq++;");
            _builder.newLine();
            {
              if ((a instanceof ParameterDistribution)) {
                _builder.append("\t\t\t\t");
                _builder.append("\t");
                _builder.append("parameter=");
                CharSequence _generateName = TransformationUtility.generateName(((ParameterDistribution)a).getParameter());
                _builder.append(_generateName, "\t\t\t\t\t");
                _builder.newLineIfNotEmpty();
              }
            }
            {
              if ((a instanceof MeanParameter)) {
                _builder.append("\t\t\t\t");
                _builder.append("\t");
                _builder.append("sumParameter=sumParameter+");
                CharSequence _generateName_1 = TransformationUtility.generateName(((MeanParameter)a).getParameter());
                _builder.append(_generateName_1, "\t\t\t\t\t");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t\t\t");
                _builder.append("\t");
                _builder.append("meanParameter=sumParameter/freq;");
                _builder.newLine();
              }
            }
          }
        }
        _builder.append("\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateMonitor(final EndCondition e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public class MonitorOf");
    String _generateEndConditionName = TransformationUtility.generateEndConditionName(e);
    _builder.append(_generateEndConditionName, "\t");
    _builder.append(" implements ");
    String _firstUpper = StringExtensions.toFirstUpper(e.getEvent().getPort().getInterfaceRealization().getInterface().getName());
    _builder.append(_firstUpper, "\t");
    _builder.append("Interface.Listener.Provided {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public String state=\"run\";");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public void reset(){");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("state=\"run\";");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t\t");
    _builder.newLine();
    {
      EList<EventDeclaration> _events = e.getEvent().getPort().getInterfaceRealization().getInterface().getEvents();
      for(final EventDeclaration event : _events) {
        _builder.append("\t\t\t\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("public void raise");
        String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
        _builder.append(_firstUpper_1, "\t\t\t\t");
        _builder.append("(");
        CharSequence _generateParameters = TransformationUtility.generateParameters(event.getEvent());
        _builder.append(_generateParameters, "\t\t\t\t");
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        {
          Event _event = event.getEvent();
          Event _event_1 = e.getEvent().getEvent();
          boolean _equals = Objects.equal(_event, _event_1);
          if (_equals) {
            _builder.append("\t\t\t\t");
            _builder.append("\t");
            _builder.append("state=\"stop\";");
            _builder.newLine();
          }
        }
        _builder.append("\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
