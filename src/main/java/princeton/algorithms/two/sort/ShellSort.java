package princeton.algorithms.two.sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class ShellSort {

    int[] arrayToSort;
    
    public ShellSort(int N) {
        arrayToSort = SortUtil.initRandArr(N);

        System.out.println("ShellSort: BEFORE -> " + Arrays.toString(arrayToSort));
    }
    
    public void sortArray() {
        int h = 1;
        int n = arrayToSort.length;
        
        while (h < n/3) {
            h = h*3 + 1;
        }
        
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h  && SortUtil.less(arrayToSort[j], arrayToSort[j - h]); j -= h) {
                    SortUtil.exch(arrayToSort, j, j - h);
                }
            }
            h = h/3;
        }
        
        System.out.println("ShellSort: AFTER -> " + Arrays.toString(arrayToSort));
    }

    public static void main(String[] args) {
        StdRandom.setSeed(System.currentTimeMillis());
        ShellSort ss = new ShellSort(10);
        ss.sortArray();
    }
}
