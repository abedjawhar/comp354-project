package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.auth.IAuthenticationService;
import com.github.comp354project.model.exceptions.ValidationError;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.IUserService;
import com.github.comp354project.model.user.User;
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

public class SignUpController implements Initializable, EventHandler<KeyEvent>{

    @FXML private JFXTextField usernameTxt;
    @FXML private JFXTextField firstnameTxt;
    @FXML private JFXTextField lastnameTxt;
    @FXML private JFXPasswordField passwordField;
    @FXML private JFXPasswordField passwordRepeatField;

    @Inject
    IUserService userService;

    public SignUpController() {
        MyMoneyApplication.application.getComponent().inject(this);
    }

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void signUp(ActionEvent event) {
        signUp();
    }

    @FXML
    public void goBackToLogin(ActionEvent event)  {
        MyMoneyApplication.application.displayLogin();
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            signUp();
        }
    }

    private void signUp(){
        try {
            validatePasswords();

            User user = User.builder()
                    .username(this.usernameTxt.getText())
                    .firstName(this.firstnameTxt.getText())
                    .lastName(this.lastnameTxt.getText())
                    .password(this.passwordField.getText())
                    .build();

            this.userService.createUser(user);

            MyMoneyApplication.application.displayLogin();
        } catch(ValidationException e) {
            AlertHelper.generateErrorAlert("Sign up error",  e)
                    .showAndWait();
        }
    }

    /**
     * Checks if the passwords are the same
     * @throws ValidationException
     */
    private void validatePasswords() throws ValidationException {
        if (!this.passwordField.getText().equalsIgnoreCase(this.passwordRepeatField.getText())) {
            throw ValidationException.builder()
                    .error(ValidationError.builder()
                            .message("The passwords do not match")
                            .parameterName("password").build())
                    .build();
        }
    }
}