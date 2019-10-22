package com.curtisnewbie.controlsandmultimedia;

import java.net.URI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;

public class MediaDemo extends Application {

    @Override
    public void start(Stage priStage) {

        // From the book
        String videoLink = "file:///C:/Users/Curtis/Downloads/JustChatting.mp4";

        // 1. Create Media to get the source
        Media media = new Media(videoLink);

        // 2. MediaPlayer to play and control the media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // 3. MediaView to display the vedio
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(700);
        mediaView.setFitWidth(700);

        Button play = new Button(">");
        Button pause = new Button("||");
        Button rewind = new Button("<<");
        Button fastForward = new Button(">>");

        play.setOnAction(e -> mediaPlayer.play());
        pause.setOnAction(e -> mediaPlayer.pause());
        rewind.setOnAction(e -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.millis(5000)));
        });
        fastForward.setOnAction(e -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.millis(5000)));
        });

        Slider volume = new Slider();
        volume.setPrefWidth(150);
        volume.setMaxWidth(100);
        volume.setMinWidth(0);
        volume.setValue(20);
        volume.setMax(100);
        volume.setMin(0);
        mediaPlayer.volumeProperty().bind(volume.valueProperty());

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(rewind, play, pause, fastForward, new Label("Vol"), volume);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mediaView);
        borderPane.setBottom(hbox);

        Scene scene = new Scene(borderPane);
        priStage.setScene(scene);
        priStage.setTitle("Media Demo");
        priStage.show();
    }
}