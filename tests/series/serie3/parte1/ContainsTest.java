package series.serie3.parte1;


import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static series.serie3.parte1.TreeUtils.contains;

public class ContainsTest {
    static final Comparator<Integer> CMP_NATURAL_ORDER = new Comparator<Integer>() {
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    };
    static final Node<Integer> T1 = createT1();
    static final Node<Integer> T2 = createT2();
    static final Node<Integer> T3 = createT3();

    private static Node<Integer> createT1() {
        Node<Integer> root = new Node<>(9);
        root.left = new Node<>(4);
        root.left.left = new Node<>(1);
        return root;
    }

    private static Node<Integer> createT2() {
        Node<Integer> root = new Node<>(1);
        root.right = new Node<>(5);
        root.right.right = new Node<>(10);
        return root;
    }

    private static Node<Integer> createT3() {
        Node<Integer> root = new Node<>(15);
        root.left = new Node<>(10);
        root.left.left = new Node<>(8);
        root.left.right = new Node<>(12);
        root.right = new Node<>(20);
        root.right.left = new Node<>(16);
        root.right.right = new Node<>(25);
        return root;
    }

    @Test
    public void contains_zero_limit() {
        assertFalse(contains(T1, 0, 0, CMP_NATURAL_ORDER));
        assertFalse(contains(T2, 0, 0, CMP_NATURAL_ORDER));
        assertFalse(contains(T3, 0, 0, CMP_NATURAL_ORDER));
    }

    @Test
    public void contains_root() {
        assertTrue(contains(T1, 8, 10, CMP_NATURAL_ORDER));
        assertTrue(contains(T2, 0, 2, CMP_NATURAL_ORDER));
        assertTrue(contains(T3, 14, 16, CMP_NATURAL_ORDER));
    }

    @Test
    public void contains_leaf() {
        int[] test = {8, 12, 16, 25};
        for (int i = 0; i < test.length; i++)
            assertTrue(contains(T3, test[i], test[i] + 1, CMP_NATURAL_ORDER));
        assertTrue(contains(T1, 1, 2, CMP_NATURAL_ORDER));
        assertTrue(contains(T2, 10, 11, CMP_NATURAL_ORDER));
    }

    @Test
    public void contains_internal() {
        assertTrue(contains(T1, 2, 8, CMP_NATURAL_ORDER));
        assertFalse(contains(T1, 10, 11, CMP_NATURAL_ORDER));

        assertTrue(contains(T2, 2, 8, CMP_NATURAL_ORDER));
        assertFalse(contains(T2, 11, 12, CMP_NATURAL_ORDER));

        assertTrue(contains(T3, 9, 11, CMP_NATURAL_ORDER));
        assertTrue(contains(T3, 17, 21, CMP_NATURAL_ORDER));
        assertFalse(contains(T3, 0, 2, CMP_NATURAL_ORDER));
    }


}
