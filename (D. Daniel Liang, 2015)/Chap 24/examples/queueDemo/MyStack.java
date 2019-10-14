import java.util.ArrayList;

public class MyStack<E> {

    private ArrayList<E> stack;
    private int size;

    public MyStack() {
        stack = new ArrayList<>();
        size = 0;
    }

    public MyStack(E[] arr) {
        stack = new ArrayList<>();
        size = 0;
        for (E e : arr) {
            stack.add(e);
            size++;
        }
    }

    public void push(E e) {
        stack.add(e);
        size++;
    }

    public E pop() {
        var e = stack.get(size - 1);
        stack.set(size - 1, null);
        size--;
        return e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Stack:[");

        for (int i = 0; i < size; i++) {
            sb.append(stack.get(i));
            if (i != size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}