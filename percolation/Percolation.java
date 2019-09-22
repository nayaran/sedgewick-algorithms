/* *****************************************************************************
 *  Name:    Anurag Narayan
 *  NetID:   narayan
 *  Precept: P00
 *
 *  Description:  Programming assignment for coursera algs4 percolation
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // models the underlying grid
    private final WeightedQuickUnionUF grid;

    // stores size of the grid
    private final int size;

    // tracks opened sites
    private final boolean[] openedSites;

    // keeps count of opened sites
    private int openedSitesCount = 0;

    // first dummy site at index 0
    private int firstDummySite;

    // last dummy site at index size+1
    private int lastDummySite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n - " + n + " should be greater than 0!");
        }
        size = n;
        grid = new WeightedQuickUnionUF(size * size + 2);
        openedSites = new boolean[size * size + 2];

        initializeDummySites();
    }

    // initializes dummy sites
    private void initializeDummySites() {
        // Open first dummy site
        firstDummySite = getSite(1, 1) - 1;
        openedSites[firstDummySite] = true;

        // Link first row to first dummy site
        for (int i = 1; i <= size; i++) {
            grid.union(firstDummySite, i);
        }

        // Open last dummy site
        lastDummySite = getSite(size, size) + 1;
        openedSites[lastDummySite] = true;

        // Link first row to first dummy site
        for (int i = (size*size - size) + 1; i <= size*size; i++) {
            grid.union(lastDummySite, i);
        }
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int site = getSite(row, col);

        if (isOpen(row, col)) {
            return;
        }

        // connect the site to its adjacent open sites
        int neighbor;

        // LEFT
        if (col - 1 >= 1) {
            neighbor = getSite(row, col - 1);
            if (openedSites[neighbor]) {
                grid.union(neighbor, site);
            }
        }

        // RIGHT
        if (col + 1 <= size) {
            neighbor = getSite(row, col + 1);
            if (openedSites[neighbor]) {
                grid.union(neighbor, site);
            }
        }

        // DOWN
        if (row - 1 >= 1) {
            neighbor = getSite(row - 1, col);
            if (openedSites[neighbor]) {
                grid.union(neighbor, site);
            }
        }

        // UP
        if (row + 1 <= size) {
            neighbor = getSite(row + 1, col);
            if (openedSites[neighbor]) {
                grid.union(neighbor, site);
            }
        }

        openedSites[site] = true;
        openedSitesCount++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openedSites[getSite(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int site = getSite(row, col);
        return isOpen(row, col) && grid.connected(firstDummySite, site);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        if (size == 1) {
            return isOpen(1, 1);
        }
        else {
            return grid.connected(firstDummySite, lastDummySite);
        }
    }

    // gets the underlying index corresponding to the grid cell
    private int getSite(int row, int col) {
        return ((row-1) * size + (col));
    }

    // validates
    private void validate(int row, int col) {
        if (row < 1 || row > size) {
            throw new IllegalArgumentException("row - " + row + " should be between 1 and " + size);
        }
        if (col < 1 || col > size) {
            throw new IllegalArgumentException("col - " + col + " should be between 1 and " + size);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        /*
        Percolation percolation = new Percolation(4);
        System.out.println("getAdjacentSites(1, 1) - " +
                                   percolation.getAdjacentSites(1, 1));
        System.out.println("getAdjacentSites(1, 4) - " +
                                   percolation.getAdjacentSites(1, 4));
        System.out.println("getAdjacentSites(4, 4) - " +
                                   percolation.getAdjacentSites(4, 4));
        System.out.println("getAdjacentSites(4, 1) - " +
                                   percolation.getAdjacentSites(4, 1));
        System.out.println("open(1,1)");
        percolation.open(1,1);
        System.out.println("isOpen(1, 1) - " + percolation.isOpen(1, 1));
        System.out.println("open(1,2)");
        percolation.open(1,2);
        System.out.println("isOpen(1, 1) - " + percolation.isOpen(1, 1));*/
    }
}