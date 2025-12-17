public class Multiply implements Expression{
    private Expression left, right;

    public Multiply(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public double evaluate(double x){
        return left.evaluate(x) * right.evaluate(x);
    }

    public Expression sDerivative(){
        return new Add( new Multiply(left.sDerivative(), right),new Multiply(left, right.sDerivative()) );
    }

        public Expression simplify(){
        Expression sLeft = left.simplify(), sRight = right.simplify();
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
        if (sLeft instanceof Constant && sRight instanceof Constant){
            double c1 = ((Constant)sLeft).getValue();
            double c2 = ((Constant)sRight).getValue();
            return new Constant(c1 * c2);
        } 
        return new Multiply(sLeft, sRight);
    }

    public String toString(){
        if (left instanceof Constant && right instanceof Variable){//instanceof operator checks if an obj is an instance of a class; it is not a method
            return "" + left.toString() + right.toString();
        }
        if (right instanceof Constant && left instanceof Variable){
            return "" + right.toString() + left.toString();
        }
        return "(" + left.toString() + ")" + "(" + right.toString() + ")";
    }
}
