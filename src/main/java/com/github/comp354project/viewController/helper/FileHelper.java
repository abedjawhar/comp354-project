package com.github.comp354project.viewController.helper;

import com.github.comp354project.MyMoneyApplication;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileHelper {
    private static final Logger logger = LogManager.getLogger(FileHelper.class);

    public static void saveFile(File file) throws IOException{
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File userFile = fileChooser.showSaveDialog(MyMoneyApplication.application.getPrimaryStage());

        if(userFile != null){
            copyFile(file, userFile);
        }
    }

    private static void copyFile(File sourceFile, File destFile) throws IOException {
        FileChannel src = new FileInputStream(sourceFile).getChannel();
        FileChannel dest = new FileOutputStream(destFile).getChannel();
        dest.transferFrom(src, 0, src.size());
        src.close();
        dest.close();
    }

}
