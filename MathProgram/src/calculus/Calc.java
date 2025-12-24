package calculus;


import expressions.*;
import utils.*;


public class Calc {
    private static final double dx = 1e-7, dt = 1e-7;
 
    

    
    public static Function1D derivative(Function1D f){
        return x -> (f.apply(x + dx) - f.apply(x - dx))/(2 * dx);//central difference for accuracy ex. |x| at x = 0
    }   

    //public static double defIntegral(Function1D f, double lbound){  
    //}
    
    public static Function1D integral(Function1D f){
         return (x) -> {
            double trapezoidalSum = 0.0;
            boolean negative = x < 0;
            if (negative){x = -x;}// ∫ from a to b f(x) dx = - ∫ from b to a f(x) dx
            for (double t = 0; t < x; t+=dt) {
                double left = f.apply(t);
                double right = f.apply(t + dt);
                trapezoidalSum += ((left + right)/2) * dt;
            }

             return negative ? -trapezoidalSum : trapezoidalSum; //variable = (condition) ? valueIfTrue : valueIfFalse;
        };
    } 

    public static Expression taylorSeries(Expression func, double c, int terms){
        Expression result = new Constant(func.evaluate(c));
        Expression currentD = func;
        long factorial = 1;

        for (int i = 1; i < terms; i++) {
            factorial *= i;
            currentD = currentD.sDerivative();
            Expression derivativeAtC = new Constant(currentD.evaluate(c));
            result = result.add(derivativeAtC.multiply(new Variable().subtract(new Constant(c)).power(i)).divide(new Constant(factorial)));
        }
        return result;
    }

    public static double newtonsMethod(String func, double guess, double iterations){
        Expression f = Parser.parse(func);
        Expression df = f.sDerivative();
        double x = guess;
        double xn = x;
        for (int i = 0; i < iterations; i++) {
            xn = x - (f.evaluate(x) / df.evaluate(x));
            x = xn;
        }
        return xn;
    }
}
