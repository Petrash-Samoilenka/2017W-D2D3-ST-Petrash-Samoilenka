package decorator.beverages;

public class Decaf implements IBeverage {

  @Override
  public String makeBeverage() {    
    return "Decaf";
  }
  
}
