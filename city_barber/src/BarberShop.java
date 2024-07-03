import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BarberShop {
    private final int numChairs; // buffer
    private int waitingCustomers; // cont
    private final Lock lock;
    private final Condition hasCostumer; // signal flag

    public BarberShop(int numChairs) {
        this.numChairs = numChairs;
        this.waitingCustomers = 0;
        this.lock = new ReentrantLock();
        this.hasCostumer = lock.newCondition();

    }

    public void getHaircut() { // producer
        lock.lock(); // critical region because of waitingCostumers
        try {
            while (waitingCustomers == numChairs) {
                // buffer full, all chairs occupied
                System.out.println(Thread.currentThread().getName() + " foi embora pois não há espaço.");
                return;
            }

            // enter a new costumer on barbshop
            waitingCustomers++;
            System.out.println(
                    Thread.currentThread().getName() + " sentou-se esperando. Cadeiras ocupadas: " + waitingCustomers);
            hasCostumer.signal();

        } finally {
            lock.unlock();
        }
    }

    public void cutHair() { // consumer
        while (true) {
            lock.lock();
            try {
                while (waitingCustomers == 0) {
                    // empty buffer, there is a chair available
                    System.out.println("Barbeiro dormindo.");
                    hasCostumer.await();// waits for a costumer tor arrive
                }
                waitingCustomers--;
                System.out.println("Uma cabeça foi raspada. Cadeiras ocupadas: " + waitingCustomers);

                try {
                    Thread.sleep(1500); // Barber takes 1.5 seconds to cut hair
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}