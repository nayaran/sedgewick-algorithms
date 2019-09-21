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
    // constant representing the number of neighbors
    private static final int NEIGHBORS = 4;

    // models the underlying grid
    private final WeightedQuickUnionUF grid;

    // stores size of the grid
    private final int size;

    // tracks opened sites
    private final boolean[] openedSites;

    // keeps count of opened sites
    private int openedSitesCount;

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

        // connect the site to its adjacent open sites
        int[] adjOpenSites = getAdjacentOpenSites(row, col);
        int site = getSite(row, col);

        for (int adjOpenSite : adjOpenSites) {
            if (adjOpenSite != -1) {
                grid.union(adjOpenSite, site);
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

    // retrieves adjacent open sites
    private int[] getAdjacentOpenSites(int row, int col) {
        int[] adjOpenSites = new int[NEIGHBORS];

        int[] adjSites = getAdjacentSites(row, col);

        for (int i = 0; i < adjSites.length; i++) {
            int adjSite = adjSites[i];
            if (adjSite != -1 && openedSites[adjSite]) {
                adjOpenSites[i] = adjSite;
            }
            else {
                adjOpenSites[i] = -1;
            }

        }
        return adjOpenSites;
    }

    // retrieves adjacent sites
    private int[] getAdjacentSites(int row, int col) {
        validate(row, col);
        int[] adjacentSites = new int[NEIGHBORS];
        // UP
        if (col - 1 >= 1) {
            adjacentSites[0] = getSite(row, col - 1);
        }
        else {
            adjacentSites[0] = -1;
        }
        // DOWN
        if (col + 1 <= size) {
            adjacentSites[1] = getSite(row, col + 1);
        }
        else {
            adjacentSites[1] = -1;
        }
        // LEFT
        if (row - 1 >= 1) {
            adjacentSites[2] = getSite(row - 1, col);
        }
        else {
            adjacentSites[2] = -1;
        }
        // RIGHT
        if (row + 1 <= size) {
            adjacentSites[3] = getSite(row + 1, col);
        }
        else {
            adjacentSites[3] = -1;
        }
        return adjacentSites;
    }

    // gets the underlying index corresponding to the grid cell
    private int getSite(int row, int col) {
        return ((col-1) * size + (row));
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