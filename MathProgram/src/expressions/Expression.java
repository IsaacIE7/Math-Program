package expressions;

public interface Expression {//Tree structure for expressions; uses nodes.
/*      Looks like:
              Add
            /     \
      Multiply   Constant(2)
      /      \
Constant(3)  Variable

        would hold 3x + 2
*/
    double evaluate(double x);
    Expression sDerivative();//symbolic derivative
    Expression simplify();
    String toString();

    default Expression add(Expression e){//default means it has a body here, otherwise it would be abstract
      return new Add(this, e);
    }
    default Expression multiply(Expression e){
      return new Multiply(this, e);
    }
    default Expression power(int n){
      return new Power(this, n);
    }
    default Expression divide(Expression e){
      return new Divide(this, e);
    }
    
}
