package bankConfig;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    // Constructor to initialize the balance in the account
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit the value in the account using a lock, who will be released
    // in the end of the method
    public void deposit(double value) {
        lock.lock();
        try {
            balance += value;
            System.out
                    .println(Thread.currentThread().getName() + " depositou: " + value + " | Saldo atual: " + balance);
        } finally {
            lock.unlock();
        }
    }

    // Method to substract the value from the account using a lock, who will
    // be released in the end of the method
    public void substract(double value) {
        lock.lock();
        try {
            if (balance >= value) {
                balance -= value;
                System.out
                        .println(Thread.currentThread().getName() + " sacou: " + value + " | Saldo atual: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " tentou sacar: " + value
                        + " | Saldo insuficiente: " + balance);
            }
        } finally {
            lock.unlock();
        }
    }

    // Method to return the balance of the account
    public double getBalance() {
        return balance;
    }
}