package princeton.algorithms.one;

import java.util.Arrays;

public abstract class UnionFind {
    protected int[] dataset;

    public abstract void union(int p, int q);

    public abstract boolean connected(int p, int q);

    public void initializeDataset(int size) {
        dataset = new int[size];
        for (int i = 0; i < size; i++) {
            dataset[i] = i;
        }
    }
    
    public void printArray() {
        System.out.println("Array: " + Arrays.toString(dataset));
    }
}
