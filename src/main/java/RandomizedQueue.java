import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * A randomized queue is similar to a stack or queue, except that the item 
 * removed is chosen uniformly at random from items in the data structure.
 * 
 * @author Marianna Dikaiakou
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    /**
     * Array of items.
     */
    private Item[] a;
    
    /**
     * Number of elements in queue.
     */
    private int N;
    
    /**
     * When number of random removals reaches this point, 
     * copy array contents, to get rid of nulls.
     */
    private int cleanupPoint;
    
    private int removals;
    
    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        N = 0;
        cleanupPoint = 1;
        removals = 0;
    }
    
    /**
     * Check if the queue is empty.
     * @return
     */
    public boolean isEmpty() {
        return (N == 0);
    }
    
    /**
     * Return the number of items on the queue.
     * 
     * @return
     */
    public int size() {
        return N;
    }
    
    /**
     * Add the item.
     * 
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Attempt to enqueue null item");
        }
        
        if (N == a.length) {
            resize(2 * a.length);    // double size of array if necessary
        }
        a[N++] = item;               // add item
        
        // reset cleanupPoint after every insert
        cleanupPoint = calcCleanupPoint();          
    }
    
    /**
     * Remove and return a random item.
     * 
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        
        int pointer = randomIndex();
        Item item = a[pointer];
        a[pointer] = null;
        N--;
        removals++;
        
        // check if resizing is needed
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        // move items towards the array-top to eliminate nulls
        else if (removals >= cleanupPoint) {
            cleanUpNulls(0);
        }
        // reset cleanupPoint after every removal
        cleanupPoint = calcCleanupPoint();  
        
        return item;
    }
    
    /**
     * Return (but do not remove) a random item.
     * 
     * @return
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return a[randomIndex()];
    }

    private int randomIndex() {
        return returnIfNotNull(StdRandom.uniform(a.length));
    }
    
    private int returnIfNotNull(int i) {
        if (a[i] == null) {
            return returnIfNotNull(StdRandom.uniform(a.length));
        } else {
            return i;
        }
    }
    
    private int calcCleanupPoint() {
        int result = N/2;
        if (result < 1) {
            return 1;
        }
        return result;
    }
    
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            // copy over to new array only if item != null
            copyIfNotNull(a, temp, i, i, 0);
        }
        a = temp;
    }
    
    private boolean copyIfNotNull(Item[] src, Item[] tg, int srcIx,  int tgIx, int depth) {
        StdOut.println("copyIfNotNull: src=" + Arrays.toString(src) + "\n target=" + Arrays.toString(tg) +
                "\nsrcIx=" + srcIx + ", tgIx=" + tgIx + ", depth=" + depth);
        if (srcIx == src.length) return true;
        if (src[srcIx] == null) {
            return copyIfNotNull(src, tg, srcIx+1, tgIx, ++depth);
        } else {
            Item tmp = src[srcIx];
            src[srcIx] = null;
            tg[tgIx] = tmp;
            return (depth != 0);
        }
    }
    
    private void cleanUpNulls(int pointer) {
        StdOut.println("cleanUpNulls: removals=" + removals);
        StdOut.println("cleanup START: " + Arrays.toString(a));
        boolean nullFound = false;
        for (int i = pointer; i < a.length; i++) {
            nullFound = copyIfNotNull(a, a, i, i, 0);
            /*if (nullFound) {
                removals--;
                StdOut.println("cleanUpNulls: nullFound, removals=" + removals);
            }
            if (removals == 0) {
                break;
            }*/
        }
        removals = 0;
        StdOut.println("cleanUpNulls: removals=" + removals);
        StdOut.println("cleanup END  : " + Arrays.toString(a));
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private int[] randIx;

        public RandomArrayIterator() {
            i = 0;
            
            // if needed, perform cleanup of nulls
            if (removals > 0) {
                cleanUpNulls(i);
            }
            
            randIx = new int[N];
            for (int j = 0; j < N; j++) {
                randIx[j] = j;
            }
            StdRandom.shuffle(randIx);
        }

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (a[randIx[i]] == null) {
                throw new ConcurrentModificationException("Underlying "
                        + "implementation has been modified");
            }
            return a[randIx[i++]];
        }
    }

    public static void main(String[] args) {
        StdRandom.setSeed(System.currentTimeMillis());
        
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(20);
        StdOut.println("arr: " + Arrays.toString(rq.a));
        StdOut.println("remove: " + rq.dequeue());
        StdOut.println("arr: " + Arrays.toString(rq.a));
        
        rq.enqueue(199);
        rq.enqueue(200);
        rq.enqueue(201);
        rq.enqueue(202);
        rq.enqueue(203);
        rq.enqueue(204);
        rq.enqueue(205);
        rq.enqueue(206);
        rq.enqueue(207);
        StdOut.println("arr: " + Arrays.toString(rq.a));
        
        StdOut.println("remove: " + rq.dequeue());
        StdOut.println("arr: " + Arrays.toString(rq.a));
        StdOut.println("remove: " + rq.dequeue());
        StdOut.println("arr: " + Arrays.toString(rq.a));
        StdOut.println("remove: " + rq.dequeue());
        StdOut.println("arr: " + Arrays.toString(rq.a));
        StdOut.println("remove: " + rq.dequeue());
        StdOut.println("arr: " + Arrays.toString(rq.a));
        
        StdOut.println("sample: " + rq.sample());
        
        Iterator<Integer> rqit = rq.iterator();
        while (rqit.hasNext()) {
            StdOut.println("Iterator next=" + rqit.next());
            StdOut.println("Iterating sample: " + rq.sample());
        }
        StdOut.println("hasNext? " + rqit.hasNext());
        
        rqit = rq.iterator();
        while (rqit.hasNext()) {
            StdOut.println("2 Iterator next=" + rqit.next());
        }
        StdOut.println("2 hasNext? " + rqit.hasNext());
    }
}
