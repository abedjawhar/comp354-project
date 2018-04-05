package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountDetailsController implements Initializable {
    private static final Logger logger = LogManager.getLogger(AccountDetailsController.class);

    private final static String FILE_PREFIX = "transactions";
    private final static String FILE_EXTENSION = ".csv";

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
    private SendMailController sender = new SendMailController();
    
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
            final String fileName = FILE_PREFIX + System.currentTimeMillis() + FILE_EXTENSION;
            generator.generateTransactionCsvFile(transactions,fileName);
            logger.info("Generated file: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
       @FXML
    public void sendTransactionEmail(){
        try{
            sender.SendMailNow();;
            System.out.println("File has been sent to your email");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
