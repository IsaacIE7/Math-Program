package expressions;

public class Sin implements Expression {
    Expression inner;

    public Sin(Expression inner){
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        return Math.sin(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Multiply(new Cos(inner), inner.sDerivative());
    }

    @Override
    public Expression sIntegral() { // no composites yet
        if (inner.simplify() instanceof Variable) {
            return new Cos(new Variable()).multiply(new Constant(-1));
        }
        throw new UnsupportedOperationException("Cannot integrate composites yet");
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant constant){
            return new Constant(Math.sin(constant.getValue()));
        }
        return new Sin(inner.simplify());
    }

    @Override
    public String toString(){
        return "sin(" + inner.toString() + ")";
    }

    
}
