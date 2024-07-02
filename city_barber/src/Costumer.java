class Customer implements Runnable {
    private final BarberShop shop;

    public Customer(BarberShop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        shop.getHaircut();
    }
}