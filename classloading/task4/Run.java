package task4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import animals.Animal;
import animals.Cat;
import animals.Dog;
import classloaders.AnimalClassLoader;

public class Run {
  
  public static final String DOG = "animals.Dog";
  public static final String CAT = "animals.Cat";
  
  private static final String LIB = "jar:file:src/animalsjar!/";
  
  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
    AnimalClassLoader acl = new AnimalClassLoader(new URL(LIB));
    Class<?> cDog = acl.loadClass(DOG);
    Class<?> cCat = acl.loadClass(CAT);    
    List<Animal> animals = new ArrayList<>(2);
    animals.add((Animal)cDog.newInstance());
    animals.add((Animal)cCat.newInstance());
    
    for (Animal a : animals) {
      a.play();
      a.voice();
    } 
    acl.close();
  } 

}
