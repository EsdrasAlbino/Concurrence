package main;

class Passenger implements Runnable{
   // private String name;
    private final BusSystem busSystem;
    
    public Passenger(BusSystem busSystem){
        this.busSystem = busSystem;
        //this.name = name;
    } 


    @Override
    public void run(){
        try {
            busSystem.getBus();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

}
