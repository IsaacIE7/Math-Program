public class Variable implements Expression {
    @Override
    public double evaluate(double x){
        return x;
    }

    public Constant sDerivative(){
        return new Constant(1);
    }

    public String toString(){
        return "x";
    }
}
