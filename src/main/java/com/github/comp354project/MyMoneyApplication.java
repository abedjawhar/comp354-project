package com.github.comp354project;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.auth.SessionManager;
import com.github.comp354project.model.user.User;
import com.github.comp354project.viewController.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public Scene getScene(){
        return primaryStage.getScene();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        displayLogin();
        primaryStage.show();
    }

    private <T> T updateStage(String fxml, String title, int width, int height) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            logger.error(e);
        }
        primaryStage.setScene(new Scene(root, width, height));
        setStageTitle(title);
        return loader.getController();
    }

    private void setStageTitle(String title) {
        primaryStage.setTitle(title + " - My Money");
    }

    public void displayLogin() {
        LoginController controller = updateStage("/fxml/Login.fxml", "Login", 278, 124);
        primaryStage.getScene().setOnKeyPressed(controller);
    }

    public void displaySignUp()  {
        SignUpController controller = updateStage("/fxml/SignUp.fxml", "Sign Up", 278, 248);
        primaryStage.getScene().setOnKeyPressed(controller);
    }

    public void displayAccounts() {
        AccountListController controller =
                updateStage("/fxml/AccountList.fxml", "Account List", 800, 500);
        controller.setAccounts(new ArrayList<>(sessionManager.getUser().getAccounts()));
    }

    public void displayUpdateUser() throws IOException {
        UpdateUserAccountController controller =
                updateStage("/fxml/UpdateUserAccount.fxml", "Update Account", 800, 500);
    }

    public void displayAccountDetails(Account account) {
        AccountDetailsController controller =
                updateStage("/fxml/AccountDetails.fxml", "Account Details", 800, 500);
        controller.setAccount(account);
    }

    public void displayAllTransactions(List<Account> accounts) {
        AllTransactionsController controller =
                updateStage("/fxml/AllTransactions.fxml", "All Transactions", 1000, 500);
        controller.setAccounts(accounts);
    }
}
