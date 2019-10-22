package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;

public class NodeStyleDemo extends Application {

    @Override
    public void start(Stage pstage) throws Exception {

        // StackPane
        StackPane p = new StackPane();
        Button b = new Button("Button");

        // set style using JavaFX Css
        b.setStyle("-fx-border-color: blue;");

        // add button to the ObservableList
        p.getChildren().add(b);

        // set rotate style
        p.setRotate(45);

        // set scene
        pstage.setScene(new Scene(p, 255, 255));
        pstage.show();
    }
}