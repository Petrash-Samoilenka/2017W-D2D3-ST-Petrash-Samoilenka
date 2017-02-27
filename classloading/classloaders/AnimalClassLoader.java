package classloaders;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class AnimalClassLoader extends URLClassLoader {

  public AnimalClassLoader(URL jar) {
    super(new URL[] {jar});
  }
  
  protected Class<?> findClass(String className) throws ClassNotFoundException { 
    Class<?> clazz = super.findClass(className); 
    if (className.startsWith("animals")) { 
      return clazz; 
    } else { 
       throw new ClassNotFoundException("class " + className + " isn't animal"); 
    } 
  } 
  
}
