package com.curtisnewbie.controlsandmultimedia;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class ListViewDemo extends Application {

    @Override
    public void start(Stage priStage) {

        // setup the content
        ObservableList<String> itemTitles = FXCollections
                .observableArrayList(new String[] { "Apple", "Banana", "Orange" });
        ImageView[] itemsImages = { new ImageView(
                "https://upload.wikimedia.org/wikipedia/en/thumb/a/a5/Organic_Apple.jpg/634px-Organic_Apple.jpg"),
                new ImageView("https://upload.wikimedia.org/wikipedia/commons/6/69/Banana.png"),
                new ImageView("https://upload.wikimedia.org/wikipedia/commons/7/7b/Orange-Whole-%26-Split.jpg") };

        for (ImageView i : itemsImages) {
            i.setFitHeight(300);
            i.setFitWidth(300);
        }
        String[] itemsDescrip = { "This is apple!", "This is banana!", "This is Orange" };

        // setup the display
        TextArea descrip = new TextArea();
        descrip.setEditable(false);
        FlowPane imagePane = new FlowPane();

        BorderPane contentPane = new BorderPane();
        contentPane.setBottom(new ScrollPane(descrip));
        contentPane.setCenter(imagePane);

        // ListView
        ListView<String> listView = new ListView<>();
        // multiple selection (can be single selection)
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getItems().addAll(itemTitles);

        // assign listener to its property to handle the changes
        listView.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                // remove all images
                imagePane.getChildren().clear();

                StringBuilder sb = new StringBuilder();

                for (int i : listView.getSelectionModel().getSelectedIndices()) {
                    imagePane.getChildren().add(itemsImages[i]);
                    sb.append(itemsDescrip[i] + "\n");
                }
                descrip.setText(sb.toString());
            }
        });

        // Can be using lambda
        // listView.getSelectionModel().selectedItemProperty().addListener(ov -> {
        // });

        var bp = new BorderPane();
        bp.setCenter(contentPane);
        bp.setRight(listView);

        var scene = new Scene(bp);
        priStage.setScene(scene);
        priStage.setTitle("ListViewDemo");
        priStage.show();
    }

}