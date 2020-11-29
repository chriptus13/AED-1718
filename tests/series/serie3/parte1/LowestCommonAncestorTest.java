package series.serie3.parte1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static series.serie3.parte1.TreeUtils.lowestCommonAncestor;

public class LowestCommonAncestorTest {
    static final Node<Integer> T1 = createT1();

    private static Node<Integer> createT1() {
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
    public void lowestCommonAncestor_n1EqualsN2() {
        assertEquals(new Integer(15), lowestCommonAncestor(T1, 15, 15).value);
    }

    @Test
    public void lowestCommonAncestor_n1OrN2() {
        assertEquals(new Integer(10), lowestCommonAncestor(T1, 8, 10).value);
        assertEquals(new Integer(20), lowestCommonAncestor(T1, 25, 20).value);
    }

    @Test
    public void lowestCommonAncestor_root() {
        assertEquals(new Integer(15), lowestCommonAncestor(T1, 8, 16).value);
        assertEquals(new Integer(15), lowestCommonAncestor(T1, 12, 16).value);
    }

    @Test
    public void lowestCommonAncestor_internal() {
        assertEquals(new Integer(10), lowestCommonAncestor(T1, 8, 12).value);
        assertEquals(new Integer(20), lowestCommonAncestor(T1, 25, 16).value);
    }
}
