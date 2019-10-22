import java.util.*;

public class ListIteratorDemo {

    public static void main(String[] args) {

        List<String> strList = new LinkedList<>();
        strList.add("Apple");
        strList.add("Orange");
        strList.add("Banan");

        // list iterator
        ListIterator<String> iterator = strList.listIterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");

        System.out.println();

        // backward traversing
        ListIterator<String> backwardIterator = strList.listIterator(strList.size());
        while (backwardIterator.hasPrevious())
            System.out.print(backwardIterator.previous() + " ");

    }
}