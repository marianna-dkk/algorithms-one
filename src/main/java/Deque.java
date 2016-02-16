import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque is a generalization of a stack and a queue 
 * that supports adding and removing items from either the front or the back
 * of the data structure. 
 * 
 * @author Marianna Dikaiakou
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * Pointer to the first element of the queue.
     */
    private volatile Node first = null;

    /**
     * Pointer to the last node of the queue.
     */
    private volatile Node last  = null;

    /**
     * Number of items in the Dequeue.
     */
    private volatile int  size  = 0;

    /**
     * Private class specifying the object handled by the Dequeue.
     * 
     * @author Marianna Dikaiakou
     */
    private class Node {
        Item item;
        Node next     = null;
        Node previous = null;
    }

    private class DequeueIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return!");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Attempt to call "
                    + "remove() in Dequeue iterator!");
        }

    }

    /**
     * Construct an empty deque.
     */
    public Deque() {
    }

    /**
     * Check if the deque is empty.
     * 
     * @return
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Return the number of items on the deque.
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Add an item to the front.
     * 
     * @param item
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Attempt to add null Item.");
        }

        Node oldfirst = first;

        Node newfirst = new Node();
        newfirst.item = item;
        newfirst.next = oldfirst;
        
        if (oldfirst != null) {
            synchronized (oldfirst) {
                oldfirst.previous = newfirst;
            }
        }
        
        first = newfirst;
        size++;
        if (size == 1) {
            last = first;
        }
    }

    /**
     * Add an item to the end.
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Attempt to add null Item.");
        }

        Node oldlast = last;

        Node newlast = new Node();
        newlast.item = item;
        newlast.previous = oldlast;
        if (oldlast != null) {
            synchronized(oldlast) {
                oldlast.next = newlast;
            }
        }
        
        last = newlast;
        size++;
        if (size == 1) {
            first = last;
        }
    }

    /**
     * Remove and return the item from the front.
     * 
     * @return
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Attempt to removeFirst from "
                    + "empty Dequeue");
        }
        
        Item item = first.item;
        synchronized (first) {
            first = first.next;
            size--;
        }
            
        return item;
    }

    /**
     * Remove and return the item from the end.
     * 
     * @return
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Attempt to removeFirst from "
                    + "empty Dequeue");
        }

        Item item = last.item;
        synchronized(last) {
            last = last.previous;
            size--;
        }
        
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }

    public static void main(String[] args) {

        Deque<Integer> intDeque = new Deque<Integer>();
        intDeque.addFirst(10);
        intDeque.addFirst(100);
        intDeque.addFirst(1000);
        intDeque.removeLast();
        intDeque.removeLast();
        intDeque.removeFirst();
        System.out.println("SIZE 1: " + intDeque.size());
        intDeque.addFirst(100);
        intDeque.addFirst(10);
        intDeque.addFirst(1000);
        intDeque.addFirst(10000);
        intDeque.addFirst(100);
        System.out.println("SIZE 1: " + intDeque.size());
        intDeque.removeLast();
        
        intDeque.addLast(9);
        intDeque.addLast(90);
        intDeque.addLast(900);
        intDeque.addLast(90);
        intDeque.addLast(9);
        intDeque.addLast(9000);
        intDeque.addLast(90000);
        intDeque.addLast(900000);
        intDeque.addLast(900);
        intDeque.addLast(90);
        intDeque.addLast(900);
        intDeque.addLast(91);
        intDeque.addLast(92);
        System.out.println("SIZE 2: " + intDeque.size());
        
        Iterator<Integer> ii = intDeque.iterator();
        while(ii.hasNext()) {
            System.out.println("item is: " + ii.next());
        }
    }
}
