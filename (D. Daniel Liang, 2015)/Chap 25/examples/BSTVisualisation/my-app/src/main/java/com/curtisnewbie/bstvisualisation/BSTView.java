package com.curtisnewbie.bstvisualisation;

import javafx.scene.shape.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * View in MVC.
 */
public class BSTView extends BorderPane {

    /** The BST Tree */
    private BST<Integer> tree;

    /** Where the message is displayed on the top of the screen */
    private Text message;

    /** The pane that contains the controls (e.g., buttons) */
    private ControlsPane controlsPane;

    /** The pane where the tree is visually shown */
    private TreeDisplayPane treeDisplayPane;

    public BSTView(BST<Integer> tree) {
        this.tree = tree;
        this.controlsPane = new ControlsPane();
        this.treeDisplayPane = new TreeDisplayPane(50, 70, 15);
        this.setBottom(controlsPane);
        this.setCenter(treeDisplayPane);
        this.message = new Text(20, 20, "");
        this.setMessage(tree.toString());
        this.setTop(message);
    }

    /**
     * Refresh the tree display
     */
    public void refresh() {
        treeDisplayPane.displayTree();
    }

    /**
     * Handler for Insert Button and TextField
     * 
     * @param e
     */
    public void setInsertHandler(EventHandler<ActionEvent> e) {
        controlsPane.getInsertBtn().setOnAction(e);
        controlsPane.getKeyTextField().setOnAction(e);
    }

    public void setRefreshBtnHandler(EventHandler<ActionEvent> e) {
        controlsPane.getRefreshBtn().setOnAction(e);
    }

    public void setDeleteBtnHandler(EventHandler<ActionEvent> e) {
        controlsPane.getDeleteBtn().setOnAction(e);
    }

    public String getKey() {
        return controlsPane.getKeyTextField().getText();
    }

    /**
     * Add the key to the tree, refresh message and the tree display.
     * 
     * @param key key
     */
    public void addKey(int key) {
        tree.add(key);
        refresh();
        setMessage(tree.toString());
    }

    /**
     * Remove the key to the tree, refresh message and the tree display.
     * 
     * @param key key
     */
    public void removeKey(int key) {
        tree.delete(key);
        setMessage(tree.toString());
        refresh();
    }

    /**
     * set the message that is displayed on the top of the screen
     * 
     * @param msg
     */
    private void setMessage(String msg) {
        this.message.setText(msg);
    }

    /**
     * HBox that Contains buttons, TextFields etc
     */
    private class ControlsPane extends HBox {
        private TextField keyTextField;
        private Label keyLabel;
        private Button insertBtn;
        private Button deleteBtn;
        private Button refreshBtn;

        public ControlsPane() {
            keyTextField = new TextField();
            keyLabel = new Label("Enter Key");
            insertBtn = new Button("Insert");
            deleteBtn = new Button("Delete");
            refreshBtn = new Button("Refresh");
            this.getChildren().addAll(keyLabel, keyTextField, insertBtn, deleteBtn, refreshBtn);
            this.setAlignment(Pos.CENTER);
        }

        public Button getInsertBtn() {
            return insertBtn;
        }

        public Button getDeleteBtn() {
            return deleteBtn;
        }

        public Button getRefreshBtn() {
            return refreshBtn;
        }

        public TextField getKeyTextField() {
            return keyTextField;
        }

    }

    /**
     * Where the tree is visually displayed. It displays the root, then display the
     * two subtrees recursively. displayTree() method should be called in order to
     * show the tree.
     */
    private class TreeDisplayPane extends Pane {
        private double hGap;
        private double vGap;
        private double r;

        public TreeDisplayPane(double vGap, double hGap, double r) {
            this.hGap = hGap;
            this.vGap = vGap;
            this.r = r;
        }

        /**
         * Completely redraw the tree
         */
        public void displayTree() {
            getChildren().clear();
            if (!tree.isEmpty())
                displayTree(tree.getRoot(), getWidth() / 2, vGap, hGap);
        }

        /**
         * Display a subtree rooted at (x,y).It draws the line to the left child and
         * right child first if possible, then it draws the current node. It should be
         * noticed horizontal gap between nodes decrements by 2 in every level.
         * 
         * @param node root node of the subtree
         * @param x    x coordinate
         * @param y    y coordinate
         * @param hGap horizontal gap between each node
         * @param vGap vertical gap between each level
         * @param r    radius
         */
        public void displayTree(TreeNode<Integer> node, double x, double y, double hGap) {
            // draw one level of the left subtree
            if (node.getLeft() != null) {

                double leftX = x - hGap;
                double leftY = y + vGap;

                // draw a line to the left child
                getChildren().add(new Line(x, y, leftX, leftY));

                // draw the left subtree recursively
                displayTree(node.getLeft(), leftX, leftY, hGap - 2);
            }

            // draw one level of the right subtree
            if (node.getRight() != null) {
                double rightX = x + hGap;
                double rightY = y + vGap;

                // draw a line to the right child
                getChildren().add(new Line(x, y, rightX, rightY));

                // draw the right subtree recursively
                displayTree(node.getRight(), rightX, rightY, hGap - 2);
            }

            // draw the node
            Circle c = new Circle(x, y, r);
            c.setFill(Color.WHITE);
            c.setStroke(Color.BLACK);
            getChildren().addAll(c, new Text(x - (r / 3), y + 4, node.getElement() + ""));
        }

    }

}
