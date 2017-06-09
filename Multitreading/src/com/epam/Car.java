package com.epam;

import java.util.Random;
import java.util.concurrent.Callable;

/*
 * Using suggested class 'Car' create app, which will launch race for several different cars.
 * 1. Create cars with different parameters. Start cars simultaneously and parallel. Observe which finishes first.
 * 2. When the race is over - output the winner message 'Winner is XXXXX!'.
 * 3. Disqualify one of cars from the race after 5 seconds by interrupting it's thread - car code needs to be modified for that.  
 */

public class Car implements Runnable, Callable<Object> {    
	private Random rand = new Random();           
    private String name;
    private RaceHolder holder;    
    private int traveled;
    
    public Car(String name, RaceHolder holder) {
        this.name = name;     
        this.holder = holder;
        this.traveled = 0;
    }
    
    public String getName() {
    	return name;
    }
    
    @Override 
    public void run() {
	  try {        	
	      System.out.println(name + " is on a position...");	      
	      holder.getStart().await();	      
	            	      
	      while (traveled < holder.getDistance()) {                  
	        Thread.sleep(rand.nextInt(3) * 700);                  
	        traveled += rand.nextInt(15);
	        System.out.println(name + " reached to " + traveled + "!");
	      }	      
	      System.out.println(name + " crossed the finish!");
	      holder.getPlaces().add(name);
	      holder.getFinish().countDown();
	    } catch (InterruptedException e) {
	    	System.out.println("-------> "+name + " is out of the race due to TIMEOUT");
	    	holder.getFinish().countDown();
	  }
    }

	@Override
	public Object call() throws Exception {		
		return !Boolean.valueOf(traveled < holder.getDistance());
	}
}