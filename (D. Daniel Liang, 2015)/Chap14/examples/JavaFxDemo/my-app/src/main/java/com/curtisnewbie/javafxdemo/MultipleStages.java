package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MultipleStages extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // primaryStage
        Scene scene = new Scene(new Button("btn"), 200, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Primary Stage");
        primaryStage.show();

        // second stage
        var stageTwo = new Stage();
        stageTwo.setTitle("Another Stage");
        stageTwo.setScene(new Scene(new Button("another btn"), 200, 250));
        stageTwo.show();

        // do not allow the second stage to be resizable.
        stageTwo.setResizable(false);
    }
}