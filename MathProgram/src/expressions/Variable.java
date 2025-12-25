package expressions;

public class Variable implements Expression {
    @Override
    public double evaluate(double x){
        return x;
    }
    @Override
    public Constant sDerivative(){
        return new Constant(1);
    }

    @Override
    public Expression sIntegral(){
        return new Multiply(new Constant(1).divide(new Constant(2)), new Power(new Variable(), 2));
    }

    @Override
    public Expression simplify(){
        return this;
    }

    @Override
    public String toString(){
        return "x";
    }
}
