package com.curtisnewbie.controlsandmultimedia;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SliderDemo extends Application {

    private Circle ball;
    private double ballX;
    private double ballY;
    private double radius;
    private double movement;
    private Slider speedSlider;
    private Pane paneForBall;
    private BorderPane borderPane;
    private Scene scene;
    private int sceneWidth;
    private int sceneHeight;

    @Override
    public void start(Stage priStage) {

        ballX = 15;
        ballY = 50;
        radius = 10;
        movement = 0.5;
        sceneWidth = 300;
        sceneHeight = 300;

        ball = new Circle(ballX, ballY, radius);
        paneForBall = new Pane();
        paneForBall.getChildren().add(ball);
        borderPane = new BorderPane();
        borderPane.setCenter(paneForBall);

        // bouncing ball animation
        Timeline bouncingAnimation = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            var currentPos = ball.getCenterX();

            if (currentPos < radius || currentPos > (paneForBall.getWidth() - radius))
                movement *= -1;

            ball.setCenterX(currentPos + movement);
        }));
        bouncingAnimation.setCycleCount(Timeline.INDEFINITE);
        bouncingAnimation.play();

        // bind slider's valueProperty to the animation's rateProperty to control the
        // speed of the animation
        speedSlider = new Slider();
        speedSlider.setMin(1.0);
        speedSlider.setMax(20.0);
        bouncingAnimation.rateProperty().bind(speedSlider.valueProperty());
        borderPane.setBottom(speedSlider);

        scene = new Scene(borderPane, sceneWidth, sceneHeight);
        priStage.setScene(scene);
        priStage.setTitle("Slider Demo");
        priStage.show();
    }
}