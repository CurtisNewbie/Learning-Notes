package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Scene;

public class HBoxAndVBoxDemo extends Application {

    @Override
    public void start(Stage pstage) {

        // HBox - horizontal box in a single row
        var hbox = new HBox();
        var l = hbox.getChildren();

        l.add(new Label("BBB"));
        l.add(new Label("BBB"));
        l.add(new Label("BBB"));
        l.add(new Label("BBB"));
        pstage.setScene(new Scene(hbox, 200, 200));
        pstage.setTitle("Horizontal Box");

        // VBox - vertical box in a single column
        var vbox = new VBox();
        var vl = vbox.getChildren();
        vl.add(new Label("DDDDDDD"));
        vl.add(new Label("DDDDDDD"));
        vl.add(new Label("DDDDDDD"));
        vl.add(new Label("DDDDDDD"));
        vl.add(new Label("DDDDDDD"));
        vl.add(new Label("DDDDDDD"));
        var stage = new Stage();
        stage.setScene(new Scene(vbox, 300, 300));
        stage.setTitle("Vertical Box");

        pstage.show();
        stage.show();
    }

}