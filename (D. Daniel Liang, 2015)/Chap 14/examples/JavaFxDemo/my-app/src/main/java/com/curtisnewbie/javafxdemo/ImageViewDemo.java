package com.curtisnewbie.javafxdemo;

import java.io.InputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;

public class ImageViewDemo extends Application {

    @Override
    public void start(Stage ps) throws Exception {

        // create Image using Url
        Image urlImg = new Image(
                "https://images.unsplash.com/photo-1568021922594-1cd6b93c049a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80");

        // create Image through directory - path (for demonstration only)
        String filePath = "curtisnewbie.png"; // File e.g., not works in a jar, use resources folder instead
        Image localImg = new Image(filePath);

        // create Image through inputStream
        InputStream imageIn = getClass().getClassLoader().getResourceAsStream("curtisnewbie.png");
        Image inputSteamImg = new Image(imageIn);
        imageIn.close();

        // Horizontal box - Pane
        Pane p = new HBox(10);
        p.setPadding(new Insets(5, 5, 5, 5));
        var l = p.getChildren();

        for (int x = 0; x < 3; x++) {

            ImageView img;

            switch (x) {
            case 0:
                // does not set fit width and height
                img = new ImageView(urlImg);
                l.add(img);
                break;
            case 1:
                // set fit width and height
                img = new ImageView(localImg);
                img.setFitHeight(100);
                img.setFitHeight(100);
                l.add(img);
                break;
            case 2:
                // rotate
                img = new ImageView(inputSteamImg);
                img.setRotate(90);
                l.add(img);
                break;
            }
        }

        Scene scene = new Scene(p);
        ps.setTitle("Image View");
        ps.setScene(scene);
        ps.show();
    }
}
