import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/* *****************************************************************************
 *  Name:    Anurag Narayan
 *  NetID:   narayan
 *  Precept: P00
 *
 *  Description:  Unit tests for Percolation
 **************************************************************************** */

public class PercolationTest {
    private Percolation percolation;

    @Before
    public void prepare() {
        percolation = new Percolation(4);
    }

    @After
    public void clear() {
        percolation = new Percolation(4);
    }


    @Test
    public void testOpenSuccess() {
        percolation.open(1,1);
        Assert.assertTrue(percolation.isOpen(1, 1));

        percolation.open(1,2);
        Assert.assertTrue(percolation.isOpen(1, 2));
    }

    @Test
    public void isOpenTrue() {
        percolation.open(1,1);
        Assert.assertTrue(percolation.isOpen(1, 1));

        percolation.open(1,2);
        Assert.assertTrue(percolation.isOpen(1, 2));
    }

    @Test
    public void isOpenTrueTest2() {
        percolation = new Percolation(6);
        percolation.open(1,6);
        Assert.assertTrue(percolation.isOpen(1, 6));
    }

    @Test
    public void testPercolatesForInput1_cornercase() {
        percolation = new Percolation(1);
        Assert.assertFalse(percolation.percolates());
        percolation.open(1,1);
        Assert.assertTrue(percolation.percolates());
    }


    @Test
    public void testIsFullForinput2() {
        percolation = new Percolation(2);
        percolation.open(1,1);
        percolation.open(2,2);
        Assert.assertFalse(percolation.isFull(2,2));
    }

    @Test
    public void testIsFullForinput8() {
        percolation = new Percolation(8);
        percolation.open(1,3);
        Assert.assertFalse(percolation.isFull(1,1));
    }
    @Test
    public void testIsOpenAndIsFullAreConsistentWhenZeroSitesAreOpen() {
        percolation = new Percolation(6);
        Assert.assertFalse(percolation.isOpen(1,1));
        Assert.assertFalse(percolation.isFull(1,1));
    }

    @Test
    public void testPercolatesForinput2() {
        percolation = new Percolation(2);
        percolation.open(1,1);
        percolation.open(2,2);
        Assert.assertFalse(percolation.percolates());
    }

    @Test
    public void testIsFullForInput6() {
        percolation = new Percolation(6);
        percolation.open(1,6);
        Assert.assertTrue(percolation.isFull(1, 6));
    }

    @Test
    public void isOpenFalse() {
        Assert.assertFalse(percolation.isOpen(1, 1));
    }

    @Test
    public void testNumberOfOpenSites() {
        percolation = new Percolation(6);
        Assert.assertEquals(0, percolation.numberOfOpenSites());
        percolation.open(1,6);
        Assert.assertEquals(1, percolation.numberOfOpenSites());
    }

    @Test
    public void testNumberOfOpenSitesWhenOpeningAlreadyOpenedSite() {
        percolation = new Percolation(6);
        percolation.open(1,1);
        Assert.assertEquals(1, percolation.numberOfOpenSites());

        percolation.open(1,1);
        Assert.assertEquals(1, percolation.numberOfOpenSites());
    }

    @Test
    public void numberOfOpenSites() {
        Assert.assertEquals(0, percolation.numberOfOpenSites());
        percolation.open(1,1);
        percolation.open(1,2);
        Assert.assertEquals(2, percolation.numberOfOpenSites());
    }

    @Test
    public void testPercolatesFailure() {
        Assert.assertFalse(percolation.percolates());
    }

    @Test
    public void testPercolatesSuccess() {
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,1);
        percolation.open(4,1);
        Assert.assertTrue(percolation.percolates());
    }
}