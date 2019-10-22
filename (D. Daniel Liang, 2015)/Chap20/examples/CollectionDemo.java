import java.util.*;

public class CollectionDemo {

    public static void main(String[] args) {

        // A Collection object, which is implemented as a ArrayList
        Collection<String> strCollection1 = new ArrayList<>();
        strCollection1.add("Shenzhen");
        strCollection1.add("Shanghai");
        strCollection1.add("Wolverhampton");
        strCollection1.add("London");
        strCollection1.add("Bournemouth");
        System.out.println("Collection 1 : " + strCollection1.toString());

        System.out.println("Collection 1 has London:" + (strCollection1.contains("London") ? "Yes" : "Nah"));

        strCollection1.remove("London");
        System.out.println("Collection 1 removed London: " + strCollection1.toString());

        // ArrayList implements Cloneable interface, so casting is needed.
        ArrayList<String> clonedCollections = (ArrayList<String>) ((ArrayList<String>) strCollection1).clone();
        System.out.println("Cloned Collection of Collection 1: " + clonedCollections);

        // addAll method, i.e., set union
        Collection<String> strCollections2 = new ArrayList<>();
        strCollections2.addAll(strCollection1);
        System.out.println("Collection 2 (After add all of Collection 1) has: " + strCollections2);

        // retainAll method, i.e., set intersection
        Collection<String> strCollection3 = new ArrayList<>();
        strCollection3.add("Shenzhen");
        strCollection3.add("Bournemouth");
        System.out.println("Collection 3 has: " + strCollection3);
        strCollection3.retainAll(strCollection1);
        System.out.println("Set Intersection between collection 1 and 3: " + strCollection3);

        // removeAll method, i.e., set difference
        Collection<String> strCollection4 = new ArrayList<>();
        strCollection4.add("Shenzhen");
        strCollection4.add("Sheffield");
        System.out.println("Collection 1 has: " + strCollection1);
        System.out.println("Collection 4 has: " + strCollection4);
        strCollection1.removeAll(strCollection4);
        System.out.println("Set Difference between Collection 1 and 4 (Calling by Collection 1): " + strCollection1);
    }
}