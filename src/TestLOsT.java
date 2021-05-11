/**
 * Author: 
 * Revised: 
 * 
 * Description: 
 */

package src;

import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestLOsT
{

    private LOsT LOs1;
    private LOsT LOs2;
    private LOsT LOs3;
    private LOsT LOs4;
    private LOsT LOs5;
    private IndicatorT ind;
    private AttributeT att;



    @Before
    public void setUp() {

        LOs1 = new LOsT("topic 1", 20, 10, 15, 5);
        LOs2 = new LOsT("topic 2", 92, 68, 93, 83);
        LOs3 = new LOsT("topic 3", 51, 12, 61, 12);
        LOs4 = new LOsT("topic 1", 20, 10, 15, 5);
        LOs5 = new LOsT("topic 1", 50, 30, 25, 35);

        ind = IndicatorT.assumpt;
        IndicatorT[] course1Inds = new IndicatorT[] {IndicatorT.math, IndicatorT.tools, IndicatorT.engInSoc, IndicatorT.ideaGeneration};
        att = new AttributeT("Attribute 1", course1Inds);


    }

    @After
    public void tearDown() {
        LOs1 = null;
        LOs2 = null;
        LOs3 = null;
        LOs4 = null;
        LOs5 = null;
        ind = null;
        att = null;
    }


    @Test
    public void testGetName() {
        assertTrue(LOs1.getName() == "topic 1");
        assertTrue(LOs3.getName() == "topic 3");
    }

    @Test
    public void testEquals() {
        assertTrue(LOs1.equals(LOs4));
        assertTrue(LOs1.equals(LOs5));
        assertFalse(LOs1.equals(LOs2));
    }

    @Test
    public void testMeasuresNLOFalse() {
        Norm.setNLOs(false);
        double[] a1 = LOs1.measures();
        double[] a1e = new double[] {20, 10, 15, 5};
        assertTrue(Arrays.equals(a1,a1e));

        double[] a2 = LOs2.measures();
        double[] a2e = new double[] {92, 68, 93, 83};
        assertTrue(Arrays.equals(a2,a2e));

    }

    @Test
    public void testMeasuresNLOTrue() {
        Norm.setNLOs(true);
        double[] a1 = LOs1.measures();
        double[] a1e = new double[] {0.4, 0.2, 0.3, 0.1};
        assertTrue(Arrays.equals(a1,a1e));

        final double THRESHOLD = 0.0001;

        double[] a2 = LOs2.measures();
        double[] a2e = new double[] {0.27380952381, 0.20238095238, 0.27678571428, 0.24702380952};

        for (int i = 0; i < 4; i++) {
            if (Math.abs(a2[i] - a2e[i]) > THRESHOLD) {
                assertTrue(false);
            }
        }
        assertTrue(true);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMeasuresUnsupportedOperationException() {
        LOs1.measures(ind);
        LOs1.measures(att);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        LOsT LOs6 = new LOsT("topic 6", 0, 0, 0, 0);
        LOsT LOs7 = new LOsT("topic 1", 5, -5, 15, 61);
    }


}
