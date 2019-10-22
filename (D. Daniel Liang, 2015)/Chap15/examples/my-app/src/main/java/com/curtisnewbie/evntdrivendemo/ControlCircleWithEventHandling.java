package com.curtisnewbie.evntdrivendemo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;

/**
 * Use two buttons to control the radius of the circle. The EventHandler used
 * below includes <br>
 * - Anonymous class <br>
 * - Inner class <br>
 */
public class ControlCircleWithEventHandling extends Application {

    // customised pane with a circle in it, it can be accessed by the inner classes
    private PaneWithCircle circlePane = new PaneWithCircle();

    @Override
    public void start(Stage priStage) {

        // HBox for the two buttons
        var hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        var btnEnlarge = new Button("Enlarge by 2");
        var btnShrink = new Button("Shrink by 2");
        var l = hbox.getChildren();
        l.addAll(btnEnlarge, btnShrink);

        // register two buttons with the event handlers
        btnEnlarge.setOnAction(new EnlargeEventHandler());

        // anonymous class
        btnShrink.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                circlePane.shrinkCircle();
            }
        });

        // BorderPane as the base
        var borderPane = new BorderPane();
        borderPane.setCenter(circlePane);
        borderPane.setBottom(hbox);
        borderPane.setAlignment(hbox, Pos.CENTER);

        var s = new Scene(borderPane, 200, 150);
        priStage.setTitle("Buttons To Control Circle");
        priStage.setScene(s);
        priStage.show();
    }

    /**
     * Inner class EventHandler for btnEnlarge
     */
    class EnlargeEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            circlePane.enlargeCircle();
        }

    }
}

class PaneWithCircle extends StackPane {

    private Circle c;

    public PaneWithCircle() {

        // ini Circle with radius
        c = new Circle(50);
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);
        this.getChildren().add(c);
    }

    /**
     * Incremenets the radius by 2.
     */
    public void enlargeCircle() {
        double r = c.getRadius();
        // a maximum of 100
        c.setRadius(r < 62 ? r + 2 : r);
    }

    /**
     * Decrements the radius by 2.
     */
    public void shrinkCircle() {
        double r = c.getRadius();
        // a minimum of 2
        c.setRadius(r > 2 ? r - 2 : r);
    }
}
