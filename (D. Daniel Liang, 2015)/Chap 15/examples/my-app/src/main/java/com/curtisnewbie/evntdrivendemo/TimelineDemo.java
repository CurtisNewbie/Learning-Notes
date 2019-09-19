package com.curtisnewbie.evntdrivendemo;

import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.event.*;

public class TimelineDemo extends Application {

    @Override
    public void start(Stage priStage) {

        var pane = new StackPane();
        var text = new Text(20, 50, "Programming is fun");
        text.setFill(Color.YELLOW);
        pane.getChildren().add(text);

        // Timeline and the eventHandler (onFinished)
        EventHandler<ActionEvent> onFinished = e -> {

            // changing the text repeatively after every defined duration (500 millis)
            if (text.getText().length() != 0)
                text.setText("");
            else
                text.setText("Reading books is fun");
        };

        // keyframe - 500 millisec, every time the keyframe is elapsed, onFinished is
        // called.
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(500), onFinished));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

        var scene = new Scene(pane, 250, 250);
        priStage.setScene(scene);
        priStage.setTitle("Timeline Demo");
        priStage.show();
    }

}