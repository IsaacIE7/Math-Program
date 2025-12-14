public class Constant implements Expression {
    private final double value; 

    public Constant(double val){
        value = val;
    }
    public double getValue() {return value;}

    @Override
    public double evaluate(double x){
        return value;
    }

    public Constant sDerivative(){
       return new Constant(0);
    }

    public Expression simplify(){
        return this;
    }

    public String toString(){
        return Double.toString(value);
    }
}
