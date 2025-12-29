package calculus;


import expressions.*;
import java.util.Map;
import linalg.*;
import utils.*;


public class MultiVarCalc {

    public static Expression[] gradient(String func, String... vars){//varargs allow you to input as many pararmeters of the specified type as you want, creates an array of the type
        Expression[] grad = new Expression[vars.length];
        Expression f = Parser.parse(func);

    for (int i = 0; i <= grad.length; i++) {
        grad[i] = f.sPartialDerivative(vars[i]);
    }
        
        return grad;
    }

    public static Vector gradientAt(Map<String, Double> point, String func, String... vars){
        double[] grad = new double[vars.length];
        Expression f = Parser.parse(func);

    for (int i = 0; i <= grad.length; i++) {
        grad[i] = f.sPartialDerivative(vars[i]).evaluate(point);
    }
        
        return new Vector(grad);
    }

    public static double directionalDerivative(String func, Vector direction, Map<String, Double> point, String... vars){//doesnt have to be normalized, wil lhabdle normalization in this method
        double[] grad = new double[vars.length];
        Expression f = Parser.parse(func);

    for (int i = 0; i <= grad.length; i++) {
        grad[i] = f.sPartialDerivative(vars[i]).evaluate(point);
    }
        
        Vector v = new Vector(grad);
        
        return v.dot(direction.normalize());
    }

    /*  WILL IMPLEMENT LATER
    public static  Expression[][] hessian(){
        return null;
    }

    public static Matrix hessianAt(Map<Variable, Double> point){
        return null;
    }

    
    public static Expression laplacian(Expression func){
        return null;
    }
    */
}
