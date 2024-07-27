package main;

import java.util.Random;

class Bus implements Runnable{

   // private String name;
    private final BusSystem busSystem;
    
    // generating a random number
    Random random = new Random();
    int min = 1000;
    int max = 3000;
    int randomNumber = random.nextInt((max - min)+1)+ min;
    
    
    public Bus(BusSystem busSystem){
        this.busSystem = busSystem;
        //this.name = name;
    } 

   

    @Override
    public void run(){
        try {
            Thread.sleep(randomNumber);
            busSystem.busArrived();
            Thread.sleep(500); // simula embarque
            busSystem.busLeft();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        
    }

}
