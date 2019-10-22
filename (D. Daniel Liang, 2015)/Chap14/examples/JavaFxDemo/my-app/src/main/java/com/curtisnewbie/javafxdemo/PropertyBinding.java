package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.beans.property.DoubleProperty;

public class PropertyBinding extends Application {

    @Override
    public void start(Stage primaryStage) {

        // create a Shape - a Circle
        Circle c = new Circle();
        c.setRadius(50);
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);

        // pane
        var p = new Pane();
        var l = p.getChildren();
        l.add(c);

        /*
         * 
         * Property binding, bind the circle's position to the pane.
         * 
         */

        // get the centerX DoubleProperty object, a subtype of Property
        DoubleProperty propertyX = c.centerXProperty();
        DoubleProperty propertyY = c.centerYProperty();

        // bind the centerX and CenterY to the pane - a Observable (and also a Property)
        // object that mimics
        propertyX.bind(p.widthProperty().divide(2));
        propertyY.bind(p.heightProperty().divide(2));

        // scene and pane
        var s = new Scene(p, 250, 250);
        primaryStage.setTitle("Property Binding");
        primaryStage.setScene(s);
        primaryStage.show();

        // Property object as properties, and there are getter and setter method that
        // return primitive values as well as the Property objects.
        double x = c.getCenterX();
        double y = c.getCenterY();
        DoubleProperty px = c.centerXProperty();
        DoubleProperty py = c.centerYProperty();

    }
}