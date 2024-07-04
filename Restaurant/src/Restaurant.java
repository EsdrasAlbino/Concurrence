//import java.util.LinkedList;
//import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Restaurant {

    private final int capacity;
    private final Lock lock; 
    private final Condition isFull;
    private final Condition hasWhereSit;

    private int occupiedChairs;
    private Queue queue;
  //  private Queue<Customer> queue = new LinkedList<>();

    public Restaurant(){
        this.capacity = 5;
        this.lock = new ReentrantLock();
        this.isFull = lock.newCondition();
        this.hasWhereSit = lock.newCondition();
        this.occupiedChairs = 0;
        this.queue = new Queue();
        
    }

    public void dinner(Customer customer) throws InterruptedException {
        lock.lock();
        try{
            // has more customers than chairs to occupate
            while(occupiedChairs >= capacity){
                
                queue.enqueue(customer.getName());
                System.out.println("fila de espera:" + queue.printQueue());
                isFull.await();
                
            }
            
            if (!queue.isEmpty() && queue.peek() != customer.getName()) {
                 hasWhereSit.wait();
                
            }
            
            if (!queue.isEmpty() && queue.peek() == customer.getName()) {
                queue.dequeueData(customer.getName());
                
            }
            
            occupiedChairs++;
            System.out.println(customer.getName() + " sentou. Lugares ocupados: " + occupiedChairs);
            if (occupiedChairs < capacity) {
                isFull.signal();
            }
            
        }
        finally{
            lock.unlock();
        }

    }
    
    public void getOut(Customer customer){
        lock.lock();
        try{
            occupiedChairs--;
            System.out.println(customer.getName() + " saiu. Lugares ocupados: " + occupiedChairs);
            if (occupiedChairs == 0) {
                isFull.signal();
            
            }
        }finally{
            lock.unlock();
        }

    }
}
