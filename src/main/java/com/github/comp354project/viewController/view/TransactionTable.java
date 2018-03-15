package com.github.comp354project.viewController.view;

import com.github.comp354project.service.account.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Skinnable;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class TransactionTable extends Control {

    private Node view;

    private TransactionTableController controller;

    public TransactionTable() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/view/TransactionTable.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                return controller = new TransactionTableController();
            }
        });
        try {
            this.view = (Node) fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getChildren().add(this.view);
    }

    public Skin createDefaultSkin() {
        return new Skin() {
            @Override
            public Skinnable getSkinnable() {
                return null;
            }

            @Override
            public Node getNode() {
                return view;
            }

            @Override
            public void dispose() {

            }
        };
    }

    public void addTransactions(List<Transaction> transactions) {
        this.controller.setTransactions(transactions);
    }

    public void hideAccountIDColumn() {
        this.controller.hideAccountIDColumn();
    }
}
