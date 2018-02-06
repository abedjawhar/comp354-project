package com.github.comp354project.viewController;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private JFXTextField usernameTxt;
    @FXML private JFXPasswordField passwordField;

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void login(ActionEvent event) throws IOException
    {
        StageManager.switchToAccountList();
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException
    {
        StageManager.switchToSignUp();
    }
}