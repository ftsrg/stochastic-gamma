package hu.bme.mit.gamma.analysis.transformation;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;


public class DynamicCompilation {

	
	protected Logger logger = Logger.getLogger("GammaLogger");
	
  public void compileAndRun(String sourceURL,String gatewayLoc,List<String> uriList, String libLoc) throws Exception {
	
	List<String> localURIs=new ArrayList<>();
	localURIs.addAll(uriList);
	localURIs.add(sourceURL);
	
	
	/*
	 * 
	 * 
	 * 
	List<File> sources=new ArrayList();
	for (String uri : localURIs) {
		sources.add(new File(uri));
		if(uri.contains("Statechart.java")) {
			String yuri=uri.replace("Statechart", "Statemachine");
			try {
				File yakinduSource=new File(yuri);
				sources.add(yakinduSource);
			}
			catch (Exception e) {
			}
		}
	}
	
    JavaCompiler compiler    = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager fileManager =
        compiler.getStandardFileManager(null, null, null);

    fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
                            Arrays.asList(new File(gatewayLoc)));
    
    logger.log(Level.INFO,"Java Gateway compile process has been started");
    // Compile the file
    compiler.getTask(null,
               fileManager,
               null,
               null,
               null,
               fileManager.getJavaFileObjectsFromFiles(sources))
            .call();
    fileManager.close();

    logger.log(Level.INFO,"Java Gateway compile process has been finished");

    // delete the source file
    // sourceFile.deleteOnExit();

    //runIt();
    */

	//Path path= Path.of(gatewayLoc,genLoc) ;
	
    logger.log(Level.INFO,"Java Gateway compile process has been started");

	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	
    StandardJavaFileManager supportFileManager =
            compiler.getStandardFileManager(null, null, null);
    
    
	List<File> sources=new ArrayList();
	
	List<String> optionList = new ArrayList<String>();
	// set compiler's classpath to be same as the runtime's +"py4j0.10.8.1.jar"
	optionList.addAll(Arrays.asList("-cp",libLoc+"py4j0.10.8.1.jar"));

	
	for (String uri : localURIs) {

		if(uri.contains("Event.java")) {
			String yuri2=uri.replace("Event", "IStatemachine");
			try {
				File yakinduSource=new File(yuri2);
				if (yakinduSource.exists()) {
					sources.add(yakinduSource);
					logger.log(Level.INFO,"class found: "+yuri2);
				}
			}
			catch (Exception e) {
			      e.printStackTrace();
			      logger.log(Level.SEVERE,e.getMessage());   
			}
		}
		if(uri.contains("Interface.java")) {
			String yuri=uri.replace("Interface", "Statemachine");
			try {
				File yakinduSource=new File(yuri);
				if (yakinduSource.exists()) {
					sources.add(yakinduSource);
					logger.log(Level.INFO,"class found: "+yuri);
					String yakinduInterfaceURI="";
					String[] elements=yuri.split(File.separator);
					for (String e : elements) {
						if(e.contains("Statemachine")) {
							yakinduInterfaceURI=yakinduInterfaceURI+File.separator+"I"+e;
						}else {
							yakinduInterfaceURI=yakinduInterfaceURI+File.separator+e;
						}
					}
					yakinduInterfaceURI=yakinduInterfaceURI.replace(File.separator+File.separator, File.separator);
					logger.log(Level.INFO,yakinduInterfaceURI);
					File yakinduInterfaceSource=new File(yakinduInterfaceURI);
					sources.add(yakinduInterfaceSource);
					
				}
			}
			catch (Exception e) {
			      e.printStackTrace();
			      logger.log(Level.SEVERE,e.getMessage());   
			}

		}
		if(!uri.contains("Reflective")) {
			sources.add(new File(uri));
			logger.log(Level.INFO,uri);
			
			try {
				String[] elements=uri.split(File.separator);
				String y="";
				for (int i=0;i<elements.length-1;i++) {
					y=y+File.separator+elements[i];
				}
				y=y.replace(File.separator+File.separator, File.separator);
				String y1=y+File.separator+"IStatemachine.java";
				String y2=y+File.separator+"RuntimeService.java";
				String y3=y+File.separator+"Statemachine.java";
				File y1s=new File(y1);
				if(y1s.exists()) sources.add(y1s);
				File y2s=new File(y2);
				if(y2s.exists()) sources.add(y2s);
				File y3s=new File(y3);
				if(y3s.exists()) sources.add(y3s);
			} catch (Exception e) {
			      e.printStackTrace();
			      logger.log(Level.SEVERE,e.getMessage());   
			}
			
			
		}
		
	}
	
    SimpleJavaFileManager fileManager =
            new SimpleJavaFileManager(compiler.getStandardFileManager(null, null, null));
    
    
    
    JavaCompiler.CompilationTask compilationTask = compiler.getTask(
            null, fileManager, null, Arrays.asList("-cp","/usr/share/java/py4j0.10.8.1.jar"),null, supportFileManager.getJavaFileObjectsFromFiles(  sources));//getJavaFileObjectsFromPaths(path));//

    Class params[] = {};
    String  paramsObj[] = {};
    compilationTask.call();
    logger.log(Level.INFO,"Java Gateway compile process has been finished");

    CompiledClassLoader classLoader =
            new CompiledClassLoader(fileManager.getGeneratedOutputFiles());

    Class<?> codeGenTest = classLoader.loadClass("javaenv.AnalyzerGateway");

    logger.log(Level.INFO,"Java Gateway is loaded");
    Method main = codeGenTest.getMethod("main", params);
    

    logger.log(Level.INFO,"Starting Gateway...");
    //main.invoke(codeGenTest);
    main.invoke(null, paramsObj);
    logger.log(Level.INFO,"Gateway has been started :) ");
  }

