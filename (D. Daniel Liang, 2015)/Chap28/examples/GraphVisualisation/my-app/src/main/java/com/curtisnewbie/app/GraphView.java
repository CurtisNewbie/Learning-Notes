package com.curtisnewbie.app;

import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphView extends Pane {

    /** Default radius of the circle (representing each vertex) */
    private static final int DEF_R = 10;

    private Graph<? extends Displayable> graph;

    public GraphView(Graph<? extends Displayable> graph) {
        this.graph = graph;
        List<Displayable> vertices = (List<Displayable>) graph.getVertices();
        drawVertices(vertices);
        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> adjacentVertices = graph.getNeighbors(i);
            if (adjacentVertices != null)
                drawEdges(i, adjacentVertices, vertices);
        }
    }

    private void drawVertices(List<Displayable> vertices) {
        for (Displayable d : vertices) {
            int x = d.getX();
            int y = d.getY();
            String name = d.getName();
            getChildren().add(new Circle(x, y, DEF_R));
            getChildren().add(new Text(x - 8, y - 18, name));
        }

    }

    /**
     * Draw the edges between vert1 and its adjacent vertices
     * 
     * @param vert1            index of vertex
     * @param adjacentVertices indicies of adjacent vertices of vert1
     * @param vertices         vertices
     */
    private void drawEdges(int vert1, List<Integer> adjacentVertices, List<Displayable> vertices) {
        int x1 = vertices.get(vert1).getX();
        int y1 = vertices.get(vert1).getY();

        for (int vert2 : adjacentVertices) {
            int x2 = vertices.get(vert2).getX();
            int y2 = vertices.get(vert2).getY();
            getChildren().add(new Line(x1, y1, x2, y2));
        }
    }
}
