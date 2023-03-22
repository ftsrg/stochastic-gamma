package hu.bme.mit.gamma.environment.analysis.commandhandler;

import hu.bme.mit.gamma.codegeneration.java.commandhandler.CommandHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class AnalysisCommandHandler extends CommandHandler {
  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    long t0 = System.currentTimeMillis();
    ISelection sel = HandlerUtil.getActiveMenuSelection(event);
    InputOutput.<String>println("Run analysis...--------------------------------------------");
    if ((sel instanceof IStructuredSelection)) {
      int _size = ((IStructuredSelection)sel).size();
      boolean _equals = (_size == 1);
      if (_equals) {
      }
    }
    long t1 = System.currentTimeMillis();
    InputOutput.<String>println("Analysis has been finnished--------------------------------------------------------------");
    InputOutput.<String>print("Elapsed time: ");
    InputOutput.<Double>print(Double.valueOf(((t1 - t0) / 1000.0)));
    InputOutput.<String>println(" s");
    return null;
  }
}
