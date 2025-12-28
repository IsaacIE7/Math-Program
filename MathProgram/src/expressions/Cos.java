package expressions;

import java.util.Map;

public class Cos implements Expression {
    Expression inner;

    public Cos(Expression inner){
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        return Math.cos(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Multiply(new Multiply(new Constant(-1.0), new Sin(inner)), inner.sDerivative());
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return Math.cos(inner.evaluate(variables));
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return new Multiply(new Sin(inner).multiply(new Constant(-1)), inner.sPartialDerivative(varName));
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant constant){
            return new Constant(Math.cos(constant.getValue()));
        }
        return new Cos(inner.simplify());
    }

    @Override
    public String toString(){
        return "cos(" + inner.toString() + ")";
    }
}
