package decorator.decors;

import decorator.beverages.IBeverage;

public class MochaDecorator implements IBeverage {

  private IBeverage baseBeverage;
  
  public MochaDecorator(IBeverage bev) {
    this.baseBeverage = bev;
  }
  
  @Override
  public String makeBeverage() {    
    return baseBeverage.makeBeverage() + addMilk() + addChocolate();
  }
  
  private String addMilk() {
    return " + milk";
  }
  
  private String addChocolate() {
    return " + chokolate";
  }

}
