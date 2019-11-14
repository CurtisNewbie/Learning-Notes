package com.curtisnewbie.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;

public class Client extends Application {

    // I/O streams
    private DataOutputStream to = null;
    private DataInputStream from = null;
    private Socket socket;

    private TextField radiusTF;
    private TextArea outputTA;

    @Override
    public void start(Stage priStage) {

        radiusTF = new TextField();
        outputTA = new TextArea();

        BorderPane inputPane = new BorderPane();
        inputPane.setPadding(new Insets(5, 5, 5, 5));
        inputPane.setLeft(new Label("Enter Radius: "));
        inputPane.setCenter(radiusTF);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(inputPane);
        mainPane.setCenter(new ScrollPane(outputTA));

        Scene s = new Scene(mainPane);
        priStage.setScene(s);
        priStage.setTitle("Client");
        priStage.show();

        // ActionEvent
        radiusTF.setOnAction((ActionEvent e) -> {

            try {

                if (to == null || from == null)
                    connectToServer();

                double radius = Double.parseDouble(radiusTF.getText().trim());
                to.writeDouble(radius);
                to.flush();
                double area = from.readDouble();
                outputTA.appendText("Result: " + area + "\n");

                // reset
                to = null;
                from = null;
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    private void connectToServer() {
        // try connnecting to the server
        try {
            socket = new Socket("localhost", 8000);
            from = new DataInputStream(socket.getInputStream());
            to = new DataOutputStream(socket.getOutputStream());
            outputTA.appendText("Connected to  " + socket.getInetAddress() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}