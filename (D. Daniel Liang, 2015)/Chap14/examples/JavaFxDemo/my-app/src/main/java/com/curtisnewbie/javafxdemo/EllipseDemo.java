package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import java.util.Random;

public class EllipseDemo extends Application {

    @Override
    public void start(Stage pstage) {

        var p = new Pane();
        var l = p.getChildren();

        // create 16 Ellipse
        for (int i = 0; i < 16; i++) {

            Random rand = new Random();

            // parameters centerX, centerY, and radius
            Ellipse e = new Ellipse(150, 100, 100, 50);
            int r, g, b;
            r = rand.nextInt(255);
            g = rand.nextInt(255);
            b = rand.nextInt(255);

            e.setStroke(Color.rgb(r, g, b));
            e.setFill(Color.WHITE);
            e.setRotate(i * 180 / 16);
            l.add(e);
        }

        var s = new Scene(p, 300, 200);
        pstage.setTitle("Ellipse");
        pstage.setScene(s);
        pstage.show();

    }

}