
/**
 * Inteface
 */
public interface MyList<E> extends Iterable<E> {

    /**
     * Add an E object
     * 
     * @param e
     */
    public void add(E e);

    public void add(int i, E e);

    public void clear();

    public boolean contains(E e);

    public E get(int i);

    public int indexOf(E e);

    public boolean isEmpty();

    public int lastIndexOf(E e);

    public boolean remove(E e);

    public E remove(int i);

    public E set(int i, E e);

    public int size();
}