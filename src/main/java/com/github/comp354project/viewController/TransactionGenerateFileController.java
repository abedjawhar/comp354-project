package com.github.comp354project.viewController;

import java.io.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractSequentialList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.account.ITransactionService;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.viewController.TransactionTableController.TransactionDisplayModel;
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

/**
 * This class is used to mainly to create the GenerateFile method, which will be used to generate a CSV file that will
 * display all the transactions of one account or the transactions of all the accounts
 */

public class TransactionGenerateFileController {

    /**
     * This method is used to generate and display a CSV file or excel file using the attributes from the Transaction
     * Class.
     * @param transactions first parameter that accepts an Array List of Transactions
     * @param FileName second parameter that accepts a String that will name the CSV file
     * @throws IOException
     */

    public void GenerateFile (List<Transaction> transactions, String FileName) throws IOException {

        PrintWriter pw = new PrintWriter(new File(FileName));

        Iterator<Transaction> i = transactions.listIterator();

        DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        

        pw.append("Transaction ID");
        pw.append(',');
        pw.append("Date");
        pw.append(',');
        pw.append("Amount");
        pw.append(',');
        pw.append("Type");
        pw.append(',');
        pw.append("Category");
        pw.append("\n");

        while (i.hasNext()) {
            
            Date date = Date.from(Instant.ofEpochSecond(transaction.getDate()));
            date = new SimpleStringProperty(df.format(date));
            
            Transaction transaction = (Transaction) i.next();

            pw.append(String.valueOf(transaction.getID()));
            pw.append(',');
            pw.append(date);
            pw.append(',');
            pw.append(String.valueOf(transaction.getAmount()));
            pw.append(',');
            pw.append(String.valueOf(transaction.getType()));
            pw.append(',');
            pw.append(String.valueOf(transaction.getCategory()));
            pw.append('\n');

        }


        pw.flush();
        pw.close();
    }

}




