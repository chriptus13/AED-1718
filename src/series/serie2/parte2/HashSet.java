package series.serie2.parte2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<E> {
    static class Node<E> {
        Node<E> next;
        E item;
    }

    private Node<E>[] table;
    private int m;
    private int n;
    private float loadFactor = 0.75F;

    public HashSet() {
        table = (Node<E>[]) new Node<?>[11];
        m = 11;
    }

    public HashSet(float loadFactor, int size) {
        table = (Node<E>[]) new Object[size];
        m = size;
        this.loadFactor = loadFactor;
    }

    public boolean contains(E e) {
        int pos = index(e);
        Node<E> list = table[pos];
        while (list != null) {
            if (list.item.equals(e)) return true;
            list = list.next;
        }
        return false;
    }

    public boolean add(E e) {
        if (contains(e)) return false;
        if (n / m > loadFactor) reHash();
        int pos = index(e);
        Node<E> nd = new Node<E>();
        nd.item = e;
        nd.next = table[pos];
        table[pos] = nd;
        n++;
        return true;
    }

    public E get(E e) {
        if (!contains(e)) return null;
        int pos = index(e);
        Node<E> list = table[pos];
        while (list != null) {
            if (list.item.equals(e)) return list.item;
            list = list.next;
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < m; i++) table[i] = null;
    }

    public void remove(E e) {
        if (!contains(e)) return;
        int pos = index(e);
        Node<E> list = table[pos];
        Node<E> prev = null;
        while (list != null) {
            if (list.item.equals(e)) {
                if (prev != null) prev.next = list.next;
                else table[pos] = null;
                n--;
                return;
            }
            prev = list;
            list = list.next;
        }
    }

    public int size() {
        return n;
    }

    private void reHash() {
        Node<E>[] aux = (Node<E>[]) new Node<?>[2 * m];
        Iterator<E> it = this.iterator();
        for (E e = it.next(); it.hasNext(); e = it.next()) {
            int pos = Math.abs(e.hashCode()) % (2 * m);
            Node<E> nd = new Node<E>();
            nd.item = e;
            nd.next = aux[pos];
            aux[pos] = nd;
        }
        m = aux.length;
        table = aux;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> curr = null;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (curr != null) return true;
                for (; index < m; index++) {
                    Node<E> aux = table[index];
                    if (aux != null) {
                        curr = aux;
                        index++;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E aux = curr.item;
                if (curr.next == null) curr = null;
                else curr = curr.next;
                return aux;
            }
        };
    }

    private int index(E e) {
        int code = Math.abs(e.hashCode());
        return code % m;
    }
}