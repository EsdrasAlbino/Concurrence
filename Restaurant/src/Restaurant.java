import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Restaurant {

    private final int capacity; //qnt of chairs 
    private final Lock lock; 
    private final Condition isFull; // flag
    

    private int occupiedChairs; 
    private Queue queue;
  

    public Restaurant(){ //constructor
        this.capacity = 5;
        this.lock = new ReentrantLock();
        this.isFull = lock.newCondition();
        this.occupiedChairs = 0;
        this.queue = new Queue();
        
        
    }

    public void dinner(Customer customer) throws InterruptedException {
        lock.lock(); //critical region because of occupiedChairs
        try {
            while (occupiedChairs >= capacity || (!queue.isEmpty() && !queue.peek().equals(customer.getName()))) { 
                // full hall or the first of the queue is the current thread
                if (occupiedChairs >= capacity) {
                    queue.enqueue(customer.getName());
                    System.out.println("fila de espera: " + queue.printQueue()); // enter on a wait queue
                
                }
                isFull.await();// the hall is full
            }

            if (!queue.isEmpty() && queue.peek().equals(customer.getName())) { // if the current thread is the next on queue and has where to sit, dequeue
                queue.dequeueData(customer.getName());
            }

            occupiedChairs++; 
            System.out.println(customer.getName() + " sentou. Lugares ocupados: " + occupiedChairs); // enter on hall

    
        }
        
        finally{
            lock.unlock();
        }

    }
    
    public void getOut(Customer customer){
        lock.lock();
        try {
            occupiedChairs--;
            System.out.println(customer.getName() + " saiu. Lugares ocupados: " + occupiedChairs); // customer get out

            if (occupiedChairs == 0) {
                
                isFull.signalAll(); // the hall isn't full anymore, so wake up the other threads to occupate the chairs
                //isFull.signal(); // try to run this way too, you'll see one thread enter on hall and getting out imediatly
            } 
        }
       finally{
            lock.unlock();
        }

    }
}
