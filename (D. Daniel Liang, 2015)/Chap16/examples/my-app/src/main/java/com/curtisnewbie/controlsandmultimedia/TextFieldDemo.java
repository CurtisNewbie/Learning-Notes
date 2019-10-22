package com.curtisnewbie.controlsandmultimedia;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFieldDemo extends Application {

    @Override
    public void start(Stage priStage) {

        var borderPane = new BorderPane();

        // Top part in the BorderPane
        var vBox = new VBox();
        TextField enterText = new TextField();
        Label label = new Label("Enter a message");
        vBox.getChildren().addAll(label, enterText);
        borderPane.setTop(vBox);

        // Center part in the BorderPane
        TextField displayText = new TextField();
        borderPane.setCenter(displayText);
        displayText.setEditable(false);
        displayText.setPadding(new Insets(5, 5, 5, 5));

        // setup EventHandler
        enterText.setOnAction(e -> displayText.setText(enterText.getText()));

        var scene = new Scene(borderPane);
        priStage.setScene(scene);
        priStage.setTitle("TextField Demo");
        priStage.show();
    }
}