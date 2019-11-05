
/**
 * WeightedEdge with an addtional property {@code weight} in comparison to Edge
 * class. This class Extends Edge class in Chap28.
 */
public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {

    /** Weight on this edge */
    private double weight;

    /**
     * 
     * @param vert1
     * @param vert2
     * @param w     weight
     */
    public WeightedEdge(int vert1, int vert2, double w) {
        super(vert1, vert2);
        this.weight = w;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        if (this.weight == o.weight)
            return 0;
        else if (this.weight > o.weight)
            return 1;
        else
            return -1;
    }

    public double getWeight() {
        return weight;
    }

}