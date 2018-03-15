package com.github.comp354project.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.IAccountService;
import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.service.auth.exceptions.AuthenticationException;
import com.github.comp354project.service.auth.exceptions.AuthorisationException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.IUserService;
import com.github.comp354project.viewController.helper.AlertHelper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import lombok.Data;

import javax.inject.Inject;


public class AccountListController implements Initializable {

	@FXML private Label usernameLbl;
	@FXML private TextField accountIdTxt;
	@FXML private TableView<AccountDisplayModel> accountsTable;
	@FXML private TableColumn<AccountDisplayModel, Integer>  idCol;
	@FXML private TableColumn<AccountDisplayModel, String>  bankNameCol;
	@FXML private TableColumn<AccountDisplayModel, String>  typeCol;
	@FXML private TableColumn<AccountDisplayModel, Double>  balanceCol;

	private List<Account> accounts = new ArrayList<>();
	private ObservableList<AccountDisplayModel> tableData = FXCollections.observableArrayList();

	@Inject
	SessionManager sessionManager;

	@Inject
	IAccountService accountService;

	@Inject
	IUserService userService;

	public AccountListController() {
		MyMoneyApplication.application.getComponent().inject(this);
	}

	public void initialize(URL url, ResourceBundle rb) {
		idCol.setCellValueFactory(new PropertyValueFactory<AccountDisplayModel,Integer>("id"));
		bankNameCol.setCellValueFactory(new PropertyValueFactory<AccountDisplayModel,String>("bankName"));
		typeCol.setCellValueFactory(new PropertyValueFactory<AccountDisplayModel,String>("type"));
		balanceCol.setCellValueFactory(new PropertyValueFactory<AccountDisplayModel,Double>("balance"));
		accountsTable.setItems(tableData);
	}

	public void setAccounts(List<Account> accounts){
		this.accounts = accounts;
		tableData.clear();
		for(Account account : accounts){
			tableData.add(new AccountDisplayModel(account));
		}
	}

	@FXML
	public void selectAccount(MouseEvent event)  {
	    if (event.getClickCount() == 2) {
            MyMoneyApplication.application.displayAccountDetails(accounts.get(accountsTable.getSelectionModel().getSelectedIndex()));
	    }
	}

	@FXML
	public void viewAllAccounts(ActionEvent event)  {
		MyMoneyApplication.application.displayAllAccountDetails(accounts);
	}

	@FXML
    public void logout(ActionEvent event)  {
		this.sessionManager.logout();
		MyMoneyApplication.application.displayLogin();
    }

	@FXML
	public void updateUserInfo(ActionEvent event) throws IOException {
		MyMoneyApplication.application.displayUpdateUser();
	}

	@FXML
	public void addAccount() {
		try {
			GetRemoteAccountRequest remoteAccountRequest = GetRemoteAccountRequest.builder()
					.accountID(this.accountIdFromString())
					.build();
			Account account = this.accountService.addAccount(remoteAccountRequest, this.sessionManager.getUser());
			this.accounts.add(account);
			this.tableData.add(new AccountDisplayModel(account));
		} catch (ValidationException e) {
			AlertHelper.generateErrorAlert("Creation error", "Error validating account", e)
					.showAndWait();
		} catch(RuntimeException e) {
			AlertHelper.generateErrorAlert("Creation error", "Error validating account", e.getMessage())
					.showAndWait();
		}
	}

	@FXML
	public void removeAccount() {
		try {
			int index = accountsTable.getSelectionModel().getSelectedIndex();
			if (index == -1) return; // no account selected
			userService.deleteBankAccount(accounts.get(index));
			accounts.remove(index);
			setAccounts(accounts);
		}
		catch (ValidationException e) {
			AlertHelper.generateErrorAlert("removeAccount error", "Error validating account", e).showAndWait();
		}
		catch (AuthenticationException e) {
			AlertHelper.generateErrorAlert("removeAccount error", "Error authenticating user", e.getMessage());
		}
		catch (AuthorisationException e) {
			AlertHelper.generateErrorAlert("removeAccount error",
					"Error, user does not have the authority to delete the account", e.getMessage());
		}
	}

	public static class AccountDisplayModel {
		private final SimpleIntegerProperty id;
		private final SimpleStringProperty bankName;
		private final SimpleStringProperty type;
		private final SimpleDoubleProperty balance;

		public AccountDisplayModel(Account account) {
			this.id = new SimpleIntegerProperty(account.getID());
			this.bankName = new SimpleStringProperty(account.getBankName());
			this.type = new SimpleStringProperty(account.getType());
			this.balance = new SimpleDoubleProperty(account.getBalance());
		}

		public void setId(Integer id){
			this.id.set(id);
		}

		public Integer getId(){
			return this.id.get();
		}

		public void setBankName(String bankName){
			this.bankName.set(bankName);
		}

		public String getBankName(){
			return this.bankName.get();
		}

		public void setType(String type){
			this.type.set(type);
		}

		public String getType(){
			return this.type.get();
		}

		public void setBalance(Double balance){
			this.balance.set(balance);
		}

		public Double getBalance(){
			return this.balance.get();
		}
	}

	private Integer accountIdFromString() throws RuntimeException {
		try {
			return Integer.parseInt(this.accountIdTxt.getText());
		} catch(NumberFormatException e) {
			throw new RuntimeException("Invalid accountID");
		}
	}
}