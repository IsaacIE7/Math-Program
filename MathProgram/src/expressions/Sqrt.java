package expressions;

import java.util.Map;

public class Sqrt implements Expression {
    private Expression inner;

    public Sqrt(Expression inner) {
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        if (inner.evaluate(x) < 0) {
            throw new IllegalArgumentException("Cannot take the square root of a negative number");
        }
        return Math.sqrt(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Divide(inner.sDerivative(), new Constant(2.0).multiply(new Sqrt(inner)));
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return Math.sqrt(inner.evaluate(variables));
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return new Divide(inner.sPartialDerivative(varName), new Constant(2.0).multiply(new Sqrt(inner)));
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant c) {
            double value = c.getValue();
            if (value >= 0) {
                if (Math.sqrt(value) == Math.floor(Math.sqrt(value))) {
                    return new Constant((int) Math.sqrt(value));
                }
                return new Constant(Math.sqrt(value));
            } 
        }
        return new Sqrt(inner.simplify());
    }

    public String toString() {
        return "sqrt(" + inner.toString() + ")";
    }

    
    
}
