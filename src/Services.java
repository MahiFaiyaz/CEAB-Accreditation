/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 */

package src;

public class Services {

    public static double[] normal(double[] v) {
        double sum = sum(v);
        double[] ans = new double[v.length];

        for (int i = 0; i < v.length; i++){
            ans[i] = v[i] / sum;
        }
        return ans;
    }

    private static double sum(double[] v){
        double sum = 0;
        for (double i : v){
            sum += i;
        }
        return sum;
    }
}
