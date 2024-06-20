package bridge;

import java.util.concurrent.Semaphore;

public class CarExtends extends Thread {
    private static Semaphore semaphore = new Semaphore(1);
    private String direction;
    private int id;

    public CarExtends(String direction, int index) {
        this.direction = direction;
        this.id = index;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(id + " Carro entrou na direção: " + direction);
            Thread.sleep(3000);
            System.out.println(id + " Carro saiu da ponte na direção: " + direction);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(id + " Carro foi interrompido na ponte na direção: " + direction);
        }
    }
}
