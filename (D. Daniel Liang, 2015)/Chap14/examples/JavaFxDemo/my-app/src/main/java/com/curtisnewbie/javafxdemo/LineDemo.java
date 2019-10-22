package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LineDemo extends Application {

    @Override
    public void start(Stage pstage) {

        PaneWithLine p = new PaneWithLine();
        pstage.setScene(new Scene(p, 250, 250));
        pstage.setTitle("Line Demo");
        pstage.show();
    }

}

class PaneWithLine extends Pane {

    public PaneWithLine() {

        // create two lines
        // new Line(startX, startY, endX, endY);
        Line l1 = new Line(10, 10, 10, 10);
        Line l2 = new Line(10, 10, 10, 10);

        // bind the properties to the pane, l1.startX and startY remains (10,10) and
        // l2.endX and startY remains 10 and 10.
        l1.endXProperty().bind(this.widthProperty().subtract(10));
        l1.endYProperty().bind(this.heightProperty().subtract(10));
        l2.startXProperty().bind(this.widthProperty().subtract(10));
        l2.endYProperty().bind((this.heightProperty().subtract(10)));

        // set style properties
        l1.setStrokeWidth(5);
        l2.setStrokeWidth(5);
        l1.setStroke(Color.GREEN);
        l2.setStroke(Color.RED);

        this.getChildren().addAll(l1, l2);
    }

}