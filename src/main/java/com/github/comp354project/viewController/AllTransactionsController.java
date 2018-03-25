package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AllTransactionsController implements Initializable {

    @FXML private Label totalBalanceLabel;
    @FXML private Label descriptionLabel;

    @FXML private Parent transactionTableView;

    @FXML private TransactionTableController transactionTableViewController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setAccounts(List<Account> accounts){
        Double balance = 0.0;
        List<Transaction> transactions = new ArrayList<>();
        for(Account a : accounts){
            balance += a.getBalance();
            transactions.addAll(a.getTransactions());
        }
        this.transactionTableViewController.setTransactions(transactions);
        this.totalBalanceLabel.setText("$" + balance);
        this.descriptionLabel.setText("All Transactions");
        AnchorPane.setTopAnchor(descriptionLabel, 15.0);
    }

    @FXML
    public void gotoAccountList(MouseEvent event) throws IOException {
        MyMoneyApplication.application.displayAccounts();
    }
}
