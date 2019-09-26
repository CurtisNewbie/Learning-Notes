package com.curtisnewbie.controlsandmultimedia;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;

/**
 * Move Text with ScrollBar
 */
public class ScrollBarDemo extends Application {

    @Override
    public void start(Stage priStage) {

        // through calculation
        calculateExample();

        // through Property Binding
        propertyBindingExample();
    }

    private void calculateExample() {

        Text text;
        Pane paneForText;
        ScrollBar vbar;
        ScrollBar hbar;
        BorderPane borderPane;
        Scene scene;
        Stage stage;

        text = new Text(150, 150, "Move Me");
        paneForText = new Pane();
        paneForText.getChildren().add(text);

        vbar = new ScrollBar();
        hbar = new ScrollBar();
        vbar.setOrientation(Orientation.VERTICAL);
        hbar.setOrientation(Orientation.HORIZONTAL);

        // Listeners for the DoubleProperties
        hbar.valueProperty().addListener(ov -> {
            var currentValue = hbar.getValue();
            var maxValue = hbar.getMax();
            var paneWidth = paneForText.getWidth();
            text.setX((currentValue / maxValue) * paneWidth);
            System.out.printf("HBar::: CurrentValue:\"%.3f\"" + " MaxValue:\"%.3f\"" + " PaneWidth:\"%.3f\"\n",
                    currentValue, maxValue, paneWidth);
        });
        vbar.valueProperty().addListener(ov -> {
            var currentValue = vbar.getValue();
            var maxValue = vbar.getMax();
            var paneHeight = paneForText.getHeight();
            text.setY((currentValue / maxValue) * paneHeight);
            System.out.printf("VBar::: CurrentValue:\"%.3f\"" + " MaxValue:\"%.3f\"" + " PaneHeight:\"%.3f\"\n",
                    currentValue, maxValue, paneHeight);
        });

        borderPane = new BorderPane();
        borderPane.setRight(vbar);
        borderPane.setBottom(hbar);
        borderPane.setCenter(paneForText);
        scene = new Scene(borderPane, 300, 300);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ScrollBar Demo - Through Calculation");
        stage.show();
    }

    private void propertyBindingExample() {
        Text text;
        Pane paneForText;
        ScrollBar vbar;
        ScrollBar hbar;
        BorderPane borderPane;
        Scene scene;
        Stage stage;

        text = new Text(150, 150, "Move Me");
        paneForText = new Pane();
        paneForText.getChildren().add(text);

        vbar = new ScrollBar();
        hbar = new ScrollBar();
        vbar.setOrientation(Orientation.VERTICAL);
        hbar.setOrientation(Orientation.HORIZONTAL);

        // Properties Binding
        text.xProperty().bind(paneForText.widthProperty().multiply(hbar.valueProperty().divide(hbar.maxProperty())));
        text.yProperty().bind(paneForText.heightProperty().multiply(vbar.valueProperty().divide(vbar.maxProperty())));

        borderPane = new BorderPane();
        borderPane.setRight(vbar);
        borderPane.setBottom(hbar);
        borderPane.setCenter(paneForText);
        scene = new Scene(borderPane, 300, 300);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ScrollBar Demo - Through PropertyBinding");
        stage.show();
    }
}