package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.Transaction;
import com.github.comp354project.viewController.model.TransactionDisplayModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AllTransactionsController implements Initializable {

    @FXML private TableView<TransactionDisplayModel> transactionTableView;
    @FXML private TableColumn<TransactionDisplayModel, String> dateCol;
    @FXML private TableColumn<TransactionDisplayModel, String> amountCol;
    @FXML private TableColumn<TransactionDisplayModel, String> categoryCol;
    @FXML private TableColumn<TransactionDisplayModel, String> typeCol;

    @FXML private Label totalBalanceLabel;
    @FXML private Label descriptionLabel;

    private ObservableList<TransactionDisplayModel> tableData = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dateCol.setCellValueFactory(new PropertyValueFactory<TransactionDisplayModel,String>("date"));
        this.amountCol.setCellValueFactory(new PropertyValueFactory<TransactionDisplayModel,String>("amount"));
        this.categoryCol.setCellValueFactory(new PropertyValueFactory<TransactionDisplayModel,String>("category"));
        this.typeCol.setCellValueFactory(new PropertyValueFactory<TransactionDisplayModel,String>("type"));
        this.transactionTableView.setItems(this.tableData);
    }

    public void setAccounts(List<Account> accounts){
        Double balance = 0.0;
        for(Account a : accounts){
            balance += a.getBalance();
            for(Transaction t : a.getTransactions()){
                this.tableData.add(new TransactionDisplayModel((t)));
            }
        }
        
        this.totalBalanceLabel.setText("$" + balance);
        this.descriptionLabel.setText("All Transactions");
        AnchorPane.setTopAnchor(descriptionLabel, 15.0);
    }

    @FXML
    public void gotoAccountList(MouseEvent event) throws IOException {
        MyMoneyApplication.application.displayAccounts();
    }
}
