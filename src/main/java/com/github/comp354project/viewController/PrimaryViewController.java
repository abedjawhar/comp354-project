package com.github.comp354project.viewController;

import com.github.comp354project.ApplicationComponent;
import com.github.comp354project.DaggerApplicationComponent;
import com.github.comp354project.service.budget.IBankAccountService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;

public class PrimaryViewController extends Application {

    @Inject
    IBankAccountService bankAccountService;

    public PrimaryViewController() {
        // Inject the class into the component to inject dependencies
        ApplicationComponent component = DaggerApplicationComponent.builder().build();
        component.inject(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/primary-view.fxml"));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("My Budget - Total balance: " + this.bankAccountService.getBalance());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
