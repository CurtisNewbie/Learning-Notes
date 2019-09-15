package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class CircleDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // create a Shape - a Circle
        Circle c = new Circle();
        c.setCenterX(100);
        c.setCenterY(100);
        c.setRadius(50);

        // can be null to refer to no colour
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);

        // create a pene as its parent
        var p = new Pane();
        var list = p.getChildren();
        list.add(c);

        // scene
        var s = new Scene(p, 200, 200);
        primaryStage.setTitle("Shape - Circle");
        primaryStage.setScene(s);
        primaryStage.show();
    }
}