package expressions;

public class Add implements Expression {
    private final Expression left, right;

    public Add(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(double x){
        return left.evaluate(x) + right.evaluate(x);
    }

    @Override
    public Add sDerivative(){// returns new add because there are still a left and right being added(left prime and right prime)
        return new Add(left.sDerivative(), right.sDerivative());//A method that returns Add can override a method that returns Expr
                                                                //This is called covariant return types.
    }

    @Override
    public Expression sIntegral(){// returns new add because there are still a left and right being
        return new Add(left.sIntegral(), right.sIntegral());
    }
    
    @Override
    public Expression simplify(){
        Expression sLeft = left.simplify(), sRight = right.simplify();


        if (sLeft instanceof Variable){//check if terms are lone variable x, if yes then make them a new Multiply by 1
            sLeft = new Multiply(new Constant(1), new Variable());
        }
        if (sRight instanceof Variable){
            sRight = new Multiply(new Constant(1), new Variable());
        }
        if (sLeft instanceof Power){//check if terms are lone variable x to an exp, if yes then make them a new Multiply by 1
            sLeft = new Multiply(new Constant(1), sLeft);
        }
        if (sRight instanceof Power){
            sRight = new Multiply(new Constant(1), sRight);
        }




        if (sLeft instanceof Constant && ((Constant)sLeft).getValue() == 0.0){//needed to cast sLeft to consant and use getValue to compare to a double
            return sRight;
        } 

        if (sRight instanceof Constant && ((Constant)sRight).getValue() == 0.0){//needed to use instanceof not .equals(); .equals checks if the objects point to same memory
            return sLeft;
        }

        if (sLeft instanceof Constant && sRight instanceof Constant){
            double c1 = ((Constant)sLeft).getValue();
            double c2 = ((Constant)sRight).getValue();
            return new Constant(c1 + c2);
        } 

        if (sLeft instanceof Multiply m && sRight instanceof Multiply m2){
            if (m.getLeft() instanceof  Constant c && m2.getLeft() instanceof Constant c2){
                if (m.getRight() instanceof Variable && m2.getRight() instanceof Variable) {
                    return new Multiply(new Constant(c.getValue() + c2.getValue()), m2.getRight()).simplify();
                }

                if (m.getRight() instanceof Power p && m2.getRight() instanceof Power p2 && p.getBase().toString().equals(p2.getBase().toString())  && p.getExp() == p2.getExp() ) {
                    return new Multiply(new Constant(c.getValue() + c2.getValue()), p2).simplify();
                }
            }
        }

        return new Add(sLeft, sRight);
    }

    @Override
    public String toString(){
        // return ("(" + left.toString() + " + " +  right.toString() + ")");//uses expressions unique toString
                return left.toString() + " + " +  right.toString();//uses expressions unique toString

    }
}
