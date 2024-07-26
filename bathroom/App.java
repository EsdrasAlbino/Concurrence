public class App {
    public static void main(String[] args) {
        Bathroom bathroom = new Bathroom();

        // Create and start multiple threads for men and women
        for (int i = 0; i < 100; i++) {
            Thread men = new Thread(new Person(i, "Homem", bathroom));
            Thread woman = new Thread(new Person(i, "Mulher", bathroom));
            men.start();
            woman.start();
        }
    }
}
