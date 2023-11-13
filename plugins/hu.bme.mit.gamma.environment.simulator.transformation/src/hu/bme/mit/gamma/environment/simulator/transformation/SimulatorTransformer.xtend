package hu.bme.mit.gamma.environment.simulator.transformation

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import java.util.HashSet
import hu.bme.mit.gamma.statechart.interface_.Component

import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import java.util.logging.Logger
import java.util.logging.Level
import hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen.PlantUMLTransformer
import net.sourceforge.plantuml.util.DiagramData
import hu.bme.mit.gamma.util.FileUtil
import java.io.File
import hu.bme.mit.gamma.environment.simulator.transformation.pythongen.ServerGenerator
import hu.bme.mit.gamma.environment.simulator.transformation.configgen.ConfigGenerator
import hu.bme.mit.gamma.environment.simulator.transformation.configgen.StatechartConfigGenerator
import hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen.AnalysisToPlantUMLTransformer

class SimulatorTransformer {
	
	protected Logger logger = Logger.getLogger("GammaLogger");
	
	def transform (AnalysisComponent analysisComponent,String basePackageURI){
		var components = new HashSet<Component>
		var futil = FileUtil.INSTANCE
		var transformedComponents = new HashSet<Component>
		var generatorPlantUML = new PlantUMLTransformer
		var generatorPython = new ServerGenerator
		var generatorConfig = new ConfigGenerator
		var generatorSctConfig = new StatechartConfigGenerator
		var analysisTransformer = new AnalysisToPlantUMLTransformer
		components.add(analysisComponent.analyzedComponent.type)
		while (! components.isEmpty){
			val component=components.get(0)
			components.remove(component)
			transformedComponents.add(component)
			for (instance : component.allInstances){
				val type=instance.derivedType
				if (! transformedComponents.contains(type)){
					components.add(type)
				}
			}
			logger.log(Level.INFO,"Transforming component to SVG: "+component.name)
			var source = generatorPlantUML.getComponentPlantUmlCode(component)
			var diagramData = new DiagramData(source)
			var svg = diagramData.getSvg(0)
			futil.saveString(basePackageURI+File.separator+"simulator-gen"+File.separator+component.name+".svg",svg)
		}
		var server = generatorPython.generate(analysisComponent)
		futil.saveString(basePackageURI+File.separator+"simulator-gen"+File.separator+"webserver.py",server)
		var config = generatorConfig.generate(analysisComponent)
		futil.saveString(basePackageURI+File.separator+"simulator-gen"+File.separator+"config.yml",config)
		var sct_config = generatorSctConfig.generate(analysisComponent)
		futil.saveString(basePackageURI+File.separator+"simulator-gen"+File.separator+"config_sct.yml",sct_config)
		var overall_puml=analysisTransformer.executeWBS(analysisComponent.analyzedComponent,analysisComponent.name)
		var diagramData2 = new DiagramData(overall_puml)
		var svg = diagramData2.getSvg(0)
		futil.saveString(basePackageURI+File.separator+"simulator-gen"+File.separator+"overall_structure.svg",svg)
	}
	
}