

public class Power implements Expression{     
    //limited to integer exponents and chain rule for derivative; still need to add const^x and its deriv rule
    private int exp;
    private Expression base;
        
    public Power(Expression b, int exp){
        base = b;
        this.exp = exp;
    }

    public double evaluate(double x){
        return Math.pow(base.evaluate(x), exp);
    }

    public Expression sDerivative(){
        return new Multiply(base.sDerivative(), new Multiply(new Constant(exp), new Power(base, exp - 1)));
    }

    public String toString(){
        return exp != 1 ? "" + (base.sDerivative().toString().equals(new Constant(1).toString())? "(" + base + ")" + "^" + exp : "("  +base + ")" +  "^" + exp + " * (" + base.sDerivative() + ")") : "" + base;// should probably just put in for loop, readability
    }
}
