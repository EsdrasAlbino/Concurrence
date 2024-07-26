
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

class BusStop {
    private static final int CAPACITY = 50;
    private final Lock lock = new ReentrantLock();
    private final Condition busArrived = lock.newCondition();
    private final Condition busDeparted = lock.newCondition();
    private List<Passenger> waitingPassengers = new ArrayList<>();
    private int passengersInBus = 0;

    public void waitForBus(Passenger passenger) throws InterruptedException {
        lock.lock();
        try {
            waitingPassengers.add(passenger);
            System.out.println(passenger + " is waiting for the bus.");
            busArrived.await();
            if (passengersInBus < CAPACITY) {
                passengersInBus++;
                waitingPassengers.remove(passenger);
                System.out.println(passenger + " got into the bus.");
                if (passengersInBus == CAPACITY || waitingPassengers.isEmpty()) {
                    busDeparted.signal();
                }
            } else {
                System.out.println(passenger + " has to wait for the next bus.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void busArrives() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Bus arrives at the stop.");
            busArrived.signalAll();
            while (passengersInBus < CAPACITY && !waitingPassengers.isEmpty()) {
                busDeparted.await();
            }
            System.out.println("Bus departs with " + passengersInBus + " passengers.");
            passengersInBus = 0;
        } finally {
            lock.unlock();
        }
    }
}

class Passenger implements Runnable {
    private final BusStop busStop;
    private final int id;

    public Passenger(BusStop busStop, int id) {
        this.busStop = busStop;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            busStop.waitForBus(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Passenger " + id;
    }
}

class Bus implements Runnable {
    private final BusStop busStop;
    private final CountDownLatch latch;

    public Bus(BusStop busStop, CountDownLatch latch) {
        this.busStop = busStop;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((int) (Math.random() * 2000) + 1000);
                busStop.busArrives();
                latch.countDown();
                if (latch.getCount() == 0) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int numPassengers = 100;
        int numBuses = 3;
        BusStop busStop = new BusStop();
        CountDownLatch latch = new CountDownLatch(numBuses);
        List<Thread> passengerThreads = new ArrayList<>();

        for (int i = 0; i < numPassengers; i++) {
            Passenger passenger = new Passenger(busStop, i + 1);
            Thread thread = new Thread(passenger);
            passengerThreads.add(thread);
            thread.start();
        }

        Thread busThread = new Thread(new Bus(busStop, latch));
        busThread.start();

        try {
            busThread.join();
            for (Thread thread : passengerThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Simulation completed.");
    }
}
