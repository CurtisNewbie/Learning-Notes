import java.util.*;

public class MapDemo {
    public static void main(String[] args) {

        /*
         * -----------------------------------------------------------
         * 
         * Hash Map
         * 
         * -----------------------------------------------------------
         */
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("London", 15);
        hashMap.put("Paris", 13);
        hashMap.put("New York", 122);
        hashMap.put("San Francisco", 78);

        // showing the key/value pairs
        System.out.println(hashMap);

        /*
         * -----------------------------------------------------------
         * 
         * LinkedHashMap
         * 
         * -----------------------------------------------------------
         */
        int defCapacity = 15;
        float loadFactor = 0.75f;
        // true for access order, false for insertion order
        boolean accessOrder = false;
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(defCapacity, loadFactor, accessOrder);
        linkedHashMap.put("London", 15);
        linkedHashMap.put("Paris", 13);
        linkedHashMap.put("New York", 122);
        linkedHashMap.put("San Francisco", 78);
        System.out.println(linkedHashMap);

        /*
         * -----------------------------------------------------------
         * 
         * TreeMap
         * 
         * -----------------------------------------------------------
         */
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("London", 15);
        treeMap.put("Paris", 13);
        treeMap.put("New York", 122);
        treeMap.put("San Francisco", 78);
        System.out.println(treeMap);

    }
}