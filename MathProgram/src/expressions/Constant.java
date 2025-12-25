package expressions;

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
    @Override
    public Constant sDerivative(){
       return new Constant(0);
    }

    @Override
    public Expression sIntegral() {
        return new Multiply(new Constant(value), new Variable());
    }

    @Override
    public Expression simplify(){
        return this;
    }

    @Override
    public String toString(){
        return Double.toString(value);
    }
    
}
