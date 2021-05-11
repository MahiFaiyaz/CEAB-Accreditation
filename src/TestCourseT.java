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

public class TestCourseT {
    private CourseT course1;
    private CourseT course2;
    private LOsT LOs3;
    private LOsT LOs4;


    @Before
    public void setUp() {
        IndicatorT[] course1Inds = new IndicatorT[]{IndicatorT.math, IndicatorT.tools, IndicatorT.engInSoc, IndicatorT.ideaGeneration};
        IndicatorT[] course2Inds = new IndicatorT[]{IndicatorT.ideaGeneration, IndicatorT.math, IndicatorT.engInSoc, IndicatorT.tools};

        course1 = new CourseT("Course 1", course1Inds);
        course2 = new CourseT("Course 2", course2Inds);

        LOsT LOs3 = new LOsT("topic 3", 61, 83, 71, 27);
        LOsT LOs4 = new LOsT("topic 4", 17, 82, 16, 73);

        course1.addLO(IndicatorT.math, LOs3);
        course1.addLO(IndicatorT.tools, LOs4);
    }

    @After
    public void tearDown() {
        course1 = null;
        course2 = null;
    }

    @Test
    public void testGetName() {
        assertTrue(course1.getName() == "Course 1");
        assertTrue(course2.getName() == "Course 2");

    }

    @Test
    public void testGetIndicators() {
        assertTrue(course1.getIndicators().length == course2.getIndicators().length);
        for (IndicatorT ind : course1.getIndicators()) {
            assertTrue(Arrays.asList(course2.getIndicators()).contains(ind));
        }
    }

    @Test
    public void testAddAndGetLO() {
        LOsT LOs1 = new LOsT("topic 1", 20, 10, 15, 5);
        LOsT LOs2 = new LOsT("topic 2", 92, 68, 93, 83);


        course1.addLO(IndicatorT.ideaGeneration, LOs1);
        course2.addLO(IndicatorT.engInSoc, LOs2);

        assertTrue(course1.getLOs(IndicatorT.math).length == 1);
        assertTrue(course1.getLOs(IndicatorT.math)[0].getName() == "topic 3");
        assertTrue(course1.getLOs(IndicatorT.ideaGeneration)[0].getName() == "topic 1");
        assertTrue(course1.getLOs(IndicatorT.tools)[0].getName() == "topic 4");
        assertTrue(course2.getLOs(IndicatorT.engInSoc)[0].getName() == "topic 2");

        Norm.setNLOs(true);
        double[] a1 = course1.getLOs(IndicatorT.ideaGeneration)[0].measures();
        double[] a1e = new double[]{0.4, 0.2, 0.3, 0.1};
        assertTrue(Arrays.equals(a1, a1e));
    }

    @Test
    public void testDelLO() {
        LOsT LOs1 = new LOsT("topic 1", 20, 10, 15, 5);
        course1.addLO(IndicatorT.math, LOs1);
        assertTrue(course1.getLOs(IndicatorT.math).length == 2);
        course1.delLO(IndicatorT.math, LOs1);
        assertTrue(course1.getLOs(IndicatorT.math).length == 1);
    }

    @Test
    public void testMember() {
        LOsT LOs1 = new LOsT("topic 3", 61, 83, 71, 27);
        LOsT LOs2 = new LOsT("topic 5", 63, 34, 31, 97);
        course1.addLO(IndicatorT.math, LOs2);

        LOsT LOs5 = new LOsT("topic 5", 63, 34, 31, 97);
        LOsT LOs6 = new LOsT("topic 6", 23, 38, 21, 52);


        assertTrue(course1.member(IndicatorT.math, new LOsT[]{LOs1, LOs5}));
        assertFalse(course1.member(IndicatorT.math, new LOsT[]{LOs2}));
        assertFalse(course1.member(IndicatorT.math, new LOsT[]{LOs6}));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMeasuresUnsupportedOperationException() {
        course1.measures();
        course2.measures();
    }

    @Test
    public void testMeasuresNIndFalse() {
        Norm.setNInd(false);
        double[] a1 = course1.measures(IndicatorT.engInSoc);
        double[] a1e = new double[]{0, 0, 0, 0};

        assertTrue(Arrays.equals(a1, a1e));

        LOsT LOs1 = new LOsT("topic 1", 20, 10, 15, 5);
        course1.addLO(IndicatorT.engInSoc, LOs1);

        Norm.setNLOs(true);
        double[] a2 = course1.measures(IndicatorT.engInSoc);
        double[] a2e = new double[] {0.4, 0.2, 0.3, 0.1};

        assertTrue(Arrays.equals(a2,a2e));

        Norm.setNLOs(false);
        double[] a3 = course1.measures(IndicatorT.engInSoc);
        double[] a3e = new double[] {20, 10, 15, 5};

        assertTrue(Arrays.equals(a3,a3e));

    }

    @Test
    public void testMeasuresNIndTrue() {
        Norm.setNInd(true);
        double[] a1 = course1.measures(IndicatorT.engInSoc);
        double[] a1e = new double[]{0, 0, 0, 0};
        assertTrue(Arrays.equals(a1, a1e));

        LOsT LOs1 = new LOsT("topic 1", 20, 10, 15, 5);
        course1.addLO(IndicatorT.engInSoc, LOs1);

        Norm.setNLOs(true);
        double[] a2 = course1.measures(IndicatorT.engInSoc);
        double[] a2e = new double[] {0.4, 0.2, 0.3, 0.1};

        final double THRESHOLD = 0.0001;
        for (int i = 0; i < 4; i++) {
            if (Math.abs(a2[i] - a2e[i]) > THRESHOLD) {
                assertTrue(false);
            }
        }
        assertTrue(true);

        Norm.setNLOs(false);
        double[] a3 = course1.measures(IndicatorT.engInSoc);
        double[] a3e = new double[] {0.4, 0.2, 0.3, 0.1};

        assertTrue(Arrays.equals(a3,a3e));
    }

    @Test
    public void testMeasuresNAttFalse() {
        Norm.setNorms(false, false, false);
        IndicatorT[] course0Inds = new IndicatorT[]{};
        AttributeT attribute0 = new AttributeT("Attribute 1", course0Inds);


        IndicatorT[] course3Inds = new IndicatorT[]{IndicatorT.math, IndicatorT.engInSoc,};
        AttributeT attribute3 = new AttributeT("Attribute 3", course3Inds);

        double[] a1 = course1.measures(attribute0);
        double[] a1e = new double[]{0, 0, 0, 0};

        assertTrue(Arrays.equals(a1, a1e));


        LOsT LOs1 = new LOsT("topic 1", 29, 37, 39, 53);
        course1.addLO(IndicatorT.engInSoc, LOs1);

        double[] a2 = course1.measures(attribute3);
        double[] a2e = new double[] {90, 120, 110, 80};

        assertTrue(Arrays.equals(a2,a2e));
    }

    @Test
    public void testMeasuresNAttTrue() {
        Norm.setNorms(false, false, true);

        IndicatorT[] course3Inds = new IndicatorT[]{IndicatorT.math, IndicatorT.engInSoc,};
        AttributeT attribute3 = new AttributeT("Attribute 3", course3Inds);


        LOsT LOs1 = new LOsT("topic 1", 29, 37, 39, 53);
        course1.addLO(IndicatorT.engInSoc, LOs1);

        double[] a2 = course1.measures(attribute3);
        double[] a2e = new double[] {0.225, 0.3, 0.275, 0.2};

        assertTrue(Arrays.equals(a2,a2e));
    }
}

