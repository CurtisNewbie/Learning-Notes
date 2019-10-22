package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GridPaneDemo extends Application {

    @Override
    public void start(Stage ps) {
        // GridPane
        var gridp = new GridPane();
        gridp.setAlignment(Pos.CENTER); // centred
        gridp.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridp.setHgap(5.5);
        gridp.setVgap(5.5);

        // add nodes add(node, columnIndex, rowIndex)
        gridp.add(new Label("FirstName: "), 0, 0);
        gridp.add(new TextField(), 1, 0);
        gridp.add(new Label("MI: "), 0, 1);
        gridp.add(new TextField(), 1, 1);
        gridp.add(new Label("Last Namel "), 0, 2);
        gridp.add(new TextField(), 1, 2);
        var btn = new Button("Add Name");
        gridp.add(btn, 1, 3);
        GridPane.setHalignment(btn, HPos.RIGHT); // Set and override the alignment of a node within a GridPane

        // to remove one node or all nodes
        // gridp.getChildren().remove([node]);
        // gridp.getChildren().removeAll();

        var scene = new Scene(gridp, 200, 200);
        ps.setScene(scene);
        ps.setTitle("Grid Pane");
        ps.show();
    }
}