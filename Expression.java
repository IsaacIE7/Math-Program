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
    String toString();
    Expression add(Expression e);
    Expression mulitiply(Expression e);
    Expression power(int n);
}
