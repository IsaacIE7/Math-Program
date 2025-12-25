package expressions;

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
    public Expression sIntegral() {
        if (inner.simplify() instanceof Variable){
            return new EulerX(inner.simplify());
        }
        throw new UnsupportedOperationException("Cannot integrate composites yet");
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
