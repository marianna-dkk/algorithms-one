package princeton.algorithms.two.structs;

import java.util.Iterator;

/**
 * @author Marianna Dikaiakou
 *
 */
public class GenericStack<Item> implements Iterable<Item> {

    Node first = null;
    
    private class ListIterator implements Iterator<Item> {

        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            Node toRemove = current;
            // find previous item and point to current.next
        }
        
    }
    
    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() {
        return (first == null);
    }
    
    public void push(Item item) {
        Node oldfirst = first;
        Node newfirst = new Node();
        newfirst.item = item;
        newfirst.next = oldfirst;
        first = newfirst;
    }
    
    public Item pop() {
        if (isEmpty()) return null;
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }
}
