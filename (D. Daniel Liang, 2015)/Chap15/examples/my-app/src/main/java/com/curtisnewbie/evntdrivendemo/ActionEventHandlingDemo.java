package com.curtisnewbie.evntdrivendemo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ActionEventHandlingDemo extends Application {

    @Override
    public void start(Stage priStage) {

        // Event Source
        Button hapBtn = new Button("Happy");
        Button sadBtn = new Button("Sad");

        // Event Handler
        var hapBtnHlr = new HappyBtnHandler();
        var sadBtnHlr = new SadBtnHandler();

        // Register Handler
        hapBtn.setOnAction(hapBtnHlr);
        sadBtn.setOnAction(sadBtnHlr);

        var p = new HBox();
        p.setAlignment(Pos.CENTER);
        var l = p.getChildren();
        l.addAll(hapBtn, sadBtn);

        var s = new Scene(p);
        priStage.setTitle("Action Event Handling");
        priStage.setScene(s);
        priStage.show();

    }
}

/**
 * Handling the ActionEvent, it should implments EventHandler interface for
 * ActionEvent, and implemnets the Handle method for handling the ActionEvent
 * object.
 */
class HappyBtnHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        System.out.println("Happy!");
    }
}

/**
 * Handling the ActionEvent, it should implments EventHandler interface for
 * ActionEvent, and implemnets the Handle method.
 */
class SadBtnHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        System.out.println("Sad!");
    }
}