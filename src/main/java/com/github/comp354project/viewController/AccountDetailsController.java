package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.auth.SessionManager;
import com.github.comp354project.model.csv.ICSVGenerator;
import com.github.comp354project.model.email.IEmailService;
import com.github.comp354project.viewController.helper.AlertHelper;
import com.github.comp354project.viewController.helper.FileHelper;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AccountDetailsController implements Initializable {
    private static final Logger logger = LogManager.getLogger(AccountDetailsController.class);
    @FXML
    private Label accountBalance;
    @FXML
    private Label accountDescription;
    @FXML
    private Label accountType;

    @Getter
    private Account account;

    @FXML
    @Getter
    private Parent transactionTableView;

    @FXML
    @Getter
    private TransactionTableController transactionTableViewController;

    public AccountDetailsController(){
        MyMoneyApplication.application.getComponent().inject(this);
    }
    
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void gotoAccountList() {
        MyMoneyApplication.application.displayAccounts();
    }

    public void setAccount(Account account) {
        this.account = account;

        List<Transaction> transactions = new ArrayList<>(account.getTransactions());
        this.getTransactionTableViewController().setTransactions(transactions);
        this.getTransactionTableViewController().hideAccountIDColumn();

        accountBalance.setText("$" + account.getBalance());
        accountDescription.setText(account.getID() + ": " + account.getBankName());
        accountType.setText(account.getType());
    }
}
