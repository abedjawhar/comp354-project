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

    private <T> T updateStage(String fxml, String title, int width, int height) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, width, height));
        setStageTitle(title);
        return loader.getController();
    }

    private void setStageTitle(String title) throws IOException {
        primaryStage.setTitle(title + " - My Money");
    }

    public void displayLogin() throws IOException {
        updateStage("/fxml/Login.fxml", "Login", 278, 124);
    }

    public void displaySignUp() throws IOException {
        updateStage("/fxml/SignUp.fxml", "Sign Up", 278, 248);
    }

    public void displayAccounts() throws IOException {
        AccountListController controller =
                updateStage("/fxml/AccountList.fxml", "Account List", 800, 500);
        controller.setAccounts(new ArrayList<>(sessionManager.getUser().getAccounts()));
    }

    public void displayAccountDetails(Account account) throws IOException{
        AccountDetailsController controller =
                updateStage("/fxml/AccountDetails.fxml", "Account Details", 800, 500);
        controller.setAccount(account);
    }

    public void displayAllAccountDetails(List<Account> accounts) throws IOException{
        AccountDetailsController controller =
                updateStage("/fxml/AccountDetails.fxml", "All Transactions", 800, 500);
        controller.setAccounts(accounts);
    }
}
