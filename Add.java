public class Add implements Expression {
    private Expression left, right;

    public Add(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public double evaluate(double x){
        return left.evaluate(x) + right.evaluate(x);
    }

    public Add sDerivative(){// returns new add because there are still a left and right being added(left prime and right prime)
        return new Add(left.sDerivative(), right.sDerivative());//A method that returns Add can override a method that returns Expr
                                                                //This is called covariant return types.
    }

    public String toString(){
        return ("(" + left.toString() + " + " +  right.toString() + ")");//uses expressions unique toString
    }
}
