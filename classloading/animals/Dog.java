package animals;

public class Dog extends Animal {

  @Override
  public void play() {    
    logger.info(" Dog#play ");    
  }

  @Override
  public void voice() {
    logger.info(" Dog#voice ");    
  }

}
