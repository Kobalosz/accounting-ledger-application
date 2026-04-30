package models;

import util.Colors;

import java.time.LocalTime;
import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, Double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

//    Ran into a problem displaying the information so I had to create a method
@Override
public String toString() {
    String amountColor = amount > 0 ? Colors.BG_GREEN : Colors.RED;
    String sign        = amount > 0 ? "+" : "";

    return String.format("%-12s|%-10s|%-20s|%-15s|%s%s%.2f%s",
            date,
            time,
            description,
            vendor,
            amountColor,
            sign,
            amount,
            Colors.RESET);
}
}
