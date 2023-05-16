package hu.bme.mit.gamma.logictable.transformation

import hu.bme.mit.gamma.statechart.interface_.Interface
import java.util.List
import java.io.FileInputStream
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.CellType
import java.util.ArrayList
import java.io.FileNotFoundException
import java.io.IOException
import hu.bme.mit.gamma.dialog.DialogUtil
import java.util.Iterator
import org.apache.poi.ss.usermodel.Cell
import hu.bme.mit.gamma.statechart.interface_.Persistency
import hu.bme.mit.gamma.expression.model.EnumerableTypeDefinition
import hu.bme.mit.gamma.expression.model.TypeDeclaration
import hu.bme.mit.gamma.expression.model.EnumerationTypeDefinition
import hu.bme.mit.gamma.expression.model.TypeReference
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition
import hu.bme.mit.gamma.expression.model.RationalTypeDefinition
import hu.bme.mit.gamma.expression.model.DecimalTypeDefinition
import org.apache.poi.ss.usermodel.Row
import java.io.File
import java.util.Set
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy

class TableTransformation {
	
	def isBlank(Cell cell){
			if (cell === null || cell.getCellType() == CellType.BLANK){
				return true
			}
			if (cell.cellType == CellType.STRING) {
				if (cell.stringCellValue == "") {
					return true			
				} 
			} 
			return false
	}
	
	protected def readIn(Row row){
		var cells=new ArrayList<String>()
		var i=0
		while (true) {
			var cell=row.getCell(i,MissingCellPolicy.RETURN_BLANK_AS_NULL)
			if (isBlank(cell)){
				return cells
			} 
			cells.add(cell.stringCellValue)
			i++
		}
	}
	
	protected def readOut(Row row){
		var cell = null as Cell
		var cells=new ArrayList<String>()
		var i=0
		do {
			cell=row.getCell(i)
			i++
		}while(!isBlank(cell));
		while (true) {
			cell=row.getCell(i)
			if (cell === null || cell.getCellType() == CellType.BLANK){
				return cells
			}
			if (cell.cellType == CellType.STRING) {
				if (cell.stringCellValue == "") {
					return cells			
				} 
			} 
			cells.add(cell.stringCellValue)
			i++
		}
	}
	
	protected def getCondition(Interface _interface,Cell cell,String portName){
		var event=_interface.events.map[e | e.event]
						.filter[e | e.persistency==Persistency.PERSISTENT]
						.toList.get(0)
		var param=event.parameterDeclarations.get(0)
		if (cell.cellType == CellType.BLANK){
			return "true"
		}
		if (cell.cellType == CellType.STRING){
			if (cell.stringCellValue=="any"){
				return "true"
			}
		}
		if (param.type instanceof TypeReference){
			val type=(param.type as TypeReference).reference
			if (type.type instanceof EnumerableTypeDefinition){
				if (cell.cellType == CellType.STRING){
					return '''«portName».«event.name»::«param.name»==«type.name»::«cell.stringCellValue»'''
				} else {
					throw new UnsupportedOperationException("Wrong cell type in the excel table. Cell type must be string. Type name: "+type.name)
				}
			} else {
				throw new UnsupportedOperationException("Only enumeration type reference are supported in the Gamma model. Type name: "+type.name)
			}
		}
		if (param.type instanceof IntegerTypeDefinition || param.type instanceof RationalTypeDefinition  || param.type instanceof DecimalTypeDefinition ){
			if (cell.cellType==CellType.NUMERIC){
				return '''«portName».«event.name»::«param.name»==«cell.numericCellValue.toString»'''
			}else if(cell.cellType == CellType.STRING){
				return '''«portName».«event.name»::«param.name» «cell.stringCellValue.toString»'''
			}else {
				throw new UnsupportedOperationException("Wrong cell type in the excel table. Cell type must be string or numeric.")
			}
		}
		return ""
	}
		
