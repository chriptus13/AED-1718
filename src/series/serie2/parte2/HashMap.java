package series.serie2.parte2;

import java.util.*;

public class HashMap<K, V> extends AbstractMap<K, V> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    private static class Node<K, V> extends SimpleEntry<K, V> {
        private final int hc;
        private Node<K, V> next;

        private Node(K key, V value, int hc, Node<K, V> next) {
            super(key, value);
            this.hc = hc;
            this.next = next;
        }
    }

    // << Variaveis de instancia >>
    private final float LOAD_FACTOR;
    private int capacity;
    private int nSize = 0;
    private Node<K, V>[] table;

    //<< Construtores >>
    public HashMap(int initialCapacity, float lf) {
        capacity = initialCapacity;
        LOAD_FACTOR = lf;
        table = (Node<K, V>[]) new Node<?, ?>[initialCapacity];
    }

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(Map<? extends K, ? extends V> m) {
        this(m.size(), DEFAULT_LOAD_FACTOR);
        putAll(m);
    }

    private int index(K k) {
        return Math.abs(k.hashCode()) % capacity;
    }

    @Override
    public V put(K k, V v) {
        if (k == null) throw new IllegalArgumentException("No support for null keys");
        if ((nSize / capacity) > LOAD_FACTOR) reHash();
        int hc = Math.abs(k.hashCode());
        int idx = hc % capacity;
        Node<K, V> curr = table[idx];

        if (curr == null) {
            table[idx] = new Node<>(k, v, hc, null);
            nSize++;

        } else {
            Node<K, V> prev = null;
            while (curr != null) {
                if (curr.getKey().equals(k)) {
                    V ret = curr.getValue();
                    curr = new Node<>(k, v, hc, curr.next);
                    if (prev != null) prev.next = curr;
                    return ret;
                }
                prev = curr;
                curr = curr.next;
            }
            prev.next = new Node<>(k, v, hc, null);
            nSize++;
        }
        return v;
    }

    @Override
    public V get(Object k) {
        int idx = index((K) k);
        Node<K, V> aux = table[idx];
        while (aux != null) {
            if (aux.getKey().equals(k)) return aux.getValue();
            aux = aux.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        int idx = index((K) key);
        Node<K, V> aux = table[idx];
        while (aux != null) {
            if (aux.getKey().equals(key)) return true;
            aux = aux.next;
        }
        return false;
    }

    @Override
    public V remove(Object k) {
        int idx = index((K) k);
        Node<K, V> aux = table[idx];
        if (aux == null) return null;
        if (aux.getKey().equals(k)) {
            table[idx] = aux.next;
            nSize--;
            return aux.getValue();
        }
        while (aux.next != null) {
            if (aux.next.getKey().equals(k)) {
                V ret = aux.next.getValue();
                aux.next = aux.next.next;
                nSize--;
                return ret;
            }
        }
        return null;
    }

    private void reHash() {
        Node<K, V>[] aux = table;
        capacity = 2 * capacity;
        table = (Node<K, V>[]) new Node<?, ?>[capacity];

        for (Node<K, V> nd : aux)
            while (nd != null) {
                put(nd.getKey(), nd.getValue());
                nd = nd.next;
            }
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) table[i] = null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {   //Para ser criado o Set de Entries apenas quando ja temos a table preenchida
            private int setN = nSize;
            private Entry<K, V>[] setTable = table;

            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<Entry<K, V>>() {
                    private int currIdx = 0;
                    private Entry<K, V> curr = null;

                    @Override
                    public boolean hasNext() {
                        if (curr != null) return true;
                        while (currIdx < table.length) {
                            if (setTable[currIdx] != null) {
                                curr = setTable[currIdx++];
                                return true;
                            }
                            currIdx++;
                        }
                        return false;
                    }

                    @Override
                    public Entry<K, V> next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        Entry<K, V> aux = curr;
                        if (((Node<K, V>) curr).next == null) curr = null;
                        else curr = ((Node<K, V>) curr).next;
                        return aux;
                    }
                };
            }

            @Override
            public int size() {
                return setN;
            }

            @Override
            public boolean contains(Object o) {
                return setTable[((K) o).hashCode() % capacity] != null;
            }

            @Override
            public void clear() {
                for (int i = 0; i < capacity; i++) setTable[i] = null;
            }

            @Override
            public boolean remove(Object o) {
                if (!contains(o)) return false;
                setTable[((K) o).hashCode() % capacity] = null;
                setN--;
                return true;
            }
        };
    }

    @Override
    public Set<K> keySet() {
        return new AbstractSet<K>() {
            private Set<Entry<K, V>> aux = entrySet();

            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    private Iterator<Entry<K, V>> it = aux.iterator();

                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public K next() {
                        return it.next().getKey();
                    }
                };
            }

            @Override
            public int size() {
                return aux.size();
            }
        };
    }

    @Override
    public Collection<V> values() {
        return new AbstractCollection<V>() {
            @Override
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    private Iterator<Entry<K, V>> it = entrySet().iterator();

                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public V next() {
                        return it.next().getValue();
                    }
                };
            }

            @Override
            public int size() {
                return nSize;
            }
        };
    }
}