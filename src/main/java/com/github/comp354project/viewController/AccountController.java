package com.github.comp354project.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.github.comp354project.viewModel.TransactionViewModel;

public class AccountController implements Initializable {
	@FXML private TableView<TransactionViewModel> transactions;
	@FXML private TableColumn<TransactionViewModel, String> date;
	@FXML private TableColumn<TransactionViewModel, String> amount;
	@FXML private TableColumn<TransactionViewModel, String> category;
	@FXML private TableColumn<TransactionViewModel, String> type;
	@FXML private TableColumn<TransactionViewModel, String> account;

	public void initialize(URL url, ResourceBundle rb) {
		final ObservableList<TransactionViewModel> data = FXCollections.observableArrayList(
		    new TransactionViewModel("Sep 31, 2016", "$32.12", "groceries", "withdraw", "Scotia Visa"),
		    new TransactionViewModel("Oct 1, 2016", "$12.12", "groceries", "withdraw", "TD Mastercard"),
		    new TransactionViewModel("Oct 7, 2016", "$100.96", "take out", "withdraw", "Scotia Debit"),
		    new TransactionViewModel("Oct 8, 2016", "$1.25", "groceries", "withdraw", "Manulife Visa"),
		    new TransactionViewModel("Oct 12, 2016", "$2.12", "entertainment", "withdraw", "Scotia Visa"),
		    new TransactionViewModel("Oct 19, 2016", "$21.21", "groceries", "withdraw", "BMO Debit"),
		    new TransactionViewModel("Oct 23, 2016", "$35.62", "entertainment", "withdraw", "Scotia Debit")
		);
		
		date.setCellValueFactory(new PropertyValueFactory<TransactionViewModel,String>("date"));
		amount.setCellValueFactory(new PropertyValueFactory<TransactionViewModel,String>("amount"));
		category.setCellValueFactory(new PropertyValueFactory<TransactionViewModel,String>("category"));
		type.setCellValueFactory(new PropertyValueFactory<TransactionViewModel,String>("type"));
		account.setCellValueFactory(new PropertyValueFactory<TransactionViewModel,String>("account"));
		transactions.setItems(data);
	}
	
	@FXML
	public void gotoAccountList(MouseEvent event) throws IOException
	{
		StageManager.switchToAccountList();;
	}

}