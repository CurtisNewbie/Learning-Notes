package com.curtisnewbie.evntdrivendemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A KeyEvent is fired whenever a key is pressed, released, or typed on a node
 * or a scene.
 */
public class KeyEventDemo extends Application {

    @Override
    public void start(Stage priStage) {

        var t = new Text(20, 20, "");
        var p = new Pane();
        p.getChildren().add(t);
        var s = new Scene(p);

        // the scene receives the KeyEvent
        s.setOnKeyPressed((KeyEvent e) -> {
            System.out.println("Typed: " + e.getText());

            String text = t.getText();
            if (e.getCode() == KeyCode.BACK_SPACE) {
                int len = text.length();
                t.setText(len == 0 ? "" : text.substring(0, len - 1));
            } else if (e.getText().matches("[a-zA-Z0-9.,? ]*")) {
                t.setText(text + e.getText());
            }
        });

        // to make the text receiving keyEvent
        // t.requestFocus();

        priStage.setScene(s);
        priStage.setTitle("KeyEvent");
        priStage.show();
    }
}