package princeton.algorithms.one;



public class QuickFind extends UnionFind {
    
    public QuickFind(int size) {
        initializeDataset(size);
    }

    @Override
    public void union(int p, int q) {
        // check if the indexes given are within range
        if (p < dataset.length && q < dataset.length) {
            
            int pid = dataset[p];
            int qid = dataset[q];
            
            for (int i = 0; i < dataset.length; i++) {
                if (dataset[i] == pid) {
                    dataset[i] = qid;
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("The requested indexes: " + p + " and " + q + " exceed the dataset size");
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return dataset[p] == dataset[q];
    }
    
}
