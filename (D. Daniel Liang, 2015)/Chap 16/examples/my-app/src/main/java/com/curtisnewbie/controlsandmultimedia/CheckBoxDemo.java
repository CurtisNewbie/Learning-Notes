package com.curtisnewbie.controlsandmultimedia;

import javafx.scene.image.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.text.*;

public class CheckBoxDemo extends Application {

    @Override
    public void start(Stage priStage) {

        // for the check box
        var btnImg = new ImageView(new Image("http://pngimg.com/uploads/buttons/buttons_PNG68.png"));
        btnImg.setFitHeight(30);
        btnImg.setFitWidth(30);

        CheckBox cb = new CheckBox("Check");
        cb.setGraphic(btnImg);
        cb.setSelected(false);
        var btnspane = new StackPane();
        btnspane.getChildren().add(cb);

        // for the text
        var spane = new StackPane();
        var text = new Text("UnChecked");
        spane.getChildren().add(text);

        // the main pane
        var pane = new BorderPane();
        pane.setRight(btnspane);
        pane.setCenter(spane);

        // setup EventHandler
        cb.setOnAction(e -> {
            if (cb.isSelected())
                text.setText("Checked");
            else
                text.setText("UnChecked");
        });

        // setup primary stage
        var s = new Scene(pane);
        priStage.setScene(s);
        priStage.setTitle("CheckBox Demo");
        priStage.show();
    }
}