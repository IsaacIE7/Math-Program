package expressions;

import java.util.Map;

public class Multiply implements Expression{
    private final Expression left, right;

    public Multiply(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }
    public Expression getLeft(){
        return left;
    }
    public Expression getRight(){
        return right;
    }

    @Override
    public double evaluate(double x){
        return left.evaluate(x) * right.evaluate(x);
    }

    @Override
    public Expression sDerivative(){
        return new Add( new Multiply(left.sDerivative(), right),new Multiply(left, right.sDerivative()));
    }

    @Override
    public double evaluate(Map<String, Double> vars){
        return left.evaluate(vars) * right.evaluate(vars);
    }

    @Override
    public Expression sPartialDerivative(String varName) {
        return new Add(new Multiply(left.sPartialDerivative(varName), right), new Multiply(left, right.sPartialDerivative(varName)));
    }

    @Override
    public Expression simplify(){
        Expression sLeft = left.simplify(), sRight = right.simplify();
        if (sLeft instanceof Constant && sRight instanceof Constant){
            double c1 = ((Constant)sLeft).getValue();
            double c2 = ((Constant)sRight).getValue();
            return new Constant(c1 * c2);
        } 
        if (sLeft instanceof Constant && ((Constant)sLeft).getValue() == 0.0){//needed to cast sLeft to consant and use getValue to compare to a double
            return new Constant(0);
        } 
        if (sRight instanceof Constant && ((Constant)sRight).getValue() == 0.0){//needed to use instanceof not .equals(); .equals checks if the objects point to same memory
            return new Constant(0);
        }
        if (sLeft instanceof Constant && ((Constant)sLeft).getValue() == 1.0) {
            return sRight;
        }
        if (sRight instanceof Constant && ((Constant)sRight).getValue() == 1.0) {
            return sLeft;
        }
        if (sLeft instanceof Power p1 && sRight instanceof Power p2 && (p1.getBase().toString()).equals(p2.getBase().toString())) {
            return new Power(p1.getBase(), p1.getExp() + p2.getExp());
        }
        if (sLeft.toString().equals(sRight.toString())){
            return new Power(sLeft, 2);
        }
        
        
        return new Multiply(sLeft, sRight);
    }

    @Override
    public String toString(){
        if (left instanceof Constant c && right instanceof Variable){//instanceof operator checks if an obj is an instance of a class; it is not a method
            if (c.getValue() == 1.0){
                return "" + right.toString();
            }
            if (c.getValue() == -1.0){
                return "-" + right.toString();
            }
            return "" + left.toString() + right.toString();
        }
        if (right instanceof Constant && left instanceof Variable){
            return "" + right.toString() + left.toString();
        }
        if (left instanceof Constant c && right instanceof Multiply m && m.getLeft() instanceof Constant c2){
            return "" + (c.getValue() * c2.getValue()) + m.getRight().toString();
        }
        if (left instanceof Multiply m && m.getLeft() instanceof Constant c && right instanceof Constant c2){
            return "" + (c.getValue() * c2.getValue()) + m.getRight().toString();
        }
        if (left instanceof Constant c && right instanceof Power p && p.getBase() instanceof Variable){
            
            return "" + left.toString() + right.toString();
        }
        if (left instanceof Variable && right instanceof Power p && p.getBase() instanceof Variable){
            return "" + left.toString() + right.toString() + "^" + p.getExp();
        }
        return "(" + left.toString() + ")" + "(" + right.toString() + ")";
    }
}
