package princeton.algorithms.two.structs;

/**
 * @author Marianna Dikaiakou
 *
 */
public class LinkedQueueOfString {

    private Node first, last;
    
    private class Node {
        String item;
        Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
    }
    
    public String dequeue() {
        Node oldfirst = first;
        first = oldfirst.next;
        return oldfirst.item;
    }
}
