package com.epam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Race {
    private Random rand = new Random();
    private RaceHolder holder;
    private List<Car> cars = new ArrayList<Car>();
    
    public Race(String... names)
    {
    	super();
    	holder = new RaceHolder(rand.nextInt(100), names.length);    	
    	for (String n : names) {
    		cars.add(new Car(n, holder));    		
    	}        
    }
    
    public void run() throws InterruptedException  {
        System.out.println("And the cars are driving to the start line...");
        final ExecutorService executorService = Executors.newFixedThreadPool(cars.size());
        final List<Future<?>> list = new ArrayList<>();
        for (final Car car : cars) {
        
          final Future<?> future = executorService.submit(new Thread(car));                 	
          list.add(future);
        }
        
        holder.getStart().countDown();
        Thread.sleep(200);
        Long start = TimeUnit.SECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);             
        for (final Future<?> future : list) {            
          try {
            Long taken = TimeUnit.SECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);            
            if (!future.isDone() && (taken-start) >= 2) {                          	  
              future.cancel(true);
            }       
            Thread.sleep(500);
          } catch (final Exception e) {}
        }
                              
        holder.getFinish().await();
        executorService.shutdown();
        System.out.print("-------> Winner is ");
        System.out.println(holder.getPlaces().get(0));        
    }
    
    public int getDistance() {
		return holder.getDistance();
	}
}
