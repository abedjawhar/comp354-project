package com.github.comp354project;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.IAccountService;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.service.user.IUserService;
import com.github.comp354project.viewController.AccountDetailsController;
import com.github.comp354project.viewController.AccountListController;
import com.github.comp354project.viewController.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;

import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyMoneyApplication extends Application {
    private static final Logger logger = LogManager.getLogger(MyMoneyApplication.class);

    public static MyMoneyApplication application;

    @Inject
    SessionManager sessionManager;

    @Getter
    private ApplicationComponent component;
    private Stage primaryStage;

    public MyMoneyApplication() {
        component = DaggerApplicationComponent.builder().build();
        // The Application component class needs an inject method with the name of the class. No need to implement,
        // It's only an interface!
        component.inject(this);
        application = this;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        StageManager.setStage(primaryStage);
        StageManager.switchToLogin();
        primaryStage.show();
    }

    public void displayAccounts() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccountList.fxml"));
        Parent root = loader.load();
        AccountListController controller = loader.getController();
        controller.setAccounts(new ArrayList<>(sessionManager.getUser().getAccounts()));
        primaryStage.setScene(new Scene(root, 800, 500));
    }

    public void displayAccountDetails(Account account) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccountDetails.fxml"));
        Parent root = (Parent)loader.load();
        AccountDetailsController controller = loader.getController();
        controller.setAccount(account);
        primaryStage.setScene(new Scene(root, 800, 500));
    }
}
