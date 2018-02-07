package com.github.comp354project.viewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {
    private static Stage stage;

    private static final int DEFAULT_WINDOW_WIDTH = 800;
    private static final int DEFAULT_WINDOW_HEIGHT = 500;

    private static void updateScene(String view, int width, int height) throws IOException {
        Parent root = FXMLLoader.load(StageManager.class.getClassLoader().getResource("fxml/" + view + ".fxml"));
        stage.setScene(new Scene(root, width, height));
    }

    private static void updateStage(String title, String view, int width, int height) throws IOException {
        updateScene(view, width, height);
        setStageTitle(title);
    }

    public static void setStageTitle(String title){
        stage.setTitle(title + " - My Money");
    }

    public static void setStage(Stage stage){
        StageManager.stage = stage;
    }

    public static void switchToAccount(String accountName) throws IOException {
        updateStage(accountName, "Account", DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    }

    public static void switchToAccountList() throws IOException {
        updateStage("Accounts", "AccountList", DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    }

    public static void switchToLogin() throws IOException {
        updateStage("Login","Login",  278,124);
    }

    public static void switchToSignUp() throws IOException {
        updateStage("Sign Up", "SignUp", 278,248);
    }
}
