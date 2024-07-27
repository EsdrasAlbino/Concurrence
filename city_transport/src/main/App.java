package main;


public class App {
    public static void main(String[] args) {


        BusSystem busSystem = new BusSystem();

        System.out.println("");
        for (int i = 1; i <= 60; i++) {
            // create 20 passengers threads
            Thread passenger = new Thread(new Passenger(busSystem));
            passenger.setName("passageiro" + i);
            passenger.start(); // .run()  
        }

        Thread bus1 = new Thread(new Bus(busSystem), "bus1");
           // bus1.setName("Bus1");
            bus1.start(); // .run()

            Thread bus2 = new Thread(new Bus(busSystem));
            bus2.setName("Bus2");
            bus2.start(); // .run()

            Thread bus3 = new Thread(new Bus(busSystem));
            bus3.setName("Bus3");
            bus3.start(); // .run()

            Thread bus4 = new Thread(new Bus(busSystem));
            bus4.setName("Bus4");
            bus4.start(); // .run()

            Thread bus5 = new Thread(new Bus(busSystem));
            bus5.setName("Bus5");
            bus5.start(); // .run()
            
    }
}

