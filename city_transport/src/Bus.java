class Bus implements Runnable{
    private final BusSystem busSystem;
    
    public Bus(BusSystem busSystem){
        this.busSystem = busSystem;
    } 
    @Override
    public void run(){
        busSystem.arriveBusStop();
    }

}
