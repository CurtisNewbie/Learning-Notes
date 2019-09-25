package com.curtisnewbie.controlsandmultimedia;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ButtonDemo extends Application {

    @Override
    public void start(Stage priStage) {

        CustomBorderPane pane = new CustomBorderPane();
        var s = new Scene(pane, 200, 200);
        priStage.setScene(s);
        priStage.setTitle("Button Demo");
        priStage.show();

    }
}

/**
 * The Text by default, is located at (50 , 50).
 */
class CustomBorderPane extends BorderPane {

    CustomBorderPane() {
        // pane that contains the two buttons
        var paneForButtons = new HBox(20);
        var btnImgLeft = new ImageView(new Image("http://pngimg.com/uploads/buttons/buttons_PNG68.png"));
        var btnImgRight = new ImageView(new Image("http://pngimg.com/uploads/buttons/buttons_PNG68.png"));

        btnImgLeft.setFitHeight(30);
        btnImgLeft.setFitWidth(30);

        btnImgRight.setFitHeight(30);
        btnImgRight.setFitWidth(30);

        var btnLeft = new Button("Left", btnImgLeft);
        var btnRight = new Button("Right", btnImgRight);

        paneForButtons.getChildren().addAll(btnLeft, btnRight);
        paneForButtons.setAlignment(Pos.CENTER);
        paneForButtons.setStyle("-fx-border-color: green");

        // text that is moved by pressing the buttons
        var text = new Text(50, 50, "Move me! :D");
        var paneForText = new Pane();
        paneForText.getChildren().add(text);

        // add them to the BorderPane
        this.setBottom(paneForButtons);
        this.setCenter(paneForText);

        // assign EventHandler for the ActionEvent
        btnLeft.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                text.setX(text.getX() - 10);
            }
        });
        btnRight.setOnAction(e -> text.setX(text.getX() + 10));
    }
}