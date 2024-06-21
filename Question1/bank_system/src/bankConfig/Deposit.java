package bankConfig;

public class Deposit implements Runnable {
    private final BankAccount account;
    private final double value;

    // Constructor to initialize the account and value
    public Deposit(BankAccount account, double value) {
        this.account = account;
        this.value = value;
    }

    @Override
    public void run() {
        account.deposit(value);
    }
}
