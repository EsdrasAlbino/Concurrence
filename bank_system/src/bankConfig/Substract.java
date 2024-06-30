package bankConfig;

public class Substract implements Runnable {
    private final BankAccount account;
    private final double value;

    // Constructor to initialize the account and value
    public Substract(BankAccount account, double value) {
        this.account = account;
        this.value = value;
    }

    @Override
    public void run() {
        account.substract(value);
    }
}
