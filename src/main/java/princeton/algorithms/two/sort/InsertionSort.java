package princeton.algorithms.two.sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Marianna Dikaiakou
 */
public class InsertionSort {

    int[] arrayToSort;

    public InsertionSort(int N) {
//        arrayToSort = SortUtil.initRandArr(N);
        arrayToSort = new int[]{17, 57, 72, 96, 98, 35, 91, 66, 70, 94};

        System.out.println("BEFORE SORT: " + Arrays.toString(arrayToSort));
    }
    
    public void sortArray() {
        int exchanges = 0;
        for (int i = 0; i < arrayToSort.length && exchanges < 6; i++) {
            for (int j = i; j > 0 && exchanges < 6; j--) {
                if (SortUtil.less(arrayToSort[j], arrayToSort[j - 1])) {
                    SortUtil.exch(arrayToSort, j, j - 1);
                    System.out.println("exchange: " + arrayToSort[j] + ", " + arrayToSort[j-1]);
                    exchanges++;
                }
            }
        }

        System.out.println("AFTER SORT: " + Arrays.toString(arrayToSort));
    }
    
    public static void main(String[] args) {
        StdRandom.setSeed(System.currentTimeMillis());
        InsertionSort is = new InsertionSort(10);
        is.sortArray();
    }
}
