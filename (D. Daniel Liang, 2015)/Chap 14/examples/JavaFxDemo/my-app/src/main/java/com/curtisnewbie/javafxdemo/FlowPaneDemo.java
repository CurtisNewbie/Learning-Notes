package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;

public class FlowPaneDemo extends Application {

    @Override
    public void start(Stage ps) {

        // FlowPane
        FlowPane fp = new FlowPane();
        fp.setPadding(new Insets(11, 12, 13, 14));
        fp.setHgap(5);
        fp.setVgap(5);

        // add controls to the FlowPane
        var l = fp.getChildren();
        l.addAll(new Label("First Name:"), new TextField(), new Label("MI: "));

        TextField miTextField = new TextField();
        miTextField.setPrefColumnCount(1); // set columns as "width"
        l.addAll(miTextField, new Label("Last Name:"));

        // scene and stage
        var scene = new Scene(fp, 200, 250);
        ps.setTitle("Flow Pane");
        ps.setScene(scene);
        ps.show();

    }
}