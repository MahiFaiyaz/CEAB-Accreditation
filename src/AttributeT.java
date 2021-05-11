/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 */

package src;

import java.util.HashSet;
import java.util.Set;


public class AttributeT {

    protected String name;
    protected Set<IndicatorT> s;

    public AttributeT(String attribName, IndicatorT[] indicators){
        name = attribName;
        s = new HashSet<>();

        for (IndicatorT indicator : indicators ){
            s.add(indicator);
        }

    }

    public String getName() {
        return name;
    }

    public IndicatorT[] getIndicators() {
        IndicatorT[] indicators = new IndicatorT[s.size()];

        int index = 0;
        for (IndicatorT indicator : s) {
            indicators[index] = indicator;
            index++;
        }
        return indicators;

    }

}