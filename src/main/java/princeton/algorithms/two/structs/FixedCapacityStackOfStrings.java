package princeton.algorithms.two.structs;

/**
 * @author Marianna Dikaiakou
 *
 */
public class FixedCapacityStackOfStrings {

    private String[] s;
    private int pointer = 0;
    
    public FixedCapacityStackOfStrings() {
        s = new String[1];
    }
    
    public boolean isEmpty() {
        return (pointer == 0);
    }
    
    public void push(String item) {
        if (pointer == s.length) {
            resize(2 * s.length);
        }
        s[pointer++] = item;
    }
    
    public String pop() {
        String item = s[--pointer];
        s[pointer] = null;
        if (pointer > 0 && pointer == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }
    
    private void resize(int capacity) {
        String[] newS = new String[capacity];
        for (int i = 0; i < s.length; i++) {
            newS[i] = s[i];
        }
        s = newS;
    }
}
