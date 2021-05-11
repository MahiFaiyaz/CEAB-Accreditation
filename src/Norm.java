/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 */

package src;

public class Norm{

    protected static Boolean normLOs;
    protected static Boolean normInd;
    protected static Boolean normAtt;

    public static void setNorms(Boolean nLOs, Boolean nInd, Boolean nAtt){
        normLOs = nLOs;
        normInd = nInd;
        normAtt = nAtt;
    }

    public static Boolean getNLOs(){
        return normLOs;
    }

    public static Boolean getNInd(){
        return normInd;
    }

    public static Boolean getNAtt(){
        return normAtt;
    }

    public static void setNLOs(Boolean nLOs){
        normLOs = nLOs;
    }

    public static void setNInd(Boolean nInd){
        normInd = nInd;
    }

    public static void setNAtt(Boolean nAtt){
        normAtt = nAtt;
    }
}
