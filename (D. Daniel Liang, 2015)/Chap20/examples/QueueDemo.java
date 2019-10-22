import java.util.*;

public class QueueDemo {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("Abc"); // similar to add() but it is better for queue and deque
        queue.offer("DDDD");
        queue.offer("NNNN");
        System.out.println(queue);

        while (queue.size() > 0)
            System.out.println("Remove : " + queue.remove());

    }
}