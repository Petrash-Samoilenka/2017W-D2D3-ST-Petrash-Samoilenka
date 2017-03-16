package decorator;

import decorator.beverages.*;
import decorator.decors.*;

public class DecoratorRunner {

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Implentation of Decorate pattern:");
    
    IBeverage espressoMocha = new MochaDecorator(new Espresso());
    IBeverage espressoSoy = new SoyDecorator(new Espresso());
    IBeverage espressoSoySoyWhip = new WhipDecorator(new SoyDecorator(new SoyDecorator(new Espresso())));
    IBeverage decafMochaMochaSoyWhip = new WhipDecorator(new SoyDecorator(new MochaDecorator(new MochaDecorator(new Decaf()))));
    
    System.out.println(espressoMocha.makeBeverage() + " -- done");
    System.out.println(espressoSoy.makeBeverage() + " -- done");
    System.out.println(espressoSoySoyWhip.makeBeverage() + " -- done");
    System.out.println(decafMochaMochaSoyWhip.makeBeverage() + " -- done");
  }

}
