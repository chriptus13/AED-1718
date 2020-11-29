package series.serie3.parte1;

import java.util.Comparator;

public class TreeUtils {
    public static <E> boolean contains(Node<E> root, E min, E max, Comparator<E> cmp) {
        if (root == null) return false;
        if (cmp.compare(root.value, max) > 0) return contains(root.left, min, max, cmp);
        if (cmp.compare(root.value, min) < 0) return contains(root.right, min, max, cmp);
        return true;
    }

    public static <E extends Comparable<E>> Node<E> lowestCommonAncestor(Node<E> root, E n1, E n2) {
        if (root == null) return null;
        if (root.value.equals(n1) || root.value.equals(n2)) return root;

        Node<E> l = lowestCommonAncestor(root.left, n1, n2);
        Node<E> r = lowestCommonAncestor(root.right, n1, n2);

        if (l != null && r != null) return root;
        return l != null ? l : r;
    }

    public static <E> boolean isBalanced(Node<E> root) {
        return isBalancedAux(root) != -1;
    }

    private static <E> int isBalancedAux(Node<E> root) {
        if (root == null) return 0;
        int l = isBalancedAux(root.left);
        int r = isBalancedAux(root.right);
        if (l == -1 || r == -1) return -1;
        return Math.abs(l - r) < 2 ? (l > r ? l : r) + 1 : -1;
    }
}
