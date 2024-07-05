import java.util.Random;

class Customer implements Runnable {

    private String name;
    private final Restaurant restaurant;
   

    // generating a radom number
    Random random = new Random();
    int min = 1000;
    int max = 3000;
    int randomNumber = random.nextInt((max - min)+1)+ min; 

    public Customer(String name, Restaurant restaurant) {
        this.restaurant = restaurant;
        this.name = name;
    
    }

    public String getName(){
        return name;
    }
    @Override
    public void run() {
        try{
            restaurant.dinner(this); // try to enter on restaurant
            Thread.sleep(randomNumber); // diferent people has diferent eat time
            restaurant.getOut(this); // leaves restaurant
            
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        
    }
}