/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 * Description: CourseT class
 */

package src;

import java.util.*;

/**
 * @brief An ADT that represents a Course
 * @details The course is represented by a name and a set MapInd2LOsT which maps each indicator
 * given to a set of LOs
 */

public class CourseT implements Measures {

    /**
     * @brief A private class MapInd2LOsT, which requires an Indicator and a set of
     * LOs associated with the given indicator.
     */
    private class MapInd2LOsT {
        private IndicatorT ind;
        private Set<LOsT> LOs;

        /**
         * Initilaizes A MapInd2LOsT object
         * @param indicator An Indicator object.
         * @param Ls Set of LOs associated with the given indicator
         */
        private MapInd2LOsT(IndicatorT indicator, Set<LOsT> Ls) {
            ind = indicator;
            LOs = Ls;
        }
    }

    protected String name;
    protected Set<MapInd2LOsT> m;

    /**
     * @brief Initializes a course object
     * @param courseName The name of the course
     * @param indicators The indicators associated with the given course
     */
    public CourseT(String courseName, IndicatorT[] indicators){
        name = courseName;
        m = new HashSet<>();

        for (IndicatorT indicator : indicators) {
            m.add(new MapInd2LOsT(indicator, new HashSet<>()));
        }
    }

    /**
     * @brief Gets the name of the course
     * @return The name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Gets the indicators associated with the course
     * @return An array of indicators associated with the course.
     */
    public IndicatorT[] getIndicators() {
        IndicatorT[] indicators = new IndicatorT[m.size()];

        int i = 0;
        for (MapInd2LOsT s : m) {
            indicators[i] = s.ind;
            i ++;
        }
        return indicators;
    }

    /**
     * @brief Gets the LOs associated with a given indicator in the course
     * @param indicator An indicator associated with the course
     * @return An array of LOs that are associated with the given indicator
     */
    public LOsT[] getLOs(IndicatorT indicator) {
        LOsT[] ans = new LOsT[0];
        for (MapInd2LOsT s : m) {
            if (s.ind == indicator) {
                ans = set_to_seq(s.LOs);
                return ans;
            }
        }
        return ans;
    }

    /**
     * @brief Adds an LO to the set of LOs associated with the given indicator, as long as
     * the indicator is associated with the course.
     * @param indicator An indicator associated with the course
     * @param outcome An LO to be added to the set of LOs associated with the given indicator
     */
    public void addLO(IndicatorT indicator, LOsT outcome) {

        for (MapInd2LOsT s : m) {
            if (s.ind == indicator) {
                s.LOs.add(outcome);
            }
        }
    }

    /**
     * @brief Deletes an LO from the set of LOs associated with the given indicator, as long as
     * the indicator is associated with the course.
     * @param indicator An indicator associated with the course
     * @param outcome An LO to be deleted from the set of LOs associated with the given indicator
     */
    public void delLO(IndicatorT indicator, LOsT outcome) {

        for (MapInd2LOsT s : m) {
            if (s.ind == indicator) {
                s.LOs.remove(outcome);
            }
        }
    }

    /**
     * @brief A method to check if a given indicator and its associated LOs are a member of the course
     * @param indicator An indicator
     * @param outcomes A set of LOs associated with the given indicator
     * @return True if the indicator and its associated LOs are a member of the course. Else returns False
     */
    public Boolean member(IndicatorT indicator, LOsT[] outcomes) {

        int i = 0;

        for (MapInd2LOsT s : m){
            if (s.ind == indicator){
                if (outcomes.length != s.LOs.size()) {
                    return false;
                }
                for (LOsT x: s.LOs){
                    for (LOsT y : outcomes) {
                        if (x.equals(y)) {
                            i++;
                        }
                    }
                }
            }
        }

        return (i == outcomes.length);
    }

    /**
     * @brief Aggregates number of students that are below, marginal, meet, or exceed expectations.
     * @throws UnsupportedOperationException
     */
    public double[] measures() {
        throw new UnsupportedOperationException("Requires indicator or attribute");
    }

    /**
     * @brief Aggregates the number of students that are below, marginal, meet, or exceed expectations for a given
     * indicator in the course.
     * @param ind An indicator associated with the course
     * @return A list of normal or total number of students that are below, marginal, meet, or exceed
     * expectations for the given indicator.
     */
    public double[] measures(IndicatorT ind){
        double[] measureInd = new double[]{0,0,0,0};
        if (this.getLOs(ind).length == 0) {
            return (measureInd);
        }
        else {
            for (LOsT o : this.getLOs(ind)) {
                for (int i = 0; i < 4; i++) {
                    measureInd[i] += o.measures()[i];
                }
            }
            if (Norm.getNInd()) {
                return Services.normal(measureInd);
            }
            else {
                return measureInd;
            }
        }
    }

    /**
     * @brief Aggregates the number of students that are below, marginal, meet, or exceed expectations for a given
     * attribute.
     * @param att An attribute associated with a set of indicators
     * @return A list of normal or total number of students that are below, marginal, meet, or exceed
     * expectations for the indicators in the given attribute.
     */
    public double[] measures(AttributeT att){
        double[] measureInd = new double[]{0,0,0,0};
        if (att.getIndicators().equals(new IndicatorT[0])) {
            return (measureInd);
        }
        else {
            for (IndicatorT i : att.getIndicators()) {
                for (int j = 0; j < 4; j++) {
                    measureInd[j] += this.measures(i)[j];
                }
            }
            if (Norm.getNAtt()) {
                return Services.normal(measureInd);
            }
            else {
                return measureInd;
            }
        }
    }

    /**
     * @brief Private method to convert a set of LOs into a sequence
     * @param s A set of LOs
     * @return An array of LOs
     */
    private LOsT[] set_to_seq(Set<LOsT> s) {
        LOsT[] LOsTSeq = new LOsT[s.size()];

        int i = 0;
        for (LOsT x: s) {
            LOsTSeq[i] = x;
            i++;
        }
        return LOsTSeq;

    }
}