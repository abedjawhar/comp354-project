package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
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


    private TransactionGenerateFileController generator = new TransactionGenerateFileController();


    private List<Account> accounts;


    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setAccounts(List<Account> accounts){
        this.accounts = accounts;
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


    /**
     * this method is used to generate a CSV that will list all the transactions for every account. It creates a
     * an array list of Transactions, then goes through a for loop that will get every transaction related to each account
     * in the List<Account> accounts arraylist. It then passes the transactions List through the GenerateFile method paramater, then names the file,
     * then finally generates a CSV
     */
    @FXML
    public void generateAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        if(accounts != null) {
            for(Account a : accounts){
                transactions.addAll(a.getTransactions());
            }
        }

        try {
            generator.GenerateFile(transactions,"AllTransactions.csv");
            System.out.println("File" +" "+"AllTransactions.csv"+" "+"created");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    }









