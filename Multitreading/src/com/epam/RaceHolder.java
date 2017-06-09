package com.epam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RaceHolder {
	
	private int distance;
	private CountDownLatch start;
	private CountDownLatch finish;
    private final List<String> places;
    
    public RaceHolder(int distance, int cars) {
    	this.distance = distance;
    	places = Collections.synchronizedList(new ArrayList<String>());
    	start = new CountDownLatch(1);
        finish = new CountDownLatch(cars);    	
    }
    
    public int getDistance() {
		return distance;
	}

	public CountDownLatch getStart() {
		return start;
	}

	public CountDownLatch getFinish() {
		return finish;
	}

	public List<String> getPlaces() {
		return places;
	}


}
