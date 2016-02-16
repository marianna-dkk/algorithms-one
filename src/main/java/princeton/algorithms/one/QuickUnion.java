package princeton.algorithms.one;


/**
 * @author Marianna Dikaiakou
 *
 */
public class QuickUnion extends UnionFind {
    
    public QuickUnion(int size) {
        initializeDataset(size);
    }

    @Override
    public void union(int p, int q) {
        int rootp = root(p);
        int rootq = root(q);
        dataset[rootp] = rootq;
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
