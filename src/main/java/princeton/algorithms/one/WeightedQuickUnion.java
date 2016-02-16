package princeton.algorithms.one;

public class WeightedQuickUnion extends UnionFind {

    int[] treesize;
    
    public WeightedQuickUnion(int size) {
        initializeDataset(size);
        
        // initialize sizes -> set to 1
        treesize = new int[size];
        for (int i = 0; i < size; i++) {
            treesize[i] = 1;
        }
    }
    
    @Override
    public void union(int p, int q) {
        int rootp = root(p);
        int rootq = root(q);

        if (treesize[rootp] >= treesize[rootq]) {
            // p-tree is bigger (or equal), therefore set q under p and update tree size
            dataset[rootq] = rootp;
            treesize[rootp] += treesize[rootq];
        } else {
            dataset[rootp] = rootq;
            treesize[rootq] += treesize[rootp];
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int x) {
        if (dataset[x] == x) {
            return x;
        }
        return root(dataset[x]);
    }
}
