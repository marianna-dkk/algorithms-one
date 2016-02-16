package princeton.algorithms.two.structs;

/**
 * @author Marianna Dikaiakou
 *
 */
public class StackOfStrings {

    Node first = null;
    
    private class Node {
        String item;
        Node next;
    }
    
    public boolean isEmpty() {
        return (first == null);
    }
    
    public void push(String item) {
        Node oldfirst = first;
        Node newfirst = new Node();
        newfirst.item = item;
        newfirst.next = oldfirst;
        first = newfirst;
    }
    
    public String pop() {
        if (isEmpty()) return null;
        String item = first.item;
        first = first.next;
        return item;
    }
}
