package com.curtisnewbie.evntdrivendemo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;

/**
 * Lambda Expression is like a simple version of Anonymous class.
 */
public class LambdaExpressionDemo extends Application {

    @Override
    public void start(Stage priStage) {

        // Event Source
        Button hapBtn = new Button("Happy");
        Button sadBtn = new Button("Sad");
        Button noFelBtn = new Button("No Feeling");

        // It recognises the Event type automatically by the compiler
        hapBtn.setOnAction(e -> {
            System.out.println("Happy");
        });

        // Explicitly declare the type of the Event
        sadBtn.setOnAction((ActionEvent e) -> {
            System.out.println("Sad");
        });

        // One line lambda without { }
        noFelBtn.setOnAction((ActionEvent e) -> System.out.println("No Feeling"));

        var p = new HBox();
        p.setAlignment(Pos.CENTER);
        var l = p.getChildren();
        l.addAll(hapBtn, sadBtn, noFelBtn);

        var s = new Scene(p);
        priStage.setTitle("Lambda Expression Demp");
        priStage.setScene(s);
        priStage.show();
    }
}