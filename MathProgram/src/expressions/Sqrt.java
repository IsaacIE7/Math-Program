package expressions;

public class Sqrt implements Expression {
    private Expression inner;

    public Sqrt(Expression inner) {
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        return Math.sqrt(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Divide(inner.sDerivative(), new Constant(2.0).multiply(new Sqrt(inner)));
    }

    @Override
    public Expression sIntegral() {
        if (inner.simplify() instanceof Variable) {
            return new Multiply(new Constant(2).divide(new Constant(3)), new Power(new Variable(), 3 / 2));
        }
        throw new UnsupportedOperationException("Cannot integrate composites yet");
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
            } else {
                throw new IllegalArgumentException("Cannot take the square root of a negative number");
            }
        } else {
            return new Sqrt(inner.simplify());
        }
    }

    public String toString() {
        return "sqrt(" + inner.toString() + ")";
    }
    
}
