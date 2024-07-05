import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Restaurant {

    private final int capacity; //qnt of chairs 
    private final Lock lock; 
    private final Condition isFull; // flag
    private final Condition hasWhereSit;

    private int occupiedChairs; 
    private Queue queue;
  

    public Restaurant(){ //constructor
        this.capacity = 5;
        this.lock = new ReentrantLock();
        this.isFull = lock.newCondition();
        this.occupiedChairs = 0;
        this.queue = new Queue();
        this.hasWhereSit = lock.newCondition();
        
    }

    public void dinner(Customer customer) throws InterruptedException {
        lock.lock();
        try {
            while (occupiedChairs >= capacity || (!queue.isEmpty() && !queue.peek().equals(customer.getName()))) {
                if (occupiedChairs >= capacity) {
                    queue.enqueue(customer.getName());
                    System.out.println("fila de espera: " + queue.printQueue());
                
                }
                isFull.await();
            }

            if (!queue.isEmpty() && queue.peek().equals(customer.getName())) {
                queue.dequeueData(customer.getName());
            }

            occupiedChairs++;
            System.out.println(customer.getName() + " sentou. Lugares ocupados: " + occupiedChairs);

            if (occupiedChairs < capacity) {
                hasWhereSit.signalAll();
            }
        }
        
        finally{
            lock.unlock();
        }

    }
    
    public void getOut(Customer customer){
        lock.lock();
        try {
            occupiedChairs--;
            System.out.println(customer.getName() + " saiu. Lugares ocupados: " + occupiedChairs);

            if (occupiedChairs == 0) {
                isFull.signalAll();
            } else {
                hasWhereSit.signalAll();
            }
        }
       /*  try{
            occupiedChairs--;
            System.out.println(customer.getName() + " saiu. Lugares ocupados: " + occupiedChairs);
            if (occupiedChairs == 0) {
                isFull.signalAll();
            }
        }*/finally{
            lock.unlock();
        }

    }
}
