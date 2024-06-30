import java.util.concurrent.locks.ReentrantLock;

public class Barbshop {
    private int count;
    private int chairs = 5; // buffer of size 5
    private final ReentrantLock lock = new ReentrantLock();

    //constructor to initialize the buffer and the count
    public Barbshop(int chairs, int count){
        this.chairs = chairs;
        this.count = count;
    }

    // method that indicates that a client has entered the store
    public void newClient(){
        lock.lock();
        try{
            count += 1;
            System.out.println("Cliente  entrou na loja");
        }
        finally{
            lock.unlock();
        }
    }
    // method that indicates that a client has been served
    public void serve(){
        lock.lock();
        try{
            count-= 1;
            System.out.println("Cliente foi atendido");
        }
        finally{
            lock.unlock();
        }
    }

    public int getCount(){
        return chairs - count;
    }


}
