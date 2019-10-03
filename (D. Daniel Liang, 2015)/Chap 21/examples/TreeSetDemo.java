import java.util.*;

public class TreeSetDemo {
    public static void main(String[] args) {

        Set<String> treeSet = new TreeSet<>();
        treeSet.add("London");
        treeSet.add("Paris");
        treeSet.add("New York");
        treeSet.add("San Francisco");

        /*
         * ----------------------------------------
         * 
         * Methods specified in SortedSet interface
         * 
         * ----------------------------------------
         */

        // sorted elements
        System.out.println(treeSet);

        // first element
        System.out.println("First Element: " + ((TreeSet<String>) treeSet).first());

        // last element
        System.out.println("Last Element: " + ((TreeSet<String>) treeSet).last());

        // the elements after the specified element
        System.out
                .println("Elements after \"New York\" (inclusive): " + ((TreeSet<String>) treeSet).tailSet("New York"));

        // the elements before the specified element
        System.out.println("Elements before \"Paris\": " + ((TreeSet<String>) treeSet).headSet("Paris") + "\n\n");

        /*
         * ----------------------------------------
         * 
         * Methods specified in NavigableSet interface
         * 
         * ----------------------------------------
         */
        // lower, less than specified elements (similar to tailSet())
        System.out.println(((TreeSet<String>) treeSet).lower("Paris"));

        // floor, less than or equal
        System.out.println(((TreeSet<String>) treeSet).floor("Paris"));

        // ceiling, greater than or equal
        System.out.println(((TreeSet<String>) treeSet).ceiling("Paris"));

        // higher, greater than
        System.out.println(((TreeSet<String>) treeSet).higher("Paris"));

        // pollFirst, remove first
        System.out.println(((TreeSet<String>) treeSet).pollFirst());

        // pollLast, remove last
        System.out.println(((TreeSet<String>) treeSet).pollLast());

    }
}