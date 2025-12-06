import java.util.Arrays;

public class Vector {
    private double[] comps;// stores components, and dimensions are dynamic

    public Vector(double[] comps) {
        // defensive copy so original array can't modify future vector
        this.comps = Arrays.copyOf(comps, comps.length);
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
            for (int i = 0; i < comps.length; i++) {
                result[i] = comps[i] + other.comps[i];
            }
            return new Vector(result);
        }
    }

    public double dot(Vector other){
        if (other.getDimension() != this.getDimension()) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }

        double sum = 0;
        for (int i = 0; i < comps.length; i++) {
            sum += other.comps[i] * this.comps[i]; // multiplies each index(component) by the component of the other
        }

        return sum;
    }

    public double magnitude(){
        double sum = 0;
        for (double c: comps){
            sum += c * c;
        }
        return Math.sqrt(sum);
    }

    public double direction(){ 
        return 0.0;
    }

    public Vector normalize(){//changes magnitude to one while keeping the direction the same
        double mag = magnitude();
        if (mag == 0) {
            throw new ArithmeticException("Cannot normalize the zero vector");
        } else {
            double [] result = new double [comps.length];
            for (int i = 0; i < comps.length; i++) {
                result[i] = comps[i] / mag;
            }
            return new Vector(result);
        }
    }

    public String toString() {
        return Arrays.toString(comps);
    }
}
