package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.ITransactionService;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.auth.SessionManager;
import com.github.comp354project.model.csv.ICSVGenerator;
import com.github.comp354project.model.email.IEmailService;
import com.github.comp354project.model.exceptions.ValidationError;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.viewController.helper.AlertHelper;
import com.github.comp354project.viewController.helper.FileHelper;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    @FXML
    private TextField categoryTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    @Inject
    ICSVGenerator csvGenerator;

    @Inject
    IEmailService emailService;

    @Inject
    SessionManager sessionManager;

    private ObservableList<TransactionDisplayModel> tableData = FXCollections.observableArrayList();

    private List<Transaction> transactions = new ArrayList<>();

    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
        updateTableData();
    }

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

    private void updateTableData(){
        this.tableData.clear();
        this.transactions.stream()
                // filter by category
                .filter(t -> categoryTextField.getText().trim().length() == 0 || ((t.getCategory() != null) &&
                        t.getCategory().toLowerCase().startsWith(categoryTextField.getText().trim().toLowerCase())))
                // filter by start and end dates
                .filter(t -> (startDatePicker.getValue() == null || t.getDate() >= toEpochSecond(startDatePicker.getValue())) &&
                            (endDatePicker.getValue() == null || t.getDate() <= toEpochSecond(endDatePicker.getValue())))
                .forEach(t -> this.tableData.add(new TransactionDisplayModel(t)));
    }

    private long toEpochSecond(LocalDate localDateTime) {
        return localDateTime.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
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

        this.updateColumnWidth();

        this.categoryTextField.textProperty().addListener((observable, oldValue, newValue) -> this.updateTableData());
        this.startDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> this.updateTableData());
        this.endDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> this.updateTableData());

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
        this.updateColumnWidth();
    }

    private void updateColumnWidth(){
        int columnCount = (this.idCol.isVisible() ? 5 : 4);
        idCol.prefWidthProperty().bind(transactionTableView.widthProperty().divide(columnCount)); // w * 1/4
        dateCol.prefWidthProperty().bind(transactionTableView.widthProperty().divide(columnCount)); // w * 1/2
        amountCol.prefWidthProperty().bind(transactionTableView.widthProperty().divide(columnCount)); // w * 1/4
        categoryCol.prefWidthProperty().bind(transactionTableView.widthProperty().divide(columnCount)); // w * 1/4
        typeCol.prefWidthProperty().bind(transactionTableView.widthProperty().divide(columnCount)); // w * 1/4
    }

    @FXML
    public void exportAction() {
        try {
            File csv = csvGenerator.generateCSV(tableData);
            FileHelper.saveFile(csv);
        } catch (Exception e) {
            logger.error(e);
            AlertHelper.generateErrorAlert("Error", "Could not generate the statement", "Something went wrong :(").showAndWait();
        }
    }

    @FXML
    public void emailAction(){
        try{
            File csv = csvGenerator.generateCSV(tableData);
            String email = sessionManager.getUser().getEmail();
            if(Strings.isNullOrEmpty(email)){
                throw ValidationException.builder()
                        .message("Could not email the statement")
                        .error(ValidationError.builder()
                                .parameterName("email")
                                .message("Empty email").build()).build();
            }
            emailService.sendEmail(email, "Statement", "", csv, "statement.csv");
        }
        catch (ValidationException e){
            logger.error(e);
            AlertHelper.generateErrorAlert("Error", e).showAndWait();
        }
        catch (Exception e){
            logger.error(e);
            AlertHelper.generateErrorAlert("Error", "Could not email the statement", "Something went wrong :(").showAndWait();
        }
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
        private final static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public TransactionDisplayModel(Transaction transaction) {
            this.accountID = new SimpleIntegerProperty(transaction.getAccount().getID());
            Date date = Date.from(Instant.ofEpochSecond(transaction.getDate()));
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
