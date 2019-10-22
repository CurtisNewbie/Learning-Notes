package com.curtisnewbie.bstvisualisation;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.event.*;

/**
 * Controller in MVC.
 * 
 * 
 */
public class BSTViewController extends Application {

    @Override
    public void start(Stage pristg) {
        // model
        BST<Integer> bst = new BST<>();

        // view
        BSTView bstView = new BSTView(bst);

        // event handler for insert btn and the textField
        bstView.setInsertHandler((ActionEvent e) -> {
            String key = bstView.getKey();
            // if input is legal
            if (key.matches("[0-9]{1,}")) {
                bstView.addKey(Integer.parseInt(key));
            }
        });
        // event handler for delete btn
        bstView.setDeleteBtnHandler((ActionEvent e) -> {
            String key = bstView.getKey();
            // if input is legal
            if (key.matches("[0-9]{1,}")) {
                bstView.removeKey(Integer.parseInt(key));
            }
        });
        // event handler for refresh btn
        bstView.setRefreshBtnHandler(e -> {
            bstView.refresh();
        });

        Scene s = new Scene(bstView, 500, 500);
        pristg.setScene(s);
        pristg.setTitle("Tree Visualisation");
        pristg.show();
    }

}