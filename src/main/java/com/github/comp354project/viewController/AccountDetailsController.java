package com.github.comp354project.viewController;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.List;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.Transaction;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.Data;
import lombok.Getter;

public class AccountDetailsController implements Initializable {
	@FXML private TableView<TransactionDisplayModel> transactionTableView;
	@FXML private TableColumn<TransactionDisplayModel, String> dateCol;
	@FXML private TableColumn<TransactionDisplayModel, String> amountCol;
	@FXML private TableColumn<TransactionDisplayModel, String> categoryCol;
	@FXML private TableColumn<TransactionDisplayModel, String> typeCol;

	@FXML private Label accountBalance;
	@FXML private Label accountDescription;
	@FXML private Label accountType;
	@FXML private Button deleteAccountBtn;
	@FXML private AnchorPane anchorPane;

	private ObservableList<TransactionDisplayModel> tableData = FXCollections.observableArrayList();

	@Getter
	private Account account;

	public void initialize(URL url, ResourceBundle rb) {
		dateCol.setCellValueFactory(new PropertyValueFactory<TransactionDisplayModel,String>("date"));
		amountCol.setCellValueFactory(new PropertyValueFactory<TransactionDisplayModel,String>("amount"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<TransactionDisplayModel,String>("category"));
		typeCol.setCellValueFactory(new PropertyValueFactory<TransactionDisplayModel,String>("type"));
		transactionTableView.setItems(tableData);
	}
	
	@FXML
	public void gotoAccountList(MouseEvent event) throws IOException {
		MyMoneyApplication.application.displayAccounts();
	}

	public void setAccount(Account account){
		this.account = account;
		tableData.clear();
		for(Transaction t : account.getTransactions()){
			tableData.add(new TransactionDisplayModel((t)));
		}
		accountBalance.setText("$" + account.getBalance());
		accountDescription.setText(account.getID() + ": " + account.getBankName());
		accountType.setText(account.getType());
	}

	public void setAccounts(List<Account> accounts){
		Double balance = 0.0;
		for(Account a : accounts){
			balance += a.getBalance();
			for(Transaction t : a.getTransactions()){
				tableData.add(new TransactionDisplayModel((t)));
			}
		}
		accountBalance.setText("$" + balance);
		accountDescription.setText("All Transaction");
		AnchorPane.setTopAnchor(accountDescription, 15.0);
		accountType.setText("");
	}

	public static class TransactionDisplayModel {
		private SimpleStringProperty date;
		private SimpleDoubleProperty amount;
		private SimpleStringProperty category;
		private SimpleStringProperty type;

		TransactionDisplayModel(Transaction transaction) {
			Date date = Date.from(Instant.ofEpochMilli(transaction.getDate()));
			this.date = new SimpleStringProperty(date.toString());
			this.amount = new SimpleDoubleProperty(transaction.getAmount());
			if(transaction.getType().equals("Transfer")){
				this.type = new SimpleStringProperty("Transfer to " + transaction.getDestinationID().toString());
			} else if (transaction.getType().equals("Deposit")){
				this.type = new SimpleStringProperty("Deposit from " + transaction.getSourceID().toString());
			} else {
				this.type = new SimpleStringProperty("Withdrawal");
			}
			this.category = new SimpleStringProperty(transaction.getCategory());
		}

		public void setDate(String date){
			this.date.set(date);
		}

		public String getDate(){
			return this.date.get();
		}

		public void setAmount(Double amount){
			this.amount.set(amount);
		}

		public Double getAmount(){
			return this.amount.get();
		}

		public void setCategory(String category){
			this.category.set(category);
		}

		public String getCategory(){
			return this.category.get();
		}

		public void setType(String type){
			this.type.set(type);
		}

		public String getType(){
			return this.type.get();
		}
	}


}