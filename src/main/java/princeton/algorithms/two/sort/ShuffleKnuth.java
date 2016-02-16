package princeton.algorithms.two.sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class ShuffleKnuth {
    
    int[] arrayToShuffle;
    
    public ShuffleKnuth(int N) {
        arrayToShuffle = new int[N];
        for (int i = 0; i < N; i++) {
            arrayToShuffle[i] = i;
        }
        System.out.println("BEFORE -> " + Arrays.toString(arrayToShuffle));
    }

    public void shuffle() {
        int n = arrayToShuffle.length;
        int rand = 0;
        for (int i = 0; i < n; i++) {
            rand = StdRandom.uniform(i + 1);
            SortUtil.exch(arrayToShuffle, i, rand);
        }
        
        System.out.println("AFTER -> " + Arrays.toString(arrayToShuffle));
    }

    public static void main(String[] args) {
        StdRandom.setSeed(System.currentTimeMillis());
        ShuffleKnuth sk = new ShuffleKnuth(10);
        sk.shuffle();
    }
}
