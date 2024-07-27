package main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusSystem {
 
    private int availableSeats; // seats on bus
    private int passengersAtBusStop; 

    private final Lock lock;
    private final Condition PassengerInStop; // flag to bus leaves stop
    private final Condition BusInStop; // flag to warn that bus has come
    private final Condition BusIsFull; // flag to warn that bus is full
    private  boolean emptyBus = false; // flag to warn when bus can leave

        //constructor
    public BusSystem(){

        this.availableSeats = 0; 
        this.passengersAtBusStop = 0;
        this.lock = new ReentrantLock();
        this.PassengerInStop = lock.newCondition();
        this.BusInStop = lock.newCondition();
        this.BusIsFull = lock.newCondition();
    }

    public void busArrived(){
        // critical region because of availableSeats
        lock.lock();
        try{
            System.out.println("Ônibus "+Thread.currentThread().getName()+" chegou na parada.");
            BusInStop.signalAll(); // releases passengers who are waiting at the stop
            availableSeats = 50; 

            BusIsFull.signalAll(); // realeses passengers that are waiting for a new bus, because the last one was full
        
            if (passengersAtBusStop == 0) { // The bus leaves if there are no passengers at the stop
                emptyBus = true;
            }
        }   
        finally{
            lock.unlock();
        }   
    }
    public void busLeft(){
        // critical region because of availableSeats
        lock.lock();
        try{
                while (true) {
                    if(!emptyBus){ // bus leaves when is empty or when there is no passengers on stop
                        PassengerInStop.await();
                    }
                    System.out.println("Ônibus "+Thread.currentThread().getName()+" saiu da parada de ônibus com " + (50 - availableSeats)+" passageiros.");
                    break;
                }
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        finally{
            lock.unlock();
        }
        

    }
    public void getBus(){
        // critical region because of availableSeats
        lock.lock();
        try{
            
            System.out.println("Passageiro " + Thread.currentThread().getName() + " chegou na parada");
            passengersAtBusStop++;
            BusInStop.await();//wait for the bus to arrive 

            while(availableSeats == 0 ){// if there isn't where seat
                System.out.println("Onibus cheio, " + Thread.currentThread().getName() + " terá que esperar pelo próximo ônibus.");
               BusIsFull.await(); // wait for the next bus
                
            }
            // passenger boarded
            availableSeats--;
            passengersAtBusStop --;
            System.out.println("Passageiro " + Thread.currentThread().getName() + " entrou no ônibus, assentos disponíveis: " + availableSeats);
    
            if (availableSeats == 0) { 
                PassengerInStop.signal();
            }
        }
             catch (Exception e) {
             
                // TODO: handle exception
            }
        finally{
            lock.unlock();

        }
        
    }   


    
}

