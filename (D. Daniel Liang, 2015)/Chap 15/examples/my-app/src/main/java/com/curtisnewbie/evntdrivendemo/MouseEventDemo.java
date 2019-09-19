package com.curtisnewbie.evntdrivendemo;

import javafx.application.Application;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Four constants—PRIMARY, SECONDARY, MIDDLE, and NONE—are defined in
 * MouseButton to indicate the left, right, middle, and none mouse buttons.
 */
public class MouseEventDemo extends Application {

    @Override
    public void start(Stage priStage) {
        var pane = new Pane();
        var t = new Text(20, 20, "Mouse");
        t.setStroke(Color.BLACK);
        pane.getChildren().add(t);

        t.setOnMouseDragged((MouseEvent e) -> {
            System.out.println("Mouse Dragged [" + e.getX() + " , " + e.getY() + "]");
            t.setText("Ahhhhh");
            t.setStroke(Color.RED);

            // actually drag it
            double x = e.getX();
            double y = e.getY();
            t.setX(x > 0 && x + 10 < pane.getWidth() ? x : t.getX());
            t.setY(y > 0 && y < pane.getHeight() ? y : t.getY());
        });

        t.setOnMouseReleased(e -> {
            t.setText("Mouse");
            t.setStroke(Color.BLACK);
        });

        var s = new Scene(pane);
        priStage.setTitle("Mouse Event Demo");
        priStage.setScene(s);
        priStage.show();
    }
}