package com.github.comp354project;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.viewController.AccountDetailsController;
import com.github.comp354project.viewController.AccountListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.ArrayList;

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
        MyMoneyApplication.application.displayLogin();
        primaryStage.show();
    }

    private void setStageTitle(String title){
        primaryStage.setTitle(title + " - My Money");
    }

    public void displayLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 278, 124));
        setStageTitle("Login");
    }

    public void displaySignUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SignUp.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 278, 248));
        setStageTitle("Sign Up");
    }

    public void displayAccounts() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccountList.fxml"));
        Parent root = loader.load();
        AccountListController controller = loader.getController();
        controller.setAccounts(new ArrayList<>(sessionManager.getUser().getAccounts()));
        primaryStage.setScene(new Scene(root, 800, 500));
        setStageTitle("Accounts");
    }

    public void displayAccountDetails(Account account) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccountDetails.fxml"));
        Parent root = (Parent)loader.load();
        AccountDetailsController controller = loader.getController();
        controller.setAccount(account);
        primaryStage.setScene(new Scene(root, 800, 500));
        setStageTitle("Account Details");
    }

    public void displayAllAccountDetails(List<Account> accounts) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccountDetails.fxml"));
        Parent root = (Parent)loader.load();
        AccountDetailsController controller = loader.getController();
        controller.setAccounts(accounts);
        primaryStage.setScene(new Scene(root, 800, 500));
        setStageTitle("All Transactions");
    }
}
