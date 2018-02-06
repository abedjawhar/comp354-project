package com.github.comp354project.viewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager {
    private static Stage stage;

    private static int defaultWindowWidth = 800;
    private static int defaultWindowHeight = 500;

    private static void updateScene(String view, int width, int height) throws IOException {
        Parent root = FXMLLoader.load(ViewManager.class.getClassLoader().getResource("fxml/" + view + ".fxml"));
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
        ViewManager.stage = stage;
    }

    public static void switchSceneToAccountView() throws IOException {
        updateStage("Account View", "Account",  defaultWindowWidth, defaultWindowHeight);
    }

    public static void switchSceneToAccountListView() throws IOException {
        updateStage("Account List", "AccountList", defaultWindowWidth, defaultWindowHeight);
    }

    public static void switchSceneToLoginView() throws IOException {
        updateStage("Login","Login",  278,124);
    }

    public static void switchSceneToSignUpView() throws IOException {
        updateStage("Sign Up", "SignUp", 278,248);
    }
}
