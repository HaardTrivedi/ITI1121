/*Bhalachandra Malghan - Haard Trivedi
* ITI 1121-C
* Assignment 4
* 300034277  - 300021545 
*/
//@author Marcelle Turcotte, Haard Trivedi, Bhalachandra Malghan
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
    
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public Iterator<E> iterator(int nextIndex) {
        if (nextIndex < 0 || nextIndex >= size) {
            throw new IndexOutOfBoundsException();
        }
        return new LinkedListIterator(nextIndex);
    }
    
    @Override
    public Iterator<E> iterator(Iterator<E> other){
        return new LinkedListIterator(other);
    }

    

    private static class Node<T> {

        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private int size;

    public LinkedList() {
        head = new Node<E>(null, null, null); // dummy node!
        head.prev = head.next = head;
        size = 0;
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> current = head;
        private int nextIndex;
        
        private LinkedListIterator() {
            this(0);
        }

        public LinkedListIterator(int nextIndex) {
            this.nextIndex = nextIndex;
            for (int i = 0; i < nextIndex; i++) {
                current = current.next;
            }
        }

        public LinkedListIterator(Iterator<E> other) {
            other.nextIndex();
        }        

        public boolean hasNext() {
            return (current.next != head);
        }

        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = current.next;

            return current.value;
        }

        public int nextIndex() {
            int i = 0;
            Node<E> temp = current;
            temp = temp.next;

            if (i < size()) {
                for (; i < size(); i++) {
                    if (get(i) == temp.value) {
                        return i;
                    }
                }
            }

            return i;
        }

    }

    public int size() {
        return size;
    }

    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        Node<E> p = head.next;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.value;
    }

    public void addFirst(E elem) {

        if (elem == null) {
            throw new NullPointerException();
        }

        Node<E> second = head.next;

        head.next = new Node<E>(elem, head, second);
        second.prev = head.next;

        size++;
    }

    public void add(E elem) {

        if (elem == null) {
            throw new NullPointerException();
        }

        Node<E> before = head.prev, after = head;

        before.next = new Node<E>(elem, before, after);
        after.prev = before.next;

        size++;
    }

}
