package com.curtisnewbie.evntdrivendemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;

public class FadeTransitionDemo extends Application {

    @Override
    public void start(Stage priStage) {

        var pane = new Pane();
        var scene = new Scene(pane, 200, 150);

        // radiusX, radiusY
        Ellipse ellipse = new Ellipse(100, 50);
        ellipse.setFill(Color.RED);
        ellipse.setStroke(Color.BLACK);

        // properties binding
        ellipse.centerXProperty().bind(pane.widthProperty().divide(2));
        ellipse.centerYProperty().bind(pane.heightProperty().divide(2));
        ellipse.radiusXProperty().bind(pane.widthProperty().multiply(0.4));
        ellipse.radiusYProperty().bind(pane.heightProperty().multiply(0.4));

        pane.getChildren().add(ellipse);

        // fade transition
        FadeTransition ft = new FadeTransition();
        // FadeTransition ft = new FadeTransition(Duration.millis(3000), ellipse);
        ft.setNode(ellipse);
        ft.setDuration(Duration.millis(3000));

        // set opacity change
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();

        // control for the animation
        ellipse.setOnMousePressed(e -> ft.pause());
        ellipse.setOnMouseReleased(e -> ft.play());

        // primary stage
        priStage.setScene(scene);
        priStage.setTitle("Fade Transition Demo");
        priStage.show();
    }
}