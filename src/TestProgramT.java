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

public class TestProgramT
{
    private ProgramT program;

    @Before
    public void setup() {
        IndicatorT[] course1Inds = new IndicatorT[]{IndicatorT.math, IndicatorT.tools, IndicatorT.engInSoc, IndicatorT.ideaGeneration};
        IndicatorT[] course2Inds = new IndicatorT[]{IndicatorT.assumpt, IndicatorT.desProcess, IndicatorT.awarePEO};

        CourseT course1 = new CourseT("Course 1", course1Inds);
        CourseT course2 = new CourseT("Course 2", course2Inds);

        LOsT LOs1 = new LOsT("topic 1", 29, 37, 39, 53);
        LOsT LOs3 = new LOsT("topic 3", 61, 83, 71, 27);
        LOsT LOs4 = new LOsT("topic 4", 17, 82, 16, 73);

        course1.addLO(IndicatorT.engInSoc, LOs1);
        course1.addLO(IndicatorT.math, LOs3);
        course1.addLO(IndicatorT.tools, LOs4);
        course2.addLO(IndicatorT.assumpt, LOs1);

        program = new ProgramT();
        program.add(course1);
        program.add(course2);
    }

    @After
    public void tearDown() {
        program = null;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMeasuresUnsupportedOperationException() {
        program.measures();
        program.measures(IndicatorT.engInSoc);
    }

    @Test
    public void testMeasures() {
        Norm.setNorms(false, false, false);

        IndicatorT[] Inds = new IndicatorT[]{IndicatorT.math, IndicatorT.awarePEO};
        AttributeT attribute = new AttributeT("Attribute", Inds);

        final double THRESHOLD = 0.0001;


        double[] a2 = program.measures(attribute);
        double[] a2e = new double[] {0.252066115702479, 0.342975206611570, 0.293388429752066, 0.11157024793388};

        for (int i = 0; i < 4; i++) {
            if (Math.abs(a2[i] - a2e[i]) > THRESHOLD) {
                assertTrue(false);
            }
        }
        assertTrue(true);

    }


}
