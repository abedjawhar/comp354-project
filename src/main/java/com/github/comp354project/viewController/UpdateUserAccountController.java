package com.github.comp354project.viewController;
import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.model.auth.SessionManager;
import com.github.comp354project.model.auth.exceptions.AuthenticationException;
import com.github.comp354project.model.auth.exceptions.AuthorisationException;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.IUserService;
import com.github.comp354project.model.user.User;
import com.github.comp354project.viewController.helper.AlertHelper;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputDialog;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



public class UpdateUserAccountController implements Initializable {
    private static final Logger logger = LogManager.getLogger(UpdateUserAccountController.class);
    @FXML
    private Label usernameTxt;
    @FXML
    private JFXTextField addressTxt;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXTextField lastnameTxt;
    @FXML
    private JFXTextField phoneNumberTxt;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXPasswordField passwordRepeatField;
    @FXML
    private JFXTextField firstnameTxt;

    @Inject
    IUserService userService;

    @Inject
    SessionManager sessionManager;

    @Getter
    private User user;


    public UpdateUserAccountController() {
        MyMoneyApplication.application.getComponent().inject(this);
    }


    public void initialize(URL location, ResourceBundle resources) {
        this.user = this.sessionManager.getUser();
        this.setUserInfo();
    }

    @FXML
    public void goBackToAccountList(ActionEvent actionEvent) {
        MyMoneyApplication.application.displayAccounts();
    }

    public void setUserInfo() {
        usernameTxt.setText(getOrDefault(user.getUsername(), ""));
        firstnameTxt.setText(getOrDefault(user.getFirstName(), ""));
        lastnameTxt.setText(getOrDefault(user.getLastName(), ""));
        emailTxt.setText(getOrDefault(user.getEmail(), ""));
        phoneNumberTxt.setText(getOrDefault(user.getPhone(), ""));
        addressTxt.setText(getOrDefault(user.getAddress(), ""));
        passwordField.setText(getOrDefault(user.getPassword(), ""));
        passwordRepeatField.setText(getOrDefault(user.getPassword(), ""));
    }

    @FXML
    public void updateUser(ActionEvent event)  {

    try{
        if (passwordField.getText().equals(passwordRepeatField.getText())) {
            User userToUpdate = (User.builder()
                    .ID(user.getID())
                    .username(usernameTxt.getText().trim())
                    .firstName(firstnameTxt.getText().trim())
                    .lastName(lastnameTxt.getText().trim())
                    .email(emailTxt.getText().trim())
                    .phone(phoneNumberTxt.getText().trim())
                    .address(addressTxt.getText().trim())
                    .password(passwordField.getText())
                    .build());
            sessionManager.setUser(userService.updateUser(userToUpdate));
            MyMoneyApplication.application.displayAccounts();
        }
        else
            throw ValidationException.builder().message("Your passwords don't match!").build();
    } catch(ValidationException e){
        AlertHelper.generateErrorAlert("Error",  e).showAndWait();
    } catch (AuthenticationException e) {
        AlertHelper.generateErrorAlert("removeAcc", "Error authenticating user", e.getMessage());
    }
    catch (AuthorisationException e) {
        AlertHelper.generateErrorAlert("removeAccount error",
                "Error, user does not have the authority to delete the account", e.getMessage());
    }
}

    @FXML
    public void deleteUser(ActionEvent event){
        try {
            JPasswordField pf = new JPasswordField();
            int opt = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (opt == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if(password.equals(sessionManager.getUser().getPassword())){
                    userService.deleteUser(sessionManager.getUser());
                    this.sessionManager.logout();
                    MyMoneyApplication.application.displayLogin();
                } else {
                    throw AuthenticationException.builder()
                            .message("Wrong password entered")
                            .build();
                }
            }

        }
        catch (ValidationException e) {
            AlertHelper.generateErrorAlert("deleteUser error",  e).showAndWait();
        }
        catch (AuthenticationException e) {
            AlertHelper.generateErrorAlert("deleteUser error", "Error authenticating user", e.getMessage()).showAndWait();
        }
        catch (AuthorisationException e) {
            AlertHelper.generateErrorAlert("deleteUser error",
                    "Error, user does not have the authority to delete this user", e.getMessage());
        }
    }

    private <T> T getOrDefault(T val, T def){
        return (val == null ? def : val);
    }


}