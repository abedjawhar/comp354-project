package com.github.comp354project.viewController;

import com.github.comp354project.ApplicationComponent;
import com.github.comp354project.DaggerApplicationComponent;
import com.github.comp354project.service.account.IAccountService;
import com.github.comp354project.service.user.IUserService;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.inject.Inject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PrimaryViewController extends Application {
    private static final Logger logger = LogManager.getLogger(PrimaryViewController.class);

    // Declare classes you need here and annotate with inject.
    // Dagger will take care of instatiating the class!
    @Inject
    IAccountService accountService;

    @Inject
    IUserService userService;

    public PrimaryViewController() {
        // Inject the class into the component to inject dependencies
        ApplicationComponent component = DaggerApplicationComponent.builder().build();
        // The Application component class needs an inject method with the name of the class. No need to implement,
        // It's only an interface!
        component.inject(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageManager.setStage(primaryStage);
        StageManager.switchToLogin();
        primaryStage.show();
    }
}
