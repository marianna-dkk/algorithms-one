import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Subset {
    
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        
        if (args.length != 1) {
            throw new IllegalArgumentException("Input number k should be provided");
        }
        int k = Integer.parseInt(args[0]);
        
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            if (line.trim().equals("")) {
                break;
            }
            // split to find items
            String[] items = line.split("\\s");
            
            for (String item : items) {
                rq.enqueue(item);
            }
        }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
