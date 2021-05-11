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

public class TestAttributeT
{
    private AttributeT attribute1;
    private AttributeT attribute2;
    private AttributeT attribute3;


    @Before
    public void setUp() {

        IndicatorT[] course1Inds = new IndicatorT[] {IndicatorT.math, IndicatorT.tools, IndicatorT.engInSoc, IndicatorT.ideaGeneration};
        IndicatorT[] course2Inds = new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.math, IndicatorT.engInSoc, IndicatorT.tools};
        IndicatorT[] course3Inds = new IndicatorT[] {};

        attribute1 = new AttributeT("Attribute 1", course1Inds);
        attribute2 = new AttributeT("Attribute 2", course2Inds);
        attribute3 = new AttributeT("Attribute 3", course3Inds);
    }

    @After
    public void tearDown() {
        attribute1 = null;
        attribute2 = null;
        attribute3 = null;
    }


    @Test
    public void testGetName() {
        assertSame("Attribute 1", attribute1.getName());
        assertSame("Attribute 2", attribute2.getName());
    }

    @Test
    public void testGetIndicators() {
        assertEquals(attribute1.getIndicators().length, attribute2.getIndicators().length);
        for (IndicatorT ind : attribute1.getIndicators()) {
            assertTrue(Arrays.asList(attribute2.getIndicators()).contains(ind));
        }

        assertEquals(0, attribute3.getIndicators().length);
    }

}
