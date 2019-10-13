
public abstract class MyAbstractList<E> implements MyList<E> {

    protected int size;

    protected MyAbstractList() {
        this.size = 0;
    }

    protected MyAbstractList(E[] objects) {
        this.size = 0;

        for (int i = 0; i < objects.length; i++)
            this.add(objects[i]);
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(E e) {
        var index = indexOf(e);
        if (index >= 0) {
            remove(index);
            return true;
        } else
            return false;
    }

}