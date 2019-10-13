import java.util.Iterator;

/**
 * Singly linked list
 * 
 * @param <E>
 */
public class MyLinkedList<E> extends MyAbstractList<E> {

    MyNode<E> head;
    MyNode<E> tail;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    public MyLinkedList(E[] arr) {
        super(arr);
        head = null;
        tail = null;
    }

    @Override
    public void add(int i, E e) {
        if (i == 0)
            linkedFirst(e);
        else if (i >= size)
            linkedLast(e);
        else {
            MyNode<E> node = head;
            for (int j = 1; j < i; j++) {
                node = node.next;
            }
            MyNode<E> temp = node.next;
            MyNode<E> insertedNode = new MyNode<E>(e);
            node.next = insertedNode;
            insertedNode.next = temp;
            size++;
        }
    }

    public void linkedFirst(E e) {
        MyNode<E> node = new MyNode<E>(e);
        node.next = head;
        head = node;

        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void linkedLast(E e) {

        MyNode<E> node = new MyNode<E>(e);

        if (tail == null) {
            head = node;
            tail = head;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;

    }

    @Override
    public boolean contains(E e) {

        if (head.element.equals(e))
            return true;

        MyNode<E> node = head;
        for (int j = 1; j < size; j++) {
            node = node.next;
            if (node.element.equals(e))
                return true;
        }
        return false;
    }

    @Override
    public E get(int i) {
        checkIndex(i);
        MyNode<E> node = head;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        return node == null ? null : node.element;
    }

    @Override
    public int indexOf(E e) {

        if (head.element.equals(e))
            return 0;

        MyNode<E> node = head;
        for (int j = 1; j < size; j++) {
            node = node.next;
            if (node.element.equals(e))
                return j;
        }
        return -1;

    }

    @Override
    public int lastIndexOf(E e) {

        if (tail.element.equals(e)) {
            return size - 1;
        } else {
            int lastFound = -1;

            if (head.element.equals(e))
                lastFound = 0;

            MyNode<E> node = head;
            for (int j = 1; j < size; j++) {
                node = node.next;
                if (node.element.equals(e))
                    lastFound = j;
            }
            return lastFound;
        }
    }

    @Override
    public E remove(int i) {
        checkIndex(i);
        if (i == 0)
            return removeFirst();
        else if (i == size - 1)
            return removeLast();
        else {
            MyNode<E> prev = head;
            for (int j = 1; j < i; i++) {
                prev = prev.next;
            }
            MyNode<E> current = prev.next;
            prev.next = current.next;
            size--;
            return current.element;
        }
    }

    public E removeLast() {

        if (size == 0)
            return null;
        else if (size == 1) {
            MyNode<E> node = head;
            head = null;
            tail = null;
            size = 0;
            return node.element;
        } else {
            MyNode<E> removedNode = tail;
            MyNode<E> node = head;
            for (int i = 1; i < size - 1; i++) {
                node = node.next;
            }
            node.next = null;
            tail = node;
            size--;
            return removedNode.element;
        }
    }

    public E removeFirst() {
        if (size == 0)
            return null;
        else if (size == 1) {
            MyNode<E> node = head;
            head = null;
            tail = null;
            size = 0;
            return node.element;
        } else {
            MyNode<E> removedNode = head;
            MyNode<E> node = head.next;
            head = node;
            size--;
            return removedNode.element;
        }
    }

    @Override
    public E set(int i, E e) {
        checkIndex(i);
        MyNode<E> node = head;
        for (int j = 0; j < i - 1; j++) {
            node = node.next;
        }
        MyNode<E> prev = node;
        MyNode<E> current = prev.next;
        MyNode<E> next = current.next;
        MyNode<E> insertedNode = new MyNode<E>(e);
        prev.next = insertedNode;
        insertedNode.next = next;
        return current.element;
    }

    @Override
    public Iterator iterator() {

        return new Iterator<E>() {
            MyNode<E> node = head;

            @Override
            public boolean hasNext() {

                return node != null;
            }

            @Override
            public E next() {
                E ele = node.element;
                node = node.next;
                return ele;
            }

        };
    }

    public String toString() {
        int count = 0;
        StringBuilder sb = new StringBuilder("[");
        for (E e : this) {
            sb.append(e);
            if (count < size - 2)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private class MyNode<E> {
        E element;
        MyNode<E> next;

        private MyNode(E e) {
            this.element = e;
            this.next = null;
        }

    }

}