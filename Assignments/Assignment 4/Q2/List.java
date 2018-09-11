/*Bhalachandra Malghan - Haard Trivedi
* ITI 1121-C
* Assignment 4
* 300034277  - 300021545 
*/
//@author Marcelle Turcotte
public interface List<E> {
    void addFirst(E elem);
    int size();
    E get(int index);
    Iterator<E> iterator();
    Iterator<E> iterator(int nextIndex);
    Iterator<E> iterator(Iterator<E> other);
}
