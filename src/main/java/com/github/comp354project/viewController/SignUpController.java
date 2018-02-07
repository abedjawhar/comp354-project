package com.github.comp354project.viewController;

import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.service.auth.IAuthenticationService;
import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.IUserService;
import com.github.comp354project.service.user.User;
import com.github.comp354project.viewController.helper.AlertHelper;
import com.github.comp354project.viewController.helper.StageManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

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
    public void signUp(ActionEvent event) throws IOException {
        try {
            validatePasswords();

            User user = User.builder()
                    .username(this.usernameTxt.getText())
                    .firstName(this.firstnameTxt.getText())
                    .lastName(this.lastnameTxt.getText())
                    .password(this.passwordField.getText())
                    .build();

            this.userService.createUser(user);

            StageManager.switchToLogin();
        } catch(ValidationException e) {
            System.out.println(e);
            AlertHelper.generateErrorAlert("Sign up error", "Error creating an account", e)
                    .showAndWait();
        } catch (RuntimeException e) {
            AlertHelper.generateErrorAlert("Sign up error", "Error creating an account", e.getMessage())
                    .showAndWait();
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        StageManager.switchToLogin();
    }

    /**
     * Checks if the passwords are the same
     * @throws ValidationException
     */
    private void validatePasswords() throws ValidationException {
        if (!this.passwordField.getText().equalsIgnoreCase(this.passwordRepeatField.getText())) {
            throw ValidationException.builder()
                    .error(ValidationError.builder()
                            .message("The passwords are not the same")
                            .parameterName("password").build())
                    .build();
        }
    }
}