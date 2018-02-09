package com.github.comp354project.viewController.view;

import com.github.comp354project.service.account.Transaction;
import com.github.comp354project.viewController.model.TransactionDisplayModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionTableController implements Initializable {

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

    public TransactionTableController() {
        System.out.println("TransactionTableController");
    }

    public void addTransactions(List<Transaction> transactions) {
        System.out.println("add transactions");
        transactions.forEach(t -> this.tableData.add(new TransactionDisplayModel(t)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        this.categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        this.typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.transactionTableView.setItems(this.tableData);
        System.out.println(this.dateCol);
    }
}
