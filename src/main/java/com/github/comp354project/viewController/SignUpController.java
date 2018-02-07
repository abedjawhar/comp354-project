package com.github.comp354project.viewController;

import com.github.comp354project.viewController.helper.StageManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML private JFXTextField usernameTxt;
    @FXML private JFXTextField firstnameTxt;
    @FXML private JFXTextField lastnameTxt;
    @FXML private JFXPasswordField passwordField;
    @FXML private JFXPasswordField passwordRepeatField;

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException
    {
        StageManager.switchToLogin();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException
    {
        StageManager.switchToLogin();
    }
}