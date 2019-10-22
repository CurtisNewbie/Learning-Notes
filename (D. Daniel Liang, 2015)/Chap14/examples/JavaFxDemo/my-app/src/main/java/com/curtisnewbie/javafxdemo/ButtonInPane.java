package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

public class ButtonInPane extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // create a pane
        StackPane pane = new StackPane();

        // a ObservableList of Node
        var list = pane.getChildren();
        list.add(new Button("BTN"));

        // use the new Scene(Parent, width, height) constructor
        var scene = new Scene(pane, 200, 50);

        // setup prmaryStage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}