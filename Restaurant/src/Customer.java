import java.util.Random;

class Customer implements Runnable {

    private String name;
    private final Restaurant restaurant;

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
            restaurant.dinner(this);
            Thread.sleep(randomNumber);
            restaurant.getOut(this);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        
    }
}