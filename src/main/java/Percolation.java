import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Class to model an N-by-N grid and estimate the percolation threshold of 
 * the system via Monte Carlo simulation.
 * 
 * @author Marianna Dikaiakou
 */
public class Percolation {

    private enum Positions {
        ABOVE,
        BELOW,
        LEFT,
        RIGHT;
    }
    
    /**
     * The N-by-N grid is modeled as a 1-D int array.
     * We assume that the array contains either 0 - meaning blocked - 
     * or 1 - meaning open.
     */
    private int[] grid;
    
    /**
     * Object implementing the weighted quick Union-Find algorithm, to record 
     * the connected sites. 
     */
    private WeightedQuickUnionUF uf;
    

    /**
     * Object implementing the weighted quick Union-Find algorithm, to record 
     * the full sites. 
     */
    private WeightedQuickUnionUF fs;
    
    private int virtualTopIndex = 0;
    private int virtualBottomIndex;
    
    /**
     * The N passed in the constructor to set the grid size.
     */
    private int n;
    
    /**
     * create N-by-N grid, with all sites blocked.
     * 
     * @param N
     */
    public Percolation(int N) {
        // first, perform input validation
        if (N < 1) {
            throw new IllegalArgumentException("Expected constructor"
                    + " argument N should comply to N > 0. Actual argument"
                    + " is: " + N);
        }
        
        // gridsize needs to contain all sites of the N-by-N grid, plus 2 
        // sites for the virtual-top / virtual-bottom
        int gridsize = N*N + 2;
        n = N;
        
        grid = new int[gridsize];
        uf = new WeightedQuickUnionUF(gridsize);
        fs = new WeightedQuickUnionUF(gridsize);
        virtualBottomIndex = gridsize - 1;
        
        // connect top-row to virtual-top & bottom-row to virtual-bottom
        for (int j = 1; j <= N; j++) {
            // top-row
            uf.union(virtualTopIndex, xyTo1D(1, j));
            fs.union(virtualTopIndex, xyTo1D(1, j));
            
            // bottom-row
            uf.union(virtualBottomIndex, xyTo1D(N, j));
        }
    }

    /**
     * Open site (row i, column j) if it is not open already.
     * 
     * @param i
     *            The vertical index.
     * @param j
     *            The horizontal index.
     */
    public void open(int i, int j) {
        // 1. check that site is not already open
        if (!isOpen(i, j)) {
            // 2. set to open
            int indexIn1D = xyTo1D(i, j);
            grid[indexIn1D] = 1;
            
            // 3. connect to OPEN sites above, below, left and right
            for (Positions pos : Positions.values()) {
                int neighborIndex = neighbor(pos, i, j);
                if (neighborIndex != -1) {
                    uf.union(indexIn1D, neighborIndex);
                    fs.union(indexIn1D, neighborIndex);
                }
            }
        }
    }

    /**
     * Evaluates if site (row i, column j) is open.
     * 
     * @param i
     *            The vertical index.
     * @param j
     *            The horizontal index.
     * @return
     */
    public boolean isOpen(int i, int j) {
        return grid[xyTo1D(i, j)] == 1;
    }

    /**
     * Evaluates if site (row i, column j) is open && connected to the 
     * virtual-top.
     * 
     * @param i
     *            The vertical index.
     * @param j
     *            The horizontal index.
     * @return
     */
    public boolean isFull(int i, int j) {
        return isOpen(i, j) && fs.connected(virtualTopIndex, xyTo1D(i, j));
    }

    /**
     * Evaluates if the system percolates.
     * 
     * @return
     */
    public boolean percolates() {
        // handle corner case where n = 1
        if (n == 1) return isOpen(1, 1);
        return uf.connected(virtualTopIndex, virtualBottomIndex);
    }

    private int xyTo1D(int i, int j) {
        validate2Dcoordinates(i, j);
        int result = (i - 1) * n + j;
        return result;
    }
    
    private void validate2Dcoordinates(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n) {
            throw new IndexOutOfBoundsException("Expected values for (i, j) "
                    + "are in range [1, " + n + "]. Actual values are: (" + i 
                    + ", " + j + ")");
        }
    }

    /*
     * This method maps to 1D the 2D coordinates of the site, neighboring to 
     * the one given.
     * IF the neighbor is outside the grid OR the neighbor is Full,
     *   the method returns -1, to signify to the caller that connecting 
     *   to this neighbor is not applicable.  
     */
    private int neighbor(Positions pos, int i, int j) {
        try {
            switch (pos) {
                case ABOVE:
                    if (isOpen(i - 1, j)) {
                        return xyTo1D(i - 1, j);
                    }
                    break;
                case BELOW:
                    if (isOpen(i + 1, j)) {
                        return xyTo1D(i + 1, j);
                    }
                    break;
                case LEFT:
                    if (isOpen(i, j - 1)) {
                        return xyTo1D(i, j - 1);
                    }
                    break;
                case RIGHT:
                    if (isOpen(i, j + 1)) {
                        return xyTo1D(i, j + 1);
                    }
            }
        } catch (IndexOutOfBoundsException exception) {
            // catch IndexOutOfBoundsException if neighbor at specified 
            // position does not exist
        }
        return -1;
    }

}
