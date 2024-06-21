package bridge;

import java.util.concurrent.Semaphore;

public class CarImplements implements Runnable {

    public Semaphore s;
    private String direction;
    private int id;

    public CarImplements(Semaphore s, String direction, int index) {
        this.direction = direction;
        this.id = index;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            s.acquire();
            System.out.println(id + " Carro indo para " + direction);
            Thread.sleep(1000);
            s.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(id + " Carro foi interrompido na ponte na direção: " + direction);
        }
        System.out.println(id + " Carro saiu da ponte na direção: " + direction);
    }
}
