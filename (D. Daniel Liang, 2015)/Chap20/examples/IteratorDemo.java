import java.util.*;

public class IteratorDemo {

    public static void main(String[] args) {

        Collection<String> col = new ArrayList<>();
        col.add("New York");
        col.add("Atlanta");

        Iterator<String> iterator = col.iterator();
        System.out.print("Iterator: ");
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
    }
}