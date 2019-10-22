import java.util.LinkedList;

public class MyQueue<E> {

    private LinkedList<E> queue;

    public MyQueue() {
        queue = new LinkedList<>();
    }

    public MyQueue(E[] arr) {
        queue = new LinkedList<>();
        for (E e : arr) {
            queue.addFirst(e);
        }
    }

    public void enqueue(E e) {
        queue.addFirst(e);
    }

    public E dequeue() {
        var e = queue.removeLast();
        return e;
    }

    public int getSize() {
        return queue.size();
    }

    @Override
    public String toString() {
        return "Queue:" + queue.toString();
    }
}