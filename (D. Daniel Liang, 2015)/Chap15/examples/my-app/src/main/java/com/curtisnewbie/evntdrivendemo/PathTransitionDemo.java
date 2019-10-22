package com.curtisnewbie.evntdrivendemo;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PathTransitionDemo extends Application {

    @Override
    public void start(Stage priStage) {

        // setup pane and stage
        var p = new Pane();
        var s = new Scene(p, 300, 300);
        priStage.setScene(s);
        priStage.setTitle("Path Transition");
        priStage.show();

        var c = new Circle(50);
        p.getChildren().add(c);
        c.setFill(Color.YELLOW);
        c.centerXProperty().bind(p.widthProperty().divide(2));
        c.centerYProperty().bind(p.heightProperty().divide(2));

        var r = new Rectangle(c.getCenterX() - c.getRadius(), c.getCenterY() - c.getRadius(), 25, 50);
        r.setFill(Color.BLUE);
        p.getChildren().add(r);

        /*
         * --------------------------------------------
         * 
         * PathTransition needs to specify a path (of shape), a node (which moves along
         * the path) and a time duration. Orientation can be specified for setting the
         * node's rotation.
         * 
         * Create the path as a shape (e.g., it can be a Circle or something else) by
         * 
         * 1) specifying the a shape object or 2) animating the path by adding elmenets
         * to it.
         * 
         * 
         * --------------------------------------------
         */

        // 1) Specify a shape object through setPath(Shape s)
        PathTransition pt = new PathTransition();
        pt.setPath(c);
        pt.setNode(r);
        pt.setDuration(Duration.millis(4 * 1000));

        // Orientation/ Rotation. By default, it's none
        pt.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);

        // Cycle time or repetitions
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play();

        // setup event handling
        s.setOnMouseReleased(e -> {
            pt.play();
            System.out.println("Status: " + (pt.getStatus() == Animation.Status.RUNNING ? "Running" : "Not Running"));
        });
        s.setOnMousePressed((MouseEvent e) -> pt.pause());

        // another example of manually animating the path
        animationPathTransition();

        // example of build PathTransition animation on an ImageView object
        PathTransitionWithImageView();

    }

    /**
     * Manually Animate the Shape or path
     */
    public void animationPathTransition() {

        Circle cTwo = new Circle(10, 10, 20);
        cTwo.setFill(Color.RED);

        // 2) animate the transition of coordinates. Creates an addition to the path
        // by moving to the specified coordinates.
        Path path = new Path();
        path.getElements().add(new MoveTo(0f, 100f));
        path.getElements().add(new CubicCurveTo(40f, 10f, 390f, 240f, 1904, 50f));

        PathTransition ptTwo = new PathTransition();
        ptTwo.setPath(path);
        ptTwo.setDuration(Duration.millis(4 * 1000));
        ptTwo.setNode(cTwo);
        ptTwo.setCycleCount(Timeline.INDEFINITE);
        ptTwo.setAutoReverse(true);
        ptTwo.play();

        var pTwo = new Pane();
        pTwo.getChildren().add(cTwo);
        var sTwo = new Scene(pTwo);
        var stage = new Stage();
        stage.setScene(sTwo);
        stage.setTitle("Animating The Path Manually");
        stage.show();
    }

    /**
     * Path Transition <br>
     * - Line <br>
     * - ImageView <br>
     */
    public void PathTransitionWithImageView() {

        var stage = new Stage();
        var pane = new Pane();
        var scene = new Scene(pane, 500, 500);
        stage.setScene(scene);

        ImageView imgView = new ImageView(
                "https://images.unsplash.com/photo-1477949331575-2763034b5fb5?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
        pane.getChildren().add(imgView);

        // set the attributes in constructors
        PathTransition imgPt = new PathTransition(Duration.millis(5000), new Line(100, 200, 100, 0), imgView);
        imgPt.setAutoReverse(true);
        imgPt.setCycleCount(5);
        imgPt.play();
        stage.show();
    }
}