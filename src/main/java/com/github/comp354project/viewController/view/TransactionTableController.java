package com.github.comp354project.viewController.view;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.service.account.ITransactionService;
import com.github.comp354project.service.account.Transaction;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.viewController.model.TransactionDisplayModel;
import com.google.common.collect.ImmutableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionTableController implements Initializable {
    private static final Logger logger = LogManager.getLogger(TransactionTableController.class);

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

    private List<Transaction> transactions;

    @Inject
    ITransactionService transactionService;

    private static final List<String> defaultCategories = ImmutableList.<String>builder()
            .add("Leisure")
            .add("Groceries")
            .add("Payments")
            .add("Rent").build();

    public TransactionTableController() {
    }

    public void setTransactions(List<Transaction> transactions) {
        this.tableData.clear();
        this.transactions = transactions;
        transactions.forEach(t -> this.tableData.add(new TransactionDisplayModel(t)));
        MyMoneyApplication.application.getComponent().inject(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}
