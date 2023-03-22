package hu.bme.mit.gamma.environment.analysis.commandhandler

import hu.bme.mit.gamma.codegeneration.java.commandhandler.CommandHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.resources.IFile
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.ui.handlers.HandlerUtil

class AnalysisCommandHandler extends CommandHandler {


	override Object execute(ExecutionEvent event) throws ExecutionException{
		var t0 = System.currentTimeMillis()
		var sel = HandlerUtil.getActiveMenuSelection(event);
		println("Run analysis...--------------------------------------------")

		if (sel instanceof IStructuredSelection) {
			if (sel.size() == 1) {
				/*var proc=new AnalysisProcess
				if (sel.getFirstElement() instanceof IFile) {
					var file = sel.getFirstElement() as IFile;
					proc.start(file)
				
				}*/
			}
		}

		var t1 = System.currentTimeMillis()
		println("Analysis has been finnished--------------------------------------------------------------");
		print("Elapsed time: ")
		print((t1 - t0) / 1000.0)
		println(" s");
		return null;
	}

}
