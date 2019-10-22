package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

// Every JavaFX program is defined in a class that extends javafx.application
//.Application
public class JavaFxDemo extends Application {

    // override the start method in Application (no need to creat main method)
    @Override
    public void start(Stage primaryStage) throws Exception {
        // create a button
        Button btn = new Button("Button");

        // Create a scene, and add the button into the scene
        Scene scene = new Scene(btn, 200, 200);

        // set title of the primary stage (the app)
        primaryStage.setTitle("JavaFX Demo");

        // set the scene under the stage
        primaryStage.setScene(scene);

        // show the primaryStage (the app)
        primaryStage.show();
    }
}