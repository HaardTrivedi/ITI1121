/*Bhalachandra Malghan - Haard Trivedi
* ITI 1121-C
* Assignment 4
* 300034277  - 300021545 
*/
//@author Marcelle Turcotte, Haard Trivedi, Bhalachandra Malghan
/**
 * @author Marcel Turcotte
 */
import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>> {

    private static class Node<T> {

        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node<E> root = null;

    /**
     * Inserts an object into this BinarySearchTree.
     *
     * @param elem item to be added
     * @return true if the object has been added and false otherwise
     */
    public boolean add(E elem) {

        // pre-condtion:
        if (elem == null) {
            throw new NullPointerException();
        }

        // special case:
        if (root == null) {
            root = new Node<E>(elem);
            return true;
        }

        // general case:
        return add(elem, root);
    }

    // Helper method
    private boolean add(E elem, Node<E> current) {

        boolean result;
        int test = elem.compareTo(current.value);

        if (test == 0) {
            result = false; // already exists, not added
        } else if (test < 0) {
            if (current.left == null) {
                current.left = new Node<E>(elem);
                result = true;
            } else {
                result = add(elem, current.left);
            }
        } else if (current.right == null) {
            current.right = new Node<E>(elem);
            result = true;
        } else {
            result = add(elem, current.right);
        }
        return result;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node<E> current) {

        if (current == null) {
            return "()";
        }

        return "(" + toString(current.left) + current.value + toString(current.right) + ")";
    }

    public int count(E low, E high) {
        if (low == null || high == null || root == null) {
            return 0;
        }
        return getCount(root, low, high);

    }

    int getCount(Node<E> temp, E low, E high) {
        if (temp==null){
            return 0;
        }

        //If root node is equal to or between low and high
        if (temp.value.compareTo(low) >= 0 && temp.value.compareTo(high) <= 0) {
            return getCount(temp.left, low, high) + getCount(temp.right, low, high) + 1;
        } //If node is greater than high
        else if (temp.value.compareTo(high) > 0) {
            return this.getCount(temp.left, low, high);
        } //If node is less than low
        else {
            return this.getCount(temp.right, low, high);
        }

    }

}
