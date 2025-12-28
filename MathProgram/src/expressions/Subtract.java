package expressions;

import java.util.Map;

public class Subtract implements Expression {
    private final Expression left, right;

    public Subtract(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(double x){
        return left.evaluate(x) - right.evaluate(x);
    }

    @Override
    public Subtract sDerivative(){// returns new add because there are still a left and right being added(left prime and right prime)
        return new Subtract(left.sDerivative(), right.sDerivative());//A method that returns Add can override a method that returns Expr
                                                                //This is called covariant return types.
    }

    @Override
    public double evaluate(Map<String, Double> vars){
        return left.evaluate(vars) - right.evaluate(vars);
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return left.sPartialDerivative(varName).subtract(right.sPartialDerivative(varName)); 
    }

    @Override
    public Expression simplify(){
        Expression sLeft = left.simplify(), sRight = right.simplify();
        if (sLeft instanceof Constant && ((Constant)sLeft).getValue() == 0.0){//needed to cast sLeft to consant and use getValue to compare to a double
            return sRight.multiply(new Constant(-1.0));
        } 
        if (sRight instanceof Constant && ((Constant)sRight).getValue() == 0.0){//needed to use instanceof not .equals(); .equals checks if the objects point to same memory
            return sLeft;
        }
        if (sLeft instanceof Constant && sRight instanceof Constant){
            double c1 = ((Constant)sLeft).getValue();
            double c2 = ((Constant)sRight).getValue();
            return new Constant(c1 - c2);
        } 
        return new Subtract(sLeft, sRight);
    }

    @Override
    public String toString(){
        // return ("(" + left.toString() + " - " +  right.toString() + ")");//uses expressions unique toString
                return left.toString() + " - " +  right.toString();//uses expressions unique toString

    }
}
