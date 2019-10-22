package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.text.FontPosture;

public class FontDemo extends Application {

    @Override
    public void start(Stage pstage) {

        // StackPane
        var p = new StackPane();

        // Circle
        var c = new Circle();
        c.setRadius(50);
        c.setStroke(Color.BLACK);
        c.setFill(new Color(0.5, 0.5, 0.5, 0.1));

        // Label
        var l = new Label("Label");
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));

        p.getChildren().addAll(c, l);

        var scene = new Scene(p);
        pstage.setTitle("Font Demo");
        pstage.setScene(scene);
        pstage.show();

    }
}