package com.curtisnewbie.javafxdemo;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;

/**
 * An arc is conceived as part of an ellipse, defined by the parameters centerX,
 * centerY, radiusX, radiusY, startAngle, length, and an arc type (ArcType.OPEN,
 * ArcType .CHORD, or ArcType.ROUND)
 */
public class ArcDemo extends Application {

    @Override
    public void start(Stage pstage) {

        var p = new Pane();

        // centerX, centerY, radiusX, radiusY, startAngle, length
        Arc arc1 = new Arc(150, 100, 80, 80, 30, 35);

        // ArcType.ROUND
        arc1.setFill(Color.RED); // Set fill color
        arc1.setType(ArcType.ROUND); // Set arc type
        p.getChildren().add(new Text(210, 40, "arc1: round"));
        p.getChildren().add(arc1); // Add arc to pane

        // ArcType.OPEN
        Arc arc2 = new Arc(150, 100, 80, 80, 30 + 90, 35);
        arc2.setFill(Color.BLACK); // Set fill color
        arc2.setType(ArcType.OPEN); // Set arc type
        p.getChildren().add(new Text(20, 40, "arc2: open"));
        p.getChildren().add(arc2); // Add arc to pane

        // ArcType.CHORD
        Arc arc3 = new Arc(150, 100, 80, 80, 30 + 180, 35);
        arc3.setFill(Color.WHITE);
        arc3.setType(ArcType.CHORD);
        arc3.setStroke(Color.BLACK);
        p.getChildren().add(new Text(20, 170, "arc3: chord"));
        p.getChildren().add(arc3);

        // ArcType.CHORD
        Arc arc4 = new Arc(150, 100, 80, 80, 30 + 270, 35);
        arc4.setFill(Color.GREEN);
        arc4.setType(ArcType.CHORD);
        arc4.setStroke(Color.BLACK);
        p.getChildren().add(new Text(210, 170, "arc4: chord"));
        p.getChildren().add(arc4);

        pstage.setScene(new Scene(p, 300, 200));
        pstage.setTitle("Arc Demo");
        pstage.show();

        // visual demonstration of Arc
        arcVisualDemonstration();
    }

    public void arcVisualDemonstration() {

        // load Arc.png in resources folder
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("Arc.PNG")) {
            var img = new Image(in);
            var imgView = new ImageView(img);
            var stage = new Stage();
            stage.setScene(new Scene(new Pane(imgView), 600, 300));
            stage.setTitle("Visual Demonstration of Arc (From Liang, 2015, p.568)");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}