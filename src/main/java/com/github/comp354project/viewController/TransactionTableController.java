package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.ITransactionService;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.exceptions.ValidationException;
import com.google.common.collect.ImmutableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TransactionTableController implements Initializable {
    private static final Logger logger = LogManager.getLogger(TransactionTableController.class);

    @FXML
    private TableColumn<TransactionDisplayModel, Integer> idCol;

    @FXML
    private TableView<TransactionDisplayModel> transactionTableView;
    @FXML
    private TableColumn<TransactionDisplayModel, String> dateCol;
    @FXML
    private TableColumn<TransactionDisplayModel, String> amountCol;
    @FXML
    private TableColumn<TransactionDisplayModel, String> categoryCol;
    @FXML
    private TableColumn<TransactionDisplayModel, String> typeCol;

    private ObservableList<TransactionDisplayModel> tableData = FXCollections.observableArrayList();

    @Getter
    private List<Transaction> transactions;

    @Inject
    ITransactionService transactionService;

    private static final List<String> defaultCategories = ImmutableList.<String>builder()
            .add("Leisure")
            .add("Groceries")
            .add("Payments")
            .add("Rent").build();

    public TransactionTableController() {
        MyMoneyApplication.application.getComponent().inject(this);
    }

    public void setCategoryFilter(String category){
        List<Transaction> filteredTransactions = this.transactions.stream()
                .filter(t -> {
                    return category.length() == 0 || ((t.getCategory() != null) && t.getCategory().toLowerCase().contains(category.toLowerCase()));
                }).collect(Collectors.toList());
        this.tableData.clear();
        filteredTransactions.forEach(t -> this.tableData.add(new TransactionDisplayModel(t)));
    }

    public void setTransactions(List<Transaction> transactions) {
        this.tableData.clear();
        this.transactions = transactions;
        transactions.forEach(t -> this.tableData.add(new TransactionDisplayModel(t)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.idCol.setCellValueFactory(new PropertyValueFactory<>("accountID"));
        this.dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        this.categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        this.typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.transactionTableView.setItems(this.tableData);
        this.transactionTableView.setEditable(true);

        categoryCol.setCellFactory(tableCol -> {
            ComboBoxTableCell<TransactionDisplayModel, String> ct = new ComboBoxTableCell<>();
            ct.getItems().addAll(defaultCategories);
            ct.setComboBoxEditable(true);
            return ct;
        });
        categoryCol.setOnEditCommit(event -> {
            String category = event.getNewValue() != null ? event.getNewValue() :
                    event.getOldValue();
            category = category.trim();

            Integer transactionIndex = event.getTablePosition().getRow();
            Transaction transactionToUpdate = this.transactions.get(transactionIndex);
            try {
                Transaction updatedTransaction = transactionService.updateTransactionCategory(transactionToUpdate.getID(), category);
                this.transactions.set(transactionIndex, updatedTransaction);
                event.getTableView().getItems().set(transactionIndex, new TransactionDisplayModel(updatedTransaction));
            } catch (ValidationException e){
                event.getTableView().getItems().set(transactionIndex, new TransactionDisplayModel(transactionToUpdate));
                logger.error(e);
            }
        });
    }

    public void hideAccountIDColumn() {
        this.idCol.setVisible(false);
    }

    public static class TransactionDisplayModel {

        public void setAccountID(int accountID) {
            this.accountID.set(accountID);
        }

        private SimpleIntegerProperty accountID;
        private SimpleStringProperty date;
        private SimpleDoubleProperty amount;
        private SimpleStringProperty category;
        private SimpleStringProperty type;
        private final static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        public TransactionDisplayModel(Transaction transaction) {
            this.accountID = new SimpleIntegerProperty(transaction.getAccount().getID());
            Date date = Date.from(Instant.ofEpochMilli(transaction.getDate()));
            this.date = new SimpleStringProperty(formatter.format(date));
            this.amount = new SimpleDoubleProperty(transaction.getAmount());
            if (transaction.getType().equals("Transfer")) {
                this.type = new SimpleStringProperty("Transfer to " + transaction.getDestinationID().toString());
            } else if (transaction.getType().equals("Deposit")) {
                this.type = new SimpleStringProperty("Deposit from " + transaction.getSourceID().toString());
            } else {
                this.type = new SimpleStringProperty("Withdrawal");
            }
            this.category = new SimpleStringProperty(transaction.getCategory());
        }


        public Integer getAccountID() { return this.accountID.get(); }

        public void setAccountID(Integer accountID) { this.accountID.set(accountID); }

        public String getDate() {
            return this.date.get();
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public Double getAmount() {
            return this.amount.get();
        }

        public void setAmount(Double amount) {
            this.amount.set(amount);
        }

        public String getCategory() {
            return this.category.get();
        }

        public void setCategory(String category) {
            this.category.set(category);
        }

        public String getType() {
            return this.type.get();
        }

        public void setType(String type) {
            this.type.set(type);
        }
    }

}
