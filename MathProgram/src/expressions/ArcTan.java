package expressions;

public class ArcTan implements Expression {
    private final Expression inner;

    public ArcTan(Expression inner) {
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        return Math.atan(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Divide(new Constant(1.0), new Constant(1).add(inner.power(2))).multiply(inner.sDerivative());
    }

    @Override
    public Expression simplify() {
       if (inner.simplify() instanceof Constant c){
        return new Constant(Math.atan(c.getValue()));
       }
       return new ArcTan(inner.simplify());
    }

    @Override
    public String toString(){
        return "arctan(" + inner.toString() + ")";
    }
    
}
