import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Demo for Collections class (not the Collection interface)
 */
public class CollectionsMethodsDemo {

    public static void main(String[] args) {

        // array is not a Collection, it can't be manipulated by the static methods of
        // Collections
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(new String[] { "Orange", "Apple", "Banana" }));
        System.out.println("The list includes:" + stringList.toString());

        // max() and min()
        System.out.println("Max:" + Collections.max(stringList));
        System.out.println("Min:" + Collections.min(stringList));

        // sort()
        Collections.sort(stringList);
        System.out.println("Sorted: " + stringList.toString());

        // shuffle()
        Colllections.shuffle(stringList);
        System.out.println("Shuffled: " + stringList.toString());

    }
}