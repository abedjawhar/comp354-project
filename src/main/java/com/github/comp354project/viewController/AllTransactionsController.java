package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.csv.ICSVGenerator;
import com.github.comp354project.model.user.UserService;
import com.github.comp354project.viewController.helper.AlertHelper;
import com.github.comp354project.viewController.helper.FileHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class AllTransactionsController implements Initializable {
    private static final Logger logger = LogManager.getLogger(AllTransactionsController.class);

    @FXML private Label totalBalanceLabel;
    @FXML private Label descriptionLabel;

    @FXML private Parent transactionTableView;

    @FXML private TransactionTableController transactionTableViewController;

    @Inject
    ICSVGenerator csvGenerator;

    private SendMailController sender = new SendMailController();

    private List<Account> accounts;

    public AllTransactionsController(){
        MyMoneyApplication.application.getComponent().inject(this);
    }


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
        try {
            File csv = csvGenerator.generateCSV(transactionTableViewController.getTableData());
            FileHelper.saveFile(csv);
        } catch (Exception e) {
            logger.error(e);
            AlertHelper.generateErrorAlert("Error", "Could not generate the statement", "Something went wrong :(").showAndWait();
        }
    }

    @FXML
    public void sendAllTransactionsEmail(){
        try{
            sender.SendMailNow();
            System.out.println("Done");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}









