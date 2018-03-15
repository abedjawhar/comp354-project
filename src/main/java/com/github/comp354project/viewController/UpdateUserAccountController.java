package com.github.comp354project.viewController;
import com.github.comp354project.MyMoneyApplication;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.service.auth.exceptions.AuthenticationException;
import com.github.comp354project.service.auth.exceptions.AuthorisationException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.IUserService;
import com.github.comp354project.service.user.User;
import com.github.comp354project.viewController.helper.AlertHelper;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.control.Label;
import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;



public class UpdateUserAccountController implements Initializable {
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
    usernameTxt.setText(user.getUsername());
    firstnameTxt.setText(user.getFirstName());
    lastnameTxt.setText(user.getLastName());
    emailTxt.setText(user.getEmail());
    phoneNumberTxt.setText(user.getPhone());
    addressTxt.setText(user.getAddress());
    passwordField.setText((user.getPassword()));
    passwordRepeatField.setText(user.getPassword());
    }

    @FXML
    public void updateUser(ActionEvent event)  {
    System.out.println("SHIT");
    try{
        if (passwordField.getText().equals(passwordRepeatField.getText())) {
             sessionManager.setUser(userService.updateUser((User.builder()
                    .ID(user.getID())
                    .username(usernameTxt.getText().trim())
                    .firstName(firstnameTxt.getText().trim())
                    .lastName(lastnameTxt.getText().trim())
                    .email(emailTxt.getText().trim())
                    .phone(phoneNumberTxt.getText().trim())
                    .address(addressTxt.getText().trim())
                    .password(passwordField.getText())
                    .build())));
            MyMoneyApplication.application.displayAccounts();
        }
        else
            throw ValidationException.builder().message("Your passwords don't match!").build();
    } catch(ValidationException e){
        AlertHelper.generateErrorAlert("Error", "Something", e).showAndWait();
    } catch (AuthenticationException e) {
        AlertHelper.generateErrorAlert("removeAcc", "Error authenticating user", e.getMessage());
    }
    catch (AuthorisationException e) {
        AlertHelper.generateErrorAlert("removeAccount error",
                "Error, user does not have the authority to delete the account", e.getMessage());
    }


}

}