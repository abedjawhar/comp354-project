package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.service.auth.exceptions.UserLoggedInException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.IUserService;
import com.github.comp354project.viewController.helper.AlertHelper;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, EventHandler<KeyEvent> {

    @FXML private JFXTextField usernameTxt;
    @FXML private JFXPasswordField passwordField;

    @Inject
    SessionManager sessionManager;

    @Inject
    IUserService userService;

    public LoginController() {
        MyMoneyApplication.application.getComponent().inject(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void login(ActionEvent event){
        this.login();
    }

    @FXML
    public void signUp(ActionEvent event)  {
        MyMoneyApplication.application.displaySignUp();
    }

    private void login(){
        try {
            this.sessionManager.login(this.usernameTxt.getText(), this.passwordField.getText());
            MyMoneyApplication.application.displayAccounts();
        }
        catch (ValidationException exception) {
            AlertHelper.generateErrorAlert("Login error", "Error logging in", exception)
                    .showAndWait();
        } catch (UserLoggedInException e) {
            AlertHelper.generateErrorAlert("Login error", "Error logging in", "Already logged in").showAndWait();
        }
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            login();
        }
    }
}