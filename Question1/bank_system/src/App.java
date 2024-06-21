import bankConfig.Deposit;
import bankConfig.Substract;
import bankConfig.BankAccount;

public class    App {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(1000);

        System.out.println("saldo inicial: " + acc.getBalance());
        Thread personOne = new Thread(new Deposit(acc, 80), "Person 1");
        Thread personTwo = new Thread(new Substract(acc, 100), "Person 2");
        Thread personThree = new Thread(new Substract(acc, 700), "Person 3");
        Thread personFour = new Thread(new Deposit(acc, 3000), "Person 4");
        Thread personFive = new Thread(new Substract(acc, 600), "Person 5");

        personOne.start();
        personTwo.start();
        personThree.start();
        personFour.start();
        personFive.start();

        try {
            personOne.join();
            personTwo.join();
            personThree.join();
            personFour.join();
            personFive.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final: " + acc.getBalance());
    }
}
