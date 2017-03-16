package decorator.decors;

import decorator.beverages.IBeverage;

public class WhipDecorator implements IBeverage {

  private IBeverage baseBeverage;
   
  public WhipDecorator(IBeverage bev) {
    this.baseBeverage = bev;
  }
  
  @Override
  public String makeBeverage() {    
    return baseBeverage.makeBeverage() + doWhip();
  }
  
  private String doWhip() {
    return " + whipped";
  }
  
}
