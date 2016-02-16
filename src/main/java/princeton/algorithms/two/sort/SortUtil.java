package princeton.algorithms.two.sort;

import edu.princeton.cs.algs4.StdRandom;

public class SortUtil {

    public static int[] initRandArr(int N) {
        int[] randArray = new int[N];
        
        // initialize with random ints
        for (int i = 0; i < N; i++) {
            randArray[i] = StdRandom.uniform(100000);
        }
        
        return randArray;
    }
    
    public static void exch(int[] arr, int leftIx, int rightIx) {
        int tmpVal = arr[leftIx];
        arr[leftIx] = arr[rightIx];
        arr[rightIx] = tmpVal;
    }
    
    public static boolean less(int thisn, int thatn) {
        return (thisn < thatn);
    }
}
