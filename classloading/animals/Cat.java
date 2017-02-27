package animals;

public class Cat extends Animal {

  @Override
  public void play() {
    logger.info(" Cat#play ");
  }

  @Override
  public void voice() {
    logger.info(" Cat#voice ");    
  }

}
