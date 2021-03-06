package com.curtisnewbie.app;

import java.util.*;

public abstract class AbstractGraph<E> implements Graph<E> {

    private List<E> vertices;
    /** Adjacency List for Edges */
    private List<List<Edge>> neighbors;

    /** Empty Graph */
    AbstractGraph() {
        this.vertices = new ArrayList<>();
        this.neighbors = new ArrayList<>();
    }

    /**
     * Construct Graph using a vertices array and edges array
     * 
     * @param vertices vertices represented using array
     * @param edges    edges represented using two-dimentional edges array
     */
    AbstractGraph(E[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.neighbors = new ArrayList<>();
        for (E e : vertices)
            addVertex(e);
        addEdges(edges);
    }

    /**
     * Construct Graph using a vertices List and edges List
     * 
     * @param vertices vertices represented using List
     * @param edges    edges represented using two-dimentional edges List
     */
    AbstractGraph(List<E> vesrtices, List<Edge> edges) {
        this.vertices = new ArrayList<>();
        this.neighbors = new ArrayList<>();
        for (E e : vertices)
            addVertex(e);
        addEdges(edges);
    }

    /**
     * Add a number of edges, creating the adjacency list for edges
     * 
     * @param edges edges represented with two-dimentional array
     * 
     */
    private void addEdges(int[][] edges) {
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
    }

    /**
     * Add a number of edges, creating the adjacency list for edges
     * 
     * @param edges edges represented with two-dimentional array
     * 
     */
    private void addEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            addEdge(edge.getVert1(), edge.getVert2());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @exception IllegalArgumentException if the indices of the vertices of the
     *                                     edge are illegal
     */
    @Override
    public boolean addEdge(int vert1, int vert2) {
        return addEdge(new Edge(vert1, vert2));
    }

    /**
     * {@inheritDoc}
     *
     * @exception IllegalArgumentException if the indices of the vertices of the
     *                                     edge are illegal
     */
    @Override
    public boolean addEdge(Edge edge) {
        // validate
        if (edge.getVert1() < 0 && edge.getVert1() > getSize() - 1) {
            throw new IllegalArgumentException("Illegal Index at: " + edge.getVert1());
        }

        if (edge.getVert2() < 0 && edge.getVert2() > getSize() - 1) {
            throw new IllegalArgumentException("Illegal Index at: " + edge.getVert2());
        }

        // adjacent edges share the same vertex (vert1), which connects vert1 and
        // vert2,
        // for vertex vert1:
        if (edge.getVert1() > neighbors.size()) {

        }
        var edgesForVertex1 = neighbors.get(edge.getVert1());
        if (edgesForVertex1.contains(edge))
            return false;
        else {
            // add it to the adjacency List
            edgesForVertex1.add(edge);
            return true;
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<E> getVertices() {
        return (List<E>) new ArrayList<E>(vertices);
    }

    @Override
    public boolean addVertex(E e) {
        if (!vertices.contains(e)) {
            vertices.add(e);
            neighbors.add(new ArrayList<Edge>());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E getVertex(int i) {
        return vertices.get(i);
    }

    @Override
    public int getIndex(E e) {
        return vertices.indexOf(e);
    }

    @Override
    public List<Integer> getNeighbors(int i) {
        List<Integer> n = new ArrayList<>();
        // get the corresponding adjacent vertex of the vertex (of index i)
        for (Edge e : neighbors.get(i)) {
            n.add(e.getVert2());
        }
        return n;
    }

    @Override
    public int getDegree(int i) {
        return neighbors.get(i).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Adjacency List for Edges\n");
        for (int i = 0; i < neighbors.size(); i++) {
            sb.append("    For ([" + i + "]" + vertices.get(i) + ") [");
            for (Edge e : neighbors.get(i)) {
                // print the adjacent edges for edge[i]
                sb.append("(" + e.getVert1() + ", " + e.getVert2() + ")");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    /**
     * Return a tree constructed using Depth-First Search (or Traversal), where the
     * tree starts from the specified vertex of index i
     * 
     * @param i index of the starting vertex
     * @return a tree like structure constructed using DFS
     */
    @Override
    public AbstractGraph<E>.SearchTree depthFirstSearchTree(int i) {
        // the vertices that are visisted, which maintains an order of traversal. This
        // construct the depth first search tree
        List<Integer> searchOrder = new ArrayList<>();

        // boolean[] to indicate whether each corresponding vertex is visisted.
        boolean[] visited = new boolean[vertices.size()];

        // the corresponding parents of each vertex, initialise it with an illegal value
        // -1
        int[] parents = new int[vertices.size()];
        Arrays.fill(parents, -1); // it doesn't seem to be necessary tho

        // start recursive traversal
        internalDFS(i, parents, searchOrder, visited);

        return new SearchTree(i, parents, searchOrder);
    }

    /**
     * Recursively constructing a Depth-First Search Tree through DFS traversal
     * 
     * @param i           starting vertex of the DFS
     * @param parents     indicies of parents of all corresponding vertices
     * @param searchOrder order of traversal of vertices (the indices that are
     *                    recorded)
     * @param visited     indicating whether the vertex is visited
     */
    private void internalDFS(int i, int[] parents, List<Integer> searchOrder, boolean[] visited) {
        visited[i] = true;
        searchOrder.add(i);

        // check if the neighbors of i are visisted, if not make them visited and add
        // them to the search order (or order of traversal).
        List<Edge> adjacentEdges = neighbors.get(i);
        for (Edge e : adjacentEdges) {
            if (!visited[e.getVert2()]) {
                int adjacentVertex = e.getVert2();
                // set these adjacent vertices' parent to be i
                parents[adjacentVertex] = i;

                // search the first unvisited neighbor recursively until there is nothing to
                // search (depth-first)
                internalDFS(adjacentVertex, parents, searchOrder, visited);
            }
        }
    }

    /**
     * Return a tree constructed using Breath-First Search (or Traversal), where the
     * tree starts from the specified vertex of index i. This method is not
     * recursive.
     * 
     * @param i index of the starting vertex
     * @return a tree like structure constructed using BFS
     */

    @Override
    public AbstractGraph<E>.SearchTree breadthFirstSearchTree(int i) {
        // the vertices that are visisted, which maintains an order of traversal. This
        // construct the breath first search tree
        List<Integer> searchOrder = new ArrayList<>();

        // boolean[] to indicate whether each corresponding vertex is visisted.
        boolean[] visited = new boolean[vertices.size()];

        // the corresponding parents of each vertex
        int[] parents = new int[vertices.size()];

        // add this first vertex i in the tree first
        visited[i] = true;
        searchOrder.add(i);

        // create back track queue to keep track of neighbors, which may be able to
        // further go down one level
        Deque<Integer> backTrackQueue = new LinkedList<>();
        backTrackQueue.offer(i);

        // consider the neighbors as levels, (e.g., neighbors, neighbors of
        // neighbors and so on). Traverse all the neighbors before go down level
        while (!backTrackQueue.isEmpty()) {
            // current vertex
            var current = backTrackQueue.poll();

            // traverse all neighbors of current
            List<Edge> neighborsOfCurrent = neighbors.get(current);
            for (Edge e : neighborsOfCurrent) {
                int eachNeighbor = e.getVert2();
                if (!visited[eachNeighbor]) {
                    parents[eachNeighbor] = current;
                    searchOrder.add(eachNeighbor);
                    visited[eachNeighbor] = true;

                    // put this neighbor into the back track queue for going down one level when all
                    // neighbors are added into the search Order
                    backTrackQueue.offer(eachNeighbor);
                }
            }
        }
        return new SearchTree(i, parents, searchOrder);
    }

    /** Breadth first search tree or depth first search tree */
    public class SearchTree {
        private int root;
        /** parent of each vertex */
        private int[] parentOfVertices;
        /** the search order, or the vertices that are visited */
        private List<Integer> searchOrder;

        /**
         * 
         * @param r  root
         * @param p  parents
         * @param so search order
         */
        public SearchTree(int r, int[] p, List<Integer> so) {
            this.root = r;
            this.parentOfVertices = p;
            this.searchOrder = so;
        }

        public int getRoot() {
            return root;
        }

        /** Parent of vertex at index v */
        public int getParent(int v) {
            return parentOfVertices[v];
        }

        public int numOfVerticesVisited() {
            return searchOrder.size();
        }

        @Override
        public String toString() {
            StringBuilder bs = new StringBuilder("Tree:[ ");
            for (int i : searchOrder) {
                bs.append(vertices.get(i) + " ");
            }
            bs.append("]");
            return bs.toString();
        }

    }

    /**
     * The edge between Vertex 1 and Vertex 2
     */
    public class Edge {

        /**
         * <p>
         * Index of vertex 1, by default, the vertex 1 should be used to identify
         * whether two edges are adjacent or i.e., share the same vertex (vertex 1).
         * When checking whether two edges are adjacent, the index of vertex 1 should be
         * checked instead of that of vertex 2.
         * </p>
         * <p>
         * In an adjacency list (two-dimentional), it should be as follows: <br>
         * {{vert1, vert2} {vert1, vert2} {vert1, vert2}...} <br>
         * <br>
         * E.g.,<br>
         * {{ 0, 5 }, { 1, 0 }, { 1, 2 }....}
         * </p>
         * <p>
         * So, the edge is essentially the vertex 1 connected to the vertex 2
         * </p>
         */
        private int vert1;

        /**
         * <p>
         * Index of vertex 2, by default, the vertex 2 indicates which vertex the vertex
         * 1 is connected to, i.e., vertex 2 is the adjacent vertex of vertex 1. When
         * checking whether two edges are adjacent, the index of vertex 1 should be
         * checked instead of that of vertex 2.
         * </p>
         * <p>
         * In an adjacency list (two-dimentional), it should be as follows: <br>
         * {{vert1, vert2} {vert1, vert2} {vert1, vert2}...} <br>
         * <br>
         * E.g.,<br>
         * {{ 0, 5 }, { 1, 0 }, { 1, 2 }....}
         * </p>
         * <p>
         * So, the edge is essentially the vertex 1 connected to the vertex 2
         * </p>
         */
        private int vert2;

        public Edge(int vert1, int vert2) {
            this.vert1 = vert1;
            this.vert2 = vert2;
        }

        public boolean equals(Edge e) {
            return this.getVert1() == e.getVert1() && this.getVert2() == e.getVert2();
        }

        public int getVert1() {
            return vert1;
        }

        public int getVert2() {
            return vert2;
        }

    }

}