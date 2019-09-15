package com.curtisnewbie.javafxdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class TextDemo extends Application {

    @Override
    public void start(Stage pstage) {

        // new Text(horizontalPosit, verticalPosit, "text");
        Text text = new Text(20, 20, "Javafx String");
        text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));

        var text2 = new Text(60, 60, "Javafx String\nAnother String");

        var text3 = new Text(10, 100, "Javafx String\n Ano Another String");
        text3.setFill(Color.RED);
        text3.setUnderline(true);
        text3.setStrikethrough(true);

        // pane
        var p = new Pane();
        p.setPadding(new Insets(10, 10, 10, 10));
        p.getChildren().addAll(text, text2, text3);

        // scene
        var scene = new Scene(p);
        pstage.setTitle("Text Demo");
        pstage.setScene(scene);
        pstage.show();
    }
}