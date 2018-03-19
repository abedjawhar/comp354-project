package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.view.TransactionTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Getter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountDetailsController implements Initializable {

    @FXML
    private TransactionTable transactionTable;
    @FXML
    private Label accountBalance;
    @FXML
    private Label accountDescription;
    @FXML
    private Label accountType;

    @Getter
    private Account account;

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void gotoAccountList() {
        MyMoneyApplication.application.displayAccounts();
    }

    public void setAccount(Account account) {
        this.account = account;

        List<Transaction> transactions = new ArrayList<>(account.getTransactions());
        this.transactionTable.addTransactions(transactions);
        this.transactionTable.hideAccountIDColumn();

        accountBalance.setText("$" + account.getBalance());
        accountDescription.setText(account.getID() + ": " + account.getBankName());
        accountType.setText(account.getType());
    }

}