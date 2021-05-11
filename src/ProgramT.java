/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 */

package src;

import java.util.*;

public class ProgramT extends HashSet<CourseT> implements Measures {

    private HashSet<CourseT> s = this;

    public double[] measures() {
        throw new UnsupportedOperationException("Attribute required");
    }

    public double[] measures(IndicatorT ind) {
        throw new UnsupportedOperationException("Attribute required");
    }

    public double[] measures(AttributeT att) {
        double[] measureInd = {0,0,0,0};
        for (CourseT c : s) {
            for (int j = 0; j < 4; j++) {
                measureInd[j] += c.measures(att)[j];
            }
        }
        return Services.normal(measureInd);
    }
}