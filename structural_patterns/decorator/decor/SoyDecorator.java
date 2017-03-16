package decorator.decors;

import decorator.beverages.IBeverage;

public class SoyDecorator implements IBeverage {

  private IBeverage baseBeverage;
  
  public SoyDecorator(IBeverage bev) {
    this.baseBeverage = bev;
  }
  
  @Override
  public String makeBeverage() {    
    return baseBeverage.makeBeverage() + addSoy();
  }
  
  private String addSoy() {
    return " + soy";
  }
  
}
