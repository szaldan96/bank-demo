package bank.demo.splendex;

import java.time.LocalDateTime;

public class Transaction {

    private final Account recipitent;
    private final Account sender;
    private final int amount;
    private final int balanceAfterTr;
    private final LocalDateTime date;

    public Transaction(Account recipitent, Account sender, int amount, int balanceAfterTr) {
        this.recipitent = recipitent;
        this.sender = sender;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.balanceAfterTr = balanceAfterTr;
    }

    public Account getRecipitent() {
        return recipitent;
    }

    public Account getSender() {
        return sender;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getBalanceAfterTr() {
        return balanceAfterTr;
    }

}
