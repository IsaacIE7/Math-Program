package expressions;

public class Divide implements Expression{
    private final Expression left;
    private final Expression right;

    public Divide(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(double x) {
        return left.evaluate(x) / right.evaluate(x);
    }

    @Override
    public Expression sDerivative() {
        return new Divide(new Add(new Multiply(left.sDerivative(), right), new Multiply(new Constant(-1.0), new Multiply(left, right.sDerivative()))), (new Power(right, 2)));
    }

    @Override
    public Expression simplify(){
        Expression sLeft = left.simplify(), sRight = right.simplify();
        if (sRight instanceof Constant && ((Constant)sRight).getValue() == 0.0) {
            throw new ArithmeticException("Expression includes division by zero.");
        }
        if (sLeft instanceof Constant && ((Constant)sLeft).getValue() == 0.0){//needed to cast sLeft to consant and use getValue to compare to a double
            return new Constant(0);
        } 
        if (sRight instanceof Constant && ((Constant)sRight).getValue() == 1.0){//needed to use instanceof not .equals(); .equals checks if the objects point to same memory
            return sLeft;
        }
        if (sRight instanceof Constant && sLeft instanceof Constant){
            double c1 = ((Constant)sLeft).getValue();
            double c2 = ((Constant)sRight).getValue();
            return new Constant(c1 / c2);
        }
        return new Divide(sLeft, sRight);
    }

    @Override
    public String toString(){
        return "(" + left.toString() + "/" + right.toString() + ")";
    }
}
