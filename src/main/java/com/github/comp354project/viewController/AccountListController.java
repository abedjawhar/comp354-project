package com.github.comp354project.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.github.comp354project.viewModel.AccountViewModel;
 

public class AccountListController implements Initializable {
	
	@FXML private Label usernameLbl;
	@FXML private TextField accountIdTxt;
	@FXML private TableView<AccountViewModel> accountsTable;
	@FXML private TableColumn<AccountViewModel, String>  idCol;
	@FXML private TableColumn<AccountViewModel, String>  nameCol;
	@FXML private TableColumn<AccountViewModel, String>  typeCol;
	@FXML private TableColumn<AccountViewModel, String>  balanceCol;

	public void initialize(URL url, ResourceBundle rb) {
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

            Parent accountView = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/Account.fxml"));
	        Scene scene = new Scene(accountView, 800, 500);
	        
	        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        stage.setTitle("Account View");
	        stage.setScene(scene);
	    }
	}

	@FXML
	public void viewAllAccounts(ActionEvent event) throws IOException
	{
        Parent accountView = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/Account.fxml"));
        Scene scene = new Scene(accountView, 800, 500);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Account View - All");
        stage.setScene(scene);
	}
	
	@FXML
	public void addAccount()
	{
		System.out.println(accountIdTxt.getText());
	}

	@FXML
    public void logout(ActionEvent event) throws IOException
    {
        Parent login = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/Login.fxml"));
        Scene scene = new Scene(login, 278, 124);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Account View - All");
        stage.setScene(scene);
    }
}