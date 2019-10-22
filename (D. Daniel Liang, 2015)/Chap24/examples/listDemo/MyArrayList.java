import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E> {

    public static final int INI_CAP = 16;
    private E[] arr;

    public MyArrayList() {
        arr = (E[]) new Object[INI_CAP];
    }

    public MyArrayList(E[] arr) {
        arr = (E[]) new Object[INI_CAP];

        for (E e : arr) {
            arr[size] = e;
            size++;
        }
    }

    @Override
    public void add(int i, E e) {

        this.ensureCapacity();

        // move elements to the right after i
        for (int x = size - 1; x >= i; x--) {
            arr[x + 1] = arr[x];
        }
        arr[i] = e;
        size++;
    }

    /**
     * Enlarge the capacity to be the twice of the current size when the current
     * size is greater its 75% of its capacity.
     */
    private void ensureCapacity() {
        if (size >= arr.length * 0.75) {
            E[] newArr = (E[]) new Object[size * 2 + 1];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
    }

    @Override
    public void clear() {
        arr = (E[]) new Object[INI_CAP];
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        for (E o : arr) {
            if (o.equals(e))
                return true;
        }
        return false;
    }

    @Override
    public E get(int i) {
        checkIndex(i);
        return arr[i];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(e))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i > 0; i--) {
            if (arr[i].equals(e))
                return i;
        }
        return -1;
    }

    @Override
    public E remove(int i) {
        checkIndex(i);

        E e = arr[i];
        for (int j = i; j < size - 1; j++) {
            arr[j] = arr[j + 1];
        }
        arr[size - 1] = null;
        size--;
        return e;
    }

    @Override
    public E set(int i, E e) {
        checkIndex(i);
        E o = arr[i];
        arr[i] = e;
        return o;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return (pointer < size);
            }

            @Override
            public E next() {
                return arr[pointer++];
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(pointer);
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]);
            if (i == size - 1)
                sb.append("]");
            else
                sb.append(", ");
        }
        return sb.toString();
    }

}