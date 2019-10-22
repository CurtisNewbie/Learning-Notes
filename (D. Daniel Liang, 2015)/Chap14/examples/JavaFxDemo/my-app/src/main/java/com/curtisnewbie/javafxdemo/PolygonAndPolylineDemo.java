package com.curtisnewbie.javafxdemo;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.collections.ObservableList;

public class PolygonAndPolylineDemo extends Application {

    @Override
    public void start(Stage pstage) {

        // a ploygon is consisted of a list of points
        Polygon polygon = new Polygon();
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);

        double width = 200;
        double height = 200;
        double centerX = width / 2;
        double centerY = height / 2;
        double radius = Math.min(width, height) * 0.4;

        // returns a ObservableList, where the points are added.
        ObservableList<Double> points = polygon.getPoints();
        for (int x = 0; x < 6; x++) {
            points.add(centerX + radius * Math.cos(2 * x * Math.PI / 6));
            points.add(centerY - radius * Math.sin(2 * x * Math.PI / 6));
        }

        var p = new Pane();
        p.getChildren().add(polygon);
        pstage.setTitle("Polygon Demo");
        pstage.setScene(new Scene(p, width, height));
        pstage.show();

        // show a image of how polygon and polyline work
        shownVisualDemonstration();
    }

    public void shownVisualDemonstration() {

        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("PolygonAndPolyline.PNG")) {
            var img = new Image(in);
            var imageView = new ImageView(img);
            var p = new Pane();
            p.getChildren().add(imageView);
            var stage = new Stage();
            stage.setTitle("Polygon and Polyline from (From Liang, 2015, p.570)");
            stage.setScene(new Scene(p, 800, 250));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}