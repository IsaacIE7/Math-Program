package expressions;

import java.util.Map;

public class EulerX implements Expression {
    Expression inner;

    public EulerX(Expression inner){
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        return Math.exp(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Multiply(new EulerX(inner), inner.sDerivative());
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return Math.exp(inner.evaluate(variables));
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return new EulerX(inner).multiply(inner.sPartialDerivative(varName));
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant constant){
            return new Constant(Math.exp(constant.getValue()));
        }
        return new EulerX(inner.simplify());
    }

    @Override
    public String toString(){
        return "e^(" + inner.toString() + ")";
    }

    
}
