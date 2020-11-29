package series.serie3.parte1;

public class TNode<E> {
    public boolean isWord;
    public TNode<E>[] children = (TNode<E>[]) new TNode<?>['z' - 'a' + 1];
}
