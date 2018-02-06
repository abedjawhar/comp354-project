package com.github.comp354project.viewController;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Parent login = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/Login.fxml"));
        Scene scene = new Scene(login, 278, 124);;
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException
    {
        Parent login = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/Login.fxml"));
        Scene scene = new Scene(login, 278, 124);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
    }
}