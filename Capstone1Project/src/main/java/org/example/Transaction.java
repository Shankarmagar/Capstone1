package org.example;
import java.util.Comparator;
public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction (String d, String t, String desc, String v, double amt)
    {
        this.date = d;
        this.time = t;
        this.description = desc;
        this.vendor =v;
        this.amount = amt;
    }
    //All the setters

    public void setDate(String d) {
        date = d;
    }

    public void setTime(String t) {
        time = t;
    }

    public void setDescription(String d) {
        description = d;
    }

    public void setVendor(String v) {
        this.vendor = v;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //All getters

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
}
class DateComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        // Compare the date first
        int dateComparison = t1.getDate().compareTo(t2.getDate());

        // If the dates are equal, compare the time
        if (dateComparison == 0) {
            return t1.getTime().compareTo(t2.getTime());
        }

        return dateComparison;
    }
}