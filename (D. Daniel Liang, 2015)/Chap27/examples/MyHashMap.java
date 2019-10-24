import java.util.*;

public class MyHashMap<K, V> implements MyMap<K, V> {

    private static int DEF_INI_CAPA = 4;
    // 2^30
    private static int MAX_CAPA = 1 << 30;
    private static float DEF_LOAD_FACT = 0.75f;

    /**
     * Current capacity, not the actual number of elements in the map
     */
    private int capacity;

    /** load factor **/
    private float loadFactor;

    /** Number of elements in the map **/
    private int size;

    /**
     * The map is essentially, a array where each bucket stores elements using a
     * linkedlis (See Separate Chaining)
     */
    private LinkedList<Entry<K, V>>[] hashTable;

    public MyHashMap() {
        this(DEF_INI_CAPA, DEF_LOAD_FACT);
    }

    /**
     * @param c initial capacity
     */
    public MyHashMap(int c) {
        this(c, DEF_LOAD_FACT);
    }

    /**
     * When initial capacity is greater than the defined maximum capacity, the
     * capacity of the map is set to the given capacity. Else, the capacity of the
     * map is set to the number that is the power of 2 and is the cloest to (but
     * less than) the given initial capacity.
     * 
     * @param c initial capacity
     * @param t load factor thresHold
     */
    public MyHashMap(int c, float t) {

        this.capacity = c > MAX_CAPA ? c : trimToPowerOf2(c);
        this.loadFactor = t;
        this.hashTable = new LinkedList[capacity];
        this.size = 0;
    }

    @Override
    public void clear() {
        hashTable = new LinkedList[capacity];
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key.hashCode());
        var bucket = hashTable[index];
        if (bucket != null) {
            for (Entry<K, V> e : bucket) {
                if (e.getKey().equals(key))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            var bucket = hashTable[i];

            if (bucket != null) {
                for (Entry<K, V> e : bucket) {
                    if (e.getValue().equals(value))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Return set of entries
     * 
     * @return a set of entries, each with a pair of key and value
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();

        for (int i = 0; i < capacity; i++) {
            LinkedList<Entry<K, V>> bucket;
            if ((bucket = hashTable[i]) != null) {
                for (Entry<K, V> e : bucket) {
                    entries.add(e);
                }
            }
        }
        return entries;
    }

    @Override
    public V get(K key) {
        int index = hash(key.hashCode());
        LinkedList<Entry<K, V>> bucket = hashTable[index];
        if (bucket != null) {
            for (Entry<K, V> e : bucket) {
                if (e.getKey().equals(key))
                    return e.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        for (int i = 0; i < capacity; i++) {
            LinkedList<Entry<K, V>> bucket;
            if ((bucket = hashTable[i]) != null) {
                for (Entry<K, V> e : bucket) {
                    keys.add(e.getKey());
                }
            }
        }
        return keys;
    }

    /**
     * Check the size against capacity * loadFactor. It calls hash method internally
     * if necessary.
     */
    private void rehashIfNecessary() {
        if (size >= capacity * loadFactor) {
            if (capacity >= MAX_CAPA)
                throw new RuntimeException("Exceeding allowed maximum capacity [" + MAX_CAPA + "] ");
            rehash();
        }
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key.hashCode());

        // if bucket not exists
        if (get(key) == null) {
            // check size and capacity
            rehashIfNecessary();

            // add new bucket
            hashTable[index] = new LinkedList<Entry<K, V>>();
            hashTable[index].add(new Entry<K, V>(key, value));
            size++;
        } else {
            // bucket exists, find the one with same key and replace the old value with the
            // new value. If no same key found, insert this value at the tail of this
            // bucket.
            LinkedList<Entry<K, V>> bucket = hashTable[index];
            for (Entry<K, V> e : bucket) {
                if (e.getKey().equals(key)) {
                    // replace old value
                    var old = e.getValue();
                    e.setValue(value);
                    return old;
                }
            }
        }
        return null;
    }

    @Override
    public void remove(K key) {
        int index = hash(key.hashCode());
        LinkedList<Entry<K, V>> bucket = hashTable[index];
        if (bucket != null) {
            Iterator<Entry<K, V>> it = bucket.iterator();
            while (it.hasNext()) {
                if (it.next().getKey().equals(key))
                    it.remove();
                size--;
                return;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> values() {
        Set<V> setOfValues = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            LinkedList<Entry<K, V>> bucket = hashTable[i];
            if (bucket != null)
                for (Entry<K, V> e : bucket)
                    setOfValues.add(e.getValue());
        }
        return setOfValues;
    }

    /**
     * Rehashing is the process of increase the hash table size and reloading the
     * entries into a new larger hash table when the load factor is exceeded.
     */
    private void rehash() {
        // get the set of entries
        Set<Entry<K, V>> entries = entrySet();

        // double the capacity
        capacity <<= 1; // left shift 1 bit ( *= 2)

        // new hashtable with new capacity
        clear();
        for (Entry<K, V> e : entries) {
            put(e.getKey(), e.getValue());
        }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Map[ ");
        for (int i = 0; i < capacity; i++) {
            LinkedList<Entry<K, V>> bucket;
            if ((bucket = hashTable[i]) != null) {

                for (Entry<K, V> e : bucket) {
                    sb.append(e.getKey() + "_" + e.getValue() + " ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
}