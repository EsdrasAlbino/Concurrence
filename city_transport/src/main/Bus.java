package main;

import java.util.Random;

class Bus implements Runnable{

    private final BusSystem busSystem;
    
    // generating a random number
    Random random = new Random();
    int min = 1000;
    int max = 3000;
    int randomNumber = random.nextInt((max - min)+1)+ min;
    
    //constructor
    public Bus(BusSystem busSystem){
        this.busSystem = busSystem;
    } 

    @Override
    public void run(){
        try {
            Thread.sleep(randomNumber); // interval between bus
            busSystem.busArrived(); // bus arrived stop
            busSystem.busLeft(); // bus left stop
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        
    }

}
