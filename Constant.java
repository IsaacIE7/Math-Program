public class Constant implements Expression {
    private double value; 

    public Constant(double val){
        value = val;
    }

    @Override
    public double evaluate(double x){
        return value;
    }

    public Constant sDerivative(){
       return new Constant(0);
    }

    public String toString(){
        return "" + value;
    }
}
