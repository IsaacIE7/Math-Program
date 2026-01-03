package calculus;

public class ExtremaInfo {
    public enum Crit {
        MAX, MIN, INF
    }

    private final double x;
    private final double val;
    private final Crit type;

    public ExtremaInfo(double x, double val, Crit type){
        this.x = x;
        this.val = val;
        this.type = type;
    }

    

    public double getVal() {
        return val;
    }

    public Crit getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public String toString(){
        return "" + type  + " at (" + x + "," + val + ")";
    }

    

}
