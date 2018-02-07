package com.github.comp354project.viewController;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.Transaction;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
		StageManager.switchToAccountList();
	}

	public void setAccount(Account account){
		this.account = account;
		tableData.clear();
		for(Transaction t : account.getTransactions()){
			tableData.add(new TransactionDisplayModel((t)));
		}
	}

	@Data
	private static class TransactionDisplayModel {
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
		}
	}


}