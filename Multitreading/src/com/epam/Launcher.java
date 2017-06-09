package com.epam;

public class Launcher {

	public static void main(String[] args) throws InterruptedException {			           
        System.out.println("Preparing...");
        
        Race r = new Race(
            "Mario Andretti",
            "Michael Schumacher",
            "Dale Earnhardt",
            "Ayrton Senna",
            "Nigel Mansell",
            "Richard Petty",
            "Jimmie Johnson"
            );
        
        System.out.println("It's a race of " + r.getDistance() + " lengths");                       
        r.run();            
	}

}
