package princeton.algorithms.two.structs;

import edu.princeton.cs.algs4.StdOut;

public class Test {

    public static void main(String[] args) {
        int n = 50;
        
        GenericStack<Integer> stack = new GenericStack<>();
        while( n > 0 ) {
            stack.push(n % 2);
            n = n / 2;
        }
        
        for (int d : stack) {
            StdOut.print(d);
        }
        StdOut.println();
        
    }
}
