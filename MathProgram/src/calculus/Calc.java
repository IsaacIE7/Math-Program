package calculus;


import expressions.*;
import java.util.ArrayList;
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

    public static ArrayList<Double> findCriticalPoints(String func, double lower, double upper) {
        String fp = Parser.parse(func).sDerivative().toString();
        return rootFinder(fp, lower, upper, 150);
    }
    
    public static ArrayList<Double> findInflectionPoints(String func, double lower, double upper) {
        String fpp = Parser.parse(func).sDerivative().sDerivative().toString();
        return rootFinder(fpp, lower, upper, 150);
    }
    
    public static ArrayList<ExtremaInfo> classifyPoints(String func, double lower, double upper) {
        Expression f = Parser.parse(func);
        Expression fp = f.sDerivative(); 
        Expression fpp = f.sDerivative().sDerivative(); 
        ArrayList<ExtremaInfo> results = new ArrayList<>();
        ArrayList<Double> critPoints = findCriticalPoints(func, lower, upper);
        ArrayList<Double> infPoints = findInflectionPoints(func, lower, upper);
        
    // Then reuse fpp.evaluate(p) multiple times
        
        // What do you need to do for each critical point?
        for (Double p : critPoints) {
            double secondDeriv = fpp.evaluate(p);
    
        if (Math.abs(secondDeriv) > 0) {
        // Conclusive second derivative test
            if (secondDeriv > 0) {
                results.add(new ExtremaInfo(p, f.evaluate(p), ExtremaInfo.Crit.MIN));
            }
            else {
                results.add(new ExtremaInfo(p, f.evaluate(p), ExtremaInfo.Crit.MAX));
            }
        } else {
        double fpBefore = fp.evaluate(p - .01);
        double fpAfter = fp.evaluate(p + .01);
        
        if (fpBefore < 0 && fpAfter > 0) { 
            results.add(new ExtremaInfo(p, f.evaluate(p), ExtremaInfo.Crit.MIN));
        }
        else if (fpBefore > 0 && fpAfter < 0) {
            results.add(new ExtremaInfo(p, f.evaluate(p), ExtremaInfo.Crit.MAX));
        }

    }
        }

        for (Double p : infPoints) {
        
        }
    
        return results;
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

    public static ArrayList<Double> rootFinder(String func, double lowerBound, double upperBound, int tests){
        Expression f = Parser.parse(func);
        double a = lowerBound;
        double b = upperBound;
        ArrayList<Double> roots = new ArrayList<>();
        double step = (b - a) / tests;
        for (int i = 0; i <= tests; i++) {
            double startPoint = a + i * step;
            try {
                double root = newtonsMethod(func, startPoint, 100);
                
                if (Double.isNaN(root) || Double.isInfinite(root)) continue;

                if (root < lowerBound || root > upperBound) {//check if root is out of bounds
                    continue; // skip this root
                }
                
                double fValue = f.evaluate(root);
                if (Math.abs(fValue) > 1e-6) {  // if f(root) is not close to 0
                    continue;  // not a real root, skip it
                }

                Boolean isDuplicate = false;
                for (int j = 0; j < roots.size(); j++) {
                    if (Math.abs(roots.get(j) - root) < 1e-5) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) roots.add(root);

                

            
            
            } catch (Exception e) {// Ignore exceptions from Newton's method
                
            }
        }

        return roots;
    }
}
