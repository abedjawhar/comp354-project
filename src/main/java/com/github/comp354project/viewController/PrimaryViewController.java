package com.github.comp354project.viewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryViewController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/primary-view.fxml"));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("My Budget");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
