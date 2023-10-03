package hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen;

import hu.bme.mit.gamma.environment.model.EnvironmentComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;

@SuppressWarnings("all")
public class PlantUMLTransformer {
  public String getComponentPlantUmlCode(final Component component) {
    if ((component instanceof StatechartDefinition)) {
      final StatechartDefinition statechartDefinition = ((StatechartDefinition) component);
      StatechartToPlantUmlTransformer transformer = new StatechartToPlantUmlTransformer(statechartDefinition);
      return transformer.execute();
    } else {
      if ((component instanceof EnvironmentComponent)) {
        final CompositeComponent composite = ((CompositeComponent) component);
        EnvironmentToPlantUmlTransformer transformer_1 = new EnvironmentToPlantUmlTransformer(composite);
        return transformer_1.execute();
      } else {
        if ((component instanceof AsynchronousAdapter)) {
          final AsynchronousAdapter adapter = ((AsynchronousAdapter) component);
          AdapterToPlantUmlTransformer transformer_2 = new AdapterToPlantUmlTransformer(adapter);
          return transformer_2.execute();
        }
      }
    }
    if ((component instanceof CompositeComponent)) {
      CompositeComponent composite_1 = ((CompositeComponent) component);
      CompositeToPlantUmlTransformer transformer_3 = new CompositeToPlantUmlTransformer(composite_1);
      return transformer_3.execute();
    }
    return "";
  }
}
