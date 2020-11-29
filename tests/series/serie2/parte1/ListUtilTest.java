package series.serie2.parte1;

import java.util.ArrayList;
import java.util.Comparator;

public class ListUtilTest {

    /*
     * For circular lists with sentinel
     *
     */

    public static <E> boolean isEmptyListWithSentinel(Node<E> list) {

        return list.next == list && list.previous == list;
    }

    /*
     * For non_circular lists with no sentinel
     *
     */

    public static <E> boolean isSorted(Node<E> list, Comparator<E> cmp) {
        Node<E> curr = list.next;
        if (curr == list || curr == list.previous) return true;
        while (curr.next != list) {
            if (cmp.compare(curr.value, curr.next.value) > 0) return false;
            curr = curr.next;
        }
        return true;
    }


    public static Node<Integer> getListWithoutSentinel(ArrayList<Integer> array) {
        if (array.size() == 0) return null;
        Node<Integer> list = new Node<Integer>();
        Node<Integer> cur = list;
        cur.value = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            Node<Integer> next = new Node<Integer>();
            cur.next = next;
            next.previous = cur;
            next.value = array.get(i);
            cur = cur.next;
        }
        return list;
    }


    /*
     *
     * Generic Methods
     *
     *
     */


    public static <E> ArrayList<E> flattern(ArrayList<ArrayList<E>> arraylist) {
        ArrayList<E> flattern = new ArrayList<E>();
        for (int i = 0; i < arraylist.size(); i++) {
            flattern.addAll(arraylist.get(i));
        }
        return flattern;
    }

    public static void initData(Node<Integer>[] array, ArrayList<ArrayList<Integer>> arraylist) {
        if (array.length != arraylist.size()) return;
        for (int i = 0; i < arraylist.size(); i++) {
            for (int j = arraylist.get(i).size() - 1; j >= 0; j--) {
                Node<Integer> novo = new Node<Integer>(arraylist.get(i).get(j));
                novo.next = array[i];
                array[i] = novo;
            }
        }
    }
}
