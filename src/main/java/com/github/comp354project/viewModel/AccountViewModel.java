package com.github.comp354project.viewModel;

import javafx.beans.property.SimpleStringProperty;

public class AccountViewModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
	private final SimpleStringProperty type;
    private final SimpleStringProperty balance;
    
    public AccountViewModel(String id, String name, String type, String balance) {
		super();
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleStringProperty(type);
		this.balance = new SimpleStringProperty(balance);
	}

    public String getId() {
        return id.get();
    }
    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }
    public void setType(String type) {
        this.type.set(type);
    }   

    public String getBalance() {
        return balance.get();
    }
    public void setBalance(String balance) {
        this.balance.set(balance);
    }
}
