class Passanger implements Runnable{
    private final BusSystem busSystem;
    
    public Passanger(BusSystem busSystem){
        this.busSystem = busSystem;
    } 
    @Override
    public void run(){
        busSystem.boarding();
    }

}
