package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;

public class RectangleDemo extends Application {

    @Override
    public void start(Stage pstage) {

        // parameters x, y (top left cornor), width, height
        Rectangle rc1 = new Rectangle(25, 10, 60, 30);
        rc1.setStroke(Color.BLACK);
        rc1.setFill(Color.WHITE);
        var text1 = new Text(10, 45, "r1");

        var rc2 = new Rectangle(25, 50, 60, 30);
        var text2 = new Text(10, 85, "r2");

        var rc3 = new Rectangle(25, 100, 60, 30);
        rc3.setRotate(70);
        rc3.setFill(Color.RED);
        var text3 = new Text(10, 155, "r3");

        var p = new Pane();
        var l = p.getChildren();
        l.addAll(rc1, text1, rc2, text2, rc3, text3);

        pstage.setScene(new Scene(p, 250, 250));
        pstage.setTitle("Rectangle Demo");
        pstage.show();
    }
}