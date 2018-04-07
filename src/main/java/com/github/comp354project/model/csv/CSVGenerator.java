package com.github.comp354project.model.csv;

import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.viewController.TransactionTableController;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

public class CSVGenerator implements ICSVGenerator {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS");

    @Inject
    public CSVGenerator(){}

    @Override
    public File generateCSV(Collection<TransactionTableController.TransactionDisplayModel> transactions) throws IOException {
        String date = format.format(new Date());
        String dirName = "temp/";
        File dir = new File(dirName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String filename = String.format("%s%s.csv",dirName, date);
        File file = new File(filename);
        PrintWriter pw = new PrintWriter(file);
        pw.append(date).append("\n");
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
            pw.append(String.valueOf(transaction.getAccountID()));
            pw.append(',');
            pw.append(transaction.getDate());
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
        return file;
    }

}
