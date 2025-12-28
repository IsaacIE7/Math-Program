package expressions;

import java.util.Map;

public class ArcCos implements Expression {
    private Expression inner;

    public ArcCos(Expression inner){
        this.inner = inner;
    }
    @Override
    public double evaluate(double x) {
        if (Math.abs(inner.evaluate(x)) > 1){
            throw new IllegalArgumentException("arccos is undefined.");
        }
        return Math.acos(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Divide(new Constant(1.0), new Sqrt(new Constant(1.0).add(inner.power(2).multiply(new Constant(-1.0))))).multiply(new Constant(-1.0)).multiply(inner.sDerivative());
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        if (Math.abs((double)inner.evaluate(variables)) > 1){
            throw new IllegalArgumentException("arccos is undefined.");
        }
        return Math.acos(inner.evaluate(variables));
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return new Divide(new Constant(1.0), new Sqrt(new Constant(1.0).add(inner.power(2).multiply(new Constant(-1.0))))).multiply(new Constant(-1.0)).multiply(inner.sPartialDerivative(varName));
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant c && Math.abs(c.getValue()) > 1){
            throw new IllegalArgumentException("arccos is undefined.");
        }
        if (inner.simplify() instanceof Constant c && Math.abs(c.getValue()) <= 1){
            return new Constant(Math.acos(c.getValue()));
        }
        return new ArcCos(inner.simplify());
    }

    public String toString(){
        return "arccos(" + inner.toString() + ")";
    }
    
}
