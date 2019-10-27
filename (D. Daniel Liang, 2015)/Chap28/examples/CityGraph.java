import java.util.*;

public class CityGraph {

    /**
     * This graph consists of following vertices.
     */
    public static String[] cities = { "Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City", "Chicago",
            "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston" };

    /** One way to represent and label vertices */
    private City[] vertices;

    /** One way to represent the edges, using two-dimentional array */
    private ArrayList<ArrayList<Integer>> edges;

    /** Example of Adjacency Matrix */
    private int[][] adjacencyMatrix = { { 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0 }, { 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
            { 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 } };

    CityGraph() {
        vertices = listOfCities(cities);
    }

    private City[] listOfCities(String... cities) {
        var len = cities.length;
        City[] c = new City[len];
        for (int i = 0; i < len; i++) {
            c[i] = new City(cities[i]);
        }
        return c;
    }

    // For Demo only
    // @Override
    // public String toString() {
    // StringBuilder sb = new StringBuilder();
    // for (City c : vertices) {
    // sb.append(c.toString() + "\n");
    // }
    // return sb.toString();
    // }

}

/**
 * Vertex
 */
class City {
    private String name;
    private int population;
    private String mayor;

    public City(String n, int pop, String m) {
        this.name = n;
        this.population = pop;
        this.mayor = m;
    }

    public City(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getMayor() {
        return mayor;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setPopulation(int p) {
        this.population = p;
    }

    public void setMayor(String m) {
        this.mayor = m;
    }

    @Override
    public String toString() {
        return "[" + name + " " + population + " " + mayor + "]";
    }
}

/** Representing Edges as objects */
class Edge {

    private int vert1;
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