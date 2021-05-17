package bank.demo.splendex;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String name;
    private int balance;
    private final List<Transaction> transactionList = new ArrayList<>();

    public Account(String name) {
        this.name = name;
        this.balance = 5000;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    public void withdraw(int amount) {
        this.balance = this.balance - amount;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

}
