package com.github.comp354project;

import com.github.comp354project.service.account.IAccountService;
import com.github.comp354project.viewController.PrimaryViewController;
import javafx.application.Application;

import javax.inject.Inject;

/**
 * The main entry point to the application
 */
public class Main {

    public static void main(String[] args) {
        Application.launch(PrimaryViewController.class, args);
    }
}
