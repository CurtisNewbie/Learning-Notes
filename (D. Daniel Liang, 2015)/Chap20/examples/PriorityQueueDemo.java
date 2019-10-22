import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args) {

        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.offer("Apple");
        queue.offer("Cat");
        queue.offer("Banana");
        System.out.println(queue);

        // prioritised
        while (queue.size() > 0)
            System.out.println(queue.remove());

        System.out.println("\n");

        // using Comparator
        PriorityQueue<String> compaQueue = new PriorityQueue<>(Collections.reverseOrder());
        compaQueue.offer("Apple");
        compaQueue.offer("Cat");
        compaQueue.offer("Banana");
        System.out.println(compaQueue);

        while (compaQueue.size() > 0)
            System.out.println(compaQueue.remove());

    }
}