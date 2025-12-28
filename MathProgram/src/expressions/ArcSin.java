package expressions;

import java.util.Map;

public class ArcSin implements Expression {
    private Expression inner;

    public ArcSin(Expression inner){
        this.inner = inner;
    }
    @Override
    public double evaluate(double x) {
        if (Math.abs(inner.evaluate(x)) > 1){
            throw new IllegalArgumentException("arcsin is undefined.");
        }
        return Math.asin(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Divide(new Constant(1.0), new Sqrt(new Constant(1.0).add(inner.power(2).multiply(new Constant(-1.0))))).multiply(inner.sDerivative());
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        if (Math.abs((double)inner.evaluate(variables)) > 1.0){
            throw new IllegalArgumentException("arcsin is undefined.");
        }
        return Math.asin(inner.evaluate(variables));
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return new Divide(new Constant(1.0), new Sqrt(new Constant(1.0).add(inner.power(2).multiply(new Constant(-1.0))))).multiply(inner.sPartialDerivative(varName));
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant c && Math.abs(c.getValue()) > 1){
            throw new IllegalArgumentException("arcsin is undefined.");
        }
        if (inner.simplify() instanceof Constant c && Math.abs(c.getValue()) <= 1){
            return new Constant(Math.asin(c.getValue()));
        }
        return new ArcSin(inner.simplify());
    }

    public String toString(){
        return "arcsin(" + inner.toString() + ")";
    }
    
    
}
