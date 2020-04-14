/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        if (args.length > 0) {
            int k = Integer.parseInt(args[0]);
            int n = 0;
            RandomizedQueue<String> randQueue = new RandomizedQueue<>();

            while (!StdIn.isEmpty()) {
                String input = StdIn.readString();

                // Until k elements are enqueued, keep accepting
                if (n++ < k) {
                    randQueue.enqueue(input);
                }
                // Once k elements are enqueued, accept new elemements
                // based on probability
                else {
                    if (StdRandom.uniform(n) < k) {
                        randQueue.dequeue();
                        randQueue.enqueue(input);
                    }
                }
                //n++;
            }

            Iterator<String> it = randQueue.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
    }
}

k = 5, n = 4/5