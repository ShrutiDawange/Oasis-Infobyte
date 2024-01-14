import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankUser {
    String userId;
    String pin;
    double balance;
    StringBuilder transactionHistory;

    BankUser(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new StringBuilder("Transaction History:\n");
    }
}

public class BankATM {
    private static Map<String, BankUser> bankUsers = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeUserAccounts();

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        BankUser currentUser = authenticateBankUser(userId, pin);

        if (currentUser != null) {
            System.out.println("Authentication successful. Welcome, " + currentUser.userId + "!");
            performBankOperations(currentUser);
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    private static void initializeUserAccounts() {
        bankUsers.put("user1", new BankUser("user1", "1234", 1000.0));
        bankUsers.put("user2", new BankUser("user2", "5678", 1500.0));
        // Add more user accounts as needed
    }

    private static BankUser authenticateBankUser(String userId, String pin) {
        if (bankUsers.containsKey(userId)) {
            BankUser user = bankUsers.get(userId);
            if (user.pin.equals(pin)) {
                return user;
            }
        }
        return null;
    }

    private static void performBankOperations(BankUser user) {
        while (true) {
            System.out.println("\nBank Operations:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdrawal");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    displayTransactionHistory(user);
                    break;
                case 2:
                    performWithdrawalOperation(user);
                    break;
                case 3:
                    performDepositOperation(user);
                    break;
                case 4:
                    performTransferOperation(user);
                    break;
                case 5:
                    System.out.println("Exiting Bank ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayTransactionHistory(BankUser user) {
        System.out.println(user.transactionHistory.toString());
    }

    private static void performWithdrawalOperation(BankUser user) {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        if (amount > 0 && amount <= user.balance) {
            user.balance -= amount;
            user.transactionHistory.append("Withdrawal: -").append(amount).append("\n");
            System.out.println("Withdrawal successful. Remaining balance: " + user.balance);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    private static void performDepositOperation(BankUser user) {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        if (amount > 0) {
            user.balance += amount;
            user.transactionHistory.append("Deposit: +").append(amount).append("\n");
            System.out.println("Deposit successful. Updated balance: " + user.balance);
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    private static void performTransferOperation(BankUser user) {
        System.out.print("Enter recipient's user ID: ");
        String recipientUserId = scanner.nextLine();

        if (bankUsers.containsKey(recipientUserId) && !recipientUserId.equals(user.userId)) {
            BankUser recipient = bankUsers.get(recipientUserId);

            System.out.print("Enter transfer amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume the newline character

            if (amount > 0 && amount <= user.balance) {
                user.balance -= amount;
                recipient.balance += amount;

                user.transactionHistory.append("Transfer to ").append(recipientUserId).append(": -").append(amount).append("\n");
                recipient.transactionHistory.append("Transfer from ").append(user.userId).append(": +").append(amount).append("\n");

                System.out.println("Transfer successful. Remaining balance: " + user.balance);
            } else {
                System.out.println("Invalid amount or insufficient balance for transfer.");
            }
        } else {
            System.out.println("Invalid recipient user ID.");
        }
    }
}
