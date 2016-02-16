package princeton.algorithms.one;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        testUF();
    }

    public static void testUF() {
        Scanner in = new Scanner(System.in);
        // read next byte, between 0 - 255
        int n = in.nextInt();
        UnionFind uf = new WeightedQuickUnion(n);

        while (true) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println("Connecting " + p + ", " + q + "\n");
                uf.printArray();
            }
        }

    }
}
