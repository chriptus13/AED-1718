package series.serie3.parte1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static series.serie3.parte1.TreeUtils.isBalanced;

public class IsBalancedTest {
    static final Node<Integer> T1 = createT1();
    static final Node<Integer> T2 = createT2();
    static final Node<Integer> T3 = createT3();
    static final Node<Integer> T4 = createT4();
    static final Node<Integer> T5 = createT5();

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

    private static Node<Integer> createT4() {
        Node<Integer> root = new Node<>(2);
        root.left = new Node<>(7);
        root.left.left = new Node<>(2);
        root.left.right = new Node<>(8);
        root.left.right.left = new Node<>(5);
        root.left.right.right = new Node<>(11);
        root.right = new Node<>(5);
        root.right.right = new Node<>(9);
        root.right.right.left = new Node<>(4);
        return root;
    }

    private static Node<Integer> createT5() {
        Node<Integer> root = new Node<>(11);
        root.left = new Node<>(6);
        root.left.left = new Node<>(4);
        root.left.left.right = new Node<>(5);
        root.left.right = new Node<>(8);
        root.left.right.right = new Node<>(10);
        root.right = new Node<>(19);
        root.right.left = new Node<>(17);
        root.right.right = new Node<>(43);
        root.right.right.left = new Node<>(31);
        root.right.right.right = new Node<>(49);
        return root;
    }

    @Test
    public void isBalanced_A() {
        assertFalse(isBalanced(T1));
    }

    @Test
    public void isBalanced_B() {
        assertFalse(isBalanced(T2));
    }

    @Test
    public void isBalanced_C() {
        assertTrue(isBalanced(T3));
    }

    @Test
    public void isBalanced_D() {
        assertFalse(isBalanced(T4));
    }

    @Test
    public void isBalanced_E() {
        assertTrue(isBalanced(T5));
    }
}
