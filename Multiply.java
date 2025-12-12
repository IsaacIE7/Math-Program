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
