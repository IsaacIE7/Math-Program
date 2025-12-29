package calculus;


import expressions.*;
import java.util.Map;
import linalg.*;
import utils.*;


public class MultiVarCalc {

    public static Expression[] gradient(String func, String... vars){//varargs allow you to input as many pararmeters of the specified type as you want, creates an array of the type
        Expression[] grad = new Expression[vars.length];
        Expression f = Parser.parse(func);

    for (int i = 0; i < grad.length; i++) {
        grad[i] = f.sPartialDerivative(vars[i]).simplify();
    }
        
        return grad;
    }

    public static Vector gradientAt(Map<String, Double> point, String func, String... vars){
        double[] grad = new double[vars.length];
        Expression f = Parser.parse(func);

    for (int i = 0; i < grad.length; i++) {
        grad[i] = f.sPartialDerivative(vars[i]).evaluate(point);
    }
        
        return new Vector(grad);
    }

    public static double directionalDerivative(String func, Vector direction, Map<String, Double> point, String... vars){//doesnt have to be normalized, wil lhabdle normalization in this method
        double[] grad = new double[vars.length];
        Expression f = Parser.parse(func);

        for (int i = 0; i < grad.length; i++) {
            grad[i] = f.sPartialDerivative(vars[i]).evaluate(point);
        }
        
        Vector v = new Vector(grad);
        
        return v.dot(direction.normalize());
    }


    // ^^ Static and uses the inputs to create a vector field. in the VectorField class the divergence methods use instantiated vectors
    public static Expression div(String[] vars, String... comps){ 
        if (comps.length != vars.length){
            throw new IllegalArgumentException("Components and variables do not match.");
        }
        
        Expression sum = new Constant(0);

        for (int i = 0; i < vars.length; i++){
            sum = sum.add(Parser.parse(comps[i]).sPartialDerivative(vars[i]));
        }
        return sum;
    }

    public static double divAt(String[] vars, Map<String, Double> point, String... comps){
        return div(vars, comps).evaluate(point);
    }
    //^^


    /*  WILL IMPLEMENT LATER

    public static Expression[][] jacobian(){
        return null;
    }

    public static  Expression[][] hessian(){
        return null;
    }

    public static Expression laplacian(Expression func){
        return null;
    }
    */
}
