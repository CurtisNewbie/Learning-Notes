import java.util.*;

public class CollectionsStaticMethods {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("yellow", "red", "green", "blue");

        // sort
        Collections.sort(list);
        System.out.println("Collections.sort() : " + list);

        // descending order, sort
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("Collections.sort() in reverse Order : " + list);

        // binarySearch
        System.out.println("Binary Search (\"green\") : " + Collections.binarySearch(list, "green"));

        // reverse
        Collections.reverse(list);
        System.out.println("Collections.reverse() : " + list);

        // shuffle
        Collections.shuffle(list);
        System.out.println("Collections.shuffle() : " + list);

        // shuffle with random, which creates identical sequences of elements for the
        // same bound
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        list2.addAll(list);
        list3.addAll(list);
        System.out.println("List 2 : " + list2);
        System.out.println("List 3 : " + list3);
        Collections.shuffle(list2, new Random(10));
        Collections.shuffle(list3, new Random(10));
        System.out.println("After shuffling (new Random(10)) List 2 : " + list2);
        System.out.println("After shuffling (new Random(10)) List 3 : " + list3);

        // copy (shallow copy)
        List<String> list4 = new ArrayList<>();
        for (int x = 0; x < list3.size(); x++)
            list4.add("*");
        System.out.println("List 4 : " + list4);
        Collections.copy(list4, list3);
        System.out.println("Copying list 3 to list 4 : " + list4);

        // nCopies (immutable list)
        List<String> list5 = new ArrayList<>();
        list5 = Collections.nCopies(5, "Apple");
        System.out.println("NCopies : " + list5);

        // fill
        List<String> list6 = new ArrayList<>();
        list6.add("e");
        list6.add("e");
        list6.add("e");
        Collections.fill(list6, "*"); // the * will replace all the elements stored
        System.out.println("Fill : " + list6);

        // max and min
        System.out.println("Max : " + Collections.max(list));
        System.out.println("Min : " + Collections.min(list));

        // disjoint
        System.out.println("Disjoint between list 4 and list 5 : " + Collections.disjoint(list4, list5));

        // frequency
        System.out.println("Frequency : " + Collections.frequency(list, "green"));

    }
}
