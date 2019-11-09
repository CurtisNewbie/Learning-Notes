package com.curtisnewbie.flashingtext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    // in miliseconds
    public static int frequency = 1000;

    @Override
    public void start(Stage priStage) {
        Pane textPane = new TextPane("CurtisNewbie :D");
        Scene s = new Scene(textPane, 200, 200);
        priStage.setScene(s);
        priStage.setTitle("Flashing Text");
        priStage.show();

        // make sure that program terminates when primary stage closes
        priStage.setOnCloseRequest(e -> System.exit(0));

        // additional thread for flashing text
        new Thread(new Runnable() {
            // flash 100 times
            int count = 100;

            @Override
            public void run() {
                try {
                    while (count > 0) {

                        Platform.runLater(new Runnable() { // Run from JavaFX GUI
                            @Override
                            public void run() {
                                ((TextPane) textPane).flash();
                            }
                        });
                        count--;
                        System.out.println(count);
                        Thread.sleep(frequency);
                    }
                } catch (InterruptedException e) {

                }
            }
        }).start();

    }
}

class TextPane extends StackPane {

    private Label text;
    private String content;

    TextPane(String t) {
        text = new Label(t);
        content = t;
        this.getChildren().add(text);
    }

    public void flash() {
        if (text.getText().length() != 0)
            text.setText("");
        else
            text.setText(content);
    }
}
