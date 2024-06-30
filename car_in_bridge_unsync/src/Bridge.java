import bridge.Car;

public class Bridge {
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 5; i++) {
            new Car("esquerda", i).start();
            new Car("direita", i).start();

        }

    }
}
