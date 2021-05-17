package bank.demo.splendex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    private Account activeAccount;
    private List<Account> bankAccounts = new ArrayList<>();

    public Bank() {
        menu();
    }

    public void menu() {
        if (activeAccount != null) {
            System.out.println("Balance: " + activeAccount.getBalance());
        }

        System.out.println("Login: '1', Register: '2', Deposit: '3', Withdraw: '4', Transfer: '5', Transaction history: '6' Logout: '0'");

        String input = getUserInput();

        if (input.equals("1")) {
            login();
        } else if (input.equals("2")) {
            registerAccount();
        } else if (input.equals("3")) {
            deposit();
        } else if (input.equals("4")) {
            withdraw();
        } else if (input.equals("5")) {
            transfer();
        } else if (input.equals("6")) {
            history();
        }else if (input.equals("0")) {
            logout();
        } else {
            menu();
        }
    }

    private void logout() {
        activeAccount = null;
        menu();
    }

    private void history() {
        for (Transaction t : activeAccount.getTransactionList()) {
            String direction = "";
            if (t.getSender() == activeAccount) {
                direction = "Sender";
            } else {
                direction = "Recipient";
            }

            System.out.println("Date: " + t.getDate() + "\nAmount: " + t.getAmount() + "\nBalance after transaction: " + t.getBalanceAfterTr() + "\nDirection: " + direction);
        }
    }

    private void transfer() {
        System.out.println("Please enter recipient name: ");
        String recipientName = getUserInput();
        Integer amount;

        Account recipient = bankAccounts.stream().filter(account -> recipientName.equals(account.getName())).findAny().orElse(null);

        if (recipient == null) {
            System.out.println("No such account!");
        } else {
            System.out.println("Enter amount: ");
            amount = Integer.parseInt(getUserInput());

            if (withdraw(amount)) {
                System.out.println("Successful!");
                recipient.deposit(amount);

                Transaction transaction = new Transaction(recipient, activeAccount, amount, activeAccount.getBalance());

                activeAccount.addTransaction(transaction);
                recipient.addTransaction(transaction);
            } else {
                System.out.println("Not enough balance!");
            }
        }
        menu();
    }

    private void withdraw() {
        System.out.println("Enter amount to withdraw: ");
        Integer amount = Integer.parseInt(getUserInput());

        if (amount > 0 && activeAccount.getBalance() >= amount) {
            activeAccount.withdraw(amount);
        }
        menu();
    }

    private boolean withdraw(int amount) {
        if (amount > 0 && activeAccount.getBalance() >= amount) {
            activeAccount.withdraw(amount);
            return true;
        }

        return false;
    }

    private void deposit() {
        System.out.println("Enter amount to Deposit: ");
        Integer amount = Integer.parseInt(getUserInput());

        if (amount > 0) {
            activeAccount.deposit(amount);
        }
        menu();
    }

    private void registerAccount() {
        System.out.println("Please enter your name!");
        String name = getUserInput();

        if (bankAccounts.stream().filter(account -> name.equals(account.getName())).findAny().orElse(null) == null) {
            bankAccounts.add(new Account(name));
            System.out.println("Successfully registered!");
        } else {
            System.out.println("Already registered!");
        }

        menu();
    }

    private void login() {
        System.out.println("Type in your name!");
        String name = getUserInput();

        activeAccount = bankAccounts.stream().filter(account -> name.equals(account.getName())).findAny().orElse(null);
        if (activeAccount == null) {
            System.out.println("No such account");
        }
        menu();

    }

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

}
