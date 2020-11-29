package series.serie2.parte1;

public class Stack<E> {
    private E[] stack;
    private int index = 0;

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {
        return index == stack.length;
    }

    @SuppressWarnings("unchecked")
    private void increaseStack() {
        E[] aux = (E[]) (new Object[stack.length * 2]);
        System.arraycopy(stack, 0, aux, 0, stack.length);
        stack = aux;
    }

    public E pop() {
        return isEmpty() ? null : stack[--index];
    }

    public E peek() {
        return stack[index - 1];
    }

    public void push(E e) {
        if (isFull()) increaseStack();
        stack[index++] = e;
    }

    @SuppressWarnings("unchecked")
    public Stack() {
        stack = (E[]) (new Object[64]);
    }
}
