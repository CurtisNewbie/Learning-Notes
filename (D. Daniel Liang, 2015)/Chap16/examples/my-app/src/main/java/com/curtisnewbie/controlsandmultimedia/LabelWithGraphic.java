package com.curtisnewbie.controlsandmultimedia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LabelWithGraphic extends Application {

    @Override
    public void start(Stage priStage) {

        // instantiate a Label with an ImageView
        ImageView gifImg = new ImageView(new Image(
                "https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg"));
        gifImg.setFitWidth(300);
        gifImg.setFitHeight(300);
        Label lb1 = new Label("A Copyright Free Label", gifImg);

        // set some properties of the Label
        lb1.setStyle("-fx-border-color: green; -fx-border-width: 2");
        lb1.setContentDisplay(ContentDisplay.BOTTOM);
        lb1.setTextFill(Color.BLACK);

        // instantiate a Label with a shape
        Label lb2 = new Label("Circle", new Circle(50));
        lb2.setContentDisplay(ContentDisplay.TOP);
        lb2.setTextFill(Color.RED);

        var lb3 = new Label("Rectangle", new Rectangle(50, 30));
        lb3.setContentDisplay(ContentDisplay.BOTTOM);

        var lb4 = new Label("Ellipse", new Ellipse(50, 30));
        lb3.setContentDisplay(ContentDisplay.BOTTOM);

        // I can also put a pane inside a Label
        var ellipse = new Ellipse(100, 40);
        ellipse.setFill(Color.WHITE);
        var lbInPane = new Label("A Label inside the Stackpane");
        var stackpane = new StackPane();
        stackpane.getChildren().addAll(ellipse, lbInPane);
        var lb5 = new Label("A Stackpane inside a Label", stackpane);
        lb5.setContentDisplay(ContentDisplay.BOTTOM);

        var hbox = new HBox(10);
        var l = hbox.getChildren();
        l.addAll(lb1, lb2, lb3, lb4, lb5);
        var scene = new Scene(hbox);
        priStage.setScene(scene);
        priStage.setTitle("LabelWIthGraphic");
        priStage.show();

    }
}