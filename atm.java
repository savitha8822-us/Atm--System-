import java.util.*;

class Account {

    private String name;
    private int pin;
    private double balance;
    private List<String> history = new ArrayList<>();

    // Constructor
    public Account(String name, int pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // PIN validation
    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    // Get balance (read-only)
    public double getBalance() {
        return balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add("Deposited: Rs " + amount);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            history.add("Withdrawn: Rs " + amount);
            System.out.println("Please collect your cash");
        }
    }

    // Show transaction history
    public void showHistory() {
        System.out.println("\n--- Transaction History ---");
        if (history.isEmpty()) {
            System.out.println("No transactions yet");
        } else {
            for (String h : history) {
                System.out.println(h);
            }
        }
    }
}

public class atm{

    static Scanner sc = new Scanner(System.in);
    static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {

        // Sample accounts
        accounts.add(new Account("John", 1234, 2000));
        accounts.add(new Account("Alice", 1111, 1500));

        while (true) {
            System.out.println("\n===== ATM MACHINE =====");
            System.out.println("1 Login");
            System.out.println("2 Exit");

            int choice = sc.nextInt();

            if (choice == 1)
                login();
            else
                System.exit(0);
        }
    }

    // LOGIN
    static void login() {

        int attempts = 3;

        while (attempts > 0) {

            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            for (Account acc : accounts) {

                if (acc.validatePin(pin)) {
                    System.out.println("Welcome " + acc.getName());
                    menu(acc);
                    return;
                }
            }

            attempts--;
            System.out.println("Wrong PIN! Attempts left: " + attempts);
        }

        System.out.println("Card Blocked!");
    }

    // MENU
    static void menu(Account acc) {

        while (true) {

            System.out.println("\n1 Check Balance");
            System.out.println("2 Deposit");
            System.out.println("3 Withdraw");
            System.out.println("4 Mini Statement");
            System.out.println("5 Logout");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.println("Balance: Rs " + acc.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    acc.deposit(sc.nextDouble());
                    break;

                case 3:
                    System.out.print("Enter amount: ");
                    acc.withdraw(sc.nextDouble());
                    break;

                case 4:
                    acc.showHistory();
                    break;

                case 5:
                    return;
            }
        }
    }
}