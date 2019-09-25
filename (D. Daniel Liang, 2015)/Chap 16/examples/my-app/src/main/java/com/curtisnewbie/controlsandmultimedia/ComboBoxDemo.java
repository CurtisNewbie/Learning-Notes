package com.curtisnewbie.controlsandmultimedia;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ComboBoxDemo extends Application {

    @Override
    public void start(Stage priStage) {

        showSimpleExample();

        showComplexExample();
    }

    private void showSimpleExample() {
        var text = new Text();
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setStyle("-fx-color: red");

        // similar to Pane's .getChildren() method, it returns a ObservableList
        ObservableList<String> list = comboBox.getItems();
        // list.addAll("Item 1", "Item 2", "Item 3");
        ObservableList<String> items = FXCollections.observableArrayList(new String[] { "Item 1", "Item 2", "Item 3" });
        list.addAll(items);

        // select item - "Item 1" by default
        comboBox.setValue("Item 1");
        comboBox.setOnAction(e -> {
            var value = comboBox.getValue();
            text.setText(value);
            System.out.println("Selected ::: " + value);
            System.out.println("Index of Selection ::: " + items.indexOf(value));
        });

        var hBox = new HBox();
        hBox.getChildren().addAll(text, comboBox);

        Stage s = new Stage();
        s.setScene(new Scene(hBox));
        s.setTitle("Simple Example");
        s.show();
    }

    /**
     * A tricky example of using combo box to control the content of multiple
     * components.
     */
    private void showComplexExample() {

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

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(itemTitles);

        CustomPane cp = new CustomPane();
        var bp = new BorderPane();
        bp.setCenter(cp);
        bp.setRight(comboBox);

        comboBox.setOnAction(e -> {
            var selected = comboBox.getValue();
            cp.setTitle(itemsDescrip[itemTitles.indexOf(selected)]);
            cp.setImage(itemsImages[itemTitles.indexOf(selected)]);
        });

        var s = new Stage();
        var scene = new Scene(bp);
        s.setScene(scene);
        s.setTitle("Complex Example");
        s.show();

    }

}

class CustomPane extends BorderPane {

    private TextArea descrip;
    private Label itemImage;

    CustomPane() {
        descrip = new TextArea();
        descrip.setEditable(false);
        this.setBottom(new ScrollPane(descrip));

        itemImage = new Label();
        this.setCenter(itemImage);
    }

    /**
     * @param t Description
     */
    public void setTitle(String t) {
        descrip.setText(t);
    }

    /**
     * @param img ImageView
     */
    public void setImage(ImageView img) {
        itemImage.setGraphic(img);
    }
}
