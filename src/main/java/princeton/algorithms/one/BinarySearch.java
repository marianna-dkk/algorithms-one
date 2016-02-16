package princeton.algorithms.one;

public class BinarySearch {

    public static int binarySearch(int[] dataset, int key) {
        int lo = 0, hi = dataset.length - 1;
        

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (dataset[mid] == key) {
                return mid;
            } else if (dataset[mid] > key) {
                // key is smaller than middle-point -> look to the left
                hi = mid--;
            } else {
                // key is larger than middle-point -> look to the right
                lo = mid++;
            }
        }
        
        return -1;
    }
}
