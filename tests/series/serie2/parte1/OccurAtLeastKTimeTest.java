package series.serie2.parte1;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static series.serie2.parte1.ListUtils.occurAtLeastKTimes;


public class OccurAtLeastKTimeTest {

    static final Comparator<Integer> CMP_NATURAL_ORDER = new Comparator<Integer>() {
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    };

    static final ArrayList<Integer> ELEMENTS = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));


    static ArrayList<ArrayList<Integer>> OTHER_ELEMENTS = new ArrayList<ArrayList<Integer>>(
            Arrays.asList(new ArrayList<Integer>(Arrays.asList(1, 2, 2, 5, 6, 6)),
                    new ArrayList<Integer>(Arrays.asList(0, 0, 2, 2, 6)),
                    new ArrayList<Integer>(Arrays.asList(6, 6, 7, 7, 7, 7, 8))));


    @Test
    public void occurAtLeastKTimes_empty_array() {
        Node<Integer>[] array = (Node<Integer>[]) new Node[10];
        for (int i = 1; i < 10; i++)
            assertTrue(ListUtilTest.isEmptyListWithSentinel(occurAtLeastKTimes(array, CMP_NATURAL_ORDER, i)));
    }

    @Test
    public void occurAtLeastKTimes_singleton_listsWithAtLeastOneOcurrence() {
        Node<Integer>[] array = (Node<Integer>[]) new Node[10];
        for (int i = 0; i < array.length; i++)
            array[i] = new Node<Integer>(i);
        Node<Integer> merge = occurAtLeastKTimes(array, CMP_NATURAL_ORDER, 1);
        assertTrue(ListUtilTest.isSorted(merge, CMP_NATURAL_ORDER));
        Node<Integer> aux = merge.next;
        for (int i = 0; i < ELEMENTS.size(); i++) {
            assertTrue(ELEMENTS.get(i).equals(aux.value));
            aux = aux.next;
        }
    }

    @Test
    public void occurAtLeastKTimes_singleton_listsWithMoreThanOneOcurrence() {
        Node<Integer>[] array = (Node<Integer>[]) new Node[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Node<Integer>(i);
        }
        for (int i = 2; i < 10; i++)
            assertTrue(ListUtilTest.isEmptyListWithSentinel(occurAtLeastKTimes(array, CMP_NATURAL_ORDER, i)));
    }


    @Test
    public void occurAtLeastKTimes_listsWithAtLeastOneOcurrence() {
        Node<Integer>[] array = (Node<Integer>[]) new Node[3];
        ListUtilTest.initData(array, OTHER_ELEMENTS);
        Node<Integer> merge = occurAtLeastKTimes(array, CMP_NATURAL_ORDER, 1);
        assertTrue(ListUtilTest.isSorted(merge, CMP_NATURAL_ORDER));

        ArrayList<Integer> flattern = ListUtilTest.flattern(OTHER_ELEMENTS);
        Collections.sort(flattern, CMP_NATURAL_ORDER);
        Node<Integer> aux = merge.next;
        for (int i = 0; i < flattern.size(); i++) {
            assertTrue(flattern.get(i).equals(aux.value));
            aux = aux.next;
        }
    }

    @Test
    public void occurAtLeastKTimes_listsWithMoreThanOneOcurrence() {
        Node<Integer>[] array = (Node<Integer>[]) new Node[3];
        ListUtilTest.initData(array, OTHER_ELEMENTS);
        Node<Integer> merge = occurAtLeastKTimes(array, CMP_NATURAL_ORDER, 4);
        assertTrue(ListUtilTest.isSorted(merge, CMP_NATURAL_ORDER));
        ArrayList<Integer> flattern = ListUtilTest.flattern(OTHER_ELEMENTS);
        Collections.sort(flattern, CMP_NATURAL_ORDER);
        Node<Integer> aux = merge.next;
        assertTrue(aux.value.equals(2));
        aux = aux.next;
        assertTrue(aux.value.equals(6));
        aux = aux.next;
        assertTrue(aux.value.equals(7));
    }
}

