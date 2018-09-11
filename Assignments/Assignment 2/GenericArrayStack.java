
public class GenericArrayStack<E> implements Stack<E> {

    // ADD YOUR INSTANCE VARIABLES HERE
    private E[] ArrayStack;
    private int top;

    @SuppressWarnings("unchecked")
    // Constructor
    public GenericArrayStack(int capacity) {

        // ADD YOU CODE HERE
        ArrayStack = (E[]) new Object[capacity];
        top = 0;

    }

    GenericArrayStack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        if (top == 0) {
            return true;
        }
        // ADD YOU CODE HERE
        return false;
    }

    public void push(E elem) {

        // ADD YOU CODE HERE
        ArrayStack[top + 1] = elem;
    }

    public E pop() {
        if (isEmpty() == false) {
            E popped = ArrayStack[--top];
            ArrayStack[top] = null;
            return popped;
        }
        return null;
    }

    public E peek() {

        // ADD YOU CODE HERE
        return ArrayStack[0];

    }
}
