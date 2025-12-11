public interface Expression {//Tree structure for expressions; uses nodes
    double evaluate(double x);
    Expression sDerivative();//symbolic derivative
    String toString();
}
