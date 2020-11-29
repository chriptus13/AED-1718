package series.serie2.parte1;

import java.util.Comparator;

public class ListUtils {
    public static <E> Node<E> occurAtLeastKTimes(Node<E>[] lists, Comparator<E> cmp, int k) {
        int r = 0;
        int elems = lists.length;
        Node<E> aux;

        Node<E> dummy = new Node<>();
        dummy.next = dummy;
        dummy.previous = dummy;

        if (lists[0] == null) return dummy;
        minHeapify(lists, lists.length, 0, cmp);
        aux = lists[0];
        while (lists[0] != null) {
            if (r == k) {
                aux.next = dummy;
                aux.previous = dummy.previous;
                dummy.previous.next = aux;
                dummy.previous = aux;
                aux = lists[0];
                r = 0;
            } else if (cmp.compare(aux.value, lists[0].value) == 0) {
                r++;
                lists[0] = lists[0].next;
                if (lists[0] == null) {
                    lists[0] = lists[--elems];
                }
                minHeapify(lists, elems, 0, cmp);
            } else {
                aux = lists[0];
                r = 0;
            }
        }

        if (r == k) {
            aux.next = dummy;
            aux.previous = dummy.previous;
            dummy.previous.next = aux;
            dummy.previous = aux;
        }
        return dummy;
    }

    public static <E> void internalReverse(Node<Node<E>> list) {
        for (Node<Node<E>> l = list; l != null; l = l.next) {
            for (Node<E> in = l.value, aux; in != null; in = aux) {
                aux = in.next;
                in.next = in.previous;
                in.previous = aux;
                if (aux == null) l.value = in;
            }
        }
    }

    private static <E> void minHeapify(Node<E>[] arr, int n, int pos, Comparator<E> cmp) {
        int min = pos, l = left(min), r = right(min);
        if (l < n && arr[l] != null && cmp.compare(arr[min].value, arr[l].value) > 0) min = l;
        if (r < n && arr[r] != null && cmp.compare(arr[min].value, arr[r].value) > 0) min = r;
        if (pos != min) {
            exchange(arr, min, pos);
            minHeapify(arr, n, min, cmp);
        }
    }

    private static <E> void exchange(E[] arr, int pos, int pos2) {
        E aux = arr[pos];
        arr[pos] = arr[pos2];
        arr[pos2] = aux;
    }

    private static int left(int i) {
        return i * 2 + 1;
    }

    private static int right(int i) {
        return i * 2 + 2;
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }
}
