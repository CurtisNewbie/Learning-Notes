import java.util.*;

public class LinkedHashSetDemo {
    public static void main(String[] args) {

        // LinkedHashSet maintains insertion order
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("London");
        linkedHashSet.add("Paris");
        linkedHashSet.add("New York");
        linkedHashSet.add("San Francisco");

        // this maintains the exact same order as the elements are added
        var iterator = linkedHashSet.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
    }
}
