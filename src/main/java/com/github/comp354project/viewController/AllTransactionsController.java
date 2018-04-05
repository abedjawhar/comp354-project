package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.auth.SessionManager;
import com.github.comp354project.model.csv.ICSVGenerator;
import com.github.comp354project.model.email.IEmailService;
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
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.mail.MessagingException;
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

    @FXML
    @Getter
    private Parent transactionTableView;

    @FXML
    @Getter
    private TransactionTableController transactionTableViewController;

    public AllTransactionsController(){
        MyMoneyApplication.application.getComponent().inject(this);
    }

    @FXML
    public void gotoAccountList() {
        MyMoneyApplication.application.displayAccounts();
    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setAccounts(List<Account> accounts){
        Double balance = 0.0;
        List<Transaction> transactions = new ArrayList<>();
        for(Account a : accounts){
            balance += a.getBalance();
            transactions.addAll(a.getTransactions());
        }
        this.getTransactionTableViewController().setTransactions(transactions);
        this.totalBalanceLabel.setText("$" + balance);
        this.descriptionLabel.setText("All Transactions");
        AnchorPane.setTopAnchor(descriptionLabel, 15.0);
    }
}









