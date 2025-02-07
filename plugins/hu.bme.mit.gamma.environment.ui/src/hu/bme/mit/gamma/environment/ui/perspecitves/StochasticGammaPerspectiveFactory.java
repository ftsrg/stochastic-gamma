/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.environment.ui.perspecitves;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.console.IConsoleConstants;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.help.ui.internal.views.HelpView;

public class StochasticGammaPerspectiveFactory  implements IPerspectiveFactory  {
    private static final String VIEW_ID ="hu.bme.mit.gamma.environment.ui.StochasticGammaView";
    private static final String PLANTUML_ID = "net.sourceforge.plantuml.eclipse.views.PlantUmlView";
    private static final String PLANTUML_SVG_ID = "net.sourceforge.plantuml.eclipse.views.PlantUmlSvgView";
    private static final String BOTTOM = "bottom";
    
	@Override
	public void createInitialLayout(IPageLayout layout) {  
		String editorArea = layout.getEditorArea();
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT,
				0.16f, editorArea);
		left.addView(IPageLayout.ID_PROJECT_EXPLORER);
		// Included to get rid of a warning issued by the workbench
		left.addPlaceholder("org.eclipse.jdt.ui.PackageExplorer");

		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT,0.84f, editorArea);
		right.addView(IPageLayout.ID_OUTLINE);
		right.addView(IConsoleConstants.ID_CONSOLE_VIEW);

		IFolderLayout bottom = layout.createFolder("bottom",IPageLayout.BOTTOM, 0.65f, editorArea);
		bottom.addView(IPageLayout.ID_PROP_SHEET);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addView(IPageLayout.ID_TASK_LIST);
		bottom.addView(IPageLayout.ID_PROGRESS_VIEW);
		bottom.addView(PLANTUML_ID);
		bottom.addView(PLANTUML_SVG_ID);
			/*
				URL url = new URL("https://github.com/ftsrg/gamma");
				PlatformUI.getWorkbench().getBrowserSupport().createBrowser("hu.bme.mit.gamma.environment").openURL(url);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}*/
			//IIntroManager introManager = PlatformUI.getWorkbench().getIntroManager();
			//IIntroPart intro = introManager.getIntro();
			//intro.
		}


}