	protected def getEffect(Interface _interface,Cell cell,String portName){
		var event=_interface.events.map[e | e.event]
						.filter[e | e.persistency==Persistency.PERSISTENT]
						.toList.get(0)
		var param=event.parameterDeclarations.get(0)
		if (cell.cellType == CellType.BLANK){
			return ""
		}
		if (cell.cellType == CellType.STRING){
			if (cell.stringCellValue=="NE"){
				return ""
			}
		}
		if (param.type instanceof TypeReference){
			val type=(param.type as TypeReference).reference
			if (type.type instanceof EnumerableTypeDefinition){
				if (cell.cellType == CellType.STRING){
					return '''raise «portName».«event.name»(«type.name»::«cell.stringCellValue»);'''
				} else {
					throw new UnsupportedOperationException("Wrong cell type in the excel table. Cell type must be string. Type name: "+type.name)
				}
			} else {
				throw new UnsupportedOperationException("Only enumeration type reference are supported in the Gamma model. Type name: "+type.name)
			}
		}
		if (param.type instanceof IntegerTypeDefinition || param.type instanceof RationalTypeDefinition  || param.type instanceof DecimalTypeDefinition ){
			if (cell.cellType==CellType.NUMERIC){
				return '''raise «portName».«event.name»(«cell.numericCellValue.toString»);'''
			}else if(cell.cellType == CellType.STRING){
				return '''raise «portName».«event.name»(«cell.stringCellValue.toString»);'''
			}else {
				throw new UnsupportedOperationException("Wrong cell type in the excel table. Cell type must be string or numeric.")
			}
		}
		return ""
	}
	

	
	def convert2IF(List<String> ifNames,Set<Interface> interfaces){
		var ifList=new ArrayList<Interface>()
		for (ifName : ifNames){
			var found=false
			for (i : interfaces){
				if (i.name==ifName){
					ifList.add(i)
					found=true
				}
			}
			if(!found){
				throw new UnsupportedOperationException("Interface type is not found in interface list: "+ifName)
			}
		}
		return ifList
	}
	
	def generate(String file,Set<Interface> interfaces, String fileName, Set<String> interfacePaths){
		
		var inPortNames=new ArrayList<String>()
		var outPortNames=new ArrayList<String>()
		
		var inPortIfNames=new ArrayList<String>()
		var outPortIfNames=new ArrayList<String>()
		var inInterfaces=new ArrayList<Interface>()
		var outInterfaces=new ArrayList<Interface>()
		
        try {
        	val excelFile=new FileInputStream(file)
            val workbook = new XSSFWorkbook(excelFile);
            val datatypeSheet = workbook.getSheetAt(0);
            val compName=datatypeSheet.sheetName
            var rowIterator = datatypeSheet.iterator();
            
            var headerRow=rowIterator.next
			inPortNames=readIn(headerRow)
			outPortNames=readOut(headerRow)
			var ifRow=rowIterator.next
			inPortIfNames=readIn(ifRow)
			outPortIfNames=readOut(ifRow)
			inInterfaces=convert2IF(inPortIfNames,interfaces)
			outInterfaces=convert2IF(outPortIfNames,interfaces)
            
            return 
            '''
            package «fileName»
            «FOR ifPackage : interfacePaths»
            	import "«ifPackage»"
            «ENDFOR»
            statechart «compName.toFirstUpper» [
            	//Input ports «var i=0»
            	«FOR portName : inPortNames»
            		port «portName» : requires «inPortIfNames.get(i++)»
            	«ENDFOR»
            	//Output ports «i=0»
            	«FOR portName : outPortNames»
            		port «portName» : provides «inPortIfNames.get(i++)»
            	«ENDFOR»
            ]{
            	transition from init_0 to state_0
            	transition from state_0 to c_0 when any
	            «FOR row : rowIterator.toList»
					«var cellIterator=row.cellIterator»
					transition from c_0 to state_0 [«FOR j : 0..inPortNames.length-1 SEPARATOR " and "»«getCondition(inInterfaces.get(j),cellIterator.next,inPortNames.get(j))»«ENDFOR»]/  «FOR j : 0..(outPortNames.length-1) SEPARATOR ""» «getEffect(outInterfaces.get(j),cellIterator.next,outPortNames.get(j))»«ENDFOR»
	            «ENDFOR»
            	region main_0 {
            		initial init_0
            		state state_0
            		choice c_0
            	}
            }
            '''
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.showError(e.message)
        }
	}
	
}