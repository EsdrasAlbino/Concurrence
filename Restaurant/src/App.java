public class App {
    public static void main(String[] args) {


        Restaurant restaurant = new Restaurant();
        for (int i = 1; i <= 20; i++) {
            // create 20 client threads
            Customer customer = new Customer("Cliente " + i, restaurant);
            new Thread(customer).start(); // .run()
        }
    }
}
