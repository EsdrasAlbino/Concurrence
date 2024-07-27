package main;

class Passenger implements Runnable{

    private final BusSystem busSystem;
    
    //constructor
    public Passenger(BusSystem busSystem){
        this.busSystem = busSystem;
    } 


    @Override
    public void run(){
        try {
            busSystem.getBus(); // passenger arrive at stop
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

}
