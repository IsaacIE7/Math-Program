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
}
