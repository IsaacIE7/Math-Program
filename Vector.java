public class Vector {
    private double[] comps;// stores components, and dimensions are dynamic

    public Vector(double[] comps) {

    }

    public int getDimension() {
        return comps.length;
    }

    public Vector scalarMultiply(double val) {
        double[] result = new double[comps.length];
        for (int i = 0; i < comps.length; i++) {
            result[i] = comps[i] * val;
        }

        return new Vector(result);
    }

    public Vector add(Vector other) {

        if (other.getDimension() != this.getDimension()) {
            throw new IllegalArgumentException("Vector dimensions must match");
        } else {
            double[] result = new double[comps.length];
            for (int i = 0; i < other.length; i++) {
                result[i] = comps[i] + other.comps[i];
            }
        }
        return new Vector(result);
    }

    public Vector dot(Vector other){
        if (other.getDimension() != this.getDimension()) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }
            double[] result = new double[comps.length];
            for (int i = 0; i < other.length; i++) {
                result[i] =  other.comps[i] * this.comps[i]; //mulitplies each index(component) by the component of the other
            }
            return Vector(result);
    }

    public double magnitude(Vector v){
        double sum = 0;
        for (double c: comps){
            sum += c*c;
        }
        return Math.sqrt(sum);
    }

    public double direction(Vector v){
        double direction = 0.0;

        
        return 0.0;
    }

    public Vector normalize(Vector v){//changes magnitude to one while keeping the direction the same
        double mag = magnitude(v);
        if (mag == 0) {
        throw new ArithmeticException("Cannot normalize the zero vector");
        } else {
            double [] result = new double [v.comps.length]
            for (double c: comps){
                result[i] = v.comps[i] / mag;
            }
        }
        return Vector(result);
    }

    // public Vector add(Vector other){
    // if (other.length==Vector.length){

    // }
    // return 1;
    // }

    public String toString() {
        return "Magnitude: " + mag +
                "\nDirection(with respect to x): " + Math.toDegrees(direc) +
                "\nx & y components: (" + xComp + ", " + yComp + ")\nPosition: " + pos;
    }
}
