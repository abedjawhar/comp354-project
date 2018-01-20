package com.github.comp354project;

import com.github.comp354project.service.budget.BudgetServiceModule;
import com.github.comp354project.service.budget.IBankAccountService;
import com.github.comp354project.viewController.PrimaryViewController;
import javafx.application.Application;

import javax.inject.Inject;

/**
 * The main entry point to the application
 */
public class Main {

    @Inject
    IBankAccountService bankAccountService;

    public static void main(String[] args) {
        Application.launch(PrimaryViewController.class, args);
    }
}
