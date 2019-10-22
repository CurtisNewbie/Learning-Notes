package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.geometry.*;

public class BorderPaneDemo extends Application {

    @Override
    public void start(Stage ps) {

        // BorderPane
        var bp = new BorderPane();

        // five regions
        // borderp.setTop(node);
        // borderp.setBottom(node);
        // borderp.setLeft(node);
        // borderp.setRight(node);
        // borderp.setCenter(node);

        bp.setTop(new BlankPane("Top"));
        bp.setRight(new BlankPane("Right"));
        bp.setBottom(new BlankPane("Bottom"));
        bp.setLeft(new BlankPane("Left"));
        bp.setCenter(new BlankPane("Center"));

        // setTop(null) to remove the occupation of this region

        // scene and primary stage
        var sc = new Scene(bp, 200, 200);
        ps.setScene(sc);
        ps.setTitle("Border Pane");
        ps.show();
    }
}

/**
 * BlankPane is a StackPane with a Label in it.
 */
class BlankPane extends StackPane {

    /**
     * @param t the name of the Label (looks like a title)
     */
    public BlankPane(String t) {
        this.getChildren().add(new Label(t));
        this.setStyle("-fx-border-color: red");
        this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    }
}