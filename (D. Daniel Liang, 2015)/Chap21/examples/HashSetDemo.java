import java.util.*;

public class HashSetDemo {

    public static void main(String[] args) {

        Set<String> hashSet = new HashSet<>();

        hashSet.add("London");
        hashSet.add("Paris");
        hashSet.add("New York");
        hashSet.add("San Francisco");

        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

    }
}