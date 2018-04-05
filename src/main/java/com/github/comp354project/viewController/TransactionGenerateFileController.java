package com.github.comp354project.viewController;

import java.io.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.github.comp354project.model.account.Transaction;

/**
 * This class is used to mainly to create the GenerateFile method, which will be used to generate a CSV file that will
 * display all the transactions of one account or the transactions of all the accounts
 */

public class TransactionGenerateFileController {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");

    /**
     * This method is used to generate and display a CSV file or excel file using the attributes from the Transaction
     * Class.
     * @param transactions first parameter that accepts an Array List of Transactions
     * @param FileName second parameter that accepts a String that will name the CSV file
     * @throws IOException
     */
    public void generateTransactionCsvFile(List<Transaction> transactions, String FileName) throws IOException {
        PrintWriter pw = new PrintWriter(new File(FileName));

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

        transactions.forEach(transaction -> {
            Date date = Date.from(Instant.ofEpochSecond(transaction.getDate()));
            pw.append(String.valueOf(transaction.getID()));
            pw.append(',');
            pw.append(DATE_FORMAT.format(date));
            pw.append(',');
            pw.append(String.valueOf(transaction.getAmount()));
            pw.append(',');
            pw.append(String.valueOf(transaction.getType()));
            pw.append(',');
            pw.append(String.valueOf(transaction.getCategory()));
            pw.append('\n');
        });


        pw.flush();
        pw.close();
    }

}




