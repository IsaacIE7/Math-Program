package expressions;

public class Power implements Expression{
    //limited to integer exponents and chain rule for derivative; still need to add const^x and its deriv rule
    private final int exp;
    private final Expression base;
        
    public Power(Expression b, int exp){
        base = b;
        this.exp = exp;
    }

    @Override
    public double evaluate(double x){
        return Math.pow(base.evaluate(x), exp);
    }

    @Override
    public Expression sDerivative(){
        return new Multiply(base.sDerivative(), new Multiply(new Constant(exp), new Power(base, exp - 1)));
    }

    @Override
    public Expression simplify(){
        Expression simplifiedBase = base.simplify();
        if (exp == 0) return new Constant(1);
        if (exp == 1) return simplifiedBase;
        if (base instanceof Constant constantBase) {
            double baseValue = constantBase.getValue();
            double result = Math.pow(baseValue, exp);
            return new Constant(result);
        }
        return new Power(simplifiedBase, exp);

    }

    @Override
    public String toString(){
        //return exp != 1 ? "" + (base.sDerivative().toString().equals(new Constant(1).toString())? "(" + base + ")" + "^" + exp : "("  +base + ")" +  "^" + exp + " * (" + base.sDerivative() + ")") : "" + base;// should probably just put in if, readability
        return "(" + base.toString() + ")" + "^" + exp;
    }

    public int getExp() {
        return exp;
    }

    public Expression getBase() {
        return base;
    }
}
