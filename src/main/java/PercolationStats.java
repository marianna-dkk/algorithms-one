import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


/**
 * Class used to test Percolation and provide the mean, standard deviation and hi / lo confidence level.
 * 
 * @author Marianna Dikaiakou
 *
 */
public class PercolationStats {

    /** 
     * The number of times to run the experiment.
     */
    private int t = 1;
    
    /**
     * The number of open sites at which the system percolates divided by the 
     * total number of sites (i.e. N*N).
     */
    private double[] threshold;
    
    private double mean, stddev;
    
    /**
     * Perform T independent experiments on an N-by-N grid.
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException("Expected constructor"
                    + " arguments N, t should comply to N > 0 && T > 0."
                    + " Actual arguments are: " + N + ", " + T);
        }
        
        threshold = new double[T];
        t = T;
        int gridsize = N*N;

        // run scenario T times
        for (int ix = 0; ix < T; ix++) {
            int val = runScenario(new Percolation(N), N);
            threshold[ix] = ((double) val / gridsize);
        }
        
        // calculate stats and store in global variables
        mean = StdStats.mean(threshold);
        if (T == 1) {
            stddev = Double.NaN;
        } else {
            stddev = StdStats.stddev(threshold);
        }
    }
    
    private int runScenario(Percolation percolation, int N) {
        // random i, j
        int i, j;
        
        int openSites = 0;
        while (!percolation.percolates()) {
            i = StdRandom.uniform(N) + 1;
            j = StdRandom.uniform(N) + 1;
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                openSites++;
            }
        }
        
        return openSites;
    }
    
    /**
     * Return sample mean of percolation threshold.
     * @return
     */
    public double mean() {
        return mean;
    }
    
    /**
     * Return sample standard deviation of percolation threshold.
     * @return
     */
    public double stddev() {
        return stddev;
    }
    /**
     * Return low  endpoint of 95% confidence interval.
     * @return
     */
    public double confidenceLo() {
        if (Double.isNaN(stddev)) {
            return Double.NaN;
        } 
        System.out.println("confidenceLo: " + mean + " - (1.96 * " + stddev + " / " + Math.sqrt(t) + ") with t = " + t);
        return mean - ((1.96 * stddev) / Math.sqrt(t));
    }
    
    /**
     * Return high endpoint of 95% confidence interval.
     * @return
     */
    public double confidenceHi() {
        if (Double.isNaN(stddev)) {
            return Double.NaN;
        } 
        return mean + ((1.96 * stddev) / Math.sqrt(t));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Input arguments N and T were not provided");
        }
        
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        StdRandom.setSeed(System.currentTimeMillis());
        PercolationStats ps = new PercolationStats(n, t);
        
        // print results
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
