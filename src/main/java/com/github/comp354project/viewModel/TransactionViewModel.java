package com.github.comp354project.viewModel;

import javafx.beans.property.SimpleStringProperty;

public class TransactionViewModel {
    private final SimpleStringProperty date;
    private final SimpleStringProperty amount;
    private final SimpleStringProperty category;
	private final SimpleStringProperty type;
    private final SimpleStringProperty account;
    
    public TransactionViewModel(String date, String amount, String category, String type, String account) {
		super();
		this.date = new SimpleStringProperty(date);
		this.amount = new SimpleStringProperty(amount);
		this.category = new SimpleStringProperty(category);
		this.type = new SimpleStringProperty(type);
		this.account = new SimpleStringProperty(account);
	}

    public String getDate() {
        return date.get();
    }
    public void setDate(String date) {
        this.date.set(date);
    }

    public String getAmount() {
        return amount.get();
    }
    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getCategory() {
        return category.get();
    }
    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getType() {
        return type.get();
    }
    public void setType(String type) {
        this.type.set(type);
    }

    public String getAccount() {
        return account.get();
    }
    public void setAcount(String account) {
        this.account.set(account);
    }
    
    

}
