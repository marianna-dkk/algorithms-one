package princeton.algorithms.two.sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class SelectionSort {

    int[] arrayToSort;
    
    public SelectionSort(int N) {
        arrayToSort = SortUtil.initRandArr(N);
        
        System.out.println("BEFORE SORT: " + Arrays.toString(arrayToSort));
    }
    
    public void sortArray() {
        int minIndex;
        
        for (int i = 0; i < arrayToSort.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arrayToSort.length; j++) {
                if (SortUtil.less(arrayToSort[j], arrayToSort[minIndex])) {
                    minIndex = j;
                }
            }
            SortUtil.exch(arrayToSort, i, minIndex);
        }
        
        System.out.println("AFTER SORT: " + Arrays.toString(arrayToSort));
    }
    
    public static void main(String[] args) {
        StdRandom.setSeed(System.currentTimeMillis());
        SelectionSort ss = new SelectionSort(20);
        ss.sortArray();
    }
    
}
