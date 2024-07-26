import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusSystem {
    private final int seats;
    private int availableSeats;
    private final Lock lock;
    private final Condition BusInStop;
    private final Condition BusIsFull;

        //constructor
    public BusSystem(int seats){
        this.seats = seats;
        this.availableSeats = 0;
        this.lock = new ReentrantLock();
        this.BusInStop = lock.newCondition();
        this.BusIsFull = lock.newCondition();
    }

    public void arriveBusStop(){

    }
    public void boarding(){
        lock.lock();
        try{
            if(availableSeats != 0){
                availableSeats--;
                System.out.println("Passageiro " + Thread.currentThread().getName() + "entrou no ônibus, assentos disponíveis: " + availableSeats);
                return;
            }
            
        }
        finally{
            lock.unlock();
        }
    }   
}
