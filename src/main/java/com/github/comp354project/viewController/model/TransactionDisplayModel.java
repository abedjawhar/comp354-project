package com.github.comp354project.viewController.model;

import com.github.comp354project.service.account.Transaction;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransactionDisplayModel {

    private SimpleStringProperty date;
    private SimpleDoubleProperty amount;
    private SimpleStringProperty category;
    private SimpleStringProperty type;
    private final static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    public TransactionDisplayModel(Transaction transaction) {
        Date date = Date.from(Instant.ofEpochMilli(transaction.getDate()));
        this.date = new SimpleStringProperty(formatter.format(date));
        this.amount = new SimpleDoubleProperty(transaction.getAmount());
        if (transaction.getType().equals("Transfer")) {
            this.type = new SimpleStringProperty("Transfer to " + transaction.getDestinationID().toString());
        } else if (transaction.getType().equals("Deposit")) {
            this.type = new SimpleStringProperty("Deposit from " + transaction.getSourceID().toString());
        } else {
            this.type = new SimpleStringProperty("Withdrawal");
        }
        this.category = new SimpleStringProperty(transaction.getCategory());
    }

    public String getDate() {
        return this.date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public Double getAmount() {
        return this.amount.get();
    }

    public void setAmount(Double amount) {
        this.amount.set(amount);
    }

    public String getCategory() {
        return this.category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getType() {
        return this.type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

}
