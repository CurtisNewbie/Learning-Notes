package com.curtisnewbie.app;

import java.util.List;

public class UnweightedGraph<E> extends AbstractGraph<E> {

    /** Empty UnweightedGraph */
    public UnweightedGraph() {
        super();
    }

    /**
     * Construct Unweighted Graph using a vertices array and edges array
     * 
     * @param vertices vertices represented using array
     * @param edges    edges represented using two-dimentional edges array
     */
    public UnweightedGraph(E[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    /**
     * Construct Unweighted Graph using a vertices List and edges List
     * 
     * @param vertices vertices represented using List
     * @param edges    edges represented using two-dimentional edges List
     */
    public UnweightedGraph(List<E> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

}