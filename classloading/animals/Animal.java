package animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Animal {
  final static Logger logger = LogManager.getLogger(Animal.class.getName());
  
  public abstract void play();
  
  public abstract void voice();
}
