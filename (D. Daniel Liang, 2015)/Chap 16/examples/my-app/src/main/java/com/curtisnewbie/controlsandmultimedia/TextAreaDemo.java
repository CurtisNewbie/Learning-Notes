package com.curtisnewbie.controlsandmultimedia;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TextAreaDemo extends Application {

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
        TextArea displayText = new TextArea();
        displayText.setEditable(false);
        displayText.setPrefColumnCount(20);
        displayText.setPrefRowCount(15);
        displayText.setWrapText(true);
        displayText.setFont(new Font("Verdana", 13));
        displayText.setPadding(new Insets(5, 5, 5, 5));
        borderPane.setCenter(new ScrollPane(displayText));

        // setup EventHandler
        enterText.setOnAction(e -> displayText.setText(enterText.getText()));

        var scene = new Scene(borderPane);
        priStage.setScene(scene);
        priStage.setTitle("TextField Demo");
        priStage.show();
    }
}