/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 */

package src;

public class LOsT implements Measures {

    protected String name;
    protected int n_blw;
    protected int n_mrg;
    protected int n_mts;
    protected int n_exc;

    public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc) {

        if (nblw < 0 || nmrg < 0 || nmts < 0 || nexc < 0) {
            throw new IllegalArgumentException("Cannot have negative number of students");
        }

        if (nblw == 0 && nmrg == 0 && nmts ==  0 && nexc == 0) {
            throw new IllegalArgumentException("Total number of students cannot be zero");
        }

        name = topic;
        n_blw = nblw;
        n_mrg = nmrg;
        n_mts = nmts;
        n_exc = nexc;
    }

    public String getName() {
        return name;
    }

    public Boolean equals(LOsT o) {
        return name.equals(o.getName());
    }

    public double[] measures(){

        double[] arr = {n_blw, n_mrg, n_mts, n_exc};

        if (!Norm.getNLOs()) {
            return arr;
        }
        else {
            return Services.normal(arr);
        }

    }

    public double[] measures(IndicatorT ind){
        throw new UnsupportedOperationException("Does not take any input");
    }

    public double[] measures(AttributeT att){
        throw new UnsupportedOperationException("Does not take any input");
    }


}