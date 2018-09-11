/**@Author: Guy Vincent-Jourdan, HaardTrivedi
 * @StudentNumber:300021545
 * @Course: ITI 1121 - C
 * @Assignment: 3
 */

public class CircularQueue<E> implements Queue<E> {

    private int size, front, rear;
    private E[] queue;
    private int maxSize;
    

    public boolean isFull(){
        return size == maxSize;
    }

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = (E[]) new Object[maxSize];
        size = 0;
        front = rear = 0;
    }

    public CircularQueue() {
        maxSize = 5000;
        queue = (E[]) new Object[maxSize];
        size = 0;
        front = rear = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void Enqueue(E o) {
        if (size == maxSize) {
            throw new IllegalStateException("The queue is full. No new elements can be added.");
        } else if (o == null) {
            throw new NullPointerException("Null is an invalid element to add to the queue");
        } else {
            rear = (rear++) % queue.length;
            queue[rear] = o;
            size++;
            if (front == -1) {
                front = rear;
            }
        }
    }
    
    @Override
    public E Dequeue() {
        E elem;
        if (isEmpty()) {
            throw new IllegalStateException("The circular queue is empty. An element cannot be removed");
        }
        else {
            elem = queue[front];
            queue[front] = null;
            front = (front++) % queue.length;
            size--;
        }
        return elem;
    }
    
    @Override
    public String toString() {
        String q = "";
        for (int i = 0; i<size; i++){
            if(i==size){
                q += queue[i].toString();
            }else{
                q += queue[i].toString() +", ";
            }            
        }
        return "CircularQueue[" + q + "]";
    }

}
