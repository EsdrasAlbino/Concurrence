public class Consumer implements Runnable{
    private Barbshop barbshop;
    


    public Consume(Barbshop barbshop){
        this.barbshop = barbshop;
        
    }
    @Override
    public void run(){
        barbshop.serve();
    }
}
