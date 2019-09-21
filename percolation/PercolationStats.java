/* *****************************************************************************
 *  Name:    Anurag Narayan
 *  NetID:   narayan
 *  Precept: P00
 *
 *  Description:  Programming assignment for coursera algs4 percolation
 *
 **************************************************************************** */


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // constant for determining 95% confidence level
    private static final double CONFIDENCE_95 = 1.96;

    // stores mean of percolation thresholds
    private final double mean;

    // stores stddev of percolation thresholds
    private final double stddev;

    // stores confidenceLow of percolation thresholds
    private final double confidenceLow;

    // stores confidenceHigh of percolation thresholds
    private final double confidenceHigh;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        validate(n, trials);

        double[] thresholds = new double[trials];
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int randomX = StdRandom.uniform(1, n+1);
                int randomY = StdRandom.uniform(1, n+1);
                percolation.open(randomX, randomY);
            }
            double threshold = percolation.numberOfOpenSites()/(double) (n * n);
            thresholds[trial] = threshold;
        }

        // update stats
        mean = StdStats.mean(thresholds);
        stddev =  StdStats.stddev(thresholds);
        confidenceLow = mean - ((CONFIDENCE_95 * stddev)/Math.sqrt(trials));
        confidenceHigh = mean + ((CONFIDENCE_95 * stddev)/Math.sqrt(trials));
    }

    private void validate(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n - " + n + " should be > 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials - " + n + " should be > 0");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHigh;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = "+ percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo()
         + ", " + percolationStats.confidenceHi() + "]");
    }

}