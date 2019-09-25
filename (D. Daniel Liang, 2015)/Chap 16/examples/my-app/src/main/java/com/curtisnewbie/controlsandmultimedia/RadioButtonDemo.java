package com.curtisnewbie.controlsandmultimedia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RadioButtonDemo extends Application {

    @Override
    public void start(Stage priStage) {

        // Text in the middle
        var textPane = new StackPane();
        var text = new Text();
        textPane.getChildren().add(text);

        // RadioButton on the right
        var rdRed = new RadioButton("Red");
        var rdBlack = new RadioButton("Black");
        var rdBlue = new RadioButton("Blue");

        ToggleGroup group = new ToggleGroup();
        rdRed.setToggleGroup(group);
        rdBlack.setToggleGroup(group);
        rdBlue.setToggleGroup(group);

        var vBox = new VBox();
        vBox.getChildren().addAll(rdRed, rdBlack, rdBlue);

        // setup EventHandler
        rdRed.setOnAction(e -> {
            text.setText("Red");
            text.setStroke(Color.RED);
        });

        rdBlack.setOnAction(e -> {
            text.setText("Black");
            text.setStroke(Color.BLACK);
        });

        rdBlue.setOnAction(e -> {
            text.setText("Blue");
            text.setStroke(Color.BLUE);
        });

        var borderpane = new BorderPane();
        borderpane.setRight(vBox);
        borderpane.setCenter(textPane);
        var s = new Scene(borderpane);
        priStage.setScene(s);
        priStage.setTitle("RadioButton Demo");
        priStage.show();

    }
}