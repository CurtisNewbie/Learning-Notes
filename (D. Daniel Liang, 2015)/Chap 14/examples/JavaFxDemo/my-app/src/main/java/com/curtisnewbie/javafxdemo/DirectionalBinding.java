package com.curtisnewbie.javafxdemo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Unidirectional Binding and Bidirectional binding
 */
public class DirectionalBinding {

    public static void main(String[] args) {
        // Unidirectional Binding
        DoubleProperty d1 = new SimpleDoubleProperty();
        DoubleProperty d2 = new SimpleDoubleProperty();

        // Property.bind(ObservableValue)
        d1.bind(d2);

        // Bidirectional Binding
        DoubleProperty d3 = new SimpleDoubleProperty();
        DoubleProperty d4 = new SimpleDoubleProperty();
        d3.bindBidirectional(d4);
    }
}