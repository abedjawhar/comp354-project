package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.IUserService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LoginController implements Initializable {

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
    public void login(ActionEvent event) throws IOException  {
        try {
            this.sessionManager.login(this.usernameTxt.getText(), this.passwordField.getText());
            MyMoneyApplication.application.displayAccounts();
        }
        catch (ValidationException exception) {
            final String errorsStr = exception.getErrors()
                    .stream()
                    .map(e -> e.getMessage())
                    .collect(Collectors.joining("\n"));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setHeaderText("Error logging in");
            alert.setContentText(errorsStr);

            alert.showAndWait();
        }
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        StageManager.switchToSignUp();
    }
}