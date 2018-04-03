package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AccountDetailsController implements Initializable {
    @FXML
    private Parent transactionTableView;
    @FXML
    private TransactionTableController transactionTableViewController;
    @FXML
    private Label accountBalance;
    @FXML
    private Label accountDescription;
    @FXML
    private Label accountType;

    @Getter
    private Account account;

    private TransactionGenerateFileController generator = new TransactionGenerateFileController();

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void gotoAccountList() {
        MyMoneyApplication.application.displayAccounts();
    }

    public void setAccount(Account account) {
        this.account = account;

        List<Transaction> transactions = new ArrayList<>(account.getTransactions());
        this.transactionTableViewController.setTransactions(transactions);
        this.transactionTableViewController.hideAccountIDColumn();

        accountBalance.setText("$" + account.getBalance());
        accountDescription.setText(account.getID() + ": " + account.getBankName());
        accountType.setText(account.getType());
    }

    /**
     *this method is used to generate a CSV that will list all the transactions for a specific account. it Creates an arry list of Transactions
     * and passes the transactions through its accepted paramater. It then calls the GenerateFile function in order to create the csv file
     */


    @FXML
    public void generateTransactions() {

        List<Transaction> transactions = new ArrayList<>(account.getTransactions());
        try {
            generator.GenerateFile(transactions,"Transaction.csv");
            System.out.println("File" +" "+"Transactions.csv"+" "+"created");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
