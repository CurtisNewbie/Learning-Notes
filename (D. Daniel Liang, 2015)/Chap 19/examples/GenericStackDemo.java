import java.util.*;

public class GenericStackDemo {
    public static void main(String[] args) {
        GenericStack<String> strStack = new GenericStack<>();

        strStack.push("Apple");
        strStack.push("Orange");
        strStack.push("Tea");
        strStack.push("Coffee");

        System.out.println(strStack.toString());
        System.out.println("getSize() " + strStack.getSize());
        System.out.println("peek() " + strStack.peek());
        System.out.println("pop() " + strStack.pop());
        System.out.println("peek() " + strStack.peek());
        System.out.println("isEmpty() " + strStack.isEmpty());

    }
}

class GenericStack<T> implements IsStack<T> {

    private List<T> stack;

    public GenericStack() {
        stack = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return stack.size();
    }

    @Override
    public T peek() {
        return stack.get(stack.size() - 1);
    }

    @Override
    public void push(T o) {
        stack.add(o);
    }

    @Override
    public T pop() {
        return stack.remove(stack.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return "GenericStack: " + stack.toString();
    }

}

interface IsStack<T> {

    int getSize();

    T peek();

    void push(T o);

    T pop();

    boolean isEmpty();
}
