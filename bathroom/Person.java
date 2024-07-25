package bathroom;

class Person implements Runnable {

    public String genrer;
    private Bathroom bathroom;
    public int ID;

    public Person(int ID, String genrer, Bathroom bathroom) {
        this.ID = ID;
        this.genrer = genrer;
        this.bathroom = bathroom;
    }

    @Override
    public void run() {
        try {
            if (genrer == "Homem") {
                bathroom.menInside(this);
                Thread.sleep((long) (Math.random() * 1000));
                bathroom.menExit(this);
            } else {
                bathroom.womanInside(this);
                Thread.sleep((long) (Math.random() * 1000));
                bathroom.womanExit(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
