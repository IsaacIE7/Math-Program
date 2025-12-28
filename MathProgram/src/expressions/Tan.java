package expressions;

import java.util.Map;

public class Tan implements Expression{
    Expression inner;

    public Tan(Expression inner){
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        if (Math.abs(Math.cos(inner.evaluate(x))) < 1e-9){
            throw new IllegalArgumentException("Tan is Undefined at  x = " + x);
        }
        return Math.tan(inner.evaluate(x)); 
    }

    @Override
    public Expression sDerivative() {
        return new Constant(1.0).divide(new Cos(inner).power(2)).multiply(inner.sDerivative());
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return Math.tan(inner.evaluate(variables));
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return new Constant(1.0).divide(new Cos(inner).power(2)).multiply(inner.sPartialDerivative(varName));
    }

    @Override
    public Expression simplify() {
      if (inner.simplify() instanceof Constant constant){
        return new Constant(Math.tan(constant.getValue()));
      }
    return new Tan(inner.simplify());
    }

    @Override
    public String toString(){
        return "tan(" + inner.toString() + ")";
    }

}
