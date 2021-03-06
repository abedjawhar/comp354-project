package com.github.comp354project.model.csv;

import com.github.comp354project.utils.Timing;
import com.github.comp354project.viewController.TransactionTableController;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface ICSVGenerator {
    @Timing
    File generateCSV(Collection<TransactionTableController.TransactionDisplayModel> transactions) throws IOException;
}
