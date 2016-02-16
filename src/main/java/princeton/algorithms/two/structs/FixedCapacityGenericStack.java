package princeton.algorithms.two.structs;

public class FixedCapacityGenericStack<Item> {

    private Item[] s;
    private int pointer = 0;
    
    public FixedCapacityGenericStack() {
        s = (Item[]) new Object[1];
    }
    
    public boolean isEmpty() {
        return (pointer == 0);
    }
    
    public void push(Item item) {
        if (pointer == s.length) {
            resize(2 * s.length);
        }
        s[pointer++] = item;
    }
    
    public Item pop() {
        Item item = s[--pointer];
        s[pointer] = null;
        if (pointer > 0 && pointer == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }
    
    private void resize(int capacity) {
        Item[] newS = (Item[]) new Object[capacity];
        for (int i = 0; i < s.length; i++) {
            newS[i] = s[i];
        }
        s = newS;
    }
}
