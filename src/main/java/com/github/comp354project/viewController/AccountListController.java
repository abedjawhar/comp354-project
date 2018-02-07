package com.github.comp354project.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.comp354project.DaggerApplicationComponent;
import com.github.comp354project.service.auth.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.github.comp354project.viewModel.AccountViewModel;

import javax.inject.Inject;


public class AccountListController implements Initializable {
	
	@FXML private Label usernameLbl;
	@FXML private TextField accountIdTxt;
	@FXML private TableView<AccountViewModel> accountsTable;
	@FXML private TableColumn<AccountViewModel, String>  idCol;
	@FXML private TableColumn<AccountViewModel, String>  nameCol;
	@FXML private TableColumn<AccountViewModel, String>  typeCol;
	@FXML private TableColumn<AccountViewModel, String>  balanceCol;

	@Inject
	SessionManager sessionManager;

	public void initialize(URL url, ResourceBundle rb) {
		DaggerApplicationComponent.builder()
				.build()
				.inject(this);
		System.out.println(this.sessionManager);

		final ObservableList<AccountViewModel> data = FXCollections.observableArrayList(
		    new AccountViewModel("1", "Soctia", "Debt", "$1320.00"),
		    new AccountViewModel("2", "Soctia", "Debt", "$1320.00"),
		    new AccountViewModel("3", "Soctia", "Debt", "$1320.00"),
		    new AccountViewModel("4", "Soctia", "Debt", "$1320.00"),
		    new AccountViewModel("5", "Soctia", "Debt", "$1320.00"),
		    new AccountViewModel("6", "Soctia", "Debt", "$1320.00"),
		    new AccountViewModel("7", "Soctia", "Debt", "$1320.00"),
		    new AccountViewModel("8", "Soctia", "Debt", "$1320.00")
		);
		
		idCol.setCellValueFactory(new PropertyValueFactory<AccountViewModel,String>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<AccountViewModel,String>("name"));
		typeCol.setCellValueFactory(new PropertyValueFactory<AccountViewModel,String>("type"));
		balanceCol.setCellValueFactory(new PropertyValueFactory<AccountViewModel,String>("balance"));
		accountsTable.setItems(data);
	}
	
	@FXML
	public void selectAccount(MouseEvent event) throws IOException
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	        System.out.println(accountsTable.getSelectionModel().getSelectedItem().getId());
            StageManager.switchToAccount();
	    }
	}

	@FXML
	public void viewAllAccounts(ActionEvent event) throws IOException
	{
        StageManager.switchToAccount();
	}
	
	@FXML
	public void addAccount()
	{
		System.out.println(accountIdTxt.getText());
	}

	@FXML
    public void logout(ActionEvent event) throws IOException {
		this.sessionManager.logout();
        StageManager.switchToLogin();
    }
}