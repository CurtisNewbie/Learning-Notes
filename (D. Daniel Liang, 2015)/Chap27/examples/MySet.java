
public interface MySet<E> extends Iterable<E> {

    void clear();

    boolean contains(E e);

    boolean add(E e);

    boolean remove(E e);

    boolean isEmpty();

    int size();
}