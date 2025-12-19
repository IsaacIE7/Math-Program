package expressions;

public class Tan implements Expression{
    Expression inner;

    public Tan(Expression inner){
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) {
        if (inner.simplify() instanceof Constant c && Math.abs(c.getValue()) == Math.PI/2){
            throw new IllegalArgumentException("Tan is Undefined at  x = " + x);
        }
        return Math.tan(inner.evaluate(x)); 
    }

    @Override
    public Expression sDerivative() {
        return new Constant(1).divide(new Cos(inner).power(2)).multiply(inner.sDerivative());
    }

    @Override
    public Expression simplify() {
      if (inner.simplify() instanceof Constant c && Math.abs(c.getValue()) == 2){
        throw new IllegalArgumentException("Tan is Undefined.");
      }
      if (inner.simplify() instanceof Constant constant){
        return new Constant(Math.tan(constant.getValue()));
      }
    return new Tan(inner.simplify());
    }

    public String toString(){
        return "tan(" + inner.toString() + ")";
    }

}
