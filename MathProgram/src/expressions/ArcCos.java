package expressions;

public class ArcCos implements Expression {
    private Expression inner;

    public ArcCos(Expression inner){
        this.inner = inner;
    }
    @Override
    public double evaluate(double x) {
        return Math.acos(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Divide(new Constant(1.0), new Sqrt(new Constant(1.0).add(inner.power(2).multiply(new Constant(-1.0))))).multiply(new Constant(-1.0)).multiply(inner.sDerivative());
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant c && Math.abs(c.getValue()) > 1){
            throw new IllegalArgumentException("arccos is undefined.");
        }
        return new ArcCos(inner.simplify());
    }

    public String toString(){
        return "arccos(" + inner.toString() + ")";
    }
    
}
