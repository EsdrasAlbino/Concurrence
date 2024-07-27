package main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class BusSystem {
 
    private int availableSeats;
    private int passengersAtBusStop;
    private final Lock lock;
    private final Semaphore busCapacity = new Semaphore(20);
    private final Condition PassengerInStop;
    private final Condition BusInStop;
    private final Condition BusIsFull;

        //constructor
    public BusSystem(){

        this.availableSeats = 0; // lugares disponíveis
        this.passengersAtBusStop = 0;
        this.lock = new ReentrantLock();
        this.PassengerInStop = lock.newCondition();
        this.BusInStop = lock.newCondition();
        this.BusIsFull = lock.newCondition();
    }

    public void busArrived(){
        lock.lock();
        try{
            System.out.println("Ônibus "+Thread.currentThread().getName()+" chegou na parada.");
            BusInStop.signalAll();//avisar que onibus chegou na parada
            availableSeats = 20;
            
        }   
        finally{
            lock.unlock();
        }   
    }
    public void busLeft(){
        lock.lock();
        try{
                while (true) {
                    PassengerInStop.await();
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
        busCapacity.release();

    }
    public void getBus(){
        busCapacity.acquire();

        lock.lock();
        try{
            System.out.println("Passageiro " + Thread.currentThread().getName() + " chegou na parada");
            passengersAtBusStop++;
            BusInStop.await();//wait for the bus to arrive 

            while(availableSeats == 0 ){// if there isn't where seat
                System.out.println("Onibus cheio, " + Thread.currentThread().getName() + " terá que esperar pelo próximo ônibus.");
               // BusIsFull.await(); // wait for the next bus
                
            }
            availableSeats--;
            passengersAtBusStop --;
            System.out.println("Passageiro " + Thread.currentThread().getName() + " entrou no ônibus, assentos disponíveis: " + availableSeats);
    
            if (passengersAtBusStop == 0) {
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

