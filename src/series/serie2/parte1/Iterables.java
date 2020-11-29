package series.serie2.parte1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables {
    public static Iterable<Integer> getSortedSubsequence(Iterable<Integer> src, int k) {
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Integer curr = null;
                    Integer last = null;
                    Iterator<Integer> it = src.iterator();

                    @Override
                    public boolean hasNext() {
                        if (curr != null) return true;
                        while (it.hasNext()) {
                            Integer aux = it.next();
                            if (aux == k && last == null || last != null && aux >= last) {
                                curr = aux;
                                last = aux;
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public Integer next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        Integer aux = curr;
                        curr = null;
                        return aux;
                    }
                };
            }
        };
    }

    public static Iterable<Pair<String, Integer>> groupingEquals(Iterable<String> words) {
        return new Iterable<Pair<String, Integer>>() {
            @Override
            public Iterator<Pair<String, Integer>> iterator() {
                return new Iterator<Pair<String, Integer>>() {
                    Pair<String, Integer> curr = null;
                    Iterator<String> it = words.iterator();
                    String x = it.hasNext() ? it.next() : null;


                    @Override
                    public boolean hasNext() {
                        if (curr != null) return true;
                        if (x == null) return false;
                        int i = 1;
                        while (it.hasNext()) {
                            String aux = it.next();
                            if (!aux.equals(x)) {
                                curr = new Pair<>(x, i);
                                x = aux;
                                return true;
                            }
                            i++;
                        }
                        curr = new Pair<>(x, i);
                        x = null;
                        return true;
                    }

                    @Override
                    public Pair<String, Integer> next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        Pair<String, Integer> aux = curr;
                        curr = null;
                        return aux;
                    }
                };
            }
        };
    }
}
