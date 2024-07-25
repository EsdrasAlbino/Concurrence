package bathroom;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Bathroom {
    private static final int maxCapacity = 3;
    private int menInBathroom = 0;
    private int womanInBathroom = 0;
    private final Semaphore capacitySemaphore = new Semaphore(maxCapacity);
    private final Lock lock = new ReentrantLock();
    private final Condition womenBreak = lock.newCondition();
    private final Condition menBreak = lock.newCondition();

    // Use semaphore to control limite in the bathroom, and use lock and condition to control the access to the shared resources

    public void menInside(Person men) throws InterruptedException {
        capacitySemaphore.acquire();

        lock.lock(); 
        try {
            while (womanInBathroom > 0) {
                // If there are women in the bathroom, wait until they exit
                menBreak.await();
            }

            // Increment the number of men in the bathroom
            menInBathroom++;
        } finally {
            lock.unlock();
        }

        System.out.println("Homem entrou: " + men.ID + " - " + " Homens no banheiro: " + menInBathroom);
    }

    public void menExit(Person men) {
        lock.lock();
        try {
            // Decrement the number of men in the bathroom
            menInBathroom--;

            // If no men are in the bathroom, signal the waiting women
            if (menInBathroom == 0) {
                womenBreak.signalAll();
            }
        } finally {
            lock.unlock();
        }

        capacitySemaphore.release();
        System.out.println("Homem saiu: " + men.ID + " - " + " Homens no banheiro: " + menInBathroom);
    }

    public void womanInside(Person women) throws InterruptedException {
        capacitySemaphore.acquire(); 

        lock.lock(); 
        try {
            while (menInBathroom > 0) {
                // If there are men in the bathroom, wait until they exit
                womenBreak.await();
            }

            // Increment the number of women in the bathroom
            womanInBathroom++;
        } finally {
            lock.unlock();
        }

        System.out.println("Mulher entrou: " + women.ID + " - " + " Mulheres no banheiro: " + womanInBathroom);
    }

    public void womanExit(Person women) {
        lock.lock(); 
        try {
            // Decrement the number of women in the bathroom
            womanInBathroom--;

            // If no women are in the bathroom, signal the waiting men
            if (womanInBathroom == 0) {
                menBreak.signalAll();
            }
        } finally {
            lock.unlock();
        }

        capacitySemaphore.release();
        System.out.println("Mulher saiu: " + women.ID + " - " + " Mulheres no banheiro: " + womanInBathroom);
    }

    public static void main(String[] args) {
        Bathroom bathroom = new Bathroom();

        // Create and start multiple threads for men and women
        for (int i = 0; i < 10; i++) {
            Thread men = new Thread(new Person(i, "Homem", bathroom));
            Thread woman = new Thread(new Person(i, "Mulher", bathroom));
            men.start();
            woman.start();
        }
    }
}
