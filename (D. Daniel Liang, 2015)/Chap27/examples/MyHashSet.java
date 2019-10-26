import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyHashSet<E> implements MySet<E> {

    /** Default Initial Capcaity */
    private static int DEF_INI_CAP = 4;

    /** Maximum Capcity */
    private static int MAX_CAP = 1 << 30;

    /** Default load factor */
    private static float DEF_LOAD_FACTOR = 0.75f;

    /** Capacity */
    private int capacity;

    /** Number of elements */
    private int size;

    /** Load factor */
    private float loadFactor;

    /** Use a hashMap/hashTable internally */
    private LinkedList<E>[] hashTable;

    /** Instantiate it using default intial capacity and load factor */
    public MyHashSet() {
        this(DEF_INI_CAP, DEF_LOAD_FACTOR);
    }

    /** Instantiate it using default load factor */
    public MyHashSet(int capacity) {
        this(capacity, DEF_LOAD_FACTOR);
    }

    /** Instantiate it using default initial capcity */
    public MyHashSet(float loadFactor) {
        this(DEF_INI_CAP, loadFactor);
    }

    public MyHashSet(int capacity, float loadFactor) {
        // makes the capacity the power of 2 for even distribution
        this.capacity = (capacity > MAX_CAP) ? MAX_CAP : trimToPowerOf2(capacity);
        this.loadFactor = loadFactor;
        this.size = 0;
        this.hashTable = new LinkedList[capacity];
    }

    @Override
    public void clear() {
        size = 0;
        // traverse elements
        for (int i = 0; i < capacity; i++) {
            var bucket = hashTable[i];
            if (bucket != null)
                // set all elements in the bucket to null
                bucket.clear();
        }
    }

    @Override
    public boolean contains(E e) {
        int index = hash(e.hashCode());
        var bucket = hashTable[index];
        if (bucket != null) {
            for (E ele : bucket)
                if (ele.equals(e))
                    return true;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        if (contains(e))
            return false;
        else {

            // check capacity agains load factor
            rehashIfNecessary();

            // if bucket exists
            int index = hash(e.hashCode());
            if (hashTable[index] != null)
                hashTable[index].add(e);
            else {
                hashTable[index] = new LinkedList<E>();
                hashTable[index].add(e);
            }
            size++;
            return false;
        }
    }

    @Override
    public boolean remove(E e) {
        if (!contains(e))
            return false;
        else {
            int index = hash(e.hashCode());
            size--;
            return hashTable[index].remove(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Primary hash function.
     * 
     * @return index
     */
    private int hash(int hashCode) {

        return supplementalHash(hashCode) & (capacity - 1);

        // same as supplementalHash(hashCode) % capacity, if capacity is the power
        // of 2, however, this can cause int overflow when the key is a String, making
        // the returned index a negative and causing IndexOutofBoundException
    }

    /**
     * Supplemental Hash for even distribution. This aims to achieve a better
     * avalance effect. Since, the implementation of hashCode() of objects are
     * sometimes bad.
     * 
     * @param h hash code
     * @return further hashed hash code
     */
    private int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /** find the cloest number that is the power of 2 */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1; // Same as capacity *= 2. <= is more efficient
        }
        return capacity;
    }

    /**
     * Check the size against capacity * loadFactor. It calls hash method internally
     * if necessary.
     */
    private void rehashIfNecessary() {
        if (size >= capacity * loadFactor) {
            if (capacity >= MAX_CAP)
                throw new RuntimeException("Exceeding allowed maximum capacity [" + MAX_CAP + "] ");
            rehash();
        }
    }

    /**
     * Rehashing is the process of increase the hash table size and reloading the
     * entries into a new larger hash table when the load factor is exceeded.
     */
    private void rehash() {
        // get the list of elements
        List<E> list = new ArrayList<E>();
        for (int i = 0; i < capacity; i++) {
            if (hashTable[i] != null) {
                for (E e : hashTable[i]) {
                    list.add(e);
                }
            }
        }
        // double the capacity
        capacity <<= 1; // left shift 1 bit ( *= 2)

        // new hash table with new capacity
        size = 0;
        hashTable = new LinkedList[capacity];
        for (E e : list) {
            add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashSetIterator();
    }

    @Override
    public String toString() {
        ArrayList<E> list = elementsToList();
        StringBuilder sb = new StringBuilder("[ ");
        for (E e : list)
            sb.append(e + " ");
        sb.append("]");
        return sb.toString();
    }

    private ArrayList<E> elementsToList() {
        ArrayList<E> elements = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (hashTable[i] != null) {
                for (E e : hashTable[i])
                    elements.add(e);
            }
        }
        return elements;
    }

    private class MyHashSetIterator implements Iterator<E> {
        private ArrayList<E> list;
        private int current;

        public MyHashSetIterator() {
            list = elementsToList();
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        public void remove() {
            MyHashSet.this.remove(list.get(current));
            list.remove(current);
        }

    }
}