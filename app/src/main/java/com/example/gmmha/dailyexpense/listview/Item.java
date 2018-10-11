package com.example.gmmha.dailyexpense.listview;

public class Item {


    String amount;
    String date;
    String description;

    public Item() {
    }

    public Item(String amount, String date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
