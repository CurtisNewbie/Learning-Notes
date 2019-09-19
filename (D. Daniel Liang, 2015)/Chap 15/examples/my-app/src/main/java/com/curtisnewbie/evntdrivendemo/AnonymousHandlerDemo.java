package com.curtisnewbie.evntdrivendemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class AnonymousHandlerDemo extends Application {

    @Override
    public void start(Stage priStage) {

        var btnOne = new Button("One");
        var btnTwo = new Button("Two");
        var btnThree = new Button("Three");
        var btnFour = new Button("Four");

        var hbox = new HBox();
        hbox.getChildren().addAll(btnOne, btnTwo, btnThree, btnFour);

        // register EventHanlder with anonymous classes
        btnOne.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("One");
            }
        });

        btnTwo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Two");

            }
        });

        btnThree.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Three");

            }
        });

        btnFour.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Four");

            }
        });

        var scene = new Scene(hbox, 200, 200);
        priStage.setScene(scene);
        priStage.setTitle("Anonymous Handler Demo");
        priStage.show();
    }
}