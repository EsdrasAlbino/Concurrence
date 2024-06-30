package bridge;

public class Car extends Thread {
    private String direction;
    private int id;

    public Car(String direction, int index) {
        this.direction = direction;
        this.id = index;
    }

    @Override
    public void run() {
        try {
            System.out.println(id + " Carro indo para " + direction);
            Thread.sleep(3000);
            System.out.println(id + " Carro saiu da ponte na direção: " + direction);

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(id + " Carro foi interrompido na ponte na direção: " + direction);
        }
    }
}