  @SuppressWarnings("unchecked")
  public void runIt() {
    try {
      logger.log(Level.INFO,"Starting Gateway...");
      Class params[] = {};
      String  paramsObj[] = {};
      Class thisClass = Class.forName("AnalyzerGateway");
      Object iClass = thisClass.newInstance();
      Method thisMethod = thisClass.getDeclaredMethod("doit", params);
      thisMethod.invoke(iClass, paramsObj);
      logger.log(Level.INFO,"Gateway has been started :) ");
      }
    catch (Exception e) {
      e.printStackTrace();
      logger.log(Level.SEVERE,e.getMessage());
    }
  }
  

  private static class CompiledClassLoader extends ClassLoader {
    private final List<ClassJavaFileObject> files;

    private CompiledClassLoader(List<ClassJavaFileObject> files) {
      this.files = files;
    }
    Logger logger = Logger.getLogger("GammaLogger");
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
      Iterator<ClassJavaFileObject> itr = files.iterator();
      while (itr.hasNext()) {
    	try {
	        ClassJavaFileObject file = itr.next();
	    	logger.log(Level.INFO,file.getClassName());
	        if (
	        		file.getClassName().equals(name) 
	        ) {
	          itr.remove();
	          byte[] bytes = file.getBytes();
	      		logger.log(Level.INFO,"Compiled class has been found!");
	      		try {
	          		Class<?> gateway=super.defineClass(name, bytes, 0, bytes.length);
	                return gateway;
	      		}catch (Exception e) {
	      	      e.printStackTrace();
	      	      logger.log(Level.SEVERE,e.getMessage());      			
				}
	        }
    	}catch (Exception e) {
    	      e.printStackTrace();
    	      logger.log(Level.SEVERE,e.getMessage()); 
		}
    	if(name.endsWith("Listener")) {
    		name=name+".Provided";
    		itr = files.iterator();
            while (itr.hasNext()) {
            	try {
        	        ClassJavaFileObject file = itr.next();
        	    	logger.log(Level.INFO,file.getClassName());
        	        if (
        	        		file.getClassName().equals(name) 
        	        ) {
        	          itr.remove();
        	          byte[] bytes = file.getBytes();
        	      		logger.log(Level.INFO,"Compiled class has been found!");
        	      		try {
        	          		Class<?> gateway=super.defineClass(name, bytes, 0, bytes.length);
        	                return gateway;
        	      		}catch (Exception e) {
        	      	      e.printStackTrace();
        	      	      logger.log(Level.SEVERE,e.getMessage());      			
        				}
        	        }
            	}catch (Exception e) {
            	      e.printStackTrace();
            	      logger.log(Level.SEVERE,e.getMessage()); 
        		}
    	}
    	}
    		
	  }
	logger.log(Level.WARNING,"Compiled class has not been found!");
	try {
		  URL[] url = {(new File("/usr/share/java/py4j0.10.8.1.jar")).toURI().toURL()};
		  
		  Class<?> gateway=URLClassLoader.newInstance(url).loadClass(name);
          return gateway;
	      //return super.findClass(name);
		
	} catch (Exception e) {
	      e.printStackTrace();
	      logger.log(Level.SEVERE,e.getMessage());   
	      return null; 
	}
      
    }
  }
  
  private static class ClassJavaFileObject extends SimpleJavaFileObject {
	    private final ByteArrayOutputStream outputStream;
	    private final String className;

	    protected ClassJavaFileObject(String className, Kind kind) {
	      super(URI.create("mem:///" + className.replace('.', '/') + kind.extension), kind);
	      this.className = className;
	      outputStream = new ByteArrayOutputStream();
	    }

	    @Override
	    public OutputStream openOutputStream() throws IOException {
	      return outputStream;
	    }

	    public byte[] getBytes() {
	      return outputStream.toByteArray();
	    }

	    public String getClassName() {
	      return className;
	    }
  	}
  
  private static class SimpleJavaFileManager extends ForwardingJavaFileManager {
	    private final List<ClassJavaFileObject> outputFiles;

	    protected SimpleJavaFileManager(JavaFileManager fileManager) {
	      super(fileManager);
	      outputFiles = new ArrayList<ClassJavaFileObject>();
	    }

	    @Override
	    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
	      ClassJavaFileObject file = new ClassJavaFileObject(className, kind);
	      outputFiles.add(file);
	      return file;
	    }

	    public List<ClassJavaFileObject> getGeneratedOutputFiles() {
	      return outputFiles;
	    }
	  }
  
}
