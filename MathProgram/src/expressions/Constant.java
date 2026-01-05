package expressions;

import java.util.Map;

public class Constant implements Expression {
    private final double value; 

    public Constant(double val){
        value = (val == 0.0) ? 0.0 : val;
    }
    
    public double getValue() {return value;}

    @Override
    public double evaluate(double x){
        return value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return value;
    }

    @Override
    public Constant sDerivative(){
       return new Constant(0);
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return new Constant(0);
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
