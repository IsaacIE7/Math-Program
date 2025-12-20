package expressions;

public class NaturalLog implements Expression {    
    Expression inner;

    public NaturalLog(Expression inner){
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        if (inner.evaluate(x)  <= 0){
            throw new IllegalArgumentException("Natural log undefined for non-positive values.");
        }
        return Math.log(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Multiply(new Divide(new Constant(1.0), inner), inner.sDerivative());
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant constant && constant.getValue() <= 0){
            throw new IllegalArgumentException("Natural log undefined for non-positive values.");
        }
        else if (inner.simplify() instanceof Constant c) {
                return new Constant(Math.log(c.getValue()));
        }

        return new NaturalLog(inner.simplify());
    }

    @Override
    public String toString(){
        return "ln(" + inner.toString() + ")";
    }

}
