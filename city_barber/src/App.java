public class App {
    public static void main(String[] args) {
        int numChairs = 3; // buffer size
        int totalCustomers = 10; // total number of customers

        System.out.println("");
        System.out.println("Quantidade de cadeiras da barbearia: " + numChairs);

        BarberShop shop = new BarberShop(numChairs); // create a object "shop" from BarbeShop Class

        Thread barberThread = new Thread(new Barber(shop)); // create the Barber thread, outside of the loop because we
                                                            // only have one Barber
        barberThread.start();

        for (int i = 1; i <= totalCustomers; i++) {
            // create the costumer thread, 10 times
            Thread customerThread = new Thread(new Customer(shop));
            customerThread.setName("Funalo " + i);
            customerThread.start(); // customer arrives at the barbershop waiting for a haircut

            try {
                Thread.sleep(1000); // waiting time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}