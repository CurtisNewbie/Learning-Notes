/**
 * The edge between {@code int vert1} and {@code int vert2}
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

    @Override
    public String toString() {
        return "{" + vert1 + "-" + vert2 + "}";
    }

}
