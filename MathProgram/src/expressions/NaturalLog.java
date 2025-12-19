package expressions;

public class NaturalLog implements Expression {

    //NEED TO ADD DOMAIN RESTRICTION CHECKS

    
    Expression inner;

    public NaturalLog(Expression inner){
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        return Math.log(inner.evaluate(x));
    }

    @Override
    public Expression sDerivative() {
        return new Multiply(new Divide(new Constant(1.0), inner), inner.sDerivative());
    }

    @Override
    public Expression simplify() {
        if (inner.simplify() instanceof Constant constant){
            return new Constant(Math.log(constant.getValue()));
        }
        return new NaturalLog(inner.simplify());
    }

    @Override
    public String toString(){
        return "ln(" + inner.toString() + ")";
    }

}
