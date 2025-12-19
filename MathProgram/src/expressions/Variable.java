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
    public Expression simplify(){
        return this;
    }

    @Override
    public String toString(){
        return "x";
    }
}
