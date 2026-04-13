import java.util.Scanner;
interface ATMService {
    void withdraw(Account a, double amount);
    void deposit(Account a, double amount);
    void checkBalance(Account a);
}
class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
class ATMOperation implements ATMService {

    public void withdraw(Account a, double amount) {
        if (amount > 0 && amount <= a.getBalance()) {
            a.setBalance(a.getBalance() - amount);
            System.out.println("Withdrawal Successful");
        } else {
            System.out.println("Insufficient Balance or Invalid Amount");
        }
    }

    public void deposit(Account a, double amount) {
        if (amount > 0) {
            a.setBalance(a.getBalance() + amount);
            System.out.println("Deposit Successful");
        } else {
            System.out.println("Invalid Deposit Amount");
        }
    }

    public void checkBalance(Account a) {
        System.out.println("Available Balance: " + a.getBalance());
    }
}
class ATM {
    private ATMService atmService;

    public ATM(ATMService atmService) {
        this.atmService = atmService;
    }

    public void withdrawMoney(Account a, double amount) {
        atmService.withdraw(a, amount);
    }

    public void depositMoney(Account a, double amount) {
        atmService.deposit(a, amount);
    }

    public void showBalance(Account a) {
        atmService.checkBalance(a);
    }
}
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Account a1 = new Account(12345, 1111, 10000);
        ATMService service = new ATMOperation();
        ATM atm = new ATM(service);

        System.out.print("Enter PIN: ");
        int enteredPin = sc.nextInt();

        if (enteredPin != a1.getPin()) {
            System.out.println("Invalid PIN. Access Denied.");
            return;
        }

        int choice;
        do {
            System.out.println("\n----- ATM MENU -----");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double wAmount = sc.nextDouble();
                    atm.withdrawMoney(a1, wAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double dAmount = sc.nextDouble();
                    atm.depositMoney(a1, dAmount);
                    break;

                case 3:
                    atm.showBalance(a1);
                    break;

                case 4:
                    System.out.println("Thank you for using ATM");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 4);

        sc.close();
    }
}
